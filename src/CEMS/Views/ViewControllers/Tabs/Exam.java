package CEMS.Views.ViewControllers.Tabs;

import CEMS.Controllers.ExamController;
import CEMS.Controllers.QuestionController;
import CEMS.Models.Enum.Column;
import CEMS.Views.Utilities;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.util.*;

/**
 * The type Exam.
 */
public class Exam extends Utilities {

    private String studentID;
    private String examID;
    private ExamController examController;
    private QuestionController questionController;
    private final List<Map<Enum, Object>> questions;
    private final Map<String, RadioButton> studentAnswers;
    private final Map<String, String> studentAnswersText;
    private int indexOfCurrentQuestion = 0;
    private Alert alert;
    private Timer timer;
    private ToggleGroup Answers;
    private Label questionLabel;
    private RadioButton choice1Button;
    private RadioButton choice2Button;
    private RadioButton choice3Button;
    private RadioButton choice4Button;
    private RadioButton choice5Button;
    private Button nextSubmitButton;
    private Button prevButton;

    /**
     * Instantiates a new Exam.
     *
     * @param studentID              the student id
     * @param examID                 the exam id
     * @param selectExamField        the select exam field
     * @param examNameLabel          the exam name label
     * @param totalQuestionsNumLabel the total questions num label
     * @param examController         the exam controller
     * @param questionController     the question controller
     */
    public Exam(String studentID, String examID, ComboBox<String> selectExamField, Label examNameLabel, Label totalQuestionsNumLabel,
                ExamController examController, QuestionController questionController){

        this.studentID = studentID;
        this.examID = examID;
        this.examController = examController;
        this.questionController = questionController;
        this.questions = this.retrieveExamQuestions(examID, questionController);
        this.studentAnswers = new HashMap<>();
        this.studentAnswersText = new HashMap<>();

        this.setExamName(selectExamField, examNameLabel);
        this.setTotalQuestionsNum(totalQuestionsNumLabel);
    }

    private void setExamName(ComboBox<String> selectExamField, Label examName){
        examName.setText(selectExamField.getSelectionModel().getSelectedItem());
    }

    private List<Map<Enum, Object>> retrieveExamQuestions(String examID, QuestionController controller){
        return controller.getAllQuestionsFor(examID);
    }

    /**
     * Set exam tab attributes.
     *
     * @param questionLabel    the question label
     * @param Answers          the answers
     * @param choice1Button    the choice 1 button
     * @param choice2Button    the choice 2 button
     * @param choice3Button    the choice 3 button
     * @param choice4Button    the choice 4 button
     * @param choice5Button    the choice 5 button
     * @param prevButton       the prev button
     * @param nextSubmitButton the next submit button
     */
    public void setExamTabAttributes(Label questionLabel, ToggleGroup Answers, RadioButton choice1Button, RadioButton choice2Button, RadioButton choice3Button,
                              RadioButton choice4Button, RadioButton choice5Button, Button prevButton, Button nextSubmitButton){

        this.Answers = Answers;
        this.questionLabel = questionLabel;
        this.choice1Button = choice1Button;
        this.choice2Button = choice2Button;
        this.choice3Button = choice3Button;
        this.choice4Button = choice4Button;
        this.choice5Button = choice5Button;
        this.prevButton = prevButton;
        this.nextSubmitButton = nextSubmitButton;
    }

    /**
     * Start.
     *
     * @param event                   the event
     * @param alert                   the alert
     * @param showDurationField       the show duration field
     * @param timerLabel              the timer label
     * @param currentQuestionNumLabel the current question num label
     * @return the boolean
     */
    public boolean start(ActionEvent event, Alert alert, TextField showDurationField, Label timerLabel, Label currentQuestionNumLabel){
        this.alert = alert;
        super.setAlertOwner(event, alert);

        if(this.questions.isEmpty()){
            handleAlert(alert, "Empty Exam",
                    "The exam contains 0 questions, and an empty exam cannot be taken!",
                    Alert.AlertType.ERROR);
            return false;
        }

        this.setCurrentQuestionNum(currentQuestionNumLabel);
        this.loadCurrentQuestion(this.indexOfCurrentQuestion);
        this.updatePrevButtonState();
        this.updateNextButtonText();
        long minutes = Integer.parseInt(showDurationField.getText());
        this.setTimer(minutes, timerLabel);
        this.setExamAsTaken(studentID, examID);
        return true;
    }

    private void setExamAsTaken(String studentID, String examID){
        this.examController.markExamAsTaken(studentID, examID);
    }

    private void loadCurrentQuestion(int indexOfCurrentQuestion){
        questionLabel.setText(questions.get(indexOfCurrentQuestion).get(Column.Content).toString());
        choice1Button.setText(questions.get(indexOfCurrentQuestion).get(Column.Choice1).toString());
        choice2Button.setText(questions.get(indexOfCurrentQuestion).get(Column.Choice2).toString());
        disableIfEmptyAnswer(choice2Button);
        choice3Button.setText(questions.get(indexOfCurrentQuestion).get(Column.Choice3).toString());
        disableIfEmptyAnswer(choice3Button);
        choice4Button.setText(questions.get(indexOfCurrentQuestion).get(Column.Choice4).toString());
        disableIfEmptyAnswer(choice4Button);
        choice5Button.setText(questions.get(indexOfCurrentQuestion).get(Column.Choice5).toString());
    }

