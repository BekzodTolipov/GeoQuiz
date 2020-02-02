package edu.umsl.bekzod_tolipov;

public class QuizQuestion {

    private Integer id;
    private Boolean isTrue;

    public QuizQuestion(Integer id, Boolean isTrue){
        this.id = id;
        this.isTrue = isTrue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIsTrue() {
        return isTrue;
    }

    public void setIsTrue(Boolean aTrue) {
        isTrue = aTrue;
    }
}
