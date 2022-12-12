package CEMS.Controllers;

import java.util.List;
import java.util.Map;

public interface SubjectController {

    void register(String code, String name, String description);

    boolean isSubjectRegistered(String code);

    void delete(String code);

    Map<Enum, Object> getSubjectInfo(String code, List<Enum> fields);

    List<Map<Enum, Object>> getReportForSubject(String subjectCode);

    List<Map<Enum, Object>> getAllSubjects();

    List<Map<Enum, Object>> searchBy(Enum column, String pattern);

}
