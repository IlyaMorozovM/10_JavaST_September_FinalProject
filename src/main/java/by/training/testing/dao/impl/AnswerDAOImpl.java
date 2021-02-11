package by.training.testing.dao.impl;

import by.training.testing.bean.Answer;
import by.training.testing.dao.impl.connection.ConnectionPoolException;
import by.training.testing.dao.AnswerDAO;
import by.training.testing.dao.exception.DAOException;
import by.training.testing.dao.impl.connection.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains methods, that interacts with DB relates to entity "answer".
 *
 * @author Ilya Morozov
 * @version	1.0
 * @since	2020-12-14
 */
public class AnswerDAOImpl implements AnswerDAO {

    private static final String DB_COLUMN_ANSWER = "answer";
    private static final String DB_COLUMN_QUESTION = "question_id";
    private static final String DB_COLUMN_ID = "id";
    private static final String DB_COLUMN_ISRIGHT = "is_right";

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String INSERT_ANSWER_SQL = "INSERT answers(`question_id`, `answer`, `is_right`) VALUES (?,?,?)";
    private static final String DELETE_ANSWER_SQL = "DELETE FROM answers WHERE answers.id = ?";
    private static final String UPDATE_ANSWER_SQL = "UPDATE answers SET answers.answer = ?, answers.is_right = ? WHERE answers.id = ?";
    private static final String SELECT_ANSWER_SQL = "SELECT * FROM answers WHERE answers.question_id = ?";

    /**
     * Method, that validates params (login, email, name, lastname, password, role),
     * when admin registers users.
     *
     * @param questionId Question ID, to which answers relate.
     * @return List, that contains all answers.
     */
    @Override
    public List<Answer> getAnswers(int questionId) throws DAOException {

        PreparedStatement ps;
        Connection connection = null;
        ResultSet rs;

        try {
            connection = connectionPool.takeConnection();
            ps = connection.prepareStatement(SELECT_ANSWER_SQL);
            ps.setInt(1, questionId);

            rs = ps.executeQuery();
            if(rs == null)
                return null;

            List<Answer> answers = new ArrayList<Answer>();
            while(rs.next()) {
                answers.add(new Answer(rs.getInt(DB_COLUMN_ID), rs.getInt(DB_COLUMN_QUESTION), rs.getString(DB_COLUMN_ANSWER), rs.getBoolean(DB_COLUMN_ISRIGHT)));
            }
            ps.close();
            rs.close();
            return answers;
        }
        catch (ConnectionPoolException e) {
            throw new DAOException("Error in connection pool while getting answers", e);
        }
        catch (SQLException e) {
            throw new DAOException("Error while getting answers", e);
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void addAnswer(int questionId, String answer, boolean isRight) throws DAOException {
        PreparedStatement ps;
        Connection connection = null;

        try {
            connection = connectionPool.takeConnection();
            ps = connection.prepareStatement(INSERT_ANSWER_SQL);
            ps.setInt(1, questionId);
            ps.setString(2, answer);
            ps.setBoolean(3, isRight);

            ps.executeUpdate();
            ps.close();
        }
        catch (ConnectionPoolException e) {
            throw new DAOException("Error in connection pool while inserting answer", e);
        }
        catch (SQLException e) {
            throw new DAOException("Error while inserting answer", e);
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void editAnswer(int answerId, String answer, boolean isRight) throws DAOException {
        PreparedStatement ps;
        Connection connection = null;

        try {
            connection = connectionPool.takeConnection();
            ps = connection.prepareStatement(UPDATE_ANSWER_SQL);
            ps.setString(1, answer);
            ps.setBoolean(2, isRight);
            ps.setInt(3, answerId);

            ps.executeUpdate();
            ps.close();
        }
        catch (ConnectionPoolException e) {
            throw new DAOException("Error in connection pool while updating answer", e);
        }
        catch (SQLException e) {
            throw new DAOException("Error while updating answer", e);
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void deleteAnswer(int answerId) throws DAOException {
        PreparedStatement ps = null;
        Connection connection = null;

        try {
            connection = connectionPool.takeConnection();
            ps = connection.prepareStatement(DELETE_ANSWER_SQL);
            ps.setInt(1, answerId);

            ps.executeUpdate();
            ps.close();
        }
        catch (ConnectionPoolException e) {
            throw new DAOException("Error in connection pool while deleting answer", e);
        }
        catch (SQLException e) {
            throw new DAOException("Error while deleting answer", e);
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }
}
