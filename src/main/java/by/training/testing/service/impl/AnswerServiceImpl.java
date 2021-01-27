package by.training.testing.service.impl;

import by.training.testing.bean.Answer;
import by.training.testing.dao.exception.DAOException;
import by.training.testing.dao.factory.DAOFactory;
import by.training.testing.service.AnswerService;
import by.training.testing.service.exception.ServiceException;
import by.training.testing.dao.AnswerDAO;

import java.util.List;

public class AnswerServiceImpl implements AnswerService {
    @Override
    public List<Answer> getAnswers(int questionId) throws ServiceException {

        if(questionId <= 0)
            return null;

        DAOFactory daoFactory = DAOFactory.getInstance();
        AnswerDAO answerDAO = daoFactory.getAnswerDao();

        try {
            return answerDAO.getAnswers(questionId);
        }
        catch (DAOException e) {
            throw new ServiceException("Error while getting answers", e);
        }
    }

    @Override
    public boolean addAnswer(int questionId, String answer, boolean isRight) throws ServiceException {

        if(answer.equals("") || questionId <= 0)
            return false;

        DAOFactory daoFactory = DAOFactory.getInstance();
        AnswerDAO answerDAO = daoFactory.getAnswerDao();

        try {
            answerDAO.addAnswer(questionId, answer, isRight);
        }
        catch (DAOException e) {
            throw new ServiceException("Error while adding answer", e);
        }
        return true;
    }

    @Override
    public boolean editAnswer(int answerId, String answer, boolean isRight) throws ServiceException {

        if(answer.equals("") || answerId <= 0)
            return false;

        DAOFactory daoFactory = DAOFactory.getInstance();
        AnswerDAO answerDAO = daoFactory.getAnswerDao();

        try {
            answerDAO.editAnswer(answerId, answer, isRight);
        }
        catch (DAOException e) {
            throw new ServiceException("Error while editing answer", e);
        }
        return true;
    }

    @Override
    public boolean deleteAnswer(int answerId) throws ServiceException {

        if(answerId <= 0)
            return false;

        DAOFactory daoFactory = DAOFactory.getInstance();
        AnswerDAO answerDAO = daoFactory.getAnswerDao();

        try {
            answerDAO.deleteAnswer(answerId);
        }
        catch (DAOException e) {
            throw new ServiceException("Error while deleting answer", e);
        }
        return true;
    }
}
