package com.deeja.pageguess

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton

/**
 * Displays quiz questions one at a time and handles user answer input.
 * Observes [QuizViewModel] for state changes and updates UI accordingly.
 * Supports optional countdown timer based on selected [Difficulty].
 */
class QuizActivity : AppCompatActivity() {

    private val viewModel: QuizViewModel by viewModels()

    private lateinit var tvScore: TextView
    private lateinit var tvStreak: TextView
    private lateinit var tvMultiplier: TextView
    private lateinit var tvProgress: TextView
    private lateinit var tvQuestionType: TextView
    private lateinit var tvQuestion: TextView
    private lateinit var timerBar: ProgressBar
    private lateinit var optionButtons: List<MaterialButton>

    private lateinit var difficulty: Difficulty
    private var countDownTimer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        difficulty = Difficulty.valueOf(
            intent.getStringExtra("difficulty") ?: Difficulty.EASY.name
        )

        bindViews()
        observeViewModel()
    }

    private fun bindViews() {
        tvScore = findViewById(R.id.tvScore)
        tvStreak = findViewById(R.id.tvStreak)
        tvMultiplier = findViewById(R.id.tvMultiplier)
        tvProgress = findViewById(R.id.tvProgress)
        tvQuestionType = findViewById(R.id.tvQuestionType)
        tvQuestion = findViewById(R.id.tvQuestion)
        timerBar = findViewById(R.id.timerBar)

        optionButtons = listOf(
            findViewById(R.id.btnOption0),
            findViewById(R.id.btnOption1),
            findViewById(R.id.btnOption2),
            findViewById(R.id.btnOption3)
        )

        optionButtons.forEachIndexed { index, button ->
            button.setOnClickListener { viewModel.submitAnswer(index) }
        }
    }

    private fun observeViewModel() {
        viewModel.currentQuestion.observe(this) { question ->
            displayQuestion(question)
        }

        viewModel.score.observe(this) { score ->
            tvScore.text = "Score: $score"
        }

        viewModel.streak.observe(this) { streak ->
            tvStreak.text = "🔥 $streak"
        }

        viewModel.multiplier.observe(this) { multiplier ->
            tvMultiplier.text = "${multiplier}x"
        }

        viewModel.progress.observe(this) { (current, total) ->
            tvProgress.text = "Question $current of $total"
        }

        viewModel.answerResult.observe(this) { result ->
            if (result != null) {
                showAnswerFeedback(result)
            } else {
                resetButtonColors()
            }
        }

        viewModel.quizFinished.observe(this) { finished ->
            if (finished) navigateToResult()
        }
    }

    private fun displayQuestion(question: Question) {
        tvQuestion.text = question.questionText
        tvQuestionType.text = question.type.displayName

        question.options.forEachIndexed { index, option ->
            optionButtons[index].text = option
            optionButtons[index].isEnabled = true
        }

        // Start timer if difficulty has one
        if (difficulty.timeSeconds > 0) {
            timerBar.visibility = View.VISIBLE
            startTimer(difficulty.timeSeconds.toLong())
        }
    }

    private fun startTimer(seconds: Long) {
        countDownTimer?.cancel()
        timerBar.max = 100
        timerBar.progress = 100

        countDownTimer = object : CountDownTimer(seconds * 1000, 100) {
            override fun onTick(millisUntilFinished: Long) {
                val progress = ((millisUntilFinished.toFloat() / (seconds * 1000)) * 100).toInt()
                timerBar.progress = progress
            }

            override fun onFinish() {
                // Time's up — submit a wrong answer automatically
                viewModel.submitAnswer(-1)
            }
        }.start()
    }

    /**
     * Highlights correct answer green and wrong answer red,
     * then moves to next question after a short delay.
     */
    private fun showAnswerFeedback(result: AnswerResult) {
        countDownTimer?.cancel()

        // Disable all buttons during feedback
        optionButtons.forEach { it.isEnabled = false }

        // Colour the correct answer green
        optionButtons[result.correctAnswerIndex].setBackgroundColor(
            ContextCompat.getColor(this, R.color.correct)
        )

        // If wrong, also colour the tapped button red
        // We find which button was "wrong" by checking which isn't correct but was tapped
        // Since we don't store selectedIndex in AnswerResult, colour all non-correct ones
        // lightly — a simple UX choice
        if (!result.isCorrect) {
            optionButtons.forEachIndexed { index, button ->
                if (index != result.correctAnswerIndex) {
                    button.setBackgroundColor(
                        ContextCompat.getColor(this, R.color.incorrect)
                    )
                }
            }
        }

        // Move to next question after 1.5 seconds
        tvQuestion.postDelayed({
            viewModel.moveToNext()
        }, 1500)
    }

    private fun resetButtonColors() {
        optionButtons.forEach { button ->
            button.setBackgroundColor(
                ContextCompat.getColor(this, android.R.color.transparent)
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()
    }

    private fun navigateToResult() {
        val (finalScore, highestStreak) = viewModel.getFinalStats()
        val intent = Intent(this, ResultActivity::class.java).apply {
            putExtra("final_score", finalScore)
            putExtra("highest_streak", highestStreak)
            putExtra("difficulty", difficulty.name)
        }
        startActivity(intent)
        finish()
    }
}