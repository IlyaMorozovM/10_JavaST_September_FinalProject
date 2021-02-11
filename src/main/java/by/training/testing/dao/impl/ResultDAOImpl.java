package by.training.testing.dao.impl;

import by.training.testing.bean.Result;
import by.training.testing.dao.ResultDAO;
import by.training.testing.dao.exception.DAOException;
import by.training.testing.dao.impl.connection.ConnectionPool;
import by.training.testing.dao.impl.connection.ConnectionPoolException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultDAOImpl implements ResultDAO {

    private static final String DB_COLUMN_POINTS = "points";
    private static final String DB_COLUMN_TEST = "test_id";
    private static final String DB_COLUMN_STUDENT_LOGIN = "student_login";

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String INSERT_RESULT_SQL = "INSERT results(`test_id`, `student_login`, `points`) VALUES (?,?,?)";
    private static final String SELECT_RESULT_SQL = "SELECT * FROM results WHERE results.test_id = ?";
    private static final String FIND_IN_RANGE_SQL = "SELECT * FROM results WHERE results.test_id = ? LIMIT ? OFFSET ?";
    private static final String SELECT_USER_RESULT_SQL = "SELECT * FROM results WHERE results.test_id = ? and results.student_login = ?";
    private static final String FIND_IN_RANGE_USER_RESULT_SQL = "SELECT * FROM results WHERE results.test_id = ? and results.student_login = ? LIMIT ? OFFSET ?";

    @Override
    public List<Result> getResults(int testId) throws DAOException {
        PreparedStatement ps;
        Connection connection = null;
        ResultSet rs;

        try {
            connection = connectionPool.takeConnection();
            ps = connection.prepareStatement(SELECT_RESULT_SQL);
            ps.setInt(1, testId);

            rs = ps.executeQuery();
            if(rs == null)
                return null;

            List<Result> results = new ArrayList<>();
            while(rs.next()) {
                results.add(new Result(rs.getInt(DB_COLUMN_TEST), rs.getString(DB_COLUMN_STUDENT_LOGIN), rs.getInt(DB_COLUMN_POINTS)));
            }
            ps.close();
            rs.close();
            return results;
        }
        catch (ConnectionPoolException e) {
            throw new DAOException("Error in connection pool while getting results", e);
        }
        catch (SQLException e) {
            throw new DAOException("Error while getting results", e);
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public List<Result> getResultsFromTo(int testId, int limit, int offset) throws DAOException {
        ResultSet resultSet;
        PreparedStatement ps;
        Connection connection = null;

        List<Result> results = new ArrayList<>();
        try {
            connection = connectionPool.takeConnection();
            ps = connection.prepareStatement(FIND_IN_RANGE_SQL);
            ps.setInt(1, testId);
            ps.setInt(2, limit);
            ps.setInt(3, offset);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                results.add(new Result(resultSet.getInt(DB_COLUMN_TEST), resultSet.getString(DB_COLUMN_STUDENT_LOGIN), resultSet.getInt(DB_COLUMN_POINTS)));
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("Error in connection pool while getting results", e);
        }
        catch (SQLException e) {
            throw new DAOException("Error while getting results", e);
        }
        finally {
            connectionPool.returnConnection(connection);
        }

        return results;
    }

    @Override
    public List<Result> getUserResults(int testId, String login) throws DAOException {
        PreparedStatement ps;
        Connection connection = null;
        ResultSet rs;

        try {
            connection = connectionPool.takeConnection();
            ps = connection.prepareStatement(SELECT_USER_RESULT_SQL);
            ps.setInt(1, testId);
            ps.setString(2, login);

            rs = ps.executeQuery();
            if(rs == null)
                return null;

            List<Result> results = new ArrayList<>();
            while(rs.next()) {
                results.add(new Result(rs.getInt(DB_COLUMN_TEST), rs.getString(DB_COLUMN_STUDENT_LOGIN), rs.getInt(DB_COLUMN_POINTS)));
            }
            ps.close();
            rs.close();
            return results;
        }
        catch (ConnectionPoolException e) {
            throw new DAOException("Error in connection pool while getting user results", e);
        }
        catch (SQLException e) {
            throw new DAOException("Error while getting user results", e);
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public List<Result> getUserResultsFromTo(int testId, String login, int limit, int offset) throws DAOException {
        ResultSet resultSet;
        PreparedStatement ps;
        Connection connection = null;

        List<Result> results = new ArrayList<>();
        try {
            connection = connectionPool.takeConnection();
            ps = connection.prepareStatement(FIND_IN_RANGE_USER_RESULT_SQL);
            ps.setInt(1, testId);
            ps.setString(2, login);
            ps.setInt(3, limit);
            ps.setInt(4, offset);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                results.add(new Result(resultSet.getInt(DB_COLUMN_TEST), resultSet.getString(DB_COLUMN_STUDENT_LOGIN), resultSet.getInt(DB_COLUMN_POINTS)));
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("Error in connection pool while getting user results", e);
        }
        catch (SQLException e) {
            throw new DAOException("Error while getting user results", e);
        }
        finally {
            connectionPool.returnConnection(connection);
        }

        return results;
    }

    @Override
    public void addResult(int testId, String studentLogin, int points) throws DAOException {
        PreparedStatement ps;
        Connection connection = null;

        try {
            connection = connectionPool.takeConnection();
            ps = connection.prepareStatement(INSERT_RESULT_SQL);
            ps.setInt(1, testId);
            ps.setString(2, studentLogin);
            ps.setInt(3, points);

            ps.executeUpdate();
            ps.close();
        }
        catch (ConnectionPoolException e) {
            throw new DAOException("Error in connection pool while inserting results", e);
        }
        catch (SQLException e) {
            throw new DAOException("Error while inserting results", e);
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }
}
