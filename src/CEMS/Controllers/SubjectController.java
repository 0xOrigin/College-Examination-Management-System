package CEMS.Controllers;

import java.util.List;
import java.util.Map;

/**
 * The interface Subject controller.
 */
public interface SubjectController {

    /**
     * Register.
     *
     * @param code        the subject code
     * @param name        the name
     * @param description the description
     */
    void register(String code, String name, String description);

    /**
     * Is subject registered.
     *
     * @param code the subject code
     * @return the boolean
     */
    boolean isSubjectRegistered(String code);

    /**
     * Delete.
     *
     * @param code the subject code
     */
    void delete(String code);

    /**
     * Gets subject info.
     *
     * @param code   the subject code
     * @param fields the fields
     * @return the subject info
     */
    Map<Enum, Object> getSubjectInfo(String code, List<Enum> fields);

    /**
     * Gets report for a specific subject.
     *
     * @param subjectCode the subject code
     * @return the report for subject
     */
    List<Map<Enum, Object>> getReportForSubject(String subjectCode);

    /**
     * Gets all subjects.
     *
     * @return the subjects
     */
    List<Map<Enum, Object>> getAllSubjects();

    /**
     * Search by.
     *
     * @param column  the column
     * @param pattern the pattern
     * @return the list
     */
    List<Map<Enum, Object>> searchBy(Enum column, String pattern);

}
