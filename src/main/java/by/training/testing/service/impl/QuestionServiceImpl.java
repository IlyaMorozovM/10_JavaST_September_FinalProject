package by.training.testing.service.impl;

import by.training.testing.bean.Answer;
import by.training.testing.service.exception.ServiceException;
import by.training.testing.bean.Question;
import by.training.testing.dao.QuestionDAO;
import by.training.testing.dao.exception.DAOException;
import by.training.testing.dao.factory.DAOFactory;
import by.training.testing.service.AnswerService;
import by.training.testing.service.QuestionService;
import by.training.testing.service.factory.ServiceFactory;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {
    @Override
    public List<Question> getQuestions(int testId) throws ServiceException {
        if(testId <= 0)
            return null;

        DAOFactory daoFactory = DAOFactory.getInstance();
        QuestionDAO questionDAO = daoFactory.getQuestionDao();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        AnswerService answerService = serviceFactory.getAnswerService();

        List<Question> questions;
        try {
            questions = questionDAO.getQuestions(testId);
            for(Question question : questions) {
                List<Answer> answers = answerService.getAnswers(question.getQuestionId());
                question.setAnswers(answers);
            }
            return questions;
        }
        catch (DAOException e) {
            throw new ServiceException("Error while getting questions", e);
        }
    }

    @Override
    public boolean addQuestion(int testId, String question) throws ServiceException {
        if(question.equals("") || testId <= 0)
            return false;

        DAOFactory daoFactory = DAOFactory.getInstance();
        QuestionDAO questionDAO = daoFactory.getQuestionDao();

        try {
            questionDAO.addQuestion(testId, question);
        }
        catch (DAOException e) {
            throw new ServiceException("Error while adding question", e);
        }
        return true;
    }

    @Override
    public boolean editQuestion(int questionId, String question) throws ServiceException {
        if(question.equals("") || questionId <= 0)
            return false;

        DAOFactory daoFactory = DAOFactory.getInstance();
        QuestionDAO questionDAO = daoFactory.getQuestionDao();

        try {
            questionDAO.editQuestion(questionId, question);
        }
        catch (DAOException e) {
            throw new ServiceException("Error while editing question", e);
        }
        return true;
    }

    @Override
    public boolean deleteQuestion(int questionId) throws ServiceException {
        if(questionId <= 0)
            return false;

        DAOFactory daoFactory = DAOFactory.getInstance();
        QuestionDAO questionDAO = daoFactory.getQuestionDao();

        try {
            questionDAO.deleteQuestion(questionId);
        }
        catch (DAOException e) {
            throw new ServiceException("Error while deleting question", e);
        }
        return true;
    }
}
