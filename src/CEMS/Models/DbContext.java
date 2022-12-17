package CEMS.Models;

import CEMS.Models.Database.*;

/**
 * The interface Db context.
 */
public interface DbContext {

    /**
     * Gets user model.
     *
     * @return the user model
     */
    User getUserModel();

    /**
     * Gets subject model.
     *
     * @return the subject model
     */
    Subject getSubjectModel();

    /**
     * Gets exam model.
     *
     * @return the exam model
     */
    Exam getExamModel();

    /**
     * Gets question model.
     *
     * @return the question model
     */
    Question getQuestionModel();

    /**
     * Gets register model.
     *
     * @return the register model
     */
    Register getRegisterModel();

    /**
     * Gets take model.
     *
     * @return the take model
     */
    Take getTakeModel();
    
}
