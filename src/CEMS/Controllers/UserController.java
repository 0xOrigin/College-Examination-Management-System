package CEMS.Controllers;

import java.util.List;
import java.util.Map;

public interface UserController {

    void register(String name, String email, String username, String password, Enum gender, Enum type);

    boolean isValidName(String name);

    boolean isValidEmail(String email);

    boolean isValidUsername(String username);

    boolean isValidPassword(String password);

    boolean isUsernameAvailable(String username);

    boolean isUserExists(String username);

    Map<Enum, Object> getUserInfo(String username, List<Enum> fields);

    List<Map<Enum, Object>> getAllUsers();

    List<Map<Enum, Object>> searchBy(Enum column, String pattern);

    boolean isSubjectAssigned(String username, String subjectCode);

    void assignSubject(String username, String subjectCode);

    void update(List<Enum> fields, List<Object> values, String username);

    void delete(String username);

}
