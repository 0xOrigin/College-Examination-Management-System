package CEMS.Controllers;

import CEMS.Models.DbContext;

import java.util.List;
import java.util.Map;

public class SubjectControllerImp implements SubjectController {

    protected final DbContext dbContext;

    public SubjectControllerImp(DbContext dbContext) {
        this.dbContext = dbContext;
    }

    @Override
    public void register(String code, String name, String description){
        this.dbContext.getSubjectModel().insert(code, name, description);
    }

    @Override
    public boolean isSubjectRegistered(String code){
        return this.dbContext.getSubjectModel().isSubjectExists(code);
    }

    @Override
    public Map<Enum, Object> getSubjectInfo(String code, List<Enum> fields){
        return this.dbContext.getSubjectModel().getInfo(fields, code);
    }

    @Override
    public void delete(String code){
        this.dbContext.getSubjectModel().delete(code);
    }

    @Override
    public List<Map<Enum, Object>> getReportForSubject(String subjectCode){
        return this.dbContext.getSubjectModel().getReportForSubject(subjectCode);
    }
}
