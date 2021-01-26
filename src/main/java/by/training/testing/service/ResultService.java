package by.training.testing.service;

import by.training.testing.bean.Result;
import by.training.testing.service.exception.ServiceException;

import java.util.List;

public interface ResultService {
    List<Result> getResults(int testId) throws ServiceException;
    List<Result> getUserResults(List<Result> results, String login) throws ServiceException;
    boolean addResult(int testId, String studentLogin, int points) throws ServiceException;
}
