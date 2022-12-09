package CEMS.Controllers;

import java.util.List;
import java.util.Map;

public interface QuestionController {

    void insert(String examID, String content, String choice1, String choice2, String choice3,
                String choice4, String choice5, String correctAnswer);

    void update(List<Enum> fields, List<Object> values, String questionID);

    void delete(String questionID);

    Map<Enum, Object> getQuestionInfo(String questionID, List<Enum> fields);

    List<Map<Enum, Object>> getAllQuestionsFor(String examID);

    boolean isQuestionAvailableToUser(String username, String questionID);

}
