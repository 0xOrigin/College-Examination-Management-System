package CEMS.Views.ViewControllers.Tabs;

import CEMS.Controllers.ExamController;
import CEMS.Models.Enum.Column;
import CEMS.Views.Utilities;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Map;

public class Result extends Utilities {

    private final String studentID;
    private final Exam exam;
    private final ExamController controller;
    private final Label subjectNameLabel;
    private final Label examNameLabel;
    private final Label gradeLabel;

    public Result(String studentID, Exam exam, ExamController controller, Label subjectNameLabel, Label examNameLabel, Label gradeLabel){
        this.studentID = studentID;
        this.exam = exam;
        this.controller = controller;
        this.subjectNameLabel = subjectNameLabel;
        this.examNameLabel = examNameLabel;
        this.gradeLabel = gradeLabel;
    }

    public void correctExam(){
        String grade = this.controller.correctExam(exam.getQuestions(), exam.getStudentAnswers());
        this.gradeLabel.setText(grade);
        this.controller.storeGradeForExam(this.studentID, this.exam.getExamID(), grade);
    }

    public void setResultData(TextField subjectNameField, ComboBox<String> selectExamField, TextArea correctedExamField){
        this.subjectNameLabel.setText(subjectNameField.getText());
        this.examNameLabel.setText(selectExamField.getSelectionModel().getSelectedItem());
        this.showCorrectedExam(correctedExamField);
    }

    private void showCorrectedExam(TextArea correctedExamField){
        int iterator = 0;
        Map<String, String> studentAnswers = this.exam.getStudentAnswers();

        correctedExamField.appendText("============================ Beginning of exam =============================\n\n");

        for(Map<Enum, Object> question : this.exam.getQuestions()){
            correctedExamField.appendText("# Question number: [" + (iterator++ + 1) + "]\n");
            correctedExamField.appendText("\t- " + question.get(Column.Content).toString() + "\n");
            String questionID = question.get(Column.ID).toString();
            String studentAnswer = studentAnswers.get(questionID);
            correctedExamField.appendText("[Your answer]: " + (studentAnswer == null ? "" : studentAnswer) + "\n");
            correctedExamField.appendText("[Correct answer]: " + question.get(Column.CorrectAnswer).toString() + "\n");
            correctedExamField.appendText("-------------------------------------------------------------------------------------------------------------------------\n\n");
        }

        correctedExamField.appendText("================================ End of exam ==============================\n");
    }

}
