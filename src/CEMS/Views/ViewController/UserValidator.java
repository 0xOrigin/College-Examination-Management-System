package CEMS.Views.ViewController;

import CEMS.Controllers.UserController;
import CEMS.Models.Enum.Column;
import CEMS.Models.Enum.Type;
import CEMS.Views.Utilities;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.Arrays;

public class UserValidator extends Utilities {

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

    public boolean checkSelfDeletion(TextField usernameField, Alert alert, String loggedInUsername){
        String alertTitle = "Username field";
        String username = usernameField.getText().trim();
        if(username.equals(loggedInUsername)){
            handleAlert(alert, alertTitle, "You cannot delete yourself!", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

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

    public boolean checkIfAssignedBefore(TextField usernameField, TextField subjectCode, Alert alert, UserController controller){
        String alertTitle = "Assign duplication";
        String username = usernameField.getText().trim();
        if(controller.isSubjectAssigned(username, subjectCode.getText().trim())){
            handleAlert(alert, alertTitle, "Cannot assign subject to this user twice!", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

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
