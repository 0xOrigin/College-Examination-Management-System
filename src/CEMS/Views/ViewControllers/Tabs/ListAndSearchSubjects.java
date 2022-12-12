package CEMS.Views.ViewControllers.Tabs;

import CEMS.Controllers.SubjectController;
import CEMS.Views.Utilities;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.util.List;
import java.util.Map;

public class ListAndSearchSubjects extends Utilities {

    public void search(ActionEvent event, Alert alert, SubjectController controller, TableView<Map> tableView,
                       TextField searchField, ComboBox<Enum> columnField){

        if(searchField.getText().isBlank()){
            super.setAlertOwner(event, alert);
            handleAlert(alert, "Search field", "Search field is required and cannot be blank!", Alert.AlertType.ERROR);
            return;
        }
        clearTableView(tableView);
        List<Map<Enum, Object>> users = controller.searchBy(columnField.getValue(), searchField.getText().trim());
        for(Map<Enum, Object> user : users){
            tableView.getItems().add(user);
        }
    }

    public void list(SubjectController controller, ComboBox<String> operationField, TableView<Map> tableView,
                     TextField searchField, ComboBox<Enum> columnField, Button searchButton){

        clearTableView(tableView);

        if(operationField.getSelectionModel().getSelectedItem().equals("List")){
            setSearchElementsStatus(searchField, columnField, searchButton, true);
            List<Map<Enum, Object>> subjects = controller.getAllSubjects();
            for(Map<Enum, Object> subject : subjects){
                tableView.getItems().add(subject);
            }
        }
        else {
            setSearchElementsStatus(searchField, columnField, searchButton, false);
        }
    }

    public void clearTableView(TableView<Map> tableView){
        tableView.getItems().clear();
    }

    public void setSearchElementsStatus(TextField searchField, ComboBox<Enum> columnField, Button searchButton, boolean disable){
        if(disable)
            searchField.clear();
        columnField.setDisable(disable);
        searchButton.setDisable(disable);
        searchField.setDisable(disable);
    }

    public void resetTabView(ComboBox<String> operationField, TableView<Map> tableView,
                             TextField searchField, ComboBox<Enum> columnField, Button searchButton){

        searchField.clear();
        clearTableView(tableView);
        operationField.getSelectionModel().selectFirst();
        setSearchElementsStatus(searchField, columnField, searchButton, false);
    }

}
