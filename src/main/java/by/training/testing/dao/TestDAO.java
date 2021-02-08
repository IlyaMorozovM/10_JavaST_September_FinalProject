package by.training.testing.dao;

import by.training.testing.bean.Test;
import by.training.testing.dao.exception.DAOException;

import java.util.List;

public interface TestDAO {
    List<Test> getTests(int subjectId) throws DAOException;
    List<Test> getTestsFromTo(int subjectId, int limit, int offset) throws DAOException;
    void addTest(int subjectId, String title) throws DAOException;
    void editTest(int testId, String title) throws DAOException;
    void deleteTest(int testId) throws DAOException;
}
