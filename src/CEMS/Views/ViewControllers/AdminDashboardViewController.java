package CEMS.Views.ViewControllers;

import CEMS.Controllers.SubjectController;
import CEMS.Controllers.SubjectControllerImp;
import CEMS.Controllers.UserController;
import CEMS.Controllers.UserControllerImp;
import CEMS.Models.CEMS_DbContext;
import CEMS.Models.Enum.Column;
import CEMS.Models.Enum.Gender;
import CEMS.Models.Enum.Type;
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
 * The class Admin dashboard view controller.
 */
public class AdminDashboardViewController extends Utilities implements Controller, Initializable {

    String name;
    StringBuilder username;
    UserController userController = new UserControllerImp(new CEMS_DbContext());
    SubjectController subjectController = new SubjectControllerImp(new CEMS_DbContext());
    private Alert alert;
    @FXML
    private Label nameLabel;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab addUserTab;
    @FXML
    private Tab updateUserTab;
    @FXML
    private Tab deleteUserTab;
    @FXML
    private Tab listSearchUsersTab;
    @FXML
    private Tab addSubjectTab;
    @FXML
    private Tab deleteSubjectTab;
    @FXML
    private Tab assignSubjectTab;
    @FXML
    private Button updateUserButton;
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField emailField1;
    @FXML
    private ComboBox<Enum> genderField;
    @FXML
    private ComboBox<Enum> typeField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField passwordField1;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField usernameField1;
    @FXML
    private TextField usernameField2;
    @FXML
    private TextField usernameField3;
    @FXML
    private Button searchButton;
    @FXML
    private TextField searchField;
    @FXML
    private Button searchButton1;
    @FXML
    private TextField searchField1;
    @FXML
    private TextField currentUsernameField;
    @FXML
    private TextField codeField;
    @FXML
    private TextField codeField1;
    @FXML
    private TextField codeField2;
    @FXML
    private ComboBox<Enum> columnField;
    @FXML
    private ComboBox<String> operationField;
    @FXML
    private ComboBox<Enum> columnField1;
    @FXML
    private ComboBox<String> operationField1;
    @FXML
    private TextArea descriptionField;
    @FXML
    private TableView<Map> tableView;
    @FXML
    private TextField subjectNameField;
    @FXML
    private TableColumn<Map, Enum> emailTableColumn;
    @FXML
    private TableColumn<Map, Enum> genderTableColumn;
    @FXML
    private TableColumn<Map, Enum> idTableColumn;
    @FXML
    private TableColumn<Map, Enum> nameTableColumn;
    @FXML
    private TableColumn<Map, Enum> passwordTableColumn;
    @FXML
    private TableColumn<Map, Enum> typeTableColumn;
    @FXML
    private TableColumn<Map, Enum> usernameTableColumn;
    @FXML
    private Tab listSearchSubjectsTab;
    @FXML
    private TableView<Map> tableView1;
    @FXML
    private TableColumn<Map, Enum> descriptionTableColumn;
    @FXML
    private TableColumn<Map, Enum> codeTableColumn;
    @FXML
    private TableColumn<Map, Enum> nameTableColumn1;

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
     * On add user button action.
     *
     * @param event the event
     */
    @FXML
    void onAddUserButtonAction(ActionEvent event) {
        alert = new Alert(Alert.AlertType.ERROR);
        new AddUser().add(event, alert, userController, nameField, emailField, usernameField, passwordField,
                genderField, typeField);
    }

    /**
     * On update user button action.
     *
     * @param event the event
     */
    @FXML
    void onUpdateUserButtonAction(ActionEvent event) {
        alert = new Alert(Alert.AlertType.ERROR);
        new UpdateUser().update(event, alert, userController, currentUsernameField, usernameField1,
                emailField1, passwordField1, this.username);
    }

    /**
     * On current username button action.
     *
     * @param event the event
     */
    @FXML
    void onCurrentUsernameButtonAction(ActionEvent event) {
        alert = new Alert(Alert.AlertType.ERROR);
        new UpdateUser().setUserData(event, alert, userController, currentUsernameField, emailField1, usernameField1,
                passwordField1, updateUserButton);
    }

    /**
     * On current username field key press.
     *
     * @param event the event
     */
    @FXML
    void onCurrentUsernameFieldKeyPress(KeyEvent event) {
        new UpdateUser().clearUpdateElements(emailField1, usernameField1, passwordField1);
        new UpdateUser().setUpdateElementsStatus(emailField1, usernameField1, passwordField1, updateUserButton, true);
    }

    /**
     * On delete user button action.
     *
     * @param event the event
     */
    @FXML
    void onDeleteUserButtonAction(ActionEvent event) {
        alert = new Alert(Alert.AlertType.ERROR);
        new DeleteUser().delete(event, alert, userController, usernameField2, this.username);
    }

    /**
     * On add subject button action.
     *
     * @param event the event
     */
    @FXML
    void onAddSubjectButtonAction(ActionEvent event) {
        alert = new Alert(Alert.AlertType.ERROR);
        new AddSubject().add(event, alert, subjectController, subjectNameField, codeField, descriptionField);
    }

