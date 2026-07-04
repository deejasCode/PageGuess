package com.deeja.pageguess

/**
 * Central repository of all quiz questions, organized by type.
 * Questions use paraphrased descriptions to avoid copyright issues.
 */

object QuestionBank {

    val allQuestions : List<Question> = listOf(

        // --- PLOT TWIST (30 pts) ---
        Question(
            questionText = "A teenage girl is chosen by lottery to fight to death against other children in a televised even controlled by a corrupt government.",
            options = listOf("Divergent", "The Hunger Games", "The Maze Runner", "Ender's Game"),
            correctAnswerIndex = 1,
            type = QuestionType.PLOT
        ),
        Question(
            questionText = "A young orphan discovers he is a wizard on his eleventh birthday and is accepted into a magical school for witches and wizards.",
            options = listOf("The Name of the Wind", "Eragon", "Harry Potter and the Philosopher's Stone", "The Alchemist"),
            correctAnswerIndex = 2,
            type = QuestionType.PLOT
        ),
        Question(
            questionText = "A hobbit unexpectedly joins a group of dwarves on a quest to reclaim their mountain home from a dragon.",
            options = listOf("The Fellowship of the Ring", "The Hobbit", "Eragon", "The Name of the Wind"),
            correctAnswerIndex = 1,
            type = QuestionType.PLOT
        ),
        Question(
            questionText = "A woman goes missing and her husband becomes the prime suspect. Told from alternating perspectives revealing shocking secrets.",
            options = listOf("The Girl on the Train", "Gone Girl", "Big Little Lies", "The Woman in the Window"),
            correctAnswerIndex = 1,
            type = QuestionType.PLOT
        ),
        Question(
            questionText = "An astronaut is stranded alone on Mars and must use his botany skills to survive until a rescue mission can reach him.",
            options = listOf("Interstellar", "The Martian", "Ender's Game", "Fahrenheit 451"),
            correctAnswerIndex = 1,
            type = QuestionType.PLOT
        ),
        Question(
            questionText = "Two thieves and their crew of misfits attempt an impossible heist in a fantasy city built on canals.",
            options = listOf("The Final Empire", "Six of Crows", "The Lies of Locke Lamora", "Assassin's Apprentice"),
            correctAnswerIndex = 1,
            type = QuestionType.PLOT
        ),
        Question(
            questionText = "In a dystopian future, books are outlawed and firemen burn any that are found.",
            options = listOf("1984", "Brave New World", "Fahrenheit 451", "We"),
            correctAnswerIndex = 2,
            type = QuestionType.PLOT
        ),
        Question(
            questionText = "A man wakes up with no memory inside a massive maze filled with monsters, surrounded by other boys in the same situation.",
            options = listOf("The Hunger Games", "Divergent", "The Maze Runner", "The Giver"),
            correctAnswerIndex = 2,
            type = QuestionType.PLOT
        ),

        // ---- WHO'S THAT? (20 pts) ----
        Question(
            questionText = "A clever, rule-breaking girl who ends up befriending a half-giant and a clumsy but loyal red-haired boy at a magical school.",
            options = listOf("Percy Jackson", "Harry Potter and the Philosopher's Stone", "The Chronicles of Narnia", "Eragon"),
            correctAnswerIndex = 1,
            type = QuestionType.CHARACTER
        ),
        Question(
            questionText = "A brooding, scarred young man who leads a crew of criminals and is secretly terrified of his own power.",
            options = listOf("The Final Empire", "An Ember in the Ashes", "Six of Crows", "The Cruel Prince"),
            correctAnswerIndex = 2,
            type = QuestionType.CHARACTER
        ),
        Question(
            questionText = "A young woman raised in a faction that values selflessness who discovers she doesn't fit neatly into any one group.",
            options = listOf("The Hunger Games", "Divergent", "The Maze Runner", "Legend"),
            correctAnswerIndex = 1,
            type = QuestionType.CHARACTER
        ),
        Question(
            questionText = "A disgraced soldier with a dark past who reluctantly becomes the protector of a powerful child with mysterious abilities.",
            options = listOf("Dune", "The Name of the Wind", "The Stormlight Archive", "The Way of Kings"),
            correctAnswerIndex = 3,
            type = QuestionType.CHARACTER
        ),
        Question(
            questionText = "A sharp-tongued woman who fakes her own death and narrates her story posthumously, revealing her manipulative nature.",
            options = listOf("The Girl on the Train", "Gone Girl", "Liane Moriarty", "Sharp Objects"),
            correctAnswerIndex = 1,
            type = QuestionType.CHARACTER
        ),
        Question(
            questionText = "A boy who grows up to become a legendary wizard and musician, narrating his own life story from a mysterious inn.",
            options = listOf("The Final Empire", "The Name of the Wind", "Eragon", "The Lies of Locke Lamora"),
            correctAnswerIndex = 1,
            type = QuestionType.CHARACTER
        ),
        Question(
            questionText = "A quiet botanist and astronaut with a sarcastic inner monologue who turns science into a survival tool.",
            options = listOf("Ender's Game", "The Martian", "Rendezvous with Rama", "Old Man's War"),
            correctAnswerIndex = 1,
            type = QuestionType.CHARACTER
        ),
        Question(
            questionText = "A boy genius trained from childhood in a battle school to become humanity's greatest military commander.",
            options = listOf("Ender's Game", "The Giver", "Fahrenheit 451", "The Maze Runner"),
            correctAnswerIndex = 0,
            type = QuestionType.CHARACTER
        ),

        // ---- VIBE CHECK (10 pts) ----
        Question(
            questionText = "A dimly lit library, forbidden knowledge, whispered spells, ancient rivalries between students, and a hidden chamber beneath the school.",
            options = listOf("Romance", "Fantasy", "Historical Fiction", "Thriller"),
            correctAnswerIndex = 1,
            type = QuestionType.GENRE
        ),
        Question(
            questionText = "Neon-lit city streets at 2am, a missing person, an unreliable narrator, and a plot twist that reframes everything you just read.",
            options = listOf("Fantasy", "Science Fiction", "Psychological Thriller", "Horror"),
            correctAnswerIndex = 2,
            type = QuestionType.GENRE
        ),
        Question(
            questionText = "Red sand dunes, alien ecology, political scheming between noble houses, and a prophecy that may or may not be manufactured.",
            options = listOf("Fantasy", "Science Fiction", "Dystopian", "Space Opera"),
            correctAnswerIndex = 1,
            type = QuestionType.GENRE
        ),
        Question(
            questionText = "Cobblestone streets, gas lamps, a brilliant but cold detective, and a string of impossible murders in Victorian London.",
            options = listOf("Gothic Horror", "Historical Mystery", "Thriller", "Dark Fantasy"),
            correctAnswerIndex = 1,
            type = QuestionType.GENRE
        ),
        Question(
            questionText = "Ash-covered skies, a tyrannical immortal emperor, a caste system enforced by magic, and a revolution brewing underground.",
            options = listOf("Dark Fantasy", "Science Fiction", "Dystopian", "Epic Fantasy"),
            correctAnswerIndex = 3,
            type = QuestionType.GENRE
        ),
        Question(
            questionText = "A cosy small town, misunderstandings between neighbours, letters never sent, and two people who clearly belong together.",
            options = listOf("Contemporary Romance", "Historical Fiction", "Chick Lit", "Drama"),
            correctAnswerIndex = 0,
            type = QuestionType.GENRE
        ),
        Question(
            questionText = "Sleek spaceships, corporate espionage, artificial intelligence questioning its own existence, and a war fought across solar systems.",
            options = listOf("Cyberpunk", "Space Opera", "Hard Science Fiction", "Military Sci-Fi"),
            correctAnswerIndex = 1,
            type = QuestionType.GENRE
        ),
        Question(
            questionText = "A boarding school, secret societies, a murder covered up by the faculty, and a protagonist who can't trust anyone around them.",
            options = listOf("Young Adult Thriller", "Dark Academia", "Gothic Horror", "Mystery"),
            correctAnswerIndex = 1,
            type = QuestionType.GENRE
        )
    )

    /** Returns a shuffled set of [count] questions, with a balanced mix of types */
    fun getQuestions(count: Int = 10): List<Question> {
        val plotQ = allQuestions.filter { it.type == QuestionType.PLOT }.shuffled()
        val charQ = allQuestions.filter { it.type == QuestionType.CHARACTER }.shuffled()
        val genreQ = allQuestions.filter { it.type == QuestionType.GENRE }.shuffled()

        // roughly equal split: ~3 plot, ~3 character, ~4 genre (easiest padded)
        return (plotQ.take(3) + charQ.take(3) + genreQ.take(4)).shuffled()
    }
}