    private void disableIfEmptyAnswer(RadioButton choiceButton){
        choiceButton.setDisable(choiceButton.getText().isBlank());
    }

    /**
     * Increment current question number.
     *
     * @param currentQuestionNumLabel the current question number label
     */
    public void incrementCurrentQuestionNum(Label currentQuestionNumLabel){
        updateAnswer(this.indexOfCurrentQuestion);
        if(this.indexOfCurrentQuestion < this.questions.size()-1){
            incrementCurrentQuestionIndex();
            setCurrentQuestionNum(currentQuestionNumLabel);
            loadCurrentQuestion(this.indexOfCurrentQuestion);
        }
        updateSelection(this.indexOfCurrentQuestion);
        updatePrevButtonState();
        updateNextButtonText();
    }

    /**
     * Decrement current question number.
     *
     * @param currentQuestionNumLabel the current question number label
     */
    public void decrementCurrentQuestionNum(Label currentQuestionNumLabel){
        updateAnswer(this.indexOfCurrentQuestion);
        if(this.indexOfCurrentQuestion > 0){
            decrementCurrentQuestionIndex();
            setCurrentQuestionNum(currentQuestionNumLabel);
            loadCurrentQuestion(this.indexOfCurrentQuestion);
        }
        updateSelection(this.indexOfCurrentQuestion);
        updatePrevButtonState();
        updateNextButtonText();
    }

    /**
     * Get questions.
     *
     * @return the list
     */
    public List<Map<Enum, Object>> getQuestions(){
        return this.questions;
    }

    /**
     * Get student answers.
     *
     * @return the map
     */
    public Map<String, String> getStudentAnswers(){
        return this.studentAnswersText;
    }

    /**
     * Get exam id.
     *
     * @return the exam id
     */
    public String getExamID(){
        return this.examID;
    }

    private void updateAnswer(int indexOfCurrentQuestion){
        String questionID = getQuestionID(indexOfCurrentQuestion);
        this.studentAnswers.put(questionID, (RadioButton) this.Answers.getSelectedToggle());
        if(((RadioButton) this.Answers.getSelectedToggle()) != null)
            this.studentAnswersText.put(questionID, ((RadioButton) this.Answers.getSelectedToggle()).getText());
    }

    private void updateSelection(int indexOfCurrentQuestion){
        String questionID = getQuestionID(indexOfCurrentQuestion);
        this.Answers.selectToggle(this.studentAnswers.get(questionID));
    }

    private void incrementCurrentQuestionIndex(){
        this.indexOfCurrentQuestion++;
    }

    private void decrementCurrentQuestionIndex(){
        this.indexOfCurrentQuestion--;
    }

    private String getQuestionID(int indexOfCurrentQuestion){
        return this.questions.get(indexOfCurrentQuestion).get(Column.ID).toString();
    }

    private void setCurrentQuestionNum(Label currentQuestionNumLabel){
        currentQuestionNumLabel.setText(String.valueOf(this.indexOfCurrentQuestion + 1));
    }

    private void setTotalQuestionsNum(Label totalQuestionsNumLabel){
        totalQuestionsNumLabel.setText(String.valueOf(this.questions.size()));
    }

    private boolean isFirstQuestion(){
        return this.indexOfCurrentQuestion == 0;
    }

    private boolean isLastQuestion(){
        return (this.indexOfCurrentQuestion == this.questions.size() - 1);
    }

    /**
     * Is submit unlocked.
     *
     * @return the boolean
     */
    public boolean isSubmitUnlocked(){
        return this.isLastQuestion();
    }

    private void updateNextButtonText(){
        String text = (this.isLastQuestion() ? "Submit" : "Next >>");
        this.nextSubmitButton.setText(text);
    }

    private void updatePrevButtonState(){
        this.prevButton.setDisable(this.isFirstQuestion());
    }

    /**
     * Stop timer.
     */
    public void stopTimer(){
        this.timer.cancel();
    }

    private void endOfTimeAction(){

        Platform.runLater(() -> handleAlert(this.alert, "Time is up!",
                "All previous answers have been submitted.",
                Alert.AlertType.WARNING));

        // Submit all answers
        while(!isLastQuestion()){
            Platform.runLater(() -> this.nextSubmitButton.fire());
        }
        Platform.runLater(() -> this.nextSubmitButton.fire());
    }

    private void setTimer(long minutes, Label timerLabel) {
        timer = new Timer();

        int delay = 0;
        int period = 1000;
        long seconds = minutes * 60L;

        // Start the timer
        final long[] interval = {seconds};
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {

                if(interval[0] >= 0) {

                    // Calculate the time in hours:minutes:seconds
                    long seconds = interval[0] % 60;
                    long minutes = (interval[0] % 3600) / 60;
                    long hours = interval[0] / 3600;

                    String currentTime = String.format("%02d:%02d:%02d", hours, minutes, seconds);

                    Platform.runLater(() -> timerLabel.setText(currentTime));
                    interval[0]--;
                }
                else {
                    timer.cancel();
                    endOfTimeAction();
                }
            }

        }, delay, period);
    }

}
