package CEMS.Controllers;

import AppDataReader.EmailConfigImp;
import CEMS.Models.DbContext;
import CEMS.Models.Enum.Column;
import Email.SendEmail;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserControllerImp implements UserController {

    protected final DbContext dbContext;

    public UserControllerImp(DbContext dbContext) {
        this.dbContext = dbContext;
    }

    @Override
    public void register(String name, String email, String username, String password, Enum gender, Enum type){
        this.dbContext.getUserModel().insert(name, email, gender.name(), username, password, type.name());
        this.sendSuccessfulRegistrationEmail(email, username, password);
    }

    private void sendSuccessfulRegistrationEmail(String email, String username, String password){

        String messageSubject = "Successful Registration";
        String messageText = "Your Username is " + username + "\n"
                + "Your Password is " + password + " .";

        SendEmail.setDefaultConfig(new EmailConfigImp()).send(email, messageSubject, messageText);
    }

    @Override
    public void update(List<Enum> fields, List<Object> values, String username){
        this.dbContext.getUserModel().update(fields, values, username);
        Map<Enum, String> map = new HashMap<>();
        for(int i = 0; i < fields.size(); i++)
            map.put(fields.get(i), String.valueOf(values.get(i)));
        this.sendUpdateEmail(map.get(Column.Email), map.get(Column.Username), map.get(Column.Password));
    }

    private void sendUpdateEmail(String email, String username, String password){

        String messageSubject = "Successful Update";
        String messageText = "Your Username is " + username + "\n"
                + "Your Password is " + password + " .";

        SendEmail.setDefaultConfig(new EmailConfigImp()).send(email, messageSubject, messageText);
    }

    @Override
    public void delete(String username){
        this.dbContext.getUserModel().delete(username);
    }

    @Override
    public boolean isValidName(String name){
        return DataValidator.isValidName(name);
    }

    @Override
    public boolean isValidEmail(String email){
        return DataValidator.isValidEmail(email);
    }

    @Override
    public boolean isValidUsername(String username){
        return DataValidator.isValidUsername(username);
    }

    @Override
    public boolean isValidPassword(String password){
        return DataValidator.isValidPassword(password);
    }

    @Override
    public boolean isUsernameAvailable(String username){
        return !this.dbContext.getUserModel().isUserExists(username);
    }

    @Override
    public boolean isUserExists(String username){
        return this.dbContext.getUserModel().isUserExists(username);
    }

    @Override
    public Map<Enum, Object> getUserInfo(String username, List<Enum> fields){
        return this.dbContext.getUserModel().getInfo(fields, username);
    }

    @Override
    public void assignSubject(String username, String subjectCode){
        String userID = String.valueOf(getUserInfo(username, Arrays.asList(Column.ID)).get(Column.ID));
        this.dbContext.getRegisterModel().insert(userID, subjectCode);
    }

    @Override
    public boolean isSubjectAssigned(String username, String subjectCode){
        String userID = String.valueOf(getUserInfo(username, Arrays.asList(Column.ID)).get(Column.ID));
        return this.dbContext.getRegisterModel().isRegistered(userID, subjectCode);
    }

    @Override
    public List<Map<Enum, Object>> getAllUsers(){
        return this.dbContext.getUserModel().getAllUsers();
    }

    @Override
    public List<Map<Enum, Object>> searchBy(Enum column, String pattern){
        return this.dbContext.getUserModel().searchBy(column, "%" + pattern + "%");
    }

}
