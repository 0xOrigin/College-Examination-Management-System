package CEMS.Controllers;

public class DataValidator {

    DataValidator() {

    }

    public static boolean isValidName(String name) {
        return name.matches( "[a-zA-Z]+[\\-'\\s]?[a-zA-Z ]+(_|-)*" );
    }

    public static boolean isValidEmail(String email) {
        return email.matches("(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*" +
                "@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)" +
                "*(\\.[A-Za-z]{2,})");
    }

    public static boolean isValidUsername(String username){
        return username.matches("[a-zA-Z0-9]{4,20}");
    }

    public static boolean isValidPassword(String password){
        return password.matches("[0-9a-zA-Z!@#&()–{}:;',?/~$=<>]{4,10}");
    }

}
