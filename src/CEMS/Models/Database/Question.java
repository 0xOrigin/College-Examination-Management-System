package CEMS.Models.Database;

import CEMS.Models.Enum.Column;
import CEMS.Models.Enum.Table;
import ORM.Adapter;
import ORM.Resource;
import ORM.SelectBuilder;
import ORM.SelectQuery;

import java.sql.ResultSet;
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

}
