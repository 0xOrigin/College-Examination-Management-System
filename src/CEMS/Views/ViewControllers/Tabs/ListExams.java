package CEMS.Views.ViewControllers.Tabs;

import CEMS.Controllers.UserController;
import CEMS.Views.Utilities;
import javafx.scene.control.TableView;

import java.util.List;
import java.util.Map;

public class ListExams extends Utilities {

    public void list(UserController controller, TableView<Map> tableView, String username){
        List<Map<Enum, Object>> exams = controller.getAllExamsFor(username);
        for(Map<Enum, Object> exam : exams){
            tableView.getItems().add(exam);
        }
    }

    public void clearTableView(TableView<Map> tableView){
        tableView.getItems().clear();
    }
}
