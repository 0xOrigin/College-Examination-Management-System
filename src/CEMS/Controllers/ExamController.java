package CEMS.Controllers;

import java.util.List;
import java.util.Map;

/**
 * The interface Exam controller.
 */
public interface ExamController {

    /**
     * Add.
     *
     * @param subjectCode the subject code
     * @param name        the name
     * @param duration    the duration
     */
    void add(String subjectCode, String name, int duration);

    /**
     * Update.
     *
     * @param fields the fields
     * @param values the values
     * @param id     the exam id
     */
    void update(List<Enum> fields, List<Object> values, String id);

    /**
     * Delete.
     *
     * @param id the exam id
     */
    void delete(String id);

    /**
     * Is name available.
     *
     * @param examName the exam name
     * @return the boolean
     */
    boolean isNameAvailable(String examName);

    /**
     * Is exam belongs to a specific subject.
     *
     * @param examID      the exam id
     * @param subjectCode the subject code
     * @return the boolean
     */
    boolean isExamBelongsToSubject(String examID, String subjectCode);

    /**
     * Gets exam info.
     *
     * @param examID the exam id
     * @param fields the fields
     * @return the exam info
     */
    Map<Enum, Object> getExamInfo(String examID, List<Enum> fields);

    /**
     * Gets all exams of a specific subject.
     *
     * @param subjectCode the subject code
     * @return the all exams of subject
     */
    List<Map<Enum, Object>> getAllExamsOfSubject(String subjectCode);

    /**
     * Is taken.
     *
     * @param studentID the student id
     * @param examID    the exam id
     * @return the boolean
     */
    boolean isTaken(String studentID, String examID);

    /**
     * Mark exam as taken.
     *
     * @param studentID the student id
     * @param examID    the exam id
     * @param grade     the grade
     */
    void markExamAsTaken(String studentID, String examID, String grade);

    /**
     * Mark exam as taken.
     *
     * @param studentID the student id
     * @param examID    the exam id
     */
    void markExamAsTaken(String studentID, String examID);

    /**
     * Store grade for exam.
     *
     * @param studentID the student id
     * @param examID    the exam id
     * @param grade     the grade
     */
    void storeGradeForExam(String studentID, String examID, String grade);

    /**
     * Correct exam.
     *
     * @param questions      the questions
     * @param studentAnswers the student answers
     * @return the grade
     */
    String correctExam(List<Map<Enum, Object>> questions, Map<String, String> studentAnswers);

}
