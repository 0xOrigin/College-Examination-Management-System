package CEMS.Views.ViewControllers.Tabs;

import CEMS.Controllers.SubjectController;
import CEMS.Views.Utilities;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

import java.util.List;
import java.util.Map;

public class ShowReport extends Utilities {

    public void show(SubjectController controller, TableView<Map> tableView, ComboBox<String> selectSubjectField){

        super.clearTableView(tableView);

        List<Map<Enum, Object>> rows = controller.getReportForSubject(selectSubjectField.getSelectionModel().getSelectedItem());
        for(Map<Enum, Object> row : rows){
            tableView.getItems().add(row);
        }
    }

    public void resetTabView(ComboBox<String> selectSubjectField, TableView<Map> tableView){
        super.clearTableView(tableView);
        selectSubjectField.getSelectionModel().selectFirst();
    }

}
