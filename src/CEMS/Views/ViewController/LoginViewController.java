package CEMS.Views.ViewController;

import CEMS.Controllers.LoginController;
import CEMS.Controllers.LoginControllerImp;
import CEMS.Models.CEMS_DbContext;
import CEMS.Models.Enum.Column;
import CEMS.Models.Enum.Type;
import CEMS.Views.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class LoginViewController extends Utilities {

    LoginController loginController = new LoginControllerImp(new CEMS_DbContext());

    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    void onLoginButtonAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        super.setAlertOwner(event, alert);
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if(username.isBlank())
            handleAlert(alert, "Username field", "Username field is required and cannot be blank!", Alert.AlertType.ERROR);
        else if(password.isBlank())
            handleAlert(alert, "Password field", "Password field is required and cannot be blank!", Alert.AlertType.ERROR);
        else {
            boolean result = isValidAccount();
            if(result) {
                String userType = getUserType();
                if(allowToLogin(userType, username)){
                    handleAlert(alert, "Successful Login",
                            "Welcome to the " + userType + " Dashboard!", Alert.AlertType.INFORMATION);
                    redirectToScene(event, userType);
                } else {
                    handleAlert(alert, "Not registered in any Subject",
                            "Since you are not registered in any subject, you cannot enter the " + userType + " Dashboard.\n" +
                                    "Contact the administrator to record subjects for you.",
                            Alert.AlertType.ERROR);
                }
            }
            else
                handleAlert(alert, "Unsuccessful Login", "Please Enter a valid Username and Password!", Alert.AlertType.ERROR);
        }
    }

    private boolean isValidAccount(){
        return loginController.isValidAccount(usernameField.getText().trim(), passwordField.getText().trim());
    }

    private String getUserType(){
        return loginController.getUserInfo(usernameField.getText(), Column.Type);
    }

    private boolean allowToLogin(String userType, String username){
        boolean typeEquality = userType.equals(Type.Admin.name());
        int count = loginController.countOfRegisteredSubjectsFor(username);
        return typeEquality || count > 0;
    }

    private void redirectToScene(ActionEvent event, String userType){
        String username = usernameField.getText().trim();
        String name = loginController.getUserInfo(username, Column.Name);

        super.changeSceneAndSet(event, userType + "DashboardView.fxml", userType + "Dashboard",
                username, name);
    }

}
