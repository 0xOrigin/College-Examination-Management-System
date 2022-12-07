package CEMS.Controllers;

import java.util.List;
import java.util.Map;

public interface SubjectController {

    void register(String code, String name, String description);

    boolean isSubjectRegistered(String code);

    void delete(String code);

    Map<Enum, Object> getSubjectInfo(String code, List<Enum> fields);

}
