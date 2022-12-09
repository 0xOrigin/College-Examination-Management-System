package CEMS.Views.ViewController.Tabs;

import CEMS.Controllers.ExamController;
import CEMS.Controllers.QuestionController;
import CEMS.Views.Utilities;
import CEMS.Views.ViewController.Validator.ExamValidator;
import CEMS.Views.ViewController.Validator.QuestionValidator;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AddQuestion extends Utilities {

    QuestionValidator questionValidator = new QuestionValidator();
    ExamValidator examValidator = new ExamValidator();

    public void add(ActionEvent event, Alert alert, QuestionController questionController, ExamController examController,
                    ComboBox<String> selectSubjectField, TextField examIdField, TextField contentField, TextField choice1Field,
                    TextField choice2Field, TextField choice3Field, TextField choice4Field, TextField choice5Field,
                    TextField correctAnswerField){

        super.setAlertOwner(event, alert);
        boolean generalState;

        generalState = questionValidator.validateCorrectAnswer(choice1Field, choice2Field, choice3Field, choice4Field,
                choice5Field, correctAnswerField, alert);
        generalState &= questionValidator.validateChoice(choice5Field, alert, 5);
//        generalState &= questionValidator.validateChoice(choice4Field, alert, 4);
//        generalState &= questionValidator.validateChoice(choice3Field, alert, 3);
//        generalState &= questionValidator.validateChoice(choice2Field, alert, 2);
        generalState &= questionValidator.validateChoice(choice1Field, alert, 1);
        generalState &= questionValidator.validateQuestionContent(contentField, alert);
        generalState &= examValidator.validateExamID(examIdField, selectSubjectField, alert, examController);

        if(generalState){
            questionController.insert(examIdField.getText().trim(), contentField.getText().trim(), choice1Field.getText().trim(),
                    choice2Field.getText().trim(), choice3Field.getText().trim(), choice4Field.getText().trim(),
                    choice5Field.getText().trim(), correctAnswerField.getText().trim());
            handleAlert(alert, "Successful Operation",
                    "The question has been successfully added.",
                    Alert.AlertType.INFORMATION);
        }
    }

    public void resetTabView(ComboBox<String> selectSubjectField, TextField examIdField, TextField contentField, TextField choice1Field,
                             TextField choice2Field, TextField choice3Field, TextField choice4Field, TextField choice5Field,
                             TextField correctAnswerField){

        selectSubjectField.getSelectionModel().selectFirst();
        examIdField.clear();
        contentField.clear();
        choice1Field.clear();
        choice2Field.clear();
        choice3Field.clear();
        choice4Field.clear();
        choice5Field.clear();
        correctAnswerField.clear();
    }

}
