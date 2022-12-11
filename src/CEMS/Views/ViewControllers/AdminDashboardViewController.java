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
import java.util.Map;
import java.util.ResourceBundle;

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
    private Tab listSubjectsTab;
    @FXML
    private TableView<Map> tableView1;
    @FXML
    private TableColumn<Map, Enum> descriptionTableColumn;
    @FXML
    private TableColumn<Map, Enum> codeTableColumn;
    @FXML
    private TableColumn<Map, Enum> nameTableColumn1;

    @FXML
    void onLogoutButtonAction(ActionEvent event) {
        super.changeScene(event, "LoginView.fxml", "College Examination Management System");
    }

    @FXML
    void onAddUserButtonAction(ActionEvent event) {
        alert = new Alert(Alert.AlertType.ERROR);
        new AddUser().add(event, alert, userController, nameField, emailField, usernameField, passwordField,
                genderField, typeField);
    }

    @FXML
    void onUpdateUserButtonAction(ActionEvent event) {
        alert = new Alert(Alert.AlertType.ERROR);
        new UpdateUser().update(event, alert, userController, currentUsernameField, usernameField1,
                emailField1, passwordField1, this.username);
    }

    @FXML
    void onCurrentUsernameButtonAction(ActionEvent event) {
        alert = new Alert(Alert.AlertType.ERROR);
        new UpdateUser().setUserData(event, alert, userController, currentUsernameField, emailField1, usernameField1,
                passwordField1, updateUserButton);
    }

    @FXML
    void onCurrentUsernameFieldKeyPress(KeyEvent event) {
        new UpdateUser().clearUpdateElements(emailField1, usernameField1, passwordField1);
        new UpdateUser().setUpdateElementsStatus(emailField1, usernameField1, passwordField1, updateUserButton, true);
    }

    @FXML
    void onDeleteUserButtonAction(ActionEvent event) {
        alert = new Alert(Alert.AlertType.ERROR);
        new DeleteUser().delete(event, alert, userController, usernameField2, this.username);
    }

    @FXML
    void onAddSubjectButtonAction(ActionEvent event) {
        alert = new Alert(Alert.AlertType.ERROR);
        new AddSubject().add(event, alert, subjectController, subjectNameField, codeField, descriptionField);
    }

    @FXML
    void onDeleteSubjectButtonAction(ActionEvent event) {
        alert = new Alert(Alert.AlertType.ERROR);
        new DeleteSubject().delete(event, alert, subjectController, codeField1);
    }

    @FXML
    void onAssignButtonAction(ActionEvent event) {
        alert = new Alert(Alert.AlertType.ERROR);
        new AssignSubject().assign(event, alert, userController, subjectController, usernameField3, codeField2);
    }

    @FXML
    void onSelectOperationAction(ActionEvent event) {
        new ListAndSearchUsers().list(userController, operationField, tableView, searchField,
                columnField, searchButton);
    }

    @FXML
    void onSearchButtonAction(ActionEvent event) {
        alert = new Alert(Alert.AlertType.ERROR);
        new ListAndSearchUsers().search(event, alert, userController, tableView, searchField, columnField);
    }

    @FXML
    void onListSearchTabSelection(Event event) {
        if(listSearchUsersTab.isSelected())
            new ListAndSearchUsers().resetTabView(operationField, tableView, searchField, columnField, searchButton);
    }

    @FXML
    void onListSubjectsTabSelection(Event event) {
        if(listSubjectsTab.isSelected()){
            new ListSubjects().clearTableView(tableView1);
            new ListSubjects().list(subjectController, tableView1);
        }
    }

    void initializeListSubjectsTable(){
        codeTableColumn.setCellValueFactory(new MapValueFactory<>(Column.Code));
        nameTableColumn1.setCellValueFactory(new MapValueFactory<>(Column.Name));
        descriptionTableColumn.setCellValueFactory(new MapValueFactory<>(Column.Description));

        tableView1.getColumns().setAll(Arrays.asList(codeTableColumn, nameTableColumn1, descriptionTableColumn));
    }

    void initializeListSearchTable(){
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
        operationField.getItems().setAll("Search", "List");
        operationField.getSelectionModel().selectFirst();
        columnField.getItems().setAll(Arrays.asList(Column.ID, Column.Name, Column.Email, Column.Gender,
                Column.Username, Column.Password, Column.Type));
        columnField.getSelectionModel().selectFirst();

        initializeListSearchTable();
        initializeListSubjectsTable();

        tabPane.getTabs().setAll(addUserTab, updateUserTab, deleteUserTab, listSearchUsersTab, addSubjectTab,
                listSubjectsTab, deleteSubjectTab, assignSubjectTab);
    }
}
