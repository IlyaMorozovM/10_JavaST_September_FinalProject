package by.training.testing.service;

import by.training.testing.bean.Result;
import by.training.testing.service.exception.ServiceException;

import java.util.List;

public interface ResultService {
    List<Result> getResults(int testId) throws ServiceException;
    List<Result> getResultsFromTo(int testId, int limit, int offset) throws ServiceException;
    List<Result> getUserResults(int testId, String login) throws ServiceException;
    List<Result> getUserResultsFromTo(int testId, String login, int limit, int offset) throws ServiceException;
    boolean addResult(int testId, String studentLogin, int points) throws ServiceException;
}
