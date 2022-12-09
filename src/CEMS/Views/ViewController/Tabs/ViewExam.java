package CEMS.Views.ViewController.Tabs;

import CEMS.Controllers.ExamController;
import CEMS.Controllers.QuestionController;
import CEMS.Models.Enum.Column;
import CEMS.Views.Utilities;
import CEMS.Views.ViewController.Validator.ExamValidator;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ViewExam extends Utilities {

    ExamValidator examValidator = new ExamValidator();

    public void view(ActionEvent event, Alert alert, ExamController examController, QuestionController questionController,
                     ComboBox<String> selectSubjectField, TextField examIdField, TextField examNameField,
                     TextField showDurationField, TableView<Map> tableView){

        super.setAlertOwner(event, alert);
        boolean generalState;

        String examID = examIdField.getText().trim();

        generalState = examValidator.validateExamID(examIdField, selectSubjectField, alert, examController);

        if(generalState){
            Map<Enum, Object> examInfo = examController.getExamInfo(examIdField.getText().trim(),
                    Arrays.asList(Column.Name, Column.Duration));
            examNameField.setText(examInfo.get(Column.Name).toString());
            showDurationField.setText(examInfo.get(Column.Duration).toString());

            List<Map<Enum, Object>> questions = questionController.getAllQuestionsFor(examID);
            for(Map<Enum, Object> question : questions)
                tableView.getItems().add(question);
        }
    }

    public void clearTableView(TableView<Map> tableView){
        tableView.getItems().clear();
    }

    public void resetTabView(ComboBox<String> selectSubjectField, TextField examIdField, TextField examNameField,
                             TextField showDurationField, TableView<Map> tableView){

        clearTableView(tableView);
        selectSubjectField.getSelectionModel().selectFirst();
        examIdField.clear();
        examNameField.clear();
        showDurationField.clear();
    }
}
