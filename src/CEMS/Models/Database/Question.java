package CEMS.Models.Database;

import CEMS.Models.Enum.Column;
import CEMS.Models.Enum.Table;
import ORM.*;
import ORM.Utilities.ModelExceptionHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Question extends ModelUtility {

    private final Adapter questionModel;
    private SelectQuery selectQuery;
    private ResultSet resultSet;
    private Resource resource;

    public Question(Adapter adapter){
        super(adapter);
        this.questionModel = adapter;
    }

    public void insert(String examID, String content, String choice1, String choice2, String choice3,
                       String choice4, String choice5, String correctAnswer){

        List<Enum> fields = Arrays.asList(Column.ExamID, Column.Content, Column.Choice1, Column.Choice2,
                                            Column.Choice3, Column.Choice4, Column.Choice5, Column.CorrectAnswer);

        List<Object> values = Arrays.asList(examID, content, choice1, choice2, choice3, choice4, choice5, correctAnswer);

        this.questionModel.insert(fields, values);
    }

    public void update(List<Enum> fields, List<Object> values, String id){
        this.questionModel.update(fields, values, this.questionModel.Where(Column.ID, "=", id));
    }

    public void delete(String id){
        this.questionModel.delete(this.questionModel.Where(Column.ID, "=", id));
    }

    public List<Map<Enum, Object>> getAllQuestionsFor(String examID){

        List<Enum> fields = Arrays.asList(Column.ID, Column.Content, Column.Choice1, Column.Choice2, Column.Choice3,
                                            Column.Choice4, Column.Choice5, Column.CorrectAnswer);

        this.selectQuery = new SelectBuilder(Arrays.asList(fields),
                            Table.Question)
                            .where(Column.ExamID, "=", examID)
                            .build();

        return super.getList(fields, this.selectQuery);
    }

    public boolean isQuestionAvailableToUser(String username, String questionID){

        boolean isAvailable = false;

        this.selectQuery = new SelectBuilder()
                .freeSQLStatement("SELECT COUNT(Q.ID)\n" +
                        "FROM User AS U\n" +
                        "JOIN Register AS R ON R.UserID = U.ID AND U.Username = '" + username + "'\n" +
                        "JOIN Exam AS E ON R.SubjectCode = E.SubjectCode\n" +
                        "JOIN Question AS Q ON Q.ExamID = E.ID AND Q.ID = '" + questionID + "';\n");

        this.resultSet = QueryExecutor.executeSelectQuery(this.selectQuery);
        this.resource = new Resource(this.resultSet);

        try {

            if(!this.resource.isResultSetEmpty())
                isAvailable = (this.resultSet.getInt(1) == 1);

        } catch(SQLException ex){
            ModelExceptionHandler.handle(ex, true);
        } finally {
            this.resource.close();
        }

        return isAvailable;
    }
}
