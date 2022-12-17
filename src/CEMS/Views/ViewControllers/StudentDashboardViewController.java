package CEMS.Views.ViewControllers;

import CEMS.Controllers.*;
import CEMS.Models.CEMS_DbContext;
import CEMS.Models.Enum.Column;
import CEMS.Views.Utilities;
import CEMS.Views.ViewControllers.Tabs.Exam;
import CEMS.Views.ViewControllers.Tabs.Result;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.*;

/**
 * The class Student dashboard view controller.
 */
public class StudentDashboardViewController extends Utilities implements Controller, Initializable {

    String name;
    StringBuilder username;
    String studentID;
    UserController userController = new UserControllerImp(new CEMS_DbContext());
    ExamController examController = new ExamControllerImp(new CEMS_DbContext());
    QuestionController questionController = new QuestionControllerImp(new CEMS_DbContext());
    List<Map<Enum, Object>> subjects;
    List<Map<Enum, Object>> selectedSubjectExams;
    Exam exam;
    Result result;
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
    private Label subjectNameLabel;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab examTab;
    @FXML
    private Tab resultTab;
    @FXML
    private Tab selectExamTab;
    @FXML
    private Label timerLabel;
    @FXML
    private Label examNameLabel;
    @FXML
    private Label examNameLabel1;
    @FXML
    private Label currentQuestionNumLabel;
    @FXML
    private Label totalQuestionsNumLabel;
    @FXML
    private ToggleGroup Answers;
    @FXML
    private RadioButton choice1Button;
    @FXML
    private RadioButton choice2Button;
    @FXML
    private RadioButton choice3Button;
    @FXML
    private RadioButton choice4Button;
    @FXML
    private RadioButton choice5Button;
    @FXML
    private Button nextSubmitButton;
    @FXML
    private Button prevButton;
    @FXML
    private TextArea questionTextArea;
    @FXML
    private Label gradeLabel;
    @FXML
    private Label totalMarkLabel;
    @FXML
    private TextArea correctedExamField;

    /**
     * On logout button action.
     *
     * @param event the event
     */
    @FXML
    void onLogoutButtonAction(ActionEvent event) {
        super.changeScene(event, "LoginView.fxml", "College Examination Management System");
    }

    /**
     * On select subject field change.
     *
     * @param event the event
     */
    @FXML
    void onSelectSubjectFieldChange(ActionEvent event) {
        updateSubjectNameField(selectSubjectField, subjectNameField, subjects);
        updateSelectExamField(selectExamField);
    }

    /**
     * On select exam field change.
     *
     * @param event the event
     */
    @FXML
    void onSelectExamFieldChange(ActionEvent event) {
        updateExamDurationField(selectExamField, showDurationField, selectedSubjectExams);
    }

    /**
     * On start exam button action.
     *
     * @param event the event
     */
    @FXML
    void onStartExamButtonAction(ActionEvent event) {
        alert = new Alert(Alert.AlertType.ERROR);

        String examID = getExamID(selectedSubjectExams, selectExamField);

        if(checkIfExamAvailability(event, alert, examID)){
            exam = new Exam(studentID, examID, selectExamField, examNameLabel, totalQuestionsNumLabel, examController, questionController);

            exam.setExamTabAttributes(questionTextArea, Answers, choice1Button, choice2Button, choice3Button, choice4Button,
                    choice5Button, prevButton, nextSubmitButton);

            if(exam.start(event, alert, showDurationField, timerLabel, currentQuestionNumLabel))
                transitToTab(tabPane, selectExamTab, examTab);
        }
    }

    /**
     * Get exam id.
     *
     * @param selectedSubjectExams the selected subject exams
     * @param selectExamField      the select exam field
     * @return the exam id
     */
    String getExamID(List<Map<Enum, Object>> selectedSubjectExams, ComboBox<String> selectExamField){
        return selectedSubjectExams.get(selectExamField.getSelectionModel().getSelectedIndex()).get(Column.ID).toString();
    }

