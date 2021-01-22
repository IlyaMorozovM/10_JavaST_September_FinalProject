package by.training.testing.dao;

import by.training.testing.bean.Result;
import by.training.testing.dao.exception.DAOException;

import java.util.List;

public interface ResultDAO {
    List<Result> getResults(int testId) throws DAOException;
    void addResult(int testId, String studentLogin, int points) throws DAOException;
}
