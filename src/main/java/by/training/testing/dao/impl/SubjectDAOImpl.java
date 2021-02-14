package by.training.testing.dao.impl;

import by.training.testing.bean.Subject;
import by.training.testing.dao.SubjectDAO;
import by.training.testing.dao.exception.DAOException;
import by.training.testing.dao.impl.connection.ConnectionPool;
import by.training.testing.dao.impl.connection.ConnectionPoolException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains methods, that interacts with DB relates to entity "subject".
 *
 * @author Ilya Morozov
 * @version	1.0
 * @since	2020-12-14
 */
public class SubjectDAOImpl implements SubjectDAO {

    private static final String DB_COLUMN_NAME = "name";
    private static final String DB_COLUMN_ID = "id";

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String INSERT_SUBJECT_SQL = "INSERT subjects(`name`) VALUES (?)";
    private static final String DELETE_SUBJECT_SQL = "DELETE FROM subjects WHERE subjects.id = ?";
    private static final String UPDATE_SUBJECT_SQL = "UPDATE subjects SET subjects.name = ? WHERE subjects.id = ?";
    private static final String SELECT_SUBJECT_SQL = "SELECT * FROM subjects";
    private static final String FIND_IN_RANGE_SQL = "SELECT * FROM subjects LIMIT ? OFFSET ?";

    /**
     * Method that receives all subjects from DB.
     *
     * @return List of all subjects.
     * @throws DAOException Thrown when a DB connection exception or DB exception occurs.
     */
    @Override
    public List<Subject> getSubjects() throws DAOException {
        PreparedStatement ps;
        Connection connection = null;
        ResultSet rs;

        try {
            connection = connectionPool.takeConnection();
            ps = connection.prepareStatement(SELECT_SUBJECT_SQL);

            rs = ps.executeQuery();
            if(rs == null)
                return null;

            List<Subject> subjects = new ArrayList<Subject>();
            while(rs.next()) {
                subjects.add(new Subject(rs.getInt(DB_COLUMN_ID), rs.getString(DB_COLUMN_NAME)));
            }
            ps.close();
            rs.close();
            return subjects;
        }
        catch (ConnectionPoolException e) {
            throw new DAOException("Error in connection pool while getting subjects", e);
        }
        catch (SQLException e) {
            throw new DAOException("Error while getting subjects", e);
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }

    /**
     * Method that receives subjects from DB.
     *
     * @param limit Number of subjects returned.
     * @param offset Offset of records in the database.
     * @return List of subjects with size = limit.
     * @throws DAOException Thrown when a DB connection exception or DB exception occurs.
     */
    @Override
    public List<Subject> getSubjectsFromTo(int limit, int offset) throws DAOException {
        ResultSet resultSet;
        PreparedStatement ps;
        Connection connection = null;

        List<Subject> subjects = new ArrayList<>();
        try {
            connection = connectionPool.takeConnection();
            ps = connection.prepareStatement(FIND_IN_RANGE_SQL);
            ps.setInt(1, limit);
            ps.setInt(2, offset);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                subjects.add(new Subject(resultSet.getInt(DB_COLUMN_ID), resultSet.getString(DB_COLUMN_NAME)));
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("Error in connection pool while getting subjects", e);
        }
        catch (SQLException e) {
            throw new DAOException("Error while getting subjects", e);
        }
        finally {
            connectionPool.returnConnection(connection);
        }

        return subjects;
    }

    /**
     * Method that inserts subject with some value (name) into DB.
     *
     * @param name Name of subject, that editing.
     * @throws DAOException Thrown when a DB connection exception or DB exception occurs.
     */
    @Override
    public void addSubject(String name) throws DAOException {
        PreparedStatement ps;
        Connection connection = null;

        try {
            connection = connectionPool.takeConnection();
            ps = connection.prepareStatement(INSERT_SUBJECT_SQL);
            ps.setString(1, name);

            ps.executeUpdate();
            ps.close();
        }
        catch (ConnectionPoolException e) {
            throw new DAOException("Error in connection pool while inserting subject", e);
        }
        catch (SQLException e) {
            throw new DAOException("Error while inserting subject", e);
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }

    /**
     * Method that edits subject with some new values (id, name)
     * and inserts into DB.
     *
     * @param id Subject ID, to which subject relate.
     * @param name Name of subject, that editing.
     * @throws DAOException Thrown when a DB connection exception or DB exception occurs.
     */
    @Override
    public void editSubject(int id, String name) throws DAOException {
        PreparedStatement ps;
        Connection connection = null;

        try {
            connection = connectionPool.takeConnection();
            ps = connection.prepareStatement(UPDATE_SUBJECT_SQL);
            ps.setString(1, name);
            ps.setInt(2, id);

            ps.executeUpdate();
            ps.close();
        }
        catch (ConnectionPoolException e) {
            throw new DAOException("Error in connection pool while updating subject", e);
        }
        catch (SQLException e) {
            throw new DAOException("Error while updating subject", e);
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }

    /**
     * Method that deletes subject by ID.
     *
     * @param id Subject ID, to which subject relate.
     * @throws DAOException Thrown when a DB connection exception or DB exception occurs.
     */
    @Override
    public void deleteSubject(int id) throws DAOException {
        PreparedStatement ps;
        Connection connection = null;

        try {
            connection = connectionPool.takeConnection();
            ps = connection.prepareStatement(DELETE_SUBJECT_SQL);
            ps.setInt(1, id);

            ps.executeUpdate();
            ps.close();
        }
        catch (ConnectionPoolException e) {
            throw new DAOException("Error in connection pool while deleting subject", e);
        }
        catch (SQLException e) {
            throw new DAOException("Error while deleting subject", e);
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }
}
