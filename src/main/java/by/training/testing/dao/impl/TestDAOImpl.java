package by.training.testing.dao.impl;

import by.training.testing.dao.TestDAO;
import by.training.testing.dao.impl.connection.ConnectionPool;
import by.training.testing.dao.impl.connection.ConnectionPoolException;
import by.training.testing.bean.Test;
import by.training.testing.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestDAOImpl implements TestDAO {

    private static final String DB_COLUMN_TITLE = "title";
    private static final String DB_COLUMN_ID = "id";
    private static final String DB_COLUMN_SUBJECT = "subject_id";

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String INSERT_TEST_SQL = "INSERT tests(`subject_id`, `title`) VALUES (?,?)";
    private static final String DELETE_TEST_SQL = "DELETE FROM tests WHERE tests.id = ?";
    private static final String UPDATE_TEST_SQL = "UPDATE tests SET tests.title = ? WHERE tests.id = ?";
    private static final String SELECT_TEST_SQL = "SELECT * FROM tests WHERE tests.subject_id = ?";
    private static final String FIND_IN_RANGE_SQL = "SELECT * FROM tests WHERE tests.subject_id = ? LIMIT ? OFFSET ?";

    @Override
    public List<Test> getTests(int subjectId) throws DAOException {
        PreparedStatement ps = null;
        Connection connection = null;
        ResultSet rs = null;

        try {
            connection = connectionPool.takeConnection();
            ps = connection.prepareStatement(SELECT_TEST_SQL);
            ps.setInt(1, subjectId);

            rs = ps.executeQuery();
            if(rs == null)
                return null;

            List<Test> tests = new ArrayList<>();
            while(rs.next()) {
                tests.add(new Test(rs.getInt(DB_COLUMN_ID), rs.getInt(DB_COLUMN_SUBJECT), rs.getString(DB_COLUMN_TITLE)));
            }
            ps.close();
            rs.close();
            return tests;
        }
        catch (ConnectionPoolException e) {
            throw new DAOException("Error in connection pool while getting tests", e);
        }
        catch (SQLException e) {
            throw new DAOException("Error while getting tests", e);
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public List<Test> getTestsFromTo(int subjectId, int limit, int offset) throws DAOException {
        ResultSet resultSet = null;
        PreparedStatement ps = null;
        Connection connection = null;

        List<Test> tests = new ArrayList<>();
        try {
            connection = connectionPool.takeConnection();
            ps = connection.prepareStatement(FIND_IN_RANGE_SQL);
            ps.setInt(1, subjectId);
            ps.setInt(2, limit);
            ps.setInt(3, offset);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                tests.add(new Test(resultSet.getInt(DB_COLUMN_ID), resultSet.getInt(DB_COLUMN_SUBJECT),
                        resultSet.getString(DB_COLUMN_TITLE)));
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("Error in connection pool while getting tests", e);
        }
        catch (SQLException e) {
            throw new DAOException("Error while getting tests", e);
        }
        finally {
            connectionPool.returnConnection(connection);
        }

        return tests;
    }

    @Override
    public void addTest(int subjectId, String title) throws DAOException {
        PreparedStatement ps = null;
        Connection connection = null;

        try {
            connection = connectionPool.takeConnection();
            ps = connection.prepareStatement(INSERT_TEST_SQL);
            ps.setInt(1, subjectId);
            ps.setString(2, title);

            ps.executeUpdate();
            ps.close();
        }
        catch (ConnectionPoolException e) {
            throw new DAOException("Error in connection pool while inserting test", e);
        }
        catch (SQLException e) {
            throw new DAOException("Error while inserting test", e);
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void editTest(int testId, String title) throws DAOException {
        PreparedStatement ps = null;
        Connection connection = null;

        try {
            connection = connectionPool.takeConnection();
            ps = connection.prepareStatement(UPDATE_TEST_SQL);
            ps.setString(1, title);
            ps.setInt(2, testId);

            ps.executeUpdate();
            ps.close();
        }
        catch (ConnectionPoolException e) {
            throw new DAOException("Error in connection pool while updating test", e);
        }
        catch (SQLException e) {
            throw new DAOException("Error while updating test", e);
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void deleteTest(int testId) throws DAOException {
        PreparedStatement ps = null;
        Connection connection = null;

        try {
            connection = connectionPool.takeConnection();
            ps = connection.prepareStatement(DELETE_TEST_SQL);
            ps.setInt(1, testId);

            ps.executeUpdate();
            ps.close();
        }
        catch (ConnectionPoolException e) {
            throw new DAOException("Error in connection pool while deleting test", e);
        }
        catch (SQLException e) {
            throw new DAOException("Error while deleting test", e);
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }
}
