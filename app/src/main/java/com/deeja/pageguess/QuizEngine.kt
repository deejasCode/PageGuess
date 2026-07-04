package com.deeja.pageguess

/**
 * Manages all quiz state and scoring logic for a PageGuess session.
 *
 * Scoring formula: basePoints * streakMultiplier
 * A wrong answer resets the streak to 1x.
 */
class QuizEngine(private val questions: List<Question>) {
    var currentIndex = 0
        private set

    var score = 0
        private set

    var streak = 0
        private set

    var highestStreak = 0
        private set

    /** Multiplier caps at 5x to keep scoring balanced */
    val streakMultiplier : Int
        get() = minOf(streak + 1, 5)

    val currentQuestion : Question
        get() = questions[currentIndex]

    val totalQuestions : Int
        get() = questions.size

    val isFinished : Boolean
        get() = currentIndex >= questions.size

    /**
     * Submits an answer for the current question.
     * @param selectedIndex The index (0-3) of the user's chosen answer
     * @return [AnswerResult] containing whether it was correct and points earned
     */
    fun submitAnswer(selectedIndex: Int) : AnswerResult {
        val question = currentQuestion
        val isCorrect = selectedIndex == question.correctAnswerIndex

        val pointsEarned = if (isCorrect) {
            streak++
            if (streak > highestStreak) highestStreak = streak
            val points = question.type.basePoints * streakMultiplier
            score += points
            points
        } else {
            streak = 0
            0
        }

        currentIndex++

        return  AnswerResult(
            isCorrect = isCorrect,
            pointsEarned = pointsEarned,
            newScore = score,
            newStreak = streak,
            streakMultiplier = streakMultiplier,
            correctAnswerIndex = question.correctAnswerIndex
        )
    }

    /** Resets the engine for a fresh round with the same questions */
    fun reset() {
        currentIndex = 0
        score = 0
        streak = 0
        highestStreak = 0
    }

}

/**
 * Returned after each answer submission — carries everything the UI needs to update.
 *
 * @property isCorrect Whether the user's answer was right
 * @property pointsEarned Points gained this round (0 if wrong)
 * @property newScore Running total score
 * @property newStreak Current consecutive correct answer count
 * @property streakMultiplier Active multiplier at time of answer
 * @property correctAnswerIndex The right answer index, used to highlight correct option
 */
data class AnswerResult(
    val isCorrect: Boolean,
    val pointsEarned: Int,
    val newScore: Int,
    val newStreak: Int,
    val streakMultiplier: Int,
    val correctAnswerIndex: Int
)