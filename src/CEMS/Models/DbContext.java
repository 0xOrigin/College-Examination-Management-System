package CEMS.Models;

import CEMS.Models.Database.*;

public interface DbContext {

    User getUserModel();

    Subject getSubjectModel();

    Exam getExamModel();

    Question getQuestionModel();

    Register getRegisterModel();

    Take getTakeModel();
    
}
