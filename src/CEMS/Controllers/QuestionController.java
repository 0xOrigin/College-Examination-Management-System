package CEMS.Controllers;

import java.util.List;
import java.util.Map;

/**
 * The interface Question controller.
 */
public interface QuestionController {

    /**
     * Insert.
     *
     * @param examID        the exam id
     * @param content       the question content
     * @param choice1       the choice 1
     * @param choice2       the choice 2
     * @param choice3       the choice 3
     * @param choice4       the choice 4
     * @param choice5       the choice 5
     * @param correctAnswer the correct answer
     */
    void insert(String examID, String content, String choice1, String choice2, String choice3,
                String choice4, String choice5, String correctAnswer);

    /**
     * Update.
     *
     * @param fields     the fields
     * @param values     the values
     * @param questionID the question id
     */
    void update(List<Enum> fields, List<Object> values, String questionID);

    /**
     * Delete.
     *
     * @param questionID the question id
     */
    void delete(String questionID);

    /**
     * Gets question info.
     *
     * @param questionID the question id
     * @param fields     the fields
     * @return the question info
     */
    Map<Enum, Object> getQuestionInfo(String questionID, List<Enum> fields);

    /**
     * Gets all questions for a specific exam.
     *
     * @param examID the exam id
     * @return the questions for exam
     */
    List<Map<Enum, Object>> getAllQuestionsFor(String examID);

    /**
     * Is question available to a specific user.
     *
     * @param username   the username
     * @param questionID the question id
     * @return the boolean
     */
    boolean isQuestionAvailableToUser(String username, String questionID);

}
