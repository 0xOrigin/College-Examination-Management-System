package CEMS.Views.ViewControllers.Validators;

import CEMS.Controllers.ExamController;
import CEMS.Views.Utilities;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * The class Exam validator.
 */
public class ExamValidator extends Utilities {

    /**
     * Validate exam name.
     *
     * @param examNameField the exam name field
     * @param alert         the alert
     * @param controller    the controller
     * @return the boolean
     */
    public boolean validateExamName(TextField examNameField, Alert alert, ExamController controller){
        String alertTitle = "Exam name field";
        String examName = examNameField.getText().trim();
        if(examName.isBlank()) {
            handleAlert(alert, alertTitle, "Exam name field is required and cannot be blank!", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    /**
     * Check exam name availability.
     *
     * @param examNameField the exam name field
     * @param alert         the alert
     * @param controller    the controller
     * @return the boolean
     */
    public boolean checkExamNameAvailability(TextField examNameField, Alert alert, ExamController controller){
        String alertTitle = "Exam name field";
        String examName = examNameField.getText().trim();
        if(!controller.isNameAvailable(examName)){
            handleAlert(alert, alertTitle, "There is another exam with this name, choose another name!", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    /**
     * Validate exam id.
     *
     * @param examIdField        the exam id field
     * @param selectSubjectField the select subject field
     * @param alert              the alert
     * @param controller         the controller
     * @return the boolean
     */
    public boolean validateExamID(TextField examIdField, ComboBox<String> selectSubjectField, Alert alert, ExamController controller){
        String alertTitle = "Exam ID field";
        String examID = examIdField.getText().trim();
        String subjectCode = selectSubjectField.getSelectionModel().getSelectedItem();
        if(examID.isBlank()) {
            handleAlert(alert, alertTitle, "Exam ID field is required and cannot be blank!", Alert.AlertType.ERROR);
            return false;
        }
        else if(!controller.isExamBelongsToSubject(examID, subjectCode)){
            handleAlert(alert, alertTitle, "This exam not found, or doesn't belong to this subject!", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

}
