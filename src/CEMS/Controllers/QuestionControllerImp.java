package CEMS.Controllers;

import CEMS.Models.DbContext;

import java.util.List;
import java.util.Map;

public class QuestionControllerImp implements QuestionController {

    protected final DbContext dbContext;

    public QuestionControllerImp(DbContext dbContext) {
        this.dbContext = dbContext;
    }

    @Override
    public void insert(String examID, String content, String choice1, String choice2, String choice3,
                       String choice4, String choice5, String correctAnswer){
        this.dbContext.getQuestionModel().insert(examID, content, choice1, choice2, choice3, choice4,
                choice5, correctAnswer);
    }

    @Override
    public void update(List<Enum> fields, List<Object> values, String questionID){
        this.dbContext.getQuestionModel().update(fields, values, questionID);
    }

    @Override
    public void delete(String questionID){
        this.dbContext.getQuestionModel().delete(questionID);
    }

    @Override
    public List<Map<Enum, Object>> getAllQuestionsFor(String examID){
        return this.dbContext.getQuestionModel().getAllQuestionsFor(examID);
    }

}
