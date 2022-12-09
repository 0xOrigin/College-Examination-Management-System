package CEMS.Views.ViewController.Validator;

import CEMS.Controllers.SubjectController;
import CEMS.Views.Utilities;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class SubjectValidator extends Utilities {

    public boolean validateCode(TextField codeField, Alert alert, SubjectController controller){
        String alertTitle = "Code field";
        String code = codeField.getText().trim();
        if(code.isBlank()) {
            handleAlert(alert, alertTitle, "Code field is required and cannot be blank!", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    public boolean checkSubjectExistence(TextField codeField, boolean registerVal, Alert alert, SubjectController controller){
        String alertTitle = "Code field";
        String code = codeField.getText().trim();
        if(registerVal && controller.isSubjectRegistered(code)){
            handleAlert(alert, alertTitle, "This subject has been registered before!", Alert.AlertType.ERROR);
            return false;
        }
        else if(!registerVal && !controller.isSubjectRegistered(code)){
            handleAlert(alert, alertTitle, "This subject not found!", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    public boolean validateName(TextField nameField, Alert alert, SubjectController controller){
        String alertTitle = "Name field";
        String name = nameField.getText().trim();
        if(name.isBlank()) {
            handleAlert(alert, alertTitle, "Name field is required and cannot be blank!", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

}
