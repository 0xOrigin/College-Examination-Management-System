package CEMS.Views.ViewControllers.Tabs;

import CEMS.Controllers.SubjectController;
import CEMS.Views.Utilities;
import CEMS.Views.ViewControllers.Validators.SubjectValidator;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * The class Delete subject.
 */
public class DeleteSubject extends Utilities {

    SubjectValidator subjectValidator = new SubjectValidator();

    /**
     * Delete.
     *
     * @param event      the event
     * @param alert      the alert
     * @param controller the controller
     * @param codeField  the code field
     */
    public void delete(ActionEvent event, Alert alert, SubjectController controller, TextField codeField){

        super.setAlertOwner(event, alert);
        boolean generalState;

        generalState = subjectValidator.validateCode(codeField, alert, controller);
        generalState &= subjectValidator.checkSubjectExistence(codeField, false, alert, controller);

        if(generalState){
            controller.delete(codeField.getText().trim());
            handleAlert(alert, "Successful Operation",
                    "The subject has been successfully deleted.",
                    Alert.AlertType.INFORMATION);
        }
    }
}
