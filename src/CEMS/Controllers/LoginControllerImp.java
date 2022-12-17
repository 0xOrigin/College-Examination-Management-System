package CEMS.Controllers;

import CEMS.Models.DbContext;

import java.util.Arrays;

/**
 * The class Login controller imp.
 */
public class LoginControllerImp implements LoginController {

    /**
     * The Db context.
     */
    protected final DbContext dbContext;

    /**
     * Instantiates a new Login controller imp.
     *
     * @param dbContext the db context
     */
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
