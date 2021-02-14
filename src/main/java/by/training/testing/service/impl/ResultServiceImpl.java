package by.training.testing.service.impl;

import by.training.testing.bean.Result;
import by.training.testing.dao.ResultDAO;
import by.training.testing.dao.exception.DAOException;
import by.training.testing.dao.factory.DAOFactory;
import by.training.testing.service.ResultService;
import by.training.testing.service.exception.ServiceException;

import java.util.List;

/**
 * This class contains methods, that interacts with entity "result".
 *
 * @author Ilya Morozov
 * @version	1.0
 * @since	2020-12-14
 */
public class ResultServiceImpl implements ResultService {

    ResultDAO resultDAO;

    public ResultServiceImpl() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        resultDAO = daoFactory.getResultDao();
    }

    /**
     * Method that receives all results, related to a specific test, from DB.
     *
     * @param testId Test ID, to which results relate.
     * @return List of all results.
     * @throws ServiceException Thrown when DAO exception is caught.
     */
    @Override
    public List<Result> getResults(int testId) throws ServiceException {
        if(testId <= 0)
            return null;

        try {
            return resultDAO.getResults(testId);
        }
        catch (DAOException e) {
            throw new ServiceException("Error while getting results", e);
        }
    }

    /**
     * Method that receives results, related to a specific test, from DB.
     *
     * @param testId Test ID, to which results relate.
     * @param limit Number of results returned.
     * @param offset Offset of records in the database.
     * @return List of all results with size = limit.
     * @throws ServiceException Thrown when DAO exception is caught.
     */
    @Override
    public List<Result> getResultsFromTo(int testId, int limit, int offset) throws ServiceException {
        if(testId <= 0)
            return null;

        try {
            return resultDAO.getResultsFromTo(testId, limit, offset);
        }
        catch (DAOException e) {
            throw new ServiceException("Error while getting results", e);
        }
    }

    /**
     * Method that receives student results, related to a specific test, from DB.
     *
     * @param testId Test ID, to which results relate.
     * @param login Student login, to which results relate.
     * @return List of all student results.
     * @throws ServiceException Thrown when DAO exception is caught.
     */
    @Override
    public List<Result> getUserResults(int testId, String login) throws ServiceException {
        if(testId <= 0)
            return null;

        try {
            return resultDAO.getUserResults(testId, login);
        }
        catch (DAOException e) {
            throw new ServiceException("Error while getting user results", e);
        }
    }

    /**
     * Method that receives student results, related to a specific test, from DB.
     *
     * @param testId Test ID, to which results relate.
     * @param login Student login, to which results relate.
     * @param limit Number of results returned.
     * @param offset Offset of records in the database.
     * @return List of student results with size = limit.
     * @throws ServiceException Thrown when DAO exception is caught.
     */
    @Override
    public List<Result> getUserResultsFromTo(int testId, String login, int limit, int offset) throws ServiceException {
        if(testId <= 0)
            return null;

        try {
            return resultDAO.getUserResultsFromTo(testId, login, limit, offset);
        }
        catch (DAOException e) {
            throw new ServiceException("Error while getting user results", e);
        }
    }

    /**
     * Method that inserts result with some values (testId, studentLogin, points) into DB.
     *
     * @param testId Test ID, to which result relate.
     * @param studentLogin Student login, to which result relate.
     * @param points Points scored by the student for the test.
     * @return True - if operation was successful, otherwise - false.
     * @throws ServiceException Thrown when DAO exception is caught.
     */
    @Override
    public boolean addResult(int testId, String studentLogin, int points) throws ServiceException {
        if(studentLogin.equals("") || testId <= 0)
            return false;

        try {
            resultDAO.addResult(testId, studentLogin, points);
        }
        catch (DAOException e) {
            throw new ServiceException("Error while adding test", e);
        }
        return true;
    }
}
