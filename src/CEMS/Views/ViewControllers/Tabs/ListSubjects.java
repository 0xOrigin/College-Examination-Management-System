package CEMS.Views.ViewControllers.Tabs;

import CEMS.Controllers.SubjectController;
import CEMS.Views.Utilities;
import javafx.scene.control.TableView;

import java.util.List;
import java.util.Map;

public class ListSubjects extends Utilities {

    public void list(SubjectController controller, TableView<Map> tableView){
        List<Map<Enum, Object>> exams = controller.getAllSubjects();
        for(Map<Enum, Object> exam : exams){
            tableView.getItems().add(exam);
        }
    }

    public void clearTableView(TableView<Map> tableView){
        tableView.getItems().clear();
    }
}
