package CEMS.Views.ViewControllers.Tabs;

import CEMS.Controllers.ExamController;
import CEMS.Views.Utilities;
import CEMS.Views.ViewControllers.Validators.ExamValidator;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;


/**
 * The class Add exam.
 */
public class AddExam extends Utilities {

    ExamValidator examValidator = new ExamValidator();

    /**
     * Add.
     *
     * @param event              the event
     * @param alert              the alert
     * @param controller         the controller
     * @param selectSubjectField the select subject field
     * @param examNameField      the exam name field
     * @param durationField      the duration field
     */
    public void add(ActionEvent event, Alert alert, ExamController controller, ComboBox<String> selectSubjectField,
                    TextField examNameField, Spinner<Integer> durationField){

        super.setAlertOwner(event, alert);
        boolean generalState;

        generalState = examValidator.validateExamName(examNameField, alert, controller);
        generalState &= examValidator.checkExamNameAvailability(examNameField, alert, controller);

        if(generalState){
            controller.add(selectSubjectField.getSelectionModel().getSelectedItem(), examNameField.getText().trim(),
                    durationField.getValue());
            handleAlert(alert, "Successful Operation",
                    "The exam has been successfully added.",
                    Alert.AlertType.INFORMATION);
        }
    }

}
