package CEMS.Views.ViewController;

import CEMS.Views.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class StudentDashboardViewController extends Utilities implements Controller {

    String name;
    StringBuilder username;

    @FXML
    void onLogoutButtonAction(ActionEvent event) {
        super.changeScene(event, "LoginView.fxml", "College Examination Management System");
    }

    @Override
    public void setNameOfUser(String name) {
        this.name = name;
    }

    @Override
    public void setUsername(StringBuilder username) {
        this.username = username;
    }

}
