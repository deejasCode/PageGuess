package com.deeja.pageguess

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

/**
 * Displays the final score and highest streak after a quiz round.
 * Checks and saves a new high score to SharedPreferences if beaten.
 */
class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val finalScore = intent.getIntExtra("final_score", 0)
        val highestStreak = intent.getIntExtra("highest_streak", 0)
        val difficulty = intent.getStringExtra("difficulty") ?: Difficulty.EASY.name

        val tvFinalScore = findViewById<TextView>(R.id.tvFinalScore)
        val tvHighestStreak = findViewById<TextView>(R.id.tvHighestStreak)
        val tvNewHighScore = findViewById<TextView>(R.id.tvNewHighScore)
        val btnPlayAgain = findViewById<MaterialButton>(R.id.btnPlayAgain)
        val btnHome = findViewById<MaterialButton>(R.id.btnHome)

        tvFinalScore.text = "Score: $finalScore"
        tvHighestStreak.text = "Best Streak: $highestStreak 🔥"

        // Check and update high score
        val prefs = getSharedPreferences("pageguess_prefs", MODE_PRIVATE)
        val currentHighScore = prefs.getInt("high_score", 0)
        if (finalScore > currentHighScore) {
            prefs.edit().putInt("high_score", finalScore).apply()
            tvNewHighScore.visibility = View.VISIBLE
        }

        btnPlayAgain.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            intent.putExtra("difficulty", difficulty)
            startActivity(intent)
            finish()
        }

        btnHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }
    }
}