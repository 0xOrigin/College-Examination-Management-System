package CEMS.Models;

import CEMS.Models.Database.*;
import CEMS.Models.Enum.Column;
import CEMS.Models.Enum.Table;
import ORM.SQLiteAdapter;


public class CEMS_DbContext implements DbContext {
    private final User userModel;
    private final Subject subjectModel;
    private final Exam examModel;
    private final Question questionModel;
    private final Register registerModel;
    private final Take takeModel;


    public CEMS_DbContext(){

        this.userModel= new User(new SQLiteAdapter(Table.User, Column.Username));
        this.subjectModel = new Subject(new SQLiteAdapter(Table.Subject, Column.Code));
        this.examModel = new Exam(new SQLiteAdapter(Table.Exam, Column.ID));
        this.questionModel = new Question(new SQLiteAdapter(Table.Question, Column.ID));
        this.registerModel = new Register(new SQLiteAdapter(Table.Register, Column.UserID, Column.SubjectCode));
        this.takeModel = new Take(new SQLiteAdapter(Table.Take, Column.UserID, Column.ExamID));

    }

    @Override
    public User getUserModel(){
        return this.userModel;
    }

    @Override
    public Subject getSubjectModel() {
        return this.subjectModel;
    }

    @Override
    public Exam getExamModel() {
        return this.examModel;
    }

    @Override
    public Question getQuestionModel() {
        return this.questionModel;
    }

    @Override
    public Register getRegisterModel() {
        return this.registerModel;
    }

    @Override
    public Take getTakeModel() {
        return this.takeModel;
    }

}
