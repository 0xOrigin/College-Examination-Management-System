package CEMS.Views.ViewControllers.Tabs;

import CEMS.Controllers.UserController;
import CEMS.Views.Utilities;
import javafx.scene.control.TableView;

import java.util.List;
import java.util.Map;

/**
 * The class List exams.
 */
public class ListExams extends Utilities {

    /**
     * List.
     *
     * @param controller the controller
     * @param tableView  the table view
     * @param username   the username
     */
    public void list(UserController controller, TableView<Map> tableView, String username){

        super.clearTableView(tableView);

        List<Map<Enum, Object>> exams = controller.getAllExamsFor(username);
        for(Map<Enum, Object> exam : exams){
            tableView.getItems().add(exam);
        }
    }

}
