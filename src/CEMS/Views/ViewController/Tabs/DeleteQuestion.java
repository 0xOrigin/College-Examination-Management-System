package CEMS.Views.ViewController.Tabs;

import CEMS.Controllers.QuestionController;
import CEMS.Views.Utilities;
import CEMS.Views.ViewController.Validators.QuestionValidator;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class DeleteQuestion extends Utilities {

    QuestionValidator questionValidator = new QuestionValidator();

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
