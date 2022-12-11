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

    List<Map<Enum, Object>> getAllExamsOfSubject(String subjectCode);

    boolean isTaken(String studentID, String examID);

    void markExamAsTaken(String studentID, String examID, String grade);

    void markExamAsTaken(String studentID, String examID);

    void storeGradeForExam(String studentID, String examID, String grade);

    String correctExam(List<Map<Enum, Object>> questions, Map<String, String> studentAnswers);

}
