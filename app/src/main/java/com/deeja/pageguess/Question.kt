package com.deeja.pageguess

/**
 * Represents a single quiz question in PageGuess
 *
 * @property questionText The text shown to the user(plot summary, character clue, or scene vibe)
 * @property options List of exactly 4 answer choices
 * @property correctAnswerIndex Index (0-3) of the correct answer in [options]
 * @property type The category of question, determines base point value
 */

class Question (
    val questionText: String,
    val options: List<String>,
    val correctAnswerIndex : Int,
    val type : QuestionType
)

/**
 * The three question formats in PageGuess.
 * Each type carries a different base point value reflecting its difficulty.
 */
enum class QuestionType(val displayName: String, val basePoints: Int) {
    PLOT("Plot Twist", 30),        //Hardest - guess the book from a plot summary
    CHARACTER("Who's That?", 20),  //Medium - guess the book from a character description
    GENRE("Vibe Check", 10)        //Easiest - guess the genre from a scene description
}