package CEMS.Models.Database;

import CEMS.Models.Enum.Column;
import ORM.Adapter;
import ORM.Resource;
import ORM.SelectQuery;
import java.sql.ResultSet;
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

}
