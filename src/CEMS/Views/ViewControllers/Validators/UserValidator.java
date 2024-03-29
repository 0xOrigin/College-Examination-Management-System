package CEMS.Views.ViewControllers.Validators;

import CEMS.Controllers.UserController;
import CEMS.Models.Enum.Column;
import CEMS.Models.Enum.Type;
import CEMS.Views.Utilities;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.Arrays;

/**
 * The class User validator.
 */
public class UserValidator extends Utilities {

    /**
     * Validate name.
     *
     * @param nameField  the name field
     * @param alert      the alert
     * @param controller the controller
     * @return the boolean
     */
    public boolean validateName(TextField nameField, Alert alert, UserController controller){
        String alertTitle = "Name field";
        String name = nameField.getText().trim();
        if(name.isBlank()) {
            handleAlert(alert, alertTitle, "Name field is required and cannot be blank!", Alert.AlertType.ERROR);
            return false;
        }
        else if(!controller.isValidName(name)) {
            handleAlert(alert, alertTitle, "Please Enter a valid name!", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    /**
     * Validate email.
     *
     * @param emailField the email field
     * @param alert      the alert
     * @param controller the controller
     * @return the boolean
     */
    public boolean validateEmail(TextField emailField, Alert alert, UserController controller){
        String alertTitle = "Email field";
        String email = emailField.getText().trim();
        if(email.isBlank()) {
            handleAlert(alert, alertTitle, "Email field is required and cannot be blank!", Alert.AlertType.ERROR);
            return false;
        }
        else if(!controller.isValidEmail(email)) {
            handleAlert(alert, alertTitle, "Please Enter a valid email!", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    /**
     * Validate username.
     *
     * @param usernameField the username field
     * @param alert         the alert
     * @param controller    the controller
     * @return the boolean
     */
    public boolean validateUsername(TextField usernameField, Alert alert, UserController controller){
        String alertTitle = "Username field";
        String username = usernameField.getText().trim();
        if(username.isBlank()) {
            handleAlert(alert, alertTitle, "Username field is required and cannot be blank!", Alert.AlertType.ERROR);
            return false;
        }
        else if(!controller.isValidUsername(username)) {
            handleAlert(alert, alertTitle, "Please Enter a valid username!", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    /**
     * Check self deletion.
     *
     * @param usernameField    the username field
     * @param alert            the alert
     * @param loggedInUsername the logged in username
     * @return the boolean
     */
    public boolean checkSelfDeletion(TextField usernameField, Alert alert, StringBuilder loggedInUsername){
        String alertTitle = "Username field";
        String username = usernameField.getText().trim();
        if(username.equals(loggedInUsername.toString())){
            handleAlert(alert, alertTitle, "You cannot delete yourself!", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    /**
     * Check user type before assign.
     *
     * @param usernameField the username field
     * @param alert         the alert
     * @param controller    the controller
     * @return the boolean
     */
    public boolean checkUserTypeBeforeAssign(TextField usernameField, Alert alert, UserController controller){
        String alertTitle = "User Type";
        String username = usernameField.getText().trim();
        String type = String.valueOf(controller.getUserInfo(username, Arrays.asList(Column.Type)).get(Column.Type));
        if(type.equals(Type.Admin.name())) {
            handleAlert(alert, alertTitle, "Cannot assign subject to Admin user!", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    /**
     * Check if assigned before.
     *
     * @param usernameField the username field
     * @param subjectCode   the subject code
     * @param alert         the alert
     * @param controller    the controller
     * @return the boolean
     */
    public boolean checkIfAssignedBefore(TextField usernameField, TextField subjectCode, Alert alert, UserController controller){
        String alertTitle = "Assign duplication";
        String username = usernameField.getText().trim();
        if(controller.isSubjectAssigned(username, subjectCode.getText().trim())){
            handleAlert(alert, alertTitle, "Cannot assign subject to this user twice!", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    /**
     * Check username availability.
     *
     * @param usernameField the username field
     * @param registerVal   the register val
     * @param alert         the alert
     * @param controller    the controller
     * @return the boolean
     */
    public boolean checkUsernameAvailability(TextField usernameField, boolean registerVal, Alert alert, UserController controller){
        String alertTitle = "Username field";
        String username = usernameField.getText().trim();

        if(registerVal && !controller.isUsernameAvailable(username)){
            handleAlert(alert, alertTitle, "This username not available, choose another one!", Alert.AlertType.ERROR);
            return false;
        }
        else if(!registerVal && controller.isUsernameAvailable(username)){
            handleAlert(alert, alertTitle, "This username not found!", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    /**
     * Validate password.
     *
     * @param passwordField the password field
     * @param alert         the alert
     * @param controller    the controller
     * @return the boolean
     */
    public boolean validatePassword(PasswordField passwordField, Alert alert, UserController controller){
        String alertTitle = "Password field";
        String password = passwordField.getText().trim();
        if(password.isBlank()) {
            handleAlert(alert, alertTitle, "Password field is required and cannot be blank!", Alert.AlertType.ERROR);
            return false;
        }
        else if(!controller.isValidPassword(password)) {
            handleAlert(alert, alertTitle, "Please Enter a valid password!", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

}
