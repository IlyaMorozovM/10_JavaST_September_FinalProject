package by.training.testing.service.impl;

import by.training.testing.bean.Result;
import by.training.testing.dao.ResultDAO;
import by.training.testing.dao.exception.DAOException;
import by.training.testing.dao.factory.DAOFactory;
import by.training.testing.service.ResultService;
import by.training.testing.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class ResultServiceImpl implements ResultService {
    @Override
    public List<Result> getResults(int testId) throws ServiceException {
        if(testId <= 0)
            return null;

        DAOFactory daoFactory = DAOFactory.getInstance();
        ResultDAO resultDAO = daoFactory.getResultDao();

        try {
            return resultDAO.getResults(testId);
        }
        catch (DAOException e) {
            throw new ServiceException("Error while getting results", e);
        }
    }

    @Override
    public List<Result> getUserResults(List<Result> results, String login) throws ServiceException {
        List<Result> userResults = new ArrayList<>();
        for(Result result: results){
            if (result.getStudentLogin().equals(login)){
                userResults.add(result);
            }
        }
        return userResults;
    }

    @Override
    public boolean addResult(int testId, String studentLogin, int points) throws ServiceException {
        if(studentLogin.equals("") || testId <= 0)
            return false;

        DAOFactory daoFactory = DAOFactory.getInstance();
        ResultDAO resultDAO = daoFactory.getResultDao();

        try {
            resultDAO.addResult(testId, studentLogin, points);
        }
        catch (DAOException e) {
            throw new ServiceException("Error while adding test", e);
        }
        return true;
    }
}
