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
 * The class Register.
 */
public class Register extends ModelUtility {

    private final Adapter registerModel;
    private SelectQuery selectQuery;
    private ResultSet resultSet;
    private Resource resource;

    /**
     * Instantiates a new Register.
     *
     * @param adapter the adapter
     */
    public Register(Adapter adapter){
        super(adapter);
        this.registerModel = adapter;
    }

    /**
     * Insert.
     *
     * @param userID      the user id
     * @param subjectCode the subject code
     */
    public void insert(String userID, String subjectCode){

        List<Enum> fields = Arrays.asList(Column.UserID, Column.SubjectCode);

        List<Object> values = Arrays.asList(userID, subjectCode);

        this.registerModel.insert(fields, values);
    }

    /**
     * Is registered.
     *
     * @param userID      the user id
     * @param subjectCode the subject code
     * @return the boolean
     */
    public boolean isRegistered(String userID, String subjectCode){
        boolean isRegistered = false;

        this.selectQuery = new SelectBuilder(Arrays.asList(this.registerModel.Aggregate("count", "", Column.UserID)),
                Table.Register)
                .where(Column.UserID, "=", userID)
                .operator("and")
                .where(Column.SubjectCode, "=", subjectCode)
                .build();

        this.resultSet = QueryExecutor.executeSelectQuery(this.selectQuery);
        this.resource = new Resource(this.resultSet);

        try {

            if(!this.resource.isResultSetEmpty())
                isRegistered = (this.resultSet.getInt(1) == 1);

        } catch(SQLException ex){
            ModelExceptionHandler.handle(ex, true);
        } finally {
            this.resource.close();
        }

        return isRegistered;
    }

    /**
     * Delete.
     *
     * @param userID      the user id
     * @param subjectCode the subject code
     */
    public void delete(String userID, String subjectCode){
        this.registerModel.delete(this.registerModel.Where(Column.UserID, "=", userID) +
                                    this.registerModel.Operator("and") +
                                    this.registerModel.Where(Column.SubjectCode, "=", subjectCode));
    }
}
