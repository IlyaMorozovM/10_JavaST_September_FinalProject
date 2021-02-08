package by.training.testing.service.impl;

import by.training.testing.service.exception.ServiceException;
import by.training.testing.bean.Test;
import by.training.testing.dao.TestDAO;
import by.training.testing.dao.exception.DAOException;
import by.training.testing.dao.factory.DAOFactory;
import by.training.testing.service.TestService;

import java.util.List;

public class TestServiceImpl implements TestService {

    TestDAO testDAO;

    public TestServiceImpl() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        testDAO = daoFactory.getTestDao();
    }

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
