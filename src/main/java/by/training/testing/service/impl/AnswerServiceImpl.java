package by.training.testing.service.impl;

import by.training.testing.bean.Answer;
import by.training.testing.dao.exception.DAOException;
import by.training.testing.dao.factory.DAOFactory;
import by.training.testing.service.AnswerService;
import by.training.testing.service.exception.ServiceException;
import by.training.testing.dao.AnswerDAO;

import java.util.List;

/**
 * This class contains methods, that interacts with entity "answer".
 *
 * @author Ilya Morozov
 * @version	1.0
 * @since	2020-12-14
 */
public class AnswerServiceImpl implements AnswerService {

    AnswerDAO answerDAO;

    public AnswerServiceImpl() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        answerDAO = daoFactory.getAnswerDao();
    }

    /**
     * Method that receives answers, related to a specific question, from DB.
     *
     * @param questionId Question ID, to which answers relate.
     * @return List, that contains all answers, related to a specific question.
     * @throws ServiceException Thrown when DAO exception is caught.
     */
    @Override
    public List<Answer> getAnswers(int questionId) throws ServiceException {

        if(questionId <= 0)
            return null;

        try {
            return answerDAO.getAnswers(questionId);
        }
        catch (DAOException e) {
            throw new ServiceException("Error while getting answers", e);
        }
    }

    /**
     * Method that inserts answer with some values (questionId, answer, isRight) into DB.
     *
     * @param questionId Question ID, to which answer relate.
     * @param answer Text of answer, that inserting into DB.
     * @param isRight Boolean value, that indicates whether the answer is correct.
     * @return True - if operation was successful, otherwise - false.
     * @throws ServiceException Thrown when DAO exception is caught.
     */
    @Override
    public boolean addAnswer(int questionId, String answer, boolean isRight) throws ServiceException {

        if(answer.equals("") || questionId <= 0)
            return false;

        try {
            answerDAO.addAnswer(questionId, answer, isRight);
        }
        catch (DAOException e) {
            throw new ServiceException("Error while adding answer", e);
        }
        return true;
    }

    /**
     * Method that edits answer with some new values (questionId, answer, isRight)
     * and inserts into DB.
     *
     * @param answerId Answer ID, to which answer relate.
     * @param answer Text of answer, that editing.
     * @param isRight Boolean value, that indicates whether the answer is correct.
     * @return True - if operation was successful, otherwise - false.
     * @throws ServiceException Thrown when DAO exception is caught.
     */
    @Override
    public boolean editAnswer(int answerId, String answer, boolean isRight) throws ServiceException {

        if(answer.equals("") || answerId <= 0)
            return false;

        try {
            answerDAO.editAnswer(answerId, answer, isRight);
        }
        catch (DAOException e) {
            throw new ServiceException("Error while editing answer", e);
        }
        return true;
    }

    /**
     * Method that deletes answer by ID.
     *
     * @param answerId Answer ID, to which answer relate.
     * @return True - if operation was successful, otherwise - false.
     * @throws ServiceException Thrown when DAO exception is caught.
     */
    @Override
    public boolean deleteAnswer(int answerId) throws ServiceException {

        if(answerId <= 0)
            return false;

        try {
            answerDAO.deleteAnswer(answerId);
        }
        catch (DAOException e) {
            throw new ServiceException("Error while deleting answer", e);
        }
        return true;
    }
}
