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

/**
 * This class contains methods, that interacts with entity "question".
 *
 * @author Ilya Morozov
 * @version	1.0
 * @since	2020-12-14
 */
public class QuestionServiceImpl implements QuestionService {

    QuestionDAO questionDAO;

    public QuestionServiceImpl() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        questionDAO = daoFactory.getQuestionDao();
    }

    /**
     * Method that receives questions, related to a specific test, from DB.
     *
     * @param testId Test ID, to which questions relate.
     * @return List, that contains all questions, related to a specific test.
     * @throws ServiceException Thrown when DAO exception is caught.
     */
    @Override
    public List<Question> getQuestions(int testId) throws ServiceException {
        if(testId <= 0)
            return null;

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

    /**
     * Method that inserts question with some values (testId, question) into DB.
     *
     * @param testId Test ID, to which question relate.
     * @param question Text of question, that inserting into DB.
     * @return True - if operation was successful, otherwise - false.
     * @throws ServiceException Thrown when DAO exception is caught.
     */
    @Override
    public boolean addQuestion(int testId, String question) throws ServiceException {
        if(question.equals("") || testId <= 0)
            return false;

        try {
            questionDAO.addQuestion(testId, question);
        }
        catch (DAOException e) {
            throw new ServiceException("Error while adding question", e);
        }
        return true;
    }

    /**
     * Method that edits question with some new values (questionId, question)
     * and inserts into DB.
     *
     * @param questionId Question ID, to which question relate.
     * @param question Text of question, that editing.
     * @return True - if operation was successful, otherwise - false.
     * @throws ServiceException Thrown when DAO exception is caught.
     */
    @Override
    public boolean editQuestion(int questionId, String question) throws ServiceException {
        if(question.equals("") || questionId <= 0)
            return false;

        try {
            questionDAO.editQuestion(questionId, question);
        }
        catch (DAOException e) {
            throw new ServiceException("Error while editing question", e);
        }
        return true;
    }

    /**
     * Method that deletes question by ID.
     *
     * @param questionId Question ID, to which question relate.
     * @return True - if operation was successful, otherwise - false.
     * @throws ServiceException Thrown when DAO exception is caught.
     */
    @Override
    public boolean deleteQuestion(int questionId) throws ServiceException {
        if(questionId <= 0)
            return false;

        try {
            questionDAO.deleteQuestion(questionId);
        }
        catch (DAOException e) {
            throw new ServiceException("Error while deleting question", e);
        }
        return true;
    }
}
