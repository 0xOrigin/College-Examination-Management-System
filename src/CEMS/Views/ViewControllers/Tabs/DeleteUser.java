package CEMS.Views.ViewControllers.Tabs;

import CEMS.Controllers.UserController;
import CEMS.Views.Utilities;
import CEMS.Views.ViewControllers.Validators.UserValidator;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * The class Delete user.
 */
public class DeleteUser extends Utilities {

    UserValidator userValidator = new UserValidator();

    /**
     * Delete.
     *
     * @param event            the event
     * @param alert            the alert
     * @param controller       the controller
     * @param usernameField    the username field
     * @param loggedInUsername the logged-in username
     */
    public void delete(ActionEvent event, Alert alert, UserController controller, TextField usernameField,
                       StringBuilder loggedInUsername){

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
