package com.deeja.pageguess

/**
 * Difficulty levels affecting timer behaviour in PageGuess.
 * @property timeSeconds Time allowed per question in seconds. -1 means no timer.
 */
enum class Difficulty(val timeSeconds: Int) {
    EASY(timeSeconds = -1),
    MEDIUM(timeSeconds = 30),
    HARD(timeSeconds = 15)
}