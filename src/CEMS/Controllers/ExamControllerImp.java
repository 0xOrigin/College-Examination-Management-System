package CEMS.Controllers;

import CEMS.Models.DbContext;

public class ExamControllerImp implements ExamController {

    protected final DbContext dbContext;

    public ExamControllerImp(DbContext dbContext) {
        this.dbContext = dbContext;
    }
}
