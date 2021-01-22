package by.training.testing.dao;

import by.training.testing.bean.Result;
import by.training.testing.dao.exception.DAOException;

import java.util.List;

public interface ResultDAO {
    List<Result> getResults(int testId) throws DAOException;
    void addResult(int testId, int userId, int points) throws DAOException;
}
