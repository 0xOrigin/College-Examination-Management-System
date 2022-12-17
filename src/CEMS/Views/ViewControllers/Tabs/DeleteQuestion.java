package CEMS.Views.ViewControllers.Tabs;

import CEMS.Controllers.QuestionController;
import CEMS.Views.Utilities;
import CEMS.Views.ViewControllers.Validators.QuestionValidator;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * The class Delete question.
 */
public class DeleteQuestion extends Utilities {

    QuestionValidator questionValidator = new QuestionValidator();

    /**
     * Delete.
     *
     * @param event           the event
     * @param alert           the alert
     * @param controller      the controller
     * @param questionIdField the question id field
     * @param username        the username
     */
    public void delete(ActionEvent event, Alert alert, QuestionController controller, TextField questionIdField, String username){

        super.setAlertOwner(event, alert);
        boolean generalState;

        String questionID = questionIdField.getText().trim();

        generalState = questionValidator.validateQuestionID(questionIdField, alert, controller, username);

        if(generalState){
            controller.delete(questionID);
            handleAlert(alert, "Successful Operation",
                    "The question has been successfully deleted.",
                    Alert.AlertType.INFORMATION);
        }
    }

}
