package CEMS.Models.Database;

import CEMS.Models.Enum.Column;
import CEMS.Models.Enum.Table;
import ORM.*;
import ORM.Utilities.ModelExceptionHandler;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * The type Take.
 */
public class Take extends ModelUtility {

    private final Adapter takeModel;
    private SelectQuery selectQuery;
    private ResultSet resultSet;
    private Resource resource;

    /**
     * Instantiates a new Take.
     *
     * @param adapter the adapter
     */
    public Take(Adapter adapter){
        super(adapter);
        this.takeModel = adapter;
    }

    /**
     * Insert.
     *
     * @param userID the user id
     * @param examID the exam id
     */
    public void insert(String userID, String examID){

        List<Enum> fields = Arrays.asList(Column.UserID, Column.ExamID);

        List<Object> values = Arrays.asList(userID, examID);

        this.takeModel.insert(fields, values);
    }

    /**
     * Insert.
     *
     * @param userID the user id
     * @param examID the exam id
     * @param grade  the grade
     */
    public void insert(String userID, String examID, String grade){

        List<Enum> fields = Arrays.asList(Column.UserID, Column.ExamID, Column.Grade);

        List<Object> values = Arrays.asList(userID, examID, grade);

        this.takeModel.insert(fields, values);
    }

    /**
     * Update.
     *
     * @param fields the fields
     * @param values the values
     * @param userID the user id
     * @param examID the exam id
     */
    public void update(List<Enum> fields, List<Object> values, String userID, String examID){
        this.takeModel.update(fields, values, this.takeModel.Where(Column.UserID, "=", userID) +
                                                    this.takeModel.Operator("and") +
                                                    this.takeModel.Where(Column.ExamID, "=", examID));
    }

    /**
     * Delete.
     *
     * @param userID the user id
     * @param examID the exam id
     */
    public void delete(String userID, String examID){
        this.takeModel.delete(this.takeModel.Where(Column.UserID, "=", userID) +
                this.takeModel.Operator("and") +
                this.takeModel.Where(Column.ExamID, "=", examID));
    }

    /**
     * Is taken.
     *
     * @param userID the user id
     * @param examID the exam id
     * @return the boolean
     */
    public boolean isTaken(String userID, String examID){

        boolean taken = false;

        this.selectQuery = new SelectBuilder(Arrays.asList(this.takeModel.Aggregate("count", "", Column.UserID)),
                Table.Take)
                .where(Column.UserID, "=", userID)
                .operator("and")
                .where(Column.ExamID, "=", examID)
                .build();

        this.resultSet = QueryExecutor.executeSelectQuery(this.selectQuery);
        this.resource = new Resource(this.resultSet);

        try {

            if(!this.resource.isResultSetEmpty())
                taken = (this.resultSet.getInt(1) == 1);

        } catch(SQLException ex){
            ModelExceptionHandler.handle(ex, true);
        } finally {
            this.resource.close();
        }

        return taken;
    }

}
