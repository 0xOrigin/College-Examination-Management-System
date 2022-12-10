package CEMS.Views.ViewController.Tabs;

import CEMS.Controllers.ExamController;
import CEMS.Models.Enum.Column;
import CEMS.Views.Utilities;
import CEMS.Views.ViewController.Validators.ExamValidator;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import java.util.Arrays;
import java.util.Map;

public class UpdateExam extends Utilities {

    ExamValidator examValidator = new ExamValidator();

    public void update(ActionEvent event, Alert alert, ExamController controller, TextField examIdField,
                       ComboBox<String> selectSubjectField, TextField examNameField, Spinner<Integer> durationField){

        super.setAlertOwner(event, alert);
        boolean generalState;

        String examID = examIdField.getText().trim();
        String currentExamName = controller.getExamInfo(examID, Arrays.asList(Column.Name)).get(Column.Name).toString();
        String examName = examNameField.getText().trim();
        int duration = durationField.getValue();

        generalState = examValidator.validateExamID(examIdField, selectSubjectField, alert, controller);
        if(!currentExamName.equals(examName))
            generalState &= examValidator.checkExamNameAvailability(examNameField, alert, controller);
        generalState &= examValidator.validateExamName(examNameField, alert, controller);

        if(generalState){
            controller.update(Arrays.asList(Column.Name, Column.Duration), Arrays.asList(examName, duration), examID);
            handleAlert(alert, "Successful Update",
                    "The exam has been successfully updated.",
                    Alert.AlertType.INFORMATION);
        }
    }

    public void setExamData(ActionEvent event, Alert alert, ExamController controller, TextField examIdField,
                            ComboBox<String> selectSubjectField, TextField examNameField, Spinner<Integer> durationField,
                            Button updateButton){

        super.setAlertOwner(event, alert);
        boolean generalState;

        generalState = examValidator.validateExamID(examIdField, selectSubjectField, alert, controller);

        if(generalState){
            Map<Enum, Object> examInfo = controller.getExamInfo(examIdField.getText().trim(),
                    Arrays.asList(Column.Name, Column.Duration));
            examNameField.setText(examInfo.get(Column.Name).toString());
            durationField.getValueFactory().setValue((Integer) examInfo.get(Column.Duration));
            setUpdateElementsStatus(examNameField, durationField, updateButton, false);
        }
    }

    public void setUpdateElementsStatus(TextField examNameField, Spinner<Integer> durationField,
                                        Button updateButton, boolean disable){
        examNameField.setDisable(disable);
        durationField.setDisable(disable);
        updateButton.setDisable(disable);
    }

    public void clearUpdateElements(TextField examNameField, Spinner<Integer> durationField){
        examNameField.clear();
        durationField.getValueFactory().setValue(10);
    }
}
