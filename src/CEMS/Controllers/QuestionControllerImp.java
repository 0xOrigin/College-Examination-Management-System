package CEMS.Controllers;

import CEMS.Models.DbContext;

public class QuestionControllerImp implements QuestionController {

    protected final DbContext dbContext;

    public QuestionControllerImp(DbContext dbContext) {
        this.dbContext = dbContext;
    }
}
