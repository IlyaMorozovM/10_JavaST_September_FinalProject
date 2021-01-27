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
    private static final String DB_COLUMN_TEST = "test";
    private static final String DB_COLUMN_STUDENT_LOGIN = "student_login";

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String INSERT_RESULT_SQL = "INSERT results(`test`, `student_login`, `points`) VALUES (?,?,?)";
    private static final String SELECT_RESULT_SQL = "SELECT * FROM results WHERE results.test = ?";

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
