package CEMS.Views.ViewControllers;

import CEMS.Controllers.*;
import CEMS.Models.CEMS_DbContext;
import CEMS.Models.Enum.Column;
import CEMS.Views.Utilities;
import CEMS.Views.ViewControllers.Tabs.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.input.KeyEvent;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * The class Lecturer dashboard view controller.
 */
public class LecturerDashboardViewController extends Utilities implements Controller, Initializable {

    String name;
    StringBuilder username;
    UserController userController = new UserControllerImp(new CEMS_DbContext());
    ExamController examController = new ExamControllerImp(new CEMS_DbContext());
    SubjectController subjectController = new SubjectControllerImp(new CEMS_DbContext());
    QuestionController questionController = new QuestionControllerImp(new CEMS_DbContext());
    List<Map<Enum, Object>> subjects;
    private Alert alert;
    @FXML
    private Tab addExamTab;
    @FXML
    private Tab addQuestionTab;
    @FXML
    private TextField choice1Field;
    @FXML
    private TextField choice1Field1;
    @FXML
    private TableColumn<Map, Enum> choice1TableColumn;
    @FXML
    private TextField choice2Field;
    @FXML
    private TextField choice2Field1;
    @FXML
    private TableColumn<Map, Enum> choice2TableColumn;
    @FXML
    private TextField choice3Field;
    @FXML
    private TextField choice3Field1;
    @FXML
    private TableColumn<Map, Enum> choice3TableColumn;
    @FXML
    private TextField choice4Field;
    @FXML
    private TextField choice4Field1;
    @FXML
    private TableColumn<Map, Enum> choice4TableColumn;
    @FXML
    private TextField choice5Field;
    @FXML
    private TextField choice5Field1;
    @FXML
    private TableColumn<Map, Enum> choice5TableColumn;
    @FXML
    private TextField contentField;
    @FXML
    private TextField contentField1;
    @FXML
    private TableColumn<Map, Enum> contentTableColumn;
    @FXML
    private ComboBox<String> correctAnswerSelector;
    @FXML
    private ComboBox<String> correctAnswerSelector1;
    @FXML
    private TableColumn<Map, Enum> correctAnswerTableColumn;
    @FXML
    private Tab deleteExamTab;
    @FXML
    private Tab deleteQuestionTab;
    @FXML
    private Spinner<Integer> durationField;
    @FXML
    private Spinner<Integer> durationField1;
    @FXML
    private TextField showDurationField;
    @FXML
    private TableColumn<Map, Enum> durationTableColumn;
    @FXML
    private TextField examIdField;
    @FXML
    private TextField examIdField1;
    @FXML
    private TextField examIdField2;
    @FXML
    private TextField examIdField3;
    @FXML
    private TableColumn<Map, Enum> examIdTableColumn;
    @FXML
    private TextField examNameField;
    @FXML
    private TextField examNameField1;
    @FXML
    private TextField examNameField2;
    @FXML
    private TableColumn<Map, Enum> examNameTableColumn;
    @FXML
    private TableColumn<Map, Enum> examNameTableColumn1;
    @FXML
    private TableColumn<Map, Enum> gradeTableColumn;
    @FXML
    private TableColumn<Map, Enum> idTableColumn;
    @FXML
    private Tab listExamsTab;
    @FXML
    private Label nameLabel;
    @FXML
    private TextField questionIdField;
    @FXML
    private TextField questionIdField1;
    @FXML
    private TableColumn<Map, Enum> questionIdTableColumn;
    @FXML
    private ComboBox<String> selectSubjectField;
    @FXML
    private ComboBox<String> selectSubjectField1;
    @FXML
    private ComboBox<String> selectSubjectField2;
    @FXML
    private ComboBox<String> selectSubjectField3;
    @FXML
    private ComboBox<String> selectSubjectField4;
    @FXML
    private ComboBox<String> selectSubjectField5;
    @FXML
    private Tab showReportTab;
    @FXML
    private TableColumn<Map, Enum> studentIdTableColumn;
    @FXML
    private TableColumn<Map, Enum> studentNameTableColumn;
    @FXML
    private TableColumn<Map, Enum> subjectCodeTableColumn;
    @FXML
    private TextField subjectNameField;
    @FXML
    private TextField subjectNameField1;
    @FXML
    private TableColumn<Map, Enum> subjectNameTableColumn;
    @FXML
    private TabPane tabPane;
    @FXML
    private TableView<Map> tableView;
    @FXML
    private TableView<Map> tableView1;
    @FXML
    private TableView<Map> tableView2;
    @FXML
    private Button updateExamButton;
    @FXML
    private Tab updateExamTab;
    @FXML
    private Button updateQuestionButton;
    @FXML
    private Tab updateQuestionTab;
    @FXML
    private Tab viewExamTab;

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
     * On add exam button action.
     *
     * @param event the event
     */
    @FXML
    void onAddExamButtonAction(ActionEvent event) {
        alert = new Alert(Alert.AlertType.ERROR);
        new AddExam().add(event, alert, examController, selectSubjectField, examNameField, durationField);
    }

