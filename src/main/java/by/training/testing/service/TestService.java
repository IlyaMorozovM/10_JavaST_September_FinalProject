package by.training.testing.service;

import by.training.testing.bean.Test;
import by.training.testing.service.exception.ServiceException;

import java.util.List;

public interface TestService {
    List<Test> getTests(int subjectId) throws ServiceException;
    List<Test> getTestsFromTo(int subjectId, int limit, int offset) throws ServiceException;
    boolean addTest(int subjectId, String title) throws ServiceException;
    boolean editTest(int testId, String title) throws ServiceException;
    boolean deleteTest(int testId) throws ServiceException;
}
