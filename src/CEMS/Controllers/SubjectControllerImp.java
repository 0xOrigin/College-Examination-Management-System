package CEMS.Controllers;

import CEMS.Models.DbContext;

import java.util.List;
import java.util.Map;

/**
 * The class Subject controller imp.
 */
public class SubjectControllerImp implements SubjectController {

    /**
     * The Db context.
     */
    protected final DbContext dbContext;

    /**
     * Instantiates a new Subject controller imp.
     *
     * @param dbContext the db context
     */
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

    @Override
    public List<Map<Enum, Object>> getAllSubjects(){
        return this.dbContext.getSubjectModel().getAllSubjects();
    }

    @Override
    public List<Map<Enum, Object>> searchBy(Enum column, String pattern){
        return this.dbContext.getSubjectModel().searchBy(column, "%" + pattern + "%");
    }

}
