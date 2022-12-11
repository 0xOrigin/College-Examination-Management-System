package CEMS.Controllers;

import CEMS.Models.DbContext;
import CEMS.Models.Enum.Column;

import java.util.Arrays;
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

    @Override
    public void markExamAsTaken(String studentID, String examID, String grade){
        this.dbContext.getTakeModel().insert(studentID, examID, grade);
    }

    @Override
    public void markExamAsTaken(String studentID, String examID){
        this.dbContext.getTakeModel().insert(studentID, examID);
    }

    @Override
    public void storeGradeForExam(String studentID, String examID, String grade){
        this.dbContext.getTakeModel().update(Arrays.asList(Column.Grade), Arrays.asList(grade), studentID, examID);
    }

    @Override
    public boolean isTaken(String studentID, String examID){
        return this.dbContext.getTakeModel().isTaken(studentID, examID);
    }

    @Override
    public String correctExam(List<Map<Enum, Object>> questions, Map<String, String> studentAnswers){
        return new ExamCorrector(questions, studentAnswers).correct();
    }
}
