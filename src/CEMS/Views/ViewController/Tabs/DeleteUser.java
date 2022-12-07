package CEMS.Views.ViewController.Tabs;

import CEMS.Controllers.UserController;
import CEMS.Views.Utilities;
import CEMS.Views.ViewController.UserValidator;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class DeleteUser extends Utilities {
    UserValidator userValidator = new UserValidator();

    public void delete(ActionEvent event, Alert alert, UserController controller, TextField usernameField,
                       String loggedInUsername){

        super.setAlertOwner(event, alert);
        boolean generalState;

        generalState = userValidator.validateUsername(usernameField, alert, controller);
        generalState &= userValidator.checkUsernameAvailability(usernameField, false, alert, controller);
        generalState &= userValidator.checkSelfDeletion(usernameField, alert, loggedInUsername);

        if(generalState){
            controller.delete(usernameField.getText().trim());
            handleAlert(alert, "Successful Deletion",
                    "The user has been successfully deleted.",
                    Alert.AlertType.INFORMATION);
        }
    }
}
