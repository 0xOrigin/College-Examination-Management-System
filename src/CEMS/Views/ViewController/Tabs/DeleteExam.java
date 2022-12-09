package CEMS.Views.ViewController.Tabs;

import CEMS.Controllers.ExamController;
import CEMS.Views.Utilities;
import CEMS.Views.ViewController.Validator.ExamValidator;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class DeleteExam extends Utilities {

    ExamValidator examValidator = new ExamValidator();

    public void delete(ActionEvent event, Alert alert, ExamController controller, ComboBox<String> selectSubjectField,
                       TextField examIdField){

        super.setAlertOwner(event, alert);
        boolean generalState;

        String examID = examIdField.getText().trim();

        generalState = examValidator.validateExamID(examIdField, selectSubjectField, alert, controller);

        if(generalState){
            controller.delete(examID);
            handleAlert(alert, "Successful Operation",
                    "The exam has been successfully deleted.",
                    Alert.AlertType.INFORMATION);
        }
    }

}
