package CEMS.Views.ViewControllers.Tabs;

import CEMS.Controllers.UserController;
import CEMS.Views.Utilities;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.Map;

public class ListAndSearchUsers extends Utilities {

    public void search(UserController controller, TableView<Map> tableView, TextField searchField, ComboBox<Enum> columnField){
        clearTableView(tableView);
        List<Map<Enum, Object>> users = controller.searchBy(columnField.getValue(), searchField.getText().trim());
        for(Map<Enum, Object> user : users){
            tableView.getItems().add(user);
        }
    }

    public void list(UserController controller, ComboBox<String> operationField, TableView<Map> tableView,
                     TextField searchField, ComboBox<Enum> columnField, Button searchButton){

        if(operationField.getSelectionModel().getSelectedItem().equals("List")){
            setSearchElementsStatus(searchField, columnField, searchButton, true);
            List<Map<Enum, Object>> users = controller.getAllUsers();
            for(Map<Enum, Object> user : users){
                tableView.getItems().add(user);
            }
        }
        else {
            clearTableView(tableView);
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
