package CEMS.Views.ViewControllers.Tabs;

import CEMS.Controllers.QuestionController;
import CEMS.Models.Enum.Column;
import CEMS.Views.Utilities;
import CEMS.Views.ViewControllers.Validators.QuestionValidator;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class UpdateQuestion extends Utilities {

    QuestionValidator questionValidator = new QuestionValidator();

    public void update(ActionEvent event, Alert alert, QuestionController controller, TextField questionIdField,
                       String username, TextField contentField, TextField choice1Field, TextField choice2Field,
                       TextField choice3Field, TextField choice4Field, TextField choice5Field,
                       ComboBox<String> correctAnswerSelector){

        super.setAlertOwner(event, alert);
        boolean generalState;

        generalState = questionValidator.validateCorrectAnswer(correctAnswerSelector, alert);
        generalState &= questionValidator.validateChoice(choice5Field, alert, 5);
//        generalState &= questionValidator.validateChoice(choice4Field, alert, 4);
//        generalState &= questionValidator.validateChoice(choice3Field, alert, 3);
//        generalState &= questionValidator.validateChoice(choice2Field, alert, 2);
        generalState &= questionValidator.validateChoice(choice1Field, alert, 1);
        generalState &= questionValidator.validateQuestionContent(contentField, alert);
        generalState &= questionValidator.validateQuestionID(questionIdField, alert, controller, username);

        if(generalState){
            String correctAnswer = getCorrectAnswer(correctAnswerSelector, choice1Field, choice2Field, choice3Field, choice4Field, choice5Field);

            List<Enum> fields = Arrays.asList(Column.Content, Column.Choice1, Column.Choice2,
                    Column.Choice3, Column.Choice4, Column.Choice5, Column.CorrectAnswer);
            List<Object> values = Arrays.asList(contentField.getText().trim(), choice1Field.getText().trim(),
                    choice2Field.getText().trim(), choice3Field.getText().trim(), choice4Field.getText().trim(),
                    choice5Field.getText().trim(), correctAnswer);

            controller.update(fields, values, questionIdField.getText().trim());
            handleAlert(alert, "Successful Operation",
                    "The question has been successfully updated.",
                    Alert.AlertType.INFORMATION);
        }
    }

    private String getCorrectAnswer(ComboBox<String> correctAnswerSelector, TextField choice1Field, TextField choice2Field,
                                    TextField choice3Field, TextField choice4Field, TextField choice5Field){

        int indexOfCorrectAnswerChoice = correctAnswerSelector.getSelectionModel().getSelectedIndex();
        List<TextField> choices = Arrays.asList(choice1Field, choice2Field, choice3Field, choice4Field, choice5Field);
        return choices.get(indexOfCorrectAnswerChoice).getText().trim();
    }


    public void setQuestionData(ActionEvent event, Alert alert, QuestionController controller, TextField questionIdField,
                                String username, TextField contentField, TextField choice1Field, TextField choice2Field,
                                TextField choice3Field, TextField choice4Field, TextField choice5Field,
                                ComboBox<String> correctAnswerSelector, Button updateButton){

        super.setAlertOwner(event, alert);
        boolean generalState;

        String questionID = questionIdField.getText().trim();

        generalState = questionValidator.validateQuestionID(questionIdField, alert, controller, username);

        if(generalState){
            List<Enum> fields = Arrays.asList(Column.Content, Column.Choice1, Column.Choice2,
                    Column.Choice3, Column.Choice4, Column.Choice5, Column.CorrectAnswer);
            Map<Enum, Object> questionInfo = controller.getQuestionInfo(questionID, fields);

            contentField.setText(questionInfo.get(Column.Content).toString());
            choice1Field.setText(questionInfo.get(Column.Choice1).toString());
            choice2Field.setText(questionInfo.get(Column.Choice2).toString());
            choice3Field.setText(questionInfo.get(Column.Choice3).toString());
            choice4Field.setText(questionInfo.get(Column.Choice4).toString());
            choice5Field.setText(questionInfo.get(Column.Choice5).toString());
            setCorrectAnswerSelection(correctAnswerSelector, choice1Field, choice2Field, choice3Field, choice4Field,
                    choice5Field, questionInfo.get(Column.CorrectAnswer).toString());

            setUpdateElementsStatus(contentField, choice1Field, choice2Field, choice3Field, choice4Field, choice5Field,
                    correctAnswerSelector, updateButton, false);
        }
    }

    private void setCorrectAnswerSelection(ComboBox<String> correctAnswerSelector, TextField choice1Field, TextField choice2Field,
                                           TextField choice3Field, TextField choice4Field, TextField choice5Field, String correctAnswer){

        List<TextField> choices = Arrays.asList(choice1Field, choice2Field, choice3Field, choice4Field, choice5Field);
        int index = -1;
        for(int i = 0; i < choices.size(); i++)
            if(choices.get(i).getText().equals(correctAnswer)) {
                index = i;
                break;
            }

        correctAnswerSelector.getSelectionModel().select(index);
    }

    public void setUpdateElementsStatus(TextField contentField, TextField choice1Field, TextField choice2Field,
                                        TextField choice3Field, TextField choice4Field, TextField choice5Field,
                                        ComboBox<String> correctAnswerSelector, Button updateButton, boolean disable){
        contentField.setDisable(disable);
        choice1Field.setDisable(disable);
        choice2Field.setDisable(disable);
        choice3Field.setDisable(disable);
        choice4Field.setDisable(disable);
        choice5Field.setDisable(disable);
        correctAnswerSelector.setDisable(disable);
        updateButton.setDisable(disable);
    }

    public void clearUpdateElements(TextField contentField, TextField choice1Field,
                             TextField choice2Field, TextField choice3Field, TextField choice4Field, TextField choice5Field,
                                    ComboBox<String> correctAnswerSelector){

        contentField.clear();
        choice1Field.clear();
        choice2Field.clear();
        choice3Field.clear();
        choice4Field.clear();
        choice5Field.clear();
        correctAnswerSelector.getSelectionModel().clearSelection();
    }

}
