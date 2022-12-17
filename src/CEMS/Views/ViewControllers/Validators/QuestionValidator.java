package CEMS.Views.ViewControllers.Validators;

import CEMS.Controllers.QuestionController;
import CEMS.Views.Utilities;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;


/**
 * The class Question validator.
 */
public class QuestionValidator extends Utilities {

    /**
     * Validate question id.
     *
     * @param questionIdField the question id field
     * @param alert           the alert
     * @param controller      the controller
     * @param username        the username
     * @return the boolean
     */
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

    /**
     * Validate question content.
     *
     * @param contentField the content field
     * @param alert        the alert
     * @return the boolean
     */
    public boolean validateQuestionContent(TextField contentField, Alert alert){
        String alertTitle = "Question Content field";
        String content = contentField.getText().trim();
        if(content.isBlank()) {
            handleAlert(alert, alertTitle, "Question Content field is required and cannot be blank!", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    /**
     * Validate choice.
     *
     * @param choiceField the choice field
     * @param alert       the alert
     * @param choiceNum   the choice number
     * @return the boolean
     */
    public boolean validateChoice(TextField choiceField, Alert alert, int choiceNum){
        String alertTitle = "Choice" + choiceNum + " field";
        String choice = choiceField.getText().trim();
        if(choice.isBlank()) {
            handleAlert(alert, alertTitle, "Choice" + choiceNum + " field is required and cannot be blank!", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    /**
     * Validate correct answer.
     *
     * @param correctAnswerSelector the correct answer selector
     * @param alert                 the alert
     * @return the boolean
     */
    public boolean validateCorrectAnswer(ComboBox<String> correctAnswerSelector, Alert alert) {
        String alertTitle = "Correct Answer field";
        int indexOfCurrentSelection = correctAnswerSelector.getSelectionModel().getSelectedIndex();
        if(indexOfCurrentSelection < 0){
            handleAlert(alert, alertTitle, "You haven't chosen the correct answer yet!", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }
}
