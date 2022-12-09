package CEMS.Controllers;

import java.util.List;
import java.util.Map;

public interface ExamController {

    void add(String subjectCode, String name, int duration);

    void update(List<Enum> fields, List<Object> values, String id);

    void delete(String id);

    boolean isNameAvailable(String examName);

    boolean isExamBelongsToSubject(String examID, String subjectCode);

    Map<Enum, Object> getExamInfo(String examID, List<Enum> fields);

}
