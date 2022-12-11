package CEMS.Controllers;

import CEMS.Models.Enum.Column;

import java.util.List;
import java.util.Map;

public class ExamCorrector {

    private List<Map<Enum, Object>> questions;
    private Map<String, String> studentAnswers;

    ExamCorrector(List<Map<Enum, Object>> questions, Map<String, String> studentAnswers){
        this.questions = questions;
        this.studentAnswers = studentAnswers;
    }

    String correct(){
        int grade = 0;
        int numOfQuestions = questions.size();

        for(Map<Enum, Object> question : questions){
            String questionID = question.get(Column.ID).toString();
            String correctAnswer = question.get(Column.CorrectAnswer).toString();
            String studentAnswer = studentAnswers.get(questionID);
            if(studentAnswer != null)
                grade += (studentAnswer.equals(correctAnswer) ? 1 : 0);
        }

        return grade + "/" + numOfQuestions;
    }

}
