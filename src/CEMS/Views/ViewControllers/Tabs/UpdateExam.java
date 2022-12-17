package CEMS.Views.ViewControllers.Tabs;

import CEMS.Controllers.ExamController;
import CEMS.Models.Enum.Column;
import CEMS.Views.Utilities;
import CEMS.Views.ViewControllers.Validators.ExamValidator;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import java.util.Arrays;
import java.util.Map;

/**
 * The class Update exam.
 */
public class UpdateExam extends Utilities {

    ExamValidator examValidator = new ExamValidator();

    /**
     * Update.
     *
     * @param event              the event
     * @param alert              the alert
     * @param controller         the controller
     * @param examIdField        the exam id field
     * @param selectSubjectField the select subject field
     * @param examNameField      the exam name field
     * @param durationField      the duration field
     */
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

    /**
     * Set exam data.
     *
     * @param event              the event
     * @param alert              the alert
     * @param controller         the controller
     * @param examIdField        the exam id field
     * @param selectSubjectField the select subject field
     * @param examNameField      the exam name field
     * @param durationField      the duration field
     * @param updateButton       the update button
     */
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

    /**
     * Set update elements status.
     *
     * @param examNameField the exam name field
     * @param durationField the duration field
     * @param updateButton  the update button
     * @param disable       the disable determination
     */
    public void setUpdateElementsStatus(TextField examNameField, Spinner<Integer> durationField,
                                        Button updateButton, boolean disable){
        examNameField.setDisable(disable);
        durationField.setDisable(disable);
        updateButton.setDisable(disable);
    }

    /**
     * Clear update elements.
     *
     * @param examNameField the exam name field
     * @param durationField the duration field
     */
    public void clearUpdateElements(TextField examNameField, Spinner<Integer> durationField){
        examNameField.clear();
        durationField.getValueFactory().setValue(10);
    }
}
