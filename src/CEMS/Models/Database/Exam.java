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

public class Exam extends ModelUtility {

    private final Adapter examModel;
    private SelectQuery selectQuery;
    private ResultSet resultSet;
    private Resource resource;

    public Exam(Adapter adapter){
        super(adapter);
        this.examModel = adapter;
    }

    public void insert(String subjectCode, String name, String duration){

        List<Enum> fields = Arrays.asList(Column.SubjectCode, Column.Name, Column.Duration);

        List<Object> values = Arrays.asList(subjectCode, name, duration);

        this.examModel.insert(fields, values);
    }

    public void update(List<Enum> fields, List<Object> values, String id){
        this.examModel.update(fields, values, this.examModel.Where(Column.ID, "=", id));
    }

    public void delete(String id){
        this.examModel.delete(this.examModel.Where(Column.ID, "=", id));
    }

    public List<Map<Enum, Object>> getAllTestsFor(String username){

        List<Enum> fields = Arrays.asList(Column.ID, Column.SubjectCode, Column.SubjectName, Column.ExamName, Column.Duration);

        this.selectQuery = new SelectBuilder()
                                .freeSQLStatement("SELECT E.ID, E.SubjectCode, S.Name AS SubjectName, E.Name AS ExamName, Duration\n" +
                                        "FROM User AS U\n" +
                                        "JOIN register As R ON U.Username = '"+ username +"'\n" +
                                        "JOIN Subject AS S ON R.SubjectCode = S.Code\n" +
                                        "JOIN Exam As E ON E.SubjectCode = S.Code;");

        return super.getList(fields, this.selectQuery);
    }

}
