package edu.umsl.bekzod_tolipov

class QuizModel(index: Int) {

    var currentIndex: Int = index
        private set

    var mQuestions: Array<QuizQuestion> = arrayOf(
            QuizQuestion(id = R.string.question_brazil, isTrue = true),
            QuizQuestion(R.string.question_einstein, true),
            QuizQuestion(R.string.question_georgia, false),
            QuizQuestion(R.string.question_libya, false)
    )

    val currentQuestion: QuizQuestion
        get() = mQuestions[currentIndex]

    fun advanceToNextQuestion():Unit{
        currentIndex = (currentIndex + 1) % mQuestions.size
        currentQuestion
    }

    fun isAnswerCorrect(answerValue: Boolean):Boolean{
        val currentQuestion = this.currentQuestion
        return currentQuestion.isTrue == answerValue
    }


}