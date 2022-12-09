package CEMS.Controllers;

import CEMS.Models.DbContext;

import java.util.Arrays;

public class LoginControllerImp implements LoginController {

    protected final DbContext dbContext;

    public LoginControllerImp(DbContext dbContext){
        this.dbContext = dbContext;
    }

    @Override
    public boolean isValidAccount(String username, String password){
        return this.dbContext.getUserModel().isValidAccount(username, password);
    }

    @Override
    public String getUserInfo(String username, Enum columnName){
        return String.valueOf(this.dbContext.getUserModel().getInfo(Arrays.asList(columnName), username).get(columnName));
    }

    @Override
    public int countOfRegisteredSubjectsFor(String username){
        return this.dbContext.getUserModel().countOfRegisteredSubjectsFor(username);
    }

}
