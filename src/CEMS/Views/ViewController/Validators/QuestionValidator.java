package CEMS.Views.ViewController.Validators;

import CEMS.Controllers.QuestionController;
import CEMS.Views.Utilities;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.util.Arrays;
import java.util.List;

public class QuestionValidator extends Utilities {

    public boolean validateQuestionID(TextField questionIdField, Alert alert, QuestionController controller, String username){
        String alertTitle = "Question ID field";
        String questionID = questionIdField.getText().trim();
        if(questionID.isBlank()) {
            handleAlert(alert, alertTitle, "Question ID field is required and cannot be blank!", Alert.AlertType.ERROR);
            return false;
        }
        else if(!controller.isQuestionAvailableToUser(username, questionID)){
            handleAlert(alert, alertTitle, "This question not found, or doesn't belong to any subject you registered!", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    public boolean validateQuestionContent(TextField contentField, Alert alert){
        String alertTitle = "Question Content field";
        String content = contentField.getText().trim();
        if(content.isBlank()) {
            handleAlert(alert, alertTitle, "Question Content field is required and cannot be blank!", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    public boolean validateChoice(TextField choiceField, Alert alert, int choiceNum){
        String alertTitle = "Choice" + choiceNum + " field";
        String choice = choiceField.getText().trim();
        if(choice.isBlank()) {
            handleAlert(alert, alertTitle, "Choice" + choiceNum + " field is required and cannot be blank!", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    public boolean validateCorrectAnswer(TextField choice1Field, TextField choice2Field, TextField choice3Field,
                                         TextField choice4Field, TextField choice5Field, TextField correctAnswerField,
                                         Alert alert){

        String alertTitle = "Correct Answer field";
        String correctAnswer = correctAnswerField.getText().trim();

        if(correctAnswer.isBlank()) {
            handleAlert(alert, alertTitle, "Correct Answer field is required and cannot be blank!", Alert.AlertType.ERROR);
            return false;
        }

        List<String> choices = Arrays.asList(choice1Field.getText().trim(), choice2Field.getText().trim(),
                choice3Field.getText().trim(), choice4Field.getText().trim(), choice5Field.getText().trim());

        boolean result = false;
        for(String choice : choices)
            result |= correctAnswer.equals(choice);
        if(result)
            return true;

        handleAlert(alert, alertTitle, "The correct answer does not correspond to any choice!", Alert.AlertType.ERROR);
        return false;
    }

}
