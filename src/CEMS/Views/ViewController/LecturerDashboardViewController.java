package CEMS.Views.ViewController;

import CEMS.Views.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public class LecturerDashboardViewController extends Utilities implements Controller {

    String name, username;
    @FXML
    private TabPane tabPane;
    @FXML
    private Label nameLabel;
    @FXML
    private Tab addExamTab;
    @FXML
    private Tab updateExamTab;
    @FXML
    private Tab deleteExamTab;
    @FXML
    private Tab listViewExamsTab;
    @FXML
    private Tab addQuestionTab;
    @FXML
    private Tab updateQuestionTab;
    @FXML
    private Tab deleteQuestionTab;
    @FXML
    private Tab showReportTab;

    @FXML
    void onLogoutButtonAction(ActionEvent event) {
        super.changeScene(event, "LoginView.fxml", "College Examination Management System");
    }

    @Override
    public void setNameOfUser(String name) {
        this.name = name;
        this.nameLabel.setText(name);
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

}
