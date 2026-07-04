package com.deeja.pageguess

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * ViewModel for the quiz screen.
 * Survives screen rotations and holds all quiz state above the Activity lifecycle.
 */
class QuizViewModel : ViewModel() {

    private val engine = QuizEngine(QuestionBank.getQuestions(10))

    // --- Exposed state the UI observes ---

    private val _currentQuestion = MutableLiveData<Question>()
    val currentQuestion: LiveData<Question> = _currentQuestion

    private val _score = MutableLiveData(0)
    val score: LiveData<Int> = _score

    private val _streak = MutableLiveData(0)
    val streak: LiveData<Int> = _streak

    private val _multiplier = MutableLiveData(1)
    val multiplier: LiveData<Int> = _multiplier

    private val _progress = MutableLiveData<Pair<Int, Int>>()
    val progress: LiveData<Pair<Int, Int>> = _progress

    private val _answerResult = MutableLiveData<AnswerResult?>()
    val answerResult: LiveData<AnswerResult?> = _answerResult

    private val _quizFinished = MutableLiveData(false)
    val quizFinished: LiveData<Boolean> = _quizFinished

    init {
        loadCurrentQuestion()
    }

    /** Loads the current question into LiveData for the UI to display */
    private fun loadCurrentQuestion() {
        if (!engine.isFinished) {
            _currentQuestion.value = engine.currentQuestion
            _progress.value = Pair(engine.currentIndex + 1, engine.totalQuestions)
        }
    }

    /**
     * Called when the user taps an answer button.
     * @param selectedIndex The index (0-3) of the tapped option
     */
    fun submitAnswer(selectedIndex: Int) {
        if (_answerResult.value != null) return // ignore taps during feedback phase

        val result = engine.submitAnswer(selectedIndex)
        _answerResult.value = result
        _score.value = result.newScore
        _streak.value = result.newStreak
        _multiplier.value = result.streakMultiplier
    }

    /** Called after feedback animation completes — moves to next question */
    fun moveToNext() {
        _answerResult.value = null
        if (engine.isFinished) {
            _quizFinished.value = true
        } else {
            loadCurrentQuestion()
        }
    }

    /** Returns final score and highest streak for the result screen */
    fun getFinalStats(): Pair<Int, Int> {
        return Pair(engine.score, engine.highestStreak)
    }

    /** Resets the quiz for a new round */
    fun restartQuiz() {
        engine.reset()
        _score.value = 0
        _streak.value = 0
        _multiplier.value = 1
        _quizFinished.value = false
        _answerResult.value = null
        loadCurrentQuestion()
    }
}