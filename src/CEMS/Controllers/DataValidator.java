package CEMS.Controllers;

/**
 * The class Data validator.
 */
public class DataValidator {

    /**
     * Instantiates a new Data validator.
     */
    DataValidator() {

    }

    /**
     * Is valid name.
     *
     * @param name the name
     * @return the boolean
     */
    public static boolean isValidName(String name) {
        return name.matches( "[a-zA-Z]+[\\-'\\s]?[a-zA-Z ]+(_|-)*" );
    }

    /**
     * Is valid email.
     *
     * @param email the email
     * @return the boolean
     */
    public static boolean isValidEmail(String email) {
        return email.matches("(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*" +
                "@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)" +
                "*(\\.[A-Za-z]{2,})");
    }

    /**
     * Is valid username.
     *
     * @param username the username
     * @return the boolean
     */
    public static boolean isValidUsername(String username){
        return username.matches("[a-zA-Z0-9]{4,20}");
    }

    /**
     * Is valid password.
     *
     * @param password the password
     * @return the boolean
     */
    public static boolean isValidPassword(String password){
        return password.matches("[0-9a-zA-Z!@#&()–{}:;',?/~$=<>]{4,10}");
    }

}