    /**
     * On add question button action.
     *
     * @param event the event
     */
    @FXML
    void onAddQuestionButtonAction(ActionEvent event) {
        alert = new Alert(Alert.AlertType.ERROR);
        new AddQuestion().add(event, alert, questionController, examController, selectSubjectField4, examIdField3,
                contentField, choice1Field, choice2Field, choice3Field, choice4Field, choice5Field, correctAnswerSelector);
    }

    /**
     * On add question tab selection.
     *
     * @param event the event
     */
    @FXML
    void onAddQuestionTabSelection(Event event) {
        if(addQuestionTab.isSelected()){
            new AddQuestion().resetTabView(selectSubjectField4, examIdField3, contentField, choice1Field, choice2Field,
                    choice3Field, choice4Field, choice5Field, correctAnswerSelector);
        }
    }

    /**
     * On current exam button action.
     *
     * @param event the event
     */
    @FXML
    void onCurrentExamButtonAction(ActionEvent event) {
        alert = new Alert(Alert.AlertType.ERROR);
        new UpdateExam().setExamData(event, alert, examController, examIdField, selectSubjectField1, examNameField1,
                durationField1, updateExamButton);
    }

    /**
     * On current exam id field key press.
     *
     * @param event the event
     */
    @FXML
    void onCurrentExamIDFieldKeyPress(KeyEvent event){
        new UpdateExam().clearUpdateElements(examNameField1, durationField1);
        new UpdateExam().setUpdateElementsStatus(examNameField1, durationField1, updateExamButton, true);
    }

    /**
     * On current question button action.
     *
     * @param event the event
     */
    @FXML
    void onCurrentQuestionButtonAction(ActionEvent event) {
        alert = new Alert(Alert.AlertType.ERROR);
        new UpdateQuestion().setQuestionData(event, alert, questionController, questionIdField, this.username.toString(),
                contentField1, choice1Field1, choice2Field1, choice3Field1, choice4Field1, choice5Field1,
                correctAnswerSelector1, updateQuestionButton);
    }

    /**
     * On current question id field key press.
     *
     * @param event the event
     */
    @FXML
    void onCurrentQuestionIDFieldKeyPress(KeyEvent event){
        new UpdateQuestion().clearUpdateElements(contentField1, choice1Field1, choice2Field1, choice3Field1, choice4Field1,
                choice5Field1, correctAnswerSelector1);
        new UpdateQuestion().setUpdateElementsStatus(contentField1, choice1Field1, choice2Field1, choice3Field1, choice4Field1, choice5Field1,
                correctAnswerSelector1, updateQuestionButton, true);
    }

