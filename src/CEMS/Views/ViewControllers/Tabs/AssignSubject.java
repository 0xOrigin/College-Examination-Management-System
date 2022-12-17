package CEMS.Views.ViewControllers.Tabs;

import CEMS.Controllers.SubjectController;
import CEMS.Controllers.UserController;
import CEMS.Views.Utilities;
import CEMS.Views.ViewControllers.Validators.SubjectValidator;
import CEMS.Views.ViewControllers.Validators.UserValidator;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * The class Assign subject.
 */
public class AssignSubject extends Utilities {

    UserValidator userValidator = new UserValidator();
    SubjectValidator subjectValidator = new SubjectValidator();

    /**
     * Assign.
     *
     * @param event             the event
     * @param alert             the alert
     * @param userController    the user controller
     * @param subjectController the subject controller
     * @param usernameField     the username field
     * @param codeField         the code field
     */
    public void assign(ActionEvent event, Alert alert, UserController userController, SubjectController subjectController,
                       TextField usernameField, TextField codeField){

        super.setAlertOwner(event, alert);
        boolean generalState;

        generalState = subjectValidator.validateCode(codeField, alert, subjectController);
        generalState &= subjectValidator.checkSubjectExistence(codeField, false, alert, subjectController);
        generalState &= userValidator.validateUsername(usernameField, alert, userController);
        generalState &= userValidator.checkUsernameAvailability(usernameField, false, alert, userController);
        generalState &= userValidator.checkUserTypeBeforeAssign(usernameField, alert, userController);
        generalState &= userValidator.checkIfAssignedBefore(usernameField, codeField, alert, userController);

        if(generalState){
            userController.assignSubject(usernameField.getText().trim(), codeField.getText().trim());
            handleAlert(alert, "Successful Operation",
                    "The subject has been registered to this user.",
                    Alert.AlertType.INFORMATION);
        }
    }
}
