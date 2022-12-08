package CEMS.Views.ViewController.Tabs;

import CEMS.Controllers.UserController;
import CEMS.Models.Enum.Column;
import CEMS.Views.Utilities;
import CEMS.Views.ViewController.UserValidator;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.Arrays;
import java.util.Map;

public class UpdateUser extends Utilities {
    UserValidator userValidator = new UserValidator();

    public void update(ActionEvent event, Alert alert, UserController controller, TextField currentUsernameField,
                       TextField usernameField, TextField emailField, PasswordField passwordField, StringBuilder loggedInUsername){

        super.setAlertOwner(event, alert);
        boolean generalState;

        String currentUsername = currentUsernameField.getText().trim();
        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();

        generalState = userValidator.validatePassword(passwordField, alert, controller);
        generalState &= userValidator.validateUsername(usernameField, alert, controller);
        if(!currentUsername.equals(username))
            generalState &= userValidator.checkUsernameAvailability(usernameField, true, alert, controller);
        generalState &= userValidator.validateEmail(emailField, alert, controller);

        if(generalState){
            controller.update(Arrays.asList(Column.Email, Column.Username, Column.Password),
                    Arrays.asList(email, username, password), currentUsername);
            loggedInUsername.setLength(0);
            loggedInUsername.append(username);
            handleAlert(alert, "Successful Update",
                    "A message was sent to the updated email that includes the username and password.",
                    Alert.AlertType.INFORMATION);
        }
    }

    public void setUserData(ActionEvent event, Alert alert, UserController controller, TextField currentUsernameField,
                            TextField emailField, TextField usernameField, PasswordField passwordField, Button updateButton){

        super.setAlertOwner(event, alert);
        boolean generalState;

        generalState = userValidator.validateUsername(currentUsernameField, alert, controller);
        generalState &= userValidator.checkUsernameAvailability(currentUsernameField, false, alert, controller);

        if(generalState){
            Map<Enum, Object> userInfo = controller.getUserInfo(currentUsernameField.getText().trim(),
                    Arrays.asList(Column.Email, Column.Username, Column.Password));
            emailField.setText(String.valueOf(userInfo.get(Column.Email)));
            usernameField.setText(String.valueOf(userInfo.get(Column.Username)));
            passwordField.setText(String.valueOf(userInfo.get(Column.Password)));
            setUpdateElementsStatus(emailField, usernameField, passwordField, updateButton, false);
        }
    }

    public void setUpdateElementsStatus(TextField emailField, TextField usernameField, PasswordField passwordField,
                                        Button updateButton, boolean disable){
        emailField.setDisable(disable);
        usernameField.setDisable(disable);
        passwordField.setDisable(disable);
        updateButton.setDisable(disable);
    }

    public void clearUpdateElements(TextField emailField, TextField usernameField, PasswordField passwordField){
        emailField.clear();
        usernameField.clear();
        passwordField.clear();
    }
}
