package CEMS.Views.ViewControllers.Tabs;

import CEMS.Controllers.UserController;
import CEMS.Views.Utilities;
import CEMS.Views.ViewControllers.Validators.UserValidator;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * The class Add user.
 */
public class AddUser extends Utilities {

    UserValidator userValidator = new UserValidator();

    /**
     * Add.
     *
     * @param event         the event
     * @param alert         the alert
     * @param controller    the controller
     * @param nameField     the name field
     * @param emailField    the email field
     * @param usernameField the username field
     * @param passwordField the password field
     * @param genderField   the gender field
     * @param typeField     the type field
     */
    public void add(ActionEvent event, Alert alert, UserController controller, TextField nameField, TextField emailField,
             TextField usernameField, PasswordField passwordField, ComboBox<Enum> genderField, ComboBox<Enum> typeField){

        super.setAlertOwner(event, alert);
        boolean generalState;

        generalState = userValidator.validatePassword(passwordField, alert, controller);
        generalState &= userValidator.validateUsername(usernameField, alert, controller);
        generalState &= userValidator.checkUsernameAvailability(usernameField, true, alert, controller);
        generalState &= userValidator.validateEmail(emailField, alert, controller);
        generalState &= userValidator.validateName(nameField, alert, controller);

        if(generalState){
            controller.register(nameField.getText().trim(), emailField.getText().trim(), usernameField.getText().trim(),
                    passwordField.getText().trim(), genderField.getSelectionModel().getSelectedItem(),
                    typeField.getSelectionModel().getSelectedItem());
            handleAlert(alert, "Successful Registration",
                    "A message was sent to this email that includes the username and password.",
                    Alert.AlertType.INFORMATION);
        }
    }
}
