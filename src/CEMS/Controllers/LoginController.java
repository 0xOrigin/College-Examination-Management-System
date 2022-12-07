package CEMS.Controllers;

public interface LoginController {

    boolean isValidAccount(String username, String password);

    String getUserInfo(String username, Enum columnName);

}