    /**
     * On delete exam button action.
     *
     * @param event the event
     */
    @FXML
    void onDeleteExamButtonAction(ActionEvent event) {
        alert = new Alert(Alert.AlertType.ERROR);
        new DeleteExam().delete(event, alert, examController, selectSubjectField2, examIdField1);
    }

    /**
     * On delete question button action.
     *
     * @param event the event
     */
    @FXML
    void onDeleteQuestionButtonAction(ActionEvent event) {
        alert = new Alert(Alert.AlertType.ERROR);
        new DeleteQuestion().delete(event, alert, questionController, questionIdField1, this.username.toString());
    }

    /**
     * On list exams tab selection.
     *
     * @param event the event
     */
    @FXML
    void onListExamsTabSelection(Event event) {
        if(listExamsTab.isSelected())
            new ListExams().list(userController, tableView, this.username.toString());
    }

    /**
     * On show report button action.
     *
     * @param event the event
     */
    @FXML
    void onShowReportButtonAction(ActionEvent event) {
        new ShowReport().show(subjectController, tableView2, selectSubjectField5);
    }

    /**
     * On show report tab selection.
     *
     * @param event the event
     */
    @FXML
    void onShowReportTabSelection(Event event){
        if(showReportTab.isSelected())
            new ShowReport().resetTabView(selectSubjectField5, tableView2);
    }

    /**
     * On select subject field change.
     *
     * @param event the event
     */
    @FXML
    void onSelectSubjectFieldChange(ActionEvent event) {
        updateSubjectNameField(selectSubjectField, subjectNameField, subjects);
    }

    /**
     * On select subject field 1 action.
     *
     * @param event the event
     */
    @FXML
    void onSelectSubjectField1Action(ActionEvent event){
        onCurrentExamIDFieldKeyPress(null);
    }

    /**
     * On select subject field 5 action.
     *
     * @param event the event
     */
    @FXML
    void onSelectSubjectField5Action(ActionEvent event){
        updateSubjectNameField(selectSubjectField5, subjectNameField1, subjects);
    }

    /**
     * On update exam button action.
     *
     * @param event the event
     */
    @FXML
    void onUpdateExamButtonAction(ActionEvent event) {
        alert = new Alert(Alert.AlertType.ERROR);
        new UpdateExam().update(event, alert, examController, examIdField, selectSubjectField1, examNameField1, durationField1);
    }

    /**
     * On update question button action.
     *
     * @param event the event
     */
    @FXML
    void onUpdateQuestionButtonAction(ActionEvent event) {
        alert = new Alert(Alert.AlertType.ERROR);
        new UpdateQuestion().update(event, alert, questionController, questionIdField, this.username.toString(),
                contentField1, choice1Field1, choice2Field1, choice3Field1, choice4Field1, choice5Field1,
                correctAnswerSelector1);
    }

    /**
     * On view exam button action.
     *
     * @param event the event
     */
    @FXML
    void onViewExamButtonAction(ActionEvent event) {
        alert = new Alert(Alert.AlertType.ERROR);
        new ViewExam().view(event, alert, examController, questionController, selectSubjectField3, examIdField2, examNameField2,
                showDurationField, tableView1);
    }

