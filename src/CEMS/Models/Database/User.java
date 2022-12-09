package CEMS.Models.Database;

import CEMS.Models.Enum.Column;
import CEMS.Models.Enum.Table;
import ORM.*;
import ORM.Utilities.ModelExceptionHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class User extends ModelUtility {
    private final Adapter userModel;
    private SelectQuery selectQuery;
    private ResultSet resultSet;
    private Resource resource;

    public User(Adapter adapter){
        super(adapter);
        this.userModel = adapter;
    }

    public void insert(String name, String email, String gender, String username, String password, String type){

        List<Enum> fields = Arrays.asList(Column.Name, Column.Email, Column.Gender, Column.Username, Column.Password,
                                          Column.Type);

        List<Object> values = Arrays.asList(name, email, gender, username, password, type);

        this.userModel.insert(fields, values);
    }

    public void update(List<Enum> fields, List<Object> values, String username){
        this.userModel.update(fields, values, this.userModel.Where(Column.Username, "=", username));
    }

    public void delete(String username){
        this.userModel.delete(this.userModel.Where(Column.Username, "=", username));
    }

    public boolean isUserExists(String username){

        boolean isExists = false;

        this.selectQuery = new SelectBuilder(Arrays.asList(this.userModel.Aggregate("count", "", Column.Username)),
                Table.User)
                .where(Column.Username, "=", username).build();

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

    public boolean isValidAccount(String username, String password){

        if(!this.isUserExists(username))
            return false;

        return super.isPasswordMatch(username, password);
    }

    public List<Map<Enum, Object>> getAllUsers(){

        List<Enum> fields = Arrays.asList(Column.ID, Column.Name, Column.Email, Column.Gender,
                                          Column.Username, Column.Password, Column.Type);

        this.selectQuery = new SelectBuilder(Arrays.asList(fields),
                Table.User)
                .build();

        return super.getList(fields, this.selectQuery);
    }

    public List<Map<Enum, Object>> searchBy(Enum column, String pattern){

        List<Enum> fields = Arrays.asList(Column.ID, Column.Name, Column.Email, Column.Gender,
                Column.Username, Column.Password, Column.Type);

        this.selectQuery = new SelectBuilder(Arrays.asList(fields),
                Table.User)
                .where(column, "", null)
                .like(pattern)
                .build();

        return super.getList(fields, this.selectQuery);
    }

    public List<Map<Enum, Object>> getAllRegisteredSubjectsFor(String username){

        List<Enum> fields = Arrays.asList(Column.Code, Column.SubjectName);

        this.selectQuery = new SelectBuilder()
                .freeSQLStatement("SELECT S.Code, S.Name AS SubjectName\n" +
                        "FROM User AS U\n" +
                        "JOIN Register As R ON R.UserID = U.ID AND U.Username = '"+ username +"'\n" +
                        "JOIN Subject AS S ON R.SubjectCode = S.Code;");

        return super.getList(fields, this.selectQuery);
    }

    public List<Map<Enum, Object>> getAllExamsFor(String username){

        List<Enum> fields = Arrays.asList(Column.ID, Column.SubjectCode, Column.SubjectName, Column.ExamName, Column.Duration);

        this.selectQuery = new SelectBuilder()
                .freeSQLStatement("SELECT E.ID, E.SubjectCode, S.Name AS SubjectName, E.Name AS ExamName, Duration\n" +
                        "FROM User AS U\n" +
                        "JOIN Register As R ON R.UserID = U.ID AND U.Username = '"+ username + "'\n" +
                        "JOIN Subject AS S ON R.SubjectCode = S.Code\n" +
                        "JOIN Exam As E ON E.SubjectCode = S.Code;");

        return super.getList(fields, this.selectQuery);
    }

    public int countOfRegisteredSubjectsFor(String username){

        int count = 0;

        List<Enum> fields = Arrays.asList(Column.SubjectsCount);

        this.selectQuery = new SelectBuilder()
                .freeSQLStatement("SELECT COUNT(S.Code) AS SubjectsCount\n" +
                        "FROM User AS U\n" +
                        "JOIN Register As R ON R.UserID = U.ID AND U.Username = '"+ username +"'\n" +
                        "JOIN Subject AS S ON R.SubjectCode = S.Code;");

        this.resultSet = QueryExecutor.executeSelectQuery(this.selectQuery);
        this.resource = new Resource(this.resultSet);

        try {

            if(!this.resource.isResultSetEmpty())
                count = this.resultSet.getInt(1);

        } catch(SQLException ex){
            ModelExceptionHandler.handle(ex, true);
        } finally {
            this.resource.close();
        }

        return count;
    }

}