    /**
     * Check if exam availability.
     *
     * @param event  the event
     * @param alert  the alert
     * @param examID the exam id
     * @return the boolean
     */
    boolean checkIfExamAvailability(ActionEvent event, Alert alert, String examID){
        super.setAlertOwner(event, alert);
        if(examController.isTaken(studentID, examID)){
            handleAlert(alert, "Taken exam",
                    "You cannot take the exam again!",
                    Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    /**
     * Transit to tab.
     *
     * @param tabPane        the tab pane
     * @param currentTab     the current tab
     * @param destinationTab the destination tab
     */
    void transitToTab(TabPane tabPane, Tab currentTab, Tab destinationTab){
        destinationTab.setDisable(false);
        tabPane.getSelectionModel().select(destinationTab);
        currentTab.setDisable(true);
    }

    /**
     * On next submit button action.
     *
     * @param event the event
     */
    @FXML
    void onNextSubmitButtonAction(ActionEvent event) {
        boolean isSubmitAvailable = exam.isSubmitUnlocked();
        exam.incrementCurrentQuestionNum(currentQuestionNumLabel);
        if(isSubmitAvailable) {
            exam.stopTimer();
            result = new Result(studentID, exam, examController,
                    subjectNameLabel, examNameLabel1, gradeLabel, totalMarkLabel);
            result.setResultData(subjectNameField, selectExamField, correctedExamField);
            result.correctExam();
            transitToTab(tabPane, examTab, resultTab);
        }
    }

    /**
     * On prev button action.
     *
     * @param event the event
     */
    @FXML
    void onPrevButtonAction(ActionEvent event) {
        exam.decrementCurrentQuestionNum(currentQuestionNumLabel);
    }

    /**
     * Set start exam button state.
     *
     * @param startExamButton the start exam button
     * @param disable         the disable determination
     */
    void setStartExamButtonState(Button startExamButton, boolean disable){
        startExamButton.setDisable(disable);
    }

    /**
     * Set error label state.
     *
     * @param errorLabel the error label
     * @param visible    the visible determination
     */
    void setErrorLabelState(Label errorLabel, boolean visible){
        errorLabel.setVisible(visible);
    }

    /**
     * Update exam duration field.
     *
     * @param selectExamField   the select exam field
     * @param showDurationField the show duration field
     * @param exams             the exams
     */
    void updateExamDurationField(ComboBox<String> selectExamField, TextField showDurationField, List<Map<Enum, Object>> exams){
        int examNameIndex = selectExamField.getSelectionModel().getSelectedIndex();
        if(exams.size() > 0 && examNameIndex > -1){
            showDurationField.setText(exams.get(examNameIndex).get(Column.Duration).toString());
        } else {
            showDurationField.clear();
        }
    }

    /**
     * Update select exam field.
     *
     * @param selectExamField the select exam field
     */
    void updateSelectExamField(ComboBox<String> selectExamField){
        String selectedSubjectCode = selectSubjectField.getSelectionModel().getSelectedItem();
        selectedSubjectExams = examController.getAllExamsOfSubject(selectedSubjectCode);
        initializeSelectExamField(selectExamField, selectedSubjectExams);
    }

    /**
     * Initialize select exam field.
     *
     * @param selectExamField the select exam field
     * @param exams           the exams
     */
    void initializeSelectExamField(ComboBox<String> selectExamField, List<Map<Enum, Object>> exams){
        selectExamField.getItems().clear();
        for(Map<Enum, Object> exam : exams)
            selectExamField.getItems().add(exam.get(Column.Name).toString());
        selectExamField.getSelectionModel().selectFirst();

        boolean setDisable = (selectExamField.getSelectionModel().getSelectedIndex() < 0);
        setStartExamButtonState(startExamButton, setDisable);
        setErrorLabelState(errorLabel, setDisable);
    }

    /**
     * Update subject name field.
     *
     * @param selectSubjectField the select subject field
     * @param subjectNameField   the subject name field
     * @param subjects           the subjects
     */
    void updateSubjectNameField(ComboBox<String> selectSubjectField, TextField subjectNameField, List<Map<Enum, Object>> subjects){
        int subjectCodeIndex = selectSubjectField.getSelectionModel().getSelectedIndex();
        subjectNameField.setText(subjects.get(subjectCodeIndex).get(Column.SubjectName).toString());
    }

    /**
     * Initialize select subject field.
     *
     * @param subjectField the subject field
     * @param subjects     the subjects
     */
    void initializeSelectSubjectField(ComboBox<String> subjectField, List<Map<Enum, Object>> subjects){
        for(Map<Enum, Object> subject : subjects)
            subjectField.getItems().add(subject.get(Column.Code).toString());
        subjectField.getSelectionModel().selectFirst();
    }

    /**
     * Get user id.
     *
     * @param username the username
     * @return the string
     */
    String getUserID(String username){
        return userController.getUserInfo(username, Arrays.asList(Column.ID)).get(Column.ID).toString();
    }

    @Override
    public void setNameOfUser(String name) {
        this.name = name;
        this.nameLabel.setText(name);
    }

    @Override
    public void setUsername(StringBuilder username) {
        this.username = username;

        this.studentID = getUserID(this.username.toString());
        initializeSelectionFields(this.username.toString());
    }

    /**
     * Initialize selection fields.
     *
     * @param username the username
     */
    void initializeSelectionFields(String username){
        subjects = userController.getAllRegisteredSubjectsFor(username);

        initializeSelectSubjectField(selectSubjectField, subjects);
        updateSubjectNameField(selectSubjectField, subjectNameField, subjects);

        updateSelectExamField(selectExamField);
        updateExamDurationField(selectExamField, showDurationField, selectedSubjectExams);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabPane.getTabs().setAll(selectExamTab, examTab, resultTab);
    }

}