    /**
     * On view exam tab selection.
     *
     * @param event the event
     */
    @FXML
    void onViewExamTabSelection(Event event) {
        if(viewExamTab.isSelected()){
            new ViewExam().resetTabView(selectSubjectField3, examIdField2, examNameField2, showDurationField, tableView1);
        }
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
     * Initialize duration field.
     *
     * @param durationField the duration field
     */
    void initializeDurationField(Spinner<Integer> durationField){
        int min = 10;
        int max = 180;
        int initialValue = 10;
        int amountToStepBy = 10;
        durationField.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max, initialValue, amountToStepBy));
    }

    /**
     * Initialize list exams table.
     */
    void initializeListExamsTable(){
        idTableColumn.setCellValueFactory(new MapValueFactory<>(Column.ID));
        subjectCodeTableColumn.setCellValueFactory(new MapValueFactory<>(Column.SubjectCode));
        subjectNameTableColumn.setCellValueFactory(new MapValueFactory<>(Column.SubjectName));
        examNameTableColumn.setCellValueFactory(new MapValueFactory<>(Column.ExamName));
        durationTableColumn.setCellValueFactory(new MapValueFactory<>(Column.Duration));

        tableView.getColumns().setAll(Arrays.asList(idTableColumn, subjectCodeTableColumn, subjectNameTableColumn,
                examNameTableColumn, durationTableColumn));
    }

    /**
     * Initialize view exam table.
     */
    void initializeViewExamTable(){
        questionIdTableColumn.setCellValueFactory(new MapValueFactory<>(Column.ID));
        contentTableColumn.setCellValueFactory(new MapValueFactory<>(Column.Content));
        choice1TableColumn.setCellValueFactory(new MapValueFactory<>(Column.Choice1));
        choice2TableColumn.setCellValueFactory(new MapValueFactory<>(Column.Choice2));
        choice3TableColumn.setCellValueFactory(new MapValueFactory<>(Column.Choice3));
        choice4TableColumn.setCellValueFactory(new MapValueFactory<>(Column.Choice4));
        choice5TableColumn.setCellValueFactory(new MapValueFactory<>(Column.Choice5));
        correctAnswerTableColumn.setCellValueFactory(new MapValueFactory<>(Column.CorrectAnswer));

        tableView1.getColumns().setAll(Arrays.asList(questionIdTableColumn, contentTableColumn, choice1TableColumn,
                choice2TableColumn, choice3TableColumn, choice4TableColumn, choice5TableColumn,
                correctAnswerTableColumn));
    }

    /**
     * Initialize show report table.
     */
    void initializeShowReportTable(){
        studentIdTableColumn.setCellValueFactory(new MapValueFactory<>(Column.StudentID));
        studentNameTableColumn.setCellValueFactory(new MapValueFactory<>(Column.StudentName));
        examIdTableColumn.setCellValueFactory(new MapValueFactory<>(Column.ExamID));
        examNameTableColumn1.setCellValueFactory(new MapValueFactory<>(Column.ExamName));
        gradeTableColumn.setCellValueFactory(new MapValueFactory<>(Column.Grade));

        tableView2.getColumns().setAll(Arrays.asList(studentIdTableColumn, studentNameTableColumn, examIdTableColumn,
                examNameTableColumn1, gradeTableColumn));
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

    /**
     * Initialize selection fields.
     *
     * @param username the username
     */
    void initializeSelectionFields(String username){
        subjects = userController.getAllRegisteredSubjectsFor(username);

        initializeSelectSubjectField(selectSubjectField, subjects);
        updateSubjectNameField(selectSubjectField, subjectNameField, subjects);

        initializeSelectSubjectField(selectSubjectField1, subjects);
        initializeSelectSubjectField(selectSubjectField2, subjects);
        initializeSelectSubjectField(selectSubjectField3, subjects);
        initializeSelectSubjectField(selectSubjectField4, subjects);

        initializeSelectSubjectField(selectSubjectField5, subjects);
        updateSubjectNameField(selectSubjectField5, subjectNameField1, subjects);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeListExamsTable();
        initializeViewExamTable();
        initializeShowReportTable();

        initializeDurationField(durationField);
        initializeDurationField(durationField1);

        List<String> choices = Arrays.asList("Choice 1", "Choice 2", "Choice 3", "Choice 4", "Choice 5");
        correctAnswerSelector.getItems().setAll(choices);
        correctAnswerSelector1.getItems().setAll(choices);

        tabPane.getTabs().setAll(addExamTab, updateExamTab, deleteExamTab, listExamsTab, viewExamTab,
                addQuestionTab, updateQuestionTab, deleteQuestionTab, showReportTab);
    }

}
