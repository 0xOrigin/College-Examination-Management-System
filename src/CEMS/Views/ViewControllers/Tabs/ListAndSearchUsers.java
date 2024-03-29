package CEMS.Views.ViewControllers.Tabs;

import CEMS.Controllers.UserController;
import CEMS.Views.Utilities;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.util.List;
import java.util.Map;

/**
 * The class List and search users.
 */
public class ListAndSearchUsers extends Utilities {

    /**
     * Search.
     *
     * @param event       the event
     * @param alert       the alert
     * @param controller  the controller
     * @param tableView   the table view
     * @param searchField the search field
     * @param columnField the column field
     */
    public void search(ActionEvent event, Alert alert, UserController controller, TableView<Map> tableView,
                       TextField searchField, ComboBox<Enum> columnField){

        if(searchField.getText().isBlank()){
            super.setAlertOwner(event, alert);
            handleAlert(alert, "Search field", "Search field is required and cannot be blank!", Alert.AlertType.ERROR);
            return;
        }
        super.clearTableView(tableView);
        List<Map<Enum, Object>> users = controller.searchBy(columnField.getValue(), searchField.getText().trim());
        for(Map<Enum, Object> user : users){
            tableView.getItems().add(user);
        }
    }

    /**
     * List.
     *
     * @param controller     the controller
     * @param operationField the operation field
     * @param tableView      the table view
     * @param searchField    the search field
     * @param columnField    the column field
     * @param searchButton   the search button
     */
    public void list(UserController controller, ComboBox<String> operationField, TableView<Map> tableView,
                     TextField searchField, ComboBox<Enum> columnField, Button searchButton){

        super.clearTableView(tableView);

        if(operationField.getSelectionModel().getSelectedItem().equals("List")){
            setSearchElementsStatus(searchField, columnField, searchButton, true);
            List<Map<Enum, Object>> users = controller.getAllUsers();
            for(Map<Enum, Object> user : users){
                tableView.getItems().add(user);
            }
        }
        else {
            setSearchElementsStatus(searchField, columnField, searchButton, false);
        }
    }

    /**
     * Set search elements status.
     *
     * @param searchField  the search field
     * @param columnField  the column field
     * @param searchButton the search button
     * @param disable      the disable determination
     */
    public void setSearchElementsStatus(TextField searchField, ComboBox<Enum> columnField, Button searchButton, boolean disable){
        if(disable)
            searchField.clear();
        columnField.setDisable(disable);
        searchButton.setDisable(disable);
        searchField.setDisable(disable);
    }

    /**
     * Reset tab view.
     *
     * @param operationField the operation field
     * @param tableView      the table view
     * @param searchField    the search field
     * @param columnField    the column field
     * @param searchButton   the search button
     */
    public void resetTabView(ComboBox<String> operationField, TableView<Map> tableView,
                             TextField searchField, ComboBox<Enum> columnField, Button searchButton){

        searchField.clear();
        super.clearTableView(tableView);
        operationField.getSelectionModel().selectFirst();
        setSearchElementsStatus(searchField, columnField, searchButton, false);
    }
}
