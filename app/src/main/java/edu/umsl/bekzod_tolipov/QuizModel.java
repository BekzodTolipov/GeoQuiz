package edu.umsl.bekzod_tolipov;

import java.util.ArrayList;

public class QuizModel {

    private ArrayList<QuizQuestion> mQuestions;
    private Integer currentIndex = 0;

    public QuizModel(Integer index){
        mQuestions = new ArrayList<>();
        mQuestions.add(new QuizQuestion(R.string.placeholder_text, ""));
        mQuestions.add(new QuizQuestion(R.string.question_two, ""));
        this.currentIndex = index;
    }


    public QuizQuestion getQuestionAtIndex(){
        QuizQuestion question = mQuestions.get(currentIndex);
        return question;
    }

    public Integer getCurrentIndex(){
        return currentIndex;
    }

    public void increment(){
        currentIndex++;
    }

}
