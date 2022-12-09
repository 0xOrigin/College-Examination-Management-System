package CEMS.Models.Database;

import CEMS.Models.Enum.Column;
import CEMS.Models.Enum.Table;
import ORM.*;
import ORM.Utilities.ModelExceptionHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

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

    public boolean isNameAvailable(String examName){

        boolean isAvailable = false;

        this.selectQuery = new SelectBuilder(Arrays.asList(this.examModel.Aggregate("count", "", Column.Name)),
                Table.Exam)
                .where(Column.Name, "=", examName)
                .build();

        this.resultSet = QueryExecutor.executeSelectQuery(this.selectQuery);
        this.resource = new Resource(this.resultSet);

        try {

            if(!this.resource.isResultSetEmpty())
                isAvailable = (this.resultSet.getInt(1) == 0);

        } catch(SQLException ex){
            ModelExceptionHandler.handle(ex, true);
        } finally {
            this.resource.close();
        }

        return isAvailable;
    }


    public boolean isExamBelongsToSubject(String examID, String subjectCode){

        boolean isBelongs = false;

        this.selectQuery = new SelectBuilder(Arrays.asList(this.examModel.Aggregate("count", "", Column.ID)),
                Table.Exam)
                .where(Column.ID, "=", examID)
                .operator("and")
                .where(Column.SubjectCode, "=", subjectCode)
                .build();

        this.resultSet = QueryExecutor.executeSelectQuery(this.selectQuery);
        this.resource = new Resource(this.resultSet);

        try {

            if(!this.resource.isResultSetEmpty())
                isBelongs = (this.resultSet.getInt(1) == 1);

        } catch(SQLException ex){
            ModelExceptionHandler.handle(ex, true);
        } finally {
            this.resource.close();
        }

        return isBelongs;
    }

}
