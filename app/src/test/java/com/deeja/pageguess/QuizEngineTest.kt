package com.deeja.pageguess

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * Unit tests for [QuizEngine], verifying scoring, streak, and multiplier logic.
 */
class QuizEngineTest {

    // A fixed set of test questions so results are predictable
    private lateinit var engine: QuizEngine

    private val testQuestions = listOf(
        Question(
            questionText = "Test plot question",
            options = listOf("A", "B", "C", "D"),
            correctAnswerIndex = 0,
            type = QuestionType.PLOT
        ),
        Question(
            questionText = "Test character question",
            options = listOf("A", "B", "C", "D"),
            correctAnswerIndex = 1,
            type = QuestionType.CHARACTER
        ),
        Question(
            questionText = "Test genre question",
            options = listOf("A", "B", "C", "D"),
            correctAnswerIndex = 2,
            type = QuestionType.GENRE
        ),
        Question(
            questionText = "Test plot question 2",
            options = listOf("A", "B", "C", "D"),
            correctAnswerIndex = 3,
            type = QuestionType.PLOT
        ),
        Question(
            questionText = "Test genre question 2",
            options = listOf("A", "B", "C", "D"),
            correctAnswerIndex = 0,
            type = QuestionType.GENRE
        )
    )

    @Before
    fun setup() {
        engine = QuizEngine(testQuestions)
    }

    // --- Correct answer tests ---

    @Test
    fun `correct answer returns isCorrect true`() {
        val result = engine.submitAnswer(0) // Q1 correct index is 0
        assertTrue(result.isCorrect)
    }

    @Test
    fun `correct plot answer earns base 30 points`() {
        val result = engine.submitAnswer(0) // Q1 is PLOT, 30 base pts, 1x multiplier
        assertEquals(30, result.pointsEarned)
    }

    @Test
    fun `correct character answer earns base 20 points`() {
        engine.submitAnswer(0) // answer Q1 correctly first
        val result = engine.submitAnswer(1) // Q2 is CHARACTER, 20 base pts, 2x multiplier
        assertEquals(40, result.pointsEarned) // 20 * 2x streak
    }

    @Test
    fun `score accumulates correctly after two correct answers`() {
        engine.submitAnswer(0) // 30pts
        engine.submitAnswer(1) // 40pts (20 * 2x)
        assertEquals(70, engine.score)
    }

    // --- Wrong answer tests ---

    @Test
    fun `wrong answer returns isCorrect false`() {
        val result = engine.submitAnswer(3) // Q1 correct is 0, so 3 is wrong
        assertFalse(result.isCorrect)
    }

    @Test
    fun `wrong answer earns zero points`() {
        val result = engine.submitAnswer(3)
        assertEquals(0, result.pointsEarned)
    }

    @Test
    fun `wrong answer resets streak to zero`() {
        engine.submitAnswer(0) // correct — streak becomes 1
        engine.submitAnswer(3) // wrong — streak resets to 0
        assertEquals(0, engine.streak)
    }

    // --- Streak and multiplier tests ---

    @Test
    fun `streak increases with consecutive correct answers`() {
        engine.submitAnswer(0) // correct
        engine.submitAnswer(1) // correct
        assertEquals(2, engine.streak)
    }

    @Test
    fun `multiplier is capped at 5x`() {
        // Answer all 5 questions correctly — multiplier should cap at 5
        engine.submitAnswer(0) // streak 1 → multiplier 2x
        engine.submitAnswer(1) // streak 2 → multiplier 3x
        engine.submitAnswer(2) // streak 3 → multiplier 4x
        engine.submitAnswer(3) // streak 4 → multiplier 5x
        engine.submitAnswer(0) // streak 5 → multiplier still 5x (capped)
        assertEquals(5, engine.streakMultiplier)
    }

    @Test
    fun `highest streak is tracked correctly`() {
        engine.submitAnswer(0) // correct
        engine.submitAnswer(1) // correct — highest streak = 2
        engine.submitAnswer(3) // wrong — streak resets
        assertEquals(2, engine.highestStreak)
    }

    // --- Navigation tests ---

    @Test
    fun `quiz is not finished at start`() {
        assertFalse(engine.isFinished)
    }

    @Test
    fun `quiz is finished after all questions answered`() {
        testQuestions.forEachIndexed { index, question ->
            engine.submitAnswer(question.correctAnswerIndex)
        }
        assertTrue(engine.isFinished)
    }

    @Test
    fun `reset restores engine to initial state`() {
        engine.submitAnswer(0)
        engine.submitAnswer(1)
        engine.reset()

        assertEquals(0, engine.score)
        assertEquals(0, engine.streak)
        assertEquals(0, engine.currentIndex)
        assertFalse(engine.isFinished)
    }

    // --- Question bank tests ---

    @Test
    fun `getQuestions returns correct number of questions`() {
        val questions = QuestionBank.getQuestions(10)
        assertEquals(10, questions.size)
    }

    @Test
    fun `question bank contains all three question types`() {
        val questions = QuestionBank.allQuestions
        assertTrue(questions.any { it.type == QuestionType.PLOT })
        assertTrue(questions.any { it.type == QuestionType.CHARACTER })
        assertTrue(questions.any { it.type == QuestionType.GENRE })
    }
}