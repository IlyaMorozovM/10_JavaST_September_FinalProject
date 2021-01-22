package by.training.testing.dao.factory;

import by.training.testing.dao.*;
import by.training.testing.dao.impl.*;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private static final UserDAO sqlUserImpl = new UserDAOImpl();
    private static final SubjectDAO sqlSubjectImpl = new SubjectDAOImpl();
    private static final TestDAO sqlTestImpl = new TestDAOImpl();
    private static final AnswerDAO sqlAnswerImpl = new AnswerDAOImpl();
    private static final QuestionDAO sqlQuestionImpl = new QuestionDAOImpl();
    private static final ResultDAO sqlResultImpl = new ResultDAOImpl();

    private DAOFactory() {}
    public static DAOFactory getInstance() {
        return instance;
    }

    public UserDAO getUserDao() {
        return sqlUserImpl;
    }

    public SubjectDAO getSubjectDao() {
        return sqlSubjectImpl;
    }

    public TestDAO getTestDao() {
        return sqlTestImpl;
    }

    public AnswerDAO getAnswerDao() {
        return sqlAnswerImpl;
    }

    public QuestionDAO getQuestionDao() {
        return sqlQuestionImpl;
    }

    public ResultDAO getResultDao() {
        return sqlResultImpl;
    }
}
