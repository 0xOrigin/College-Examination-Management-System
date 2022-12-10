package CEMS.Views.ViewControllers;

import CEMS.Controllers.ExamController;
import CEMS.Controllers.ExamControllerImp;
import CEMS.Controllers.UserController;
import CEMS.Controllers.UserControllerImp;
import CEMS.Models.CEMS_DbContext;
import CEMS.Models.Enum.Column;
import CEMS.Views.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class StudentDashboardViewController extends Utilities implements Controller, Initializable {

    String name;
    StringBuilder username;
    UserController userController = new UserControllerImp(new CEMS_DbContext());
    ExamController examController = new ExamControllerImp(new CEMS_DbContext());
    List<Map<Enum, Object>> subjects;
    List<Map<Enum, Object>> selectedSubjectExams;
    private Alert alert;
    @FXML
    private Label nameLabel;
    @FXML
    private Label errorLabel;
    @FXML
    private ComboBox<String> selectExamField;
    @FXML
    private ComboBox<String> selectSubjectField;
    @FXML
    private TextField showDurationField;
    @FXML
    private Button startExamButton;
    @FXML
    private TextField subjectNameField;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab examTab;
    @FXML
    private Tab resultTab;
    @FXML
    private Tab selectExamTab;

    @FXML
    void onLogoutButtonAction(ActionEvent event) {
        super.changeScene(event, "LoginView.fxml", "College Examination Management System");
    }

    @FXML
    void onSelectSubjectFieldChange(ActionEvent event) {
        updateSubjectNameField(selectSubjectField, subjectNameField, subjects);

        String selectedSubjectCode = selectSubjectField.getSelectionModel().getSelectedItem();
        selectedSubjectExams = examController.getAllExamsOfSubject(selectedSubjectCode);
        initializeSelectExamField(selectExamField, selectedSubjectExams);
    }

    @FXML
    void onSelectExamFieldChange(ActionEvent event) {
        updateExamDurationField(selectExamField, showDurationField, selectedSubjectExams);
    }

    @FXML
    void onStartExamButtonAction(ActionEvent event) {
        examTab.setDisable(false);
        tabPane.getSelectionModel().select(examTab);
        selectExamTab.setDisable(true);
    }

    void setStartExamButtonState(Button startExamButton, boolean disable){
        startExamButton.setDisable(disable);
    }

    void setErrorLabelState(Label errorLabel, boolean visible){
        errorLabel.setVisible(visible);
    }

    void updateExamDurationField(ComboBox<String> selectExamField, TextField showDurationField, List<Map<Enum, Object>> exams){
        int examNameIndex = selectExamField.getSelectionModel().getSelectedIndex();
        if(exams.size() > 0 && examNameIndex > -1){
            showDurationField.setText(exams.get(examNameIndex).get(Column.Duration).toString());
        } else {
            showDurationField.clear();
        }
    }

    void initializeSelectExamField(ComboBox<String> selectExamField, List<Map<Enum, Object>> exams){
        selectExamField.getItems().clear();
        for(Map<Enum, Object> exam : exams)
            selectExamField.getItems().add(exam.get(Column.Name).toString());
        selectExamField.getSelectionModel().selectFirst();

        boolean setDisable = (selectExamField.getSelectionModel().getSelectedIndex() < 0);
        setStartExamButtonState(startExamButton, setDisable);
        setErrorLabelState(errorLabel, setDisable);
    }

    void updateSubjectNameField(ComboBox<String> selectSubjectField, TextField subjectNameField, List<Map<Enum, Object>> subjects){
        int subjectCodeIndex = selectSubjectField.getSelectionModel().getSelectedIndex();
        subjectNameField.setText(subjects.get(subjectCodeIndex).get(Column.SubjectName).toString());
    }

    void initializeSelectSubjectField(ComboBox<String> subjectField, List<Map<Enum, Object>> subjects){
        for(Map<Enum, Object> subject : subjects)
            subjectField.getItems().add(subject.get(Column.Code).toString());
        subjectField.getSelectionModel().selectFirst();
    }

    @Override
    public void setNameOfUser(String name) {
        this.name = name;
        this.nameLabel.setText(name);
    }

    @Override
    public void setUsername(StringBuilder username) {
        this.username = username;

        initializeSelectionFields(this.username.toString());
    }

    void initializeSelectionFields(String username){
        subjects = userController.getAllRegisteredSubjectsFor(username);

        initializeSelectSubjectField(selectSubjectField, subjects);
        updateSubjectNameField(selectSubjectField, subjectNameField, subjects);

        String selectedSubjectCode = selectSubjectField.getSelectionModel().getSelectedItem();
        selectedSubjectExams = examController.getAllExamsOfSubject(selectedSubjectCode);
        initializeSelectExamField(selectExamField, selectedSubjectExams);
        updateExamDurationField(selectExamField, showDurationField, selectedSubjectExams);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabPane.getTabs().setAll(selectExamTab, examTab, resultTab);
    }

}
