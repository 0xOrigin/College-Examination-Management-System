package CEMS.Views.ViewControllers.Tabs;

import CEMS.Controllers.SubjectController;
import CEMS.Views.Utilities;
import CEMS.Views.ViewControllers.Validators.SubjectValidator;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * The class Add subject.
 */
public class AddSubject extends Utilities {

    SubjectValidator subjectValidator = new SubjectValidator();

    /**
     * Add.
     *
     * @param event            the event
     * @param alert            the alert
     * @param controller       the controller
     * @param subjectNameField the subject name field
     * @param codeField        the code field
     * @param descriptionField the description field
     */
    public void add(ActionEvent event, Alert alert, SubjectController controller, TextField subjectNameField,
                    TextField codeField, TextArea descriptionField){
        
        super.setAlertOwner(event, alert);
        boolean generalState;

        generalState = subjectValidator.validateName(subjectNameField, alert, controller);
        generalState &= subjectValidator.validateCode(codeField, alert, controller);
        generalState &= subjectValidator.checkSubjectExistence(codeField, true, alert, controller);

        if(generalState){
            controller.register(codeField.getText().trim(), subjectNameField.getText().trim(),
                    descriptionField.getText().trim());
            handleAlert(alert, "Successful Operation",
                    "The subject has been successfully registered.",
                    Alert.AlertType.INFORMATION);
        }
    }

}
