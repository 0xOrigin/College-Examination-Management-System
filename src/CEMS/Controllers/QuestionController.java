package CEMS.Controllers;

import java.util.List;
import java.util.Map;

public interface QuestionController {

    void insert(String examID, String content, String choice1, String choice2, String choice3,
                String choice4, String choice5, String correctAnswer);

    void update(List<Enum> fields, List<Object> values, String questionID);

    void delete(String questionID);

    List<Map<Enum, Object>> getAllQuestionsFor(String examID);

}
