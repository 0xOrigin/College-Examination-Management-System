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

public class Subject extends ModelUtility {

    private final Adapter subjectModel;
    private SelectQuery selectQuery;
    private ResultSet resultSet;
    private Resource resource;

    public Subject(Adapter adapter){
        super(adapter);
        this.subjectModel = adapter;
    }

    public void insert(String code, String name, String description){

        List<Enum> fields = Arrays.asList(Column.Code, Column.Name, Column.Description);

        List<Object> values = Arrays.asList(code, name, description);

        this.subjectModel.insert(fields, values);
    }

    public void update(List<Enum> fields, List<Object> values, String code){
        this.subjectModel.update(fields, values, this.subjectModel.Where(Column.Code, "=", code));
    }

    public void delete(String code){
        this.subjectModel.delete(this.subjectModel.Where(Column.Code, "=", code));
    }

    public boolean isSubjectExists(String code){

        boolean isExists = false;

        this.selectQuery = new SelectBuilder(Arrays.asList(this.subjectModel.Aggregate("count", "", Column.Code)),
                Table.Subject)
                .where(Column.Code, "=", code)
                .build();

        this.resultSet = QueryExecutor.executeSelectQuery(this.selectQuery);
        this.resource = new Resource(this.resultSet);

        try {

            if(!this.resource.isResultSetEmpty())
                isExists = (this.resultSet.getInt(1) == 1);

        } catch(SQLException ex){
            ModelExceptionHandler.handle(ex, true);
        } finally {
            this.resource.close();
        }

        return isExists;
    }

    public List<Map<Enum, Object>> getReportForSubject(String subjectCode){

        List<Enum> fields = Arrays.asList(Column.StudentID, Column.StudentName, Column.ExamID, Column.ExamName, Column.Grade);

        this.selectQuery = new SelectBuilder()
                .freeSQLStatement("SELECT U.ID AS StudentID, U.Name AS StudentName, E.ID AS ExamID, E.Name AS ExamName, Grade\n" +
                        "From Exam AS E\n" +
                        "JOIN Subject AS S ON E.SubjectCode = S.Code AND S.Code = '" + subjectCode + "'\n" +
                        "JOIN Take AS T ON T.ExamID = E.ID\n" +
                        "JOIN User AS U ON T.UserID = U.ID;");

        return super.getList(fields, this.selectQuery);
    }

}