    /**
     * On delete subject button action.
     *
     * @param event the event
     */
    @FXML
    void onDeleteSubjectButtonAction(ActionEvent event) {
        alert = new Alert(Alert.AlertType.ERROR);
        new DeleteSubject().delete(event, alert, subjectController, codeField1);
    }

    /**
     * On assign button action.
     *
     * @param event the event
     */
    @FXML
    void onAssignButtonAction(ActionEvent event) {
        alert = new Alert(Alert.AlertType.ERROR);
        new AssignSubject().assign(event, alert, userController, subjectController, usernameField3, codeField2);
    }

    /**
     * On select operation action.
     *
     * @param event the event
     */
    @FXML
    void onSelectOperationAction(ActionEvent event) {
        new ListAndSearchUsers().list(userController, operationField, tableView, searchField,
                columnField, searchButton);
    }

    /**
     * On select operation 1 action.
     *
     * @param event the event
     */
    @FXML
    void onSelectOperation1Action(ActionEvent event) {
        new ListAndSearchSubjects().list(subjectController, operationField1, tableView1, searchField1,
                columnField1, searchButton1);
    }

    /**
     * On search button action.
     *
     * @param event the event
     */
    @FXML
    void onSearchButtonAction(ActionEvent event) {
        alert = new Alert(Alert.AlertType.ERROR);
        new ListAndSearchUsers().search(event, alert, userController, tableView, searchField, columnField);
    }

    /**
     * On search button 1 action.
     *
     * @param event the event
     */
    @FXML
    void onSearchButton1Action(ActionEvent event) {
        alert = new Alert(Alert.AlertType.ERROR);
        new ListAndSearchSubjects().search(event, alert, subjectController, tableView1, searchField1, columnField1);
    }

    /**
     * On list search users tab selection.
     *
     * @param event the event
     */
    @FXML
    void onListSearchUsersTabSelection(Event event) {
        if(listSearchUsersTab.isSelected())
            new ListAndSearchUsers().resetTabView(operationField, tableView, searchField, columnField, searchButton);
    }

    /**
     * On list search subjects tab selection.
     *
     * @param event the event
     */
    @FXML
    void onListSearchSubjectsTabSelection(Event event) {
        if(listSearchSubjectsTab.isSelected())
            new ListAndSearchSubjects().resetTabView(operationField1, tableView1, searchField1, columnField1, searchButton1);
    }

    /**
     * Initialize list search subjects table.
     */
    void initializeListSearchSubjectsTable(){
        codeTableColumn.setCellValueFactory(new MapValueFactory<>(Column.Code));
        nameTableColumn1.setCellValueFactory(new MapValueFactory<>(Column.Name));
        descriptionTableColumn.setCellValueFactory(new MapValueFactory<>(Column.Description));

        tableView1.getColumns().setAll(Arrays.asList(codeTableColumn, nameTableColumn1, descriptionTableColumn));
    }

    /**
     * Initialize list search users table.
     */
    void initializeListSearchUsersTable(){
        idTableColumn.setCellValueFactory(new MapValueFactory<>(Column.ID));
        nameTableColumn.setCellValueFactory(new MapValueFactory<>(Column.Name));
        emailTableColumn.setCellValueFactory(new MapValueFactory<>(Column.Email));
        genderTableColumn.setCellValueFactory(new MapValueFactory<>(Column.Gender));
        usernameTableColumn.setCellValueFactory(new MapValueFactory<>(Column.Username));
        passwordTableColumn.setCellValueFactory(new MapValueFactory<>(Column.Password));
        typeTableColumn.setCellValueFactory(new MapValueFactory<>(Column.Type));

        tableView.getColumns().setAll(Arrays.asList(idTableColumn, nameTableColumn, emailTableColumn,
                genderTableColumn, usernameTableColumn, passwordTableColumn, typeTableColumn));
    }

    @Override
    public void setNameOfUser(String name) {
        this.name = name;
        nameLabel.setText(name);
    }

    @Override
    public void setUsername(StringBuilder username) {
        this.username = username;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        genderField.getItems().setAll(Arrays.asList(Gender.values()));
        genderField.getSelectionModel().selectFirst();

        typeField.getItems().setAll(Arrays.asList(Type.values()));
        typeField.getSelectionModel().selectFirst();

        List<String> operations = Arrays.asList("Search", "List");

        operationField.getItems().setAll(operations);
        operationField.getSelectionModel().selectFirst();

        operationField1.getItems().setAll(operations);
        operationField1.getSelectionModel().selectFirst();

        columnField.getItems().setAll(Arrays.asList(Column.ID, Column.Name, Column.Email, Column.Gender,
                Column.Username, Column.Password, Column.Type));
        columnField.getSelectionModel().selectFirst();

        columnField1.getItems().setAll(Arrays.asList(Column.Code, Column.Name, Column.Description));
        columnField1.getSelectionModel().selectFirst();

        initializeListSearchUsersTable();
        initializeListSearchSubjectsTable();

        tabPane.getTabs().setAll(addUserTab, updateUserTab, deleteUserTab, listSearchUsersTab, addSubjectTab,
                listSearchSubjectsTab, deleteSubjectTab, assignSubjectTab);
    }
}
