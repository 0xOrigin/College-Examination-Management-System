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

/**
 * The class Exam.
 */
public class Exam extends ModelUtility {

    private final Adapter examModel;
    private SelectQuery selectQuery;
    private ResultSet resultSet;
    private Resource resource;

    /**
     * Instantiates a new Exam.
     *
     * @param adapter the adapter
     */
    public Exam(Adapter adapter){
        super(adapter);
        this.examModel = adapter;
    }

    /**
     * Insert.
     *
     * @param subjectCode the subject code
     * @param name        the name
     * @param duration    the duration
     */
    public void insert(String subjectCode, String name, String duration){

        List<Enum> fields = Arrays.asList(Column.SubjectCode, Column.Name, Column.Duration);

        List<Object> values = Arrays.asList(subjectCode, name, duration);

        this.examModel.insert(fields, values);
    }

    /**
     * Update.
     *
     * @param fields the fields
     * @param values the values
     * @param id     the exam id
     */
    public void update(List<Enum> fields, List<Object> values, String id){
        this.examModel.update(fields, values, this.examModel.Where(Column.ID, "=", id));
    }

    /**
     * Delete.
     *
     * @param id the exam id
     */
    public void delete(String id){
        this.examModel.delete(this.examModel.Where(Column.ID, "=", id));
    }

    /**
     * Is name available.
     *
     * @param examName the exam name
     * @return the boolean
     */
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


    /**
     * Is exam belongs to subject.
     *
     * @param examID      the exam id
     * @param subjectCode the subject code
     * @return the boolean
     */
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

    /**
     * Get all exams of subject.
     *
     * @param subjectCode the subject code
     * @return the list
     */
    public List<Map<Enum, Object>> getAllExamsOfSubject(String subjectCode){

        List<Enum> fields = Arrays.asList(Column.ID, Column.Name, Column.Duration);

        this.selectQuery = new SelectBuilder(Arrays.asList(fields),
                Table.Exam)
                .where(Column.SubjectCode, "=", subjectCode)
                .build();

        return super.getList(fields, this.selectQuery);
    }

}
