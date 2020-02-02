package edu.umsl.bekzod_tolipov;

import java.util.ArrayList;

public class QuizModel {

    private ArrayList<QuizQuestion> mQuestions;
    private Integer currentIndex = 0;

    public QuizModel(Integer index){
        mQuestions = new ArrayList<>();
        mQuestions.add(new QuizQuestion(R.string.question_brazil, true));
        mQuestions.add(new QuizQuestion(R.string.question_einstein, true));
        mQuestions.add(new QuizQuestion(R.string.question_georgia, false));
        mQuestions.add(new QuizQuestion(R.string.question_libya, false));
        mQuestions.add(new QuizQuestion(R.string.question_koalas, true));

        this.currentIndex = index;
    }

    QuizQuestion getCurrentQuestion(){
        return  mQuestions.get(currentIndex);
    }

    public void advanceToNextQuestion(){
        currentIndex = (currentIndex + 1) % mQuestions.size();
        getCurrentQuestion();
    }

    public Integer getCurrentIndex(){
        return currentIndex;
    }

    boolean isAnswerCorrect( boolean answerValue){
        boolean isTrue = mQuestions.get(currentIndex).getIsTrue();
        return isTrue == answerValue;
    }

}
