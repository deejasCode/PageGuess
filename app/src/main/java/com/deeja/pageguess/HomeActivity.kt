package com.deeja.pageguess

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

/**
 * Entry point of PageGuess.
 * Displays the high score and lets the user select a difficulty before starting the quiz.
 */
class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvHighScore = findViewById<TextView>(R.id.tvHighScore)
        val btnEasy = findViewById<MaterialButton>(R.id.btnEasy)
        val btnMedium = findViewById<MaterialButton>(R.id.btnMedium)
        val btnHard = findViewById<MaterialButton>(R.id.btnHard)

        // Load saved high score from SharedPreferences
        val prefs = getSharedPreferences("pageguess_prefs", MODE_PRIVATE)
        val highScore = prefs.getInt("high_score", 0)
        tvHighScore.text = "$highScore"

        btnEasy.setOnClickListener { startQuiz(Difficulty.EASY) }
        btnMedium.setOnClickListener { startQuiz(Difficulty.MEDIUM) }
        btnHard.setOnClickListener { startQuiz(Difficulty.HARD) }
    }

    private fun startQuiz(difficulty: Difficulty) {
        val intent = Intent(this, QuizActivity::class.java)
        intent.putExtra("difficulty", difficulty.name)
        startActivity(intent)
    }
}