package CEMS.Controllers;

import CEMS.Models.DbContext;

import java.util.List;
import java.util.Map;

public class ExamControllerImp implements ExamController {

    protected final DbContext dbContext;

    public ExamControllerImp(DbContext dbContext) {
        this.dbContext = dbContext;
    }

    @Override
    public void add(String subjectCode, String name, int duration){
        this.dbContext.getExamModel().insert(subjectCode, name, String.valueOf(duration));
    }

    @Override
    public void update(List<Enum> fields, List<Object> values, String id){
        this.dbContext.getExamModel().update(fields, values, id);
    }

    @Override
    public void delete(String id){
        this.dbContext.getExamModel().delete(id);
    }

    @Override
    public boolean isNameAvailable(String examName){
        return this.dbContext.getExamModel().isNameAvailable(examName);
    }

    @Override
    public boolean isExamBelongsToSubject(String examID, String subjectCode){
        return this.dbContext.getExamModel().isExamBelongsToSubject(examID, subjectCode);
    }

    @Override
    public Map<Enum, Object> getExamInfo(String examID, List<Enum> fields){
        return this.dbContext.getExamModel().getInfo(fields, examID);
    }

    @Override
    public List<Map<Enum, Object>> getAllExamsOfSubject(String subjectCode){
        return this.dbContext.getExamModel().getAllExamsOfSubject(subjectCode);
    }
}
