package CEMS.Controllers;

import java.util.List;
import java.util.Map;

/**
 * The interface User controller.
 */
public interface UserController {

    /**
     * Register.
     *
     * @param name     the name
     * @param email    the email
     * @param username the username
     * @param password the password
     * @param gender   the gender
     * @param type     the type
     */
    void register(String name, String email, String username, String password, Enum gender, Enum type);

    /**
     * Is valid name.
     *
     * @param name the name
     * @return the boolean
     */
    boolean isValidName(String name);

    /**
     * Is valid email.
     *
     * @param email the email
     * @return the boolean
     */
    boolean isValidEmail(String email);

    /**
     * Is valid username.
     *
     * @param username the username
     * @return the boolean
     */
    boolean isValidUsername(String username);

    /**
     * Is valid password.
     *
     * @param password the password
     * @return the boolean
     */
    boolean isValidPassword(String password);

    /**
     * Is username available.
     *
     * @param username the username
     * @return the boolean
     */
    boolean isUsernameAvailable(String username);

    /**
     * Is user exists.
     *
     * @param username the username
     * @return the boolean
     */
    boolean isUserExists(String username);

    /**
     * Gets user info.
     *
     * @param username the username
     * @param fields   the fields
     * @return the user info
     */
    Map<Enum, Object> getUserInfo(String username, List<Enum> fields);

    /**
     * Gets all users.
     *
     * @return the users
     */
    List<Map<Enum, Object>> getAllUsers();

    /**
     * Search by.
     *
     * @param column  the column
     * @param pattern the pattern
     * @return the list
     */
    List<Map<Enum, Object>> searchBy(Enum column, String pattern);

    /**
     * Is subject assigned.
     *
     * @param username    the username
     * @param subjectCode the subject code
     * @return the boolean
     */
    boolean isSubjectAssigned(String username, String subjectCode);

    /**
     * Assign subject.
     *
     * @param username    the username
     * @param subjectCode the subject code
     */
    void assignSubject(String username, String subjectCode);

    /**
     * Update.
     *
     * @param fields   the fields
     * @param values   the values
     * @param username the username
     */
    void update(List<Enum> fields, List<Object> values, String username);

    /**
     * Delete.
     *
     * @param username the username
     */
    void delete(String username);

    /**
     * Gets all registered subjects for a specific user .
     *
     * @param username the username
     * @return the registered subjects for user
     */
    List<Map<Enum, Object>> getAllRegisteredSubjectsFor(String username);

    /**
     * Gets all exams for a specific user.
     *
     * @param username the username
     * @return the exams for user
     */
    List<Map<Enum, Object>> getAllExamsFor(String username);

}
