package CEMS.Controllers;

/**
 * The interface Login controller.
 */
public interface LoginController {

    /**
     * Is valid account.
     *
     * @param username the username
     * @param password the password
     * @return the boolean
     */
    boolean isValidAccount(String username, String password);

    /**
     * Gets user info.
     *
     * @param username   the username
     * @param columnName the column name
     * @return the user info
     */
    String getUserInfo(String username, Enum columnName);

    /**
     * Count of registered subjects for a specific user.
     *
     * @param username the username
     * @return the count
     */
    int countOfRegisteredSubjectsFor(String username);

}
