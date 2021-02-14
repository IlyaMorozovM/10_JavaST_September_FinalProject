package by.training.testing.service.impl;

import by.training.testing.service.exception.ServiceException;
import by.training.testing.bean.Test;
import by.training.testing.dao.TestDAO;
import by.training.testing.dao.exception.DAOException;
import by.training.testing.dao.factory.DAOFactory;
import by.training.testing.service.TestService;

import java.util.List;

/**
 * This class contains methods, that interacts with entity "test".
 *
 * @author Ilya Morozov
 * @version	1.0
 * @since	2020-12-14
 */
public class TestServiceImpl implements TestService {

    TestDAO testDAO;

    public TestServiceImpl() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        testDAO = daoFactory.getTestDao();
    }

    /**
     * Method that receives all tests, related to a specific subject, from DB.
     *
     * @param subjectId Subject ID, to which test relate.
     * @return List, that contains all tests, related to a specific subject.
     * @throws ServiceException Thrown when DAO exception is caught.
     */
    @Override
    public List<Test> getTests(int subjectId) throws ServiceException {
        if(subjectId <= 0)
            return null;

        try {
            return testDAO.getTests(subjectId);
        }
        catch (DAOException e) {
            throw new ServiceException("Error while getting tests", e);
        }
    }

    /**
     * Method that receives tests, related to a specific subject, from DB.
     *
     * @param subjectId Subject ID, to which test relate.
     * @param limit Number of tests returned.
     * @param offset Offset of records in the database.
     * @return List, that contains tests, related to a specific subject (size = limit).
     * @throws ServiceException Thrown when DAO exception is caught.
     */
    @Override
    public List<Test> getTestsFromTo(int subjectId, int limit, int offset) throws ServiceException {
        if(subjectId <= 0)
            return null;
        //TODO: вынести тут все создания фабрик в поле класса + с коннекшенами попробовать такое в ДАО

        try {
            return testDAO.getTestsFromTo(subjectId, limit, offset);
        }
        catch (DAOException e) {
            throw new ServiceException("Error while getting tests", e);
        }
    }

    /**
     * Method that inserts test with some values (subjectId, title) into DB.
     *
     * @param subjectId Subject ID, to which test relate.
     * @param title Title of test, that inserting into DB.
     * @return True - if operation was successful, otherwise - false.
     * @throws ServiceException Thrown when DAO exception is caught.
     */
    @Override
    public boolean addTest(int subjectId, String title) throws ServiceException {
        if(title.equals("") || subjectId <= 0)
            return false;

        try {
            testDAO.addTest(subjectId, title);
        }
        catch (DAOException e) {
            throw new ServiceException("Error while adding test", e);
        }
        return true;
    }

    /**
     * Method that edits test with some new values (testId, title)
     * and inserts into DB.
     *
     * @param testId Test ID, to which test relate.
     * @param title Title of test, that editing.
     * @return True - if operation was successful, otherwise - false.
     * @throws ServiceException TThrown when DAO exception is caught.
     */
    @Override
    public boolean editTest(int testId, String title) throws ServiceException {
        if(title.equals("") || testId <= 0)
            return false;

        try {
            testDAO.editTest(testId, title);
        }
        catch (DAOException e) {
            throw new ServiceException("Error while editing test", e);
        }
        return true;
    }

    /**
     * Method that deletes test by ID.
     *
     * @param testId Test ID, to which test relate.
     * @return True - if operation was successful, otherwise - false.
     * @throws ServiceException Thrown when DAO exception is caught.
     */
    @Override
    public boolean deleteTest(int testId) throws ServiceException {
        if(testId <= 0)
            return false;

        try {
            testDAO.deleteTest(testId);
        }
        catch (DAOException e) {
            throw new ServiceException("Error while deleting test", e);
        }
        return true;
    }
}
