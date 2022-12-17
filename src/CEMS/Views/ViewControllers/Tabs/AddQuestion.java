package CEMS.Views.ViewControllers.Tabs;

import CEMS.Controllers.ExamController;
import CEMS.Controllers.QuestionController;
import CEMS.Views.Utilities;
import CEMS.Views.ViewControllers.Validators.ExamValidator;
import CEMS.Views.ViewControllers.Validators.QuestionValidator;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.Arrays;
import java.util.List;

/**
 * The class Add question.
 */
public class AddQuestion extends Utilities {

    QuestionValidator questionValidator = new QuestionValidator();
    ExamValidator examValidator = new ExamValidator();

    /**
     * Add.
     *
     * @param event                 the event
     * @param alert                 the alert
     * @param questionController    the question controller
     * @param examController        the exam controller
     * @param selectSubjectField    the select subject field
     * @param examIdField           the exam id field
     * @param contentField          the content field
     * @param choice1Field          the choice 1 field
     * @param choice2Field          the choice 2 field
     * @param choice3Field          the choice 3 field
     * @param choice4Field          the choice 4 field
     * @param choice5Field          the choice 5 field
     * @param correctAnswerSelector the correct answer selector
     */
    public void add(ActionEvent event, Alert alert, QuestionController questionController, ExamController examController,
                    ComboBox<String> selectSubjectField, TextField examIdField, TextField contentField, TextField choice1Field,
                    TextField choice2Field, TextField choice3Field, TextField choice4Field, TextField choice5Field,
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
        generalState &= examValidator.validateExamID(examIdField, selectSubjectField, alert, examController);

        if(generalState){
            String correctAnswer = getCorrectAnswer(correctAnswerSelector, choice1Field, choice2Field, choice3Field, choice4Field, choice5Field);

            questionController.insert(examIdField.getText().trim(), contentField.getText().trim(), choice1Field.getText().trim(),
                    choice2Field.getText().trim(), choice3Field.getText().trim(), choice4Field.getText().trim(),
                    choice5Field.getText().trim(), correctAnswer);
            handleAlert(alert, "Successful Operation",
                    "The question has been successfully added.",
                    Alert.AlertType.INFORMATION);
        }
    }

    private String getCorrectAnswer(ComboBox<String> correctAnswerSelector, TextField choice1Field, TextField choice2Field,
                                    TextField choice3Field, TextField choice4Field, TextField choice5Field){

        int indexOfCorrectAnswerChoice = correctAnswerSelector.getSelectionModel().getSelectedIndex();
        List<TextField> choices = Arrays.asList(choice1Field, choice2Field, choice3Field, choice4Field, choice5Field);
        return choices.get(indexOfCorrectAnswerChoice).getText().trim();
    }

    /**
     * Reset tab view.
     *
     * @param selectSubjectField    the select subject field
     * @param examIdField           the exam id field
     * @param contentField          the content field
     * @param choice1Field          the choice 1 field
     * @param choice2Field          the choice 2 field
     * @param choice3Field          the choice 3 field
     * @param choice4Field          the choice 4 field
     * @param choice5Field          the choice 5 field
     * @param correctAnswerSelector the correct answer selector
     */
    public void resetTabView(ComboBox<String> selectSubjectField, TextField examIdField, TextField contentField, TextField choice1Field,
                             TextField choice2Field, TextField choice3Field, TextField choice4Field, TextField choice5Field,
                             ComboBox<String> correctAnswerSelector){

        selectSubjectField.getSelectionModel().selectFirst();
        examIdField.clear();
        contentField.clear();
        choice1Field.clear();
        choice2Field.clear();
        choice3Field.clear();
        choice4Field.clear();
        choice5Field.clear();
        correctAnswerSelector.getSelectionModel().clearSelection();
    }

}
