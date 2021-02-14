package by.training.testing.dao.impl;

import by.training.testing.bean.User;
import by.training.testing.dao.UserDAO;
import by.training.testing.dao.exception.DAOException;
import by.training.testing.dao.exception.DAOUserAlreadyExistsException;
import by.training.testing.dao.impl.connection.ConnectionPool;
import by.training.testing.dao.impl.connection.ConnectionPoolException;
import by.training.testing.service.UserService;
import by.training.testing.service.factory.ServiceFactory;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains methods, that interacts with DB relates to entity "user".
 *
 * @author Ilya Morozov
 * @version	1.0
 * @since	2020-12-14
 */
public class UserDAOImpl implements UserDAO {

    private static final String DB_COLUMN_NAME = "name";
    private static final String DB_COLUMN_LASTNAME = "lastname";
    private static final String DB_COLUMN_LOGIN = "login";
    private static final String DB_COLUMN_EMAIL = "email";
    private static final String DB_COLUMN_ROLE = "roleName";
    private static final String DB_COLUMN_ID = "id";

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String DELETE_USER_SQL = "DELETE FROM users WHERE users.id = ?";
    private static final String INSERT_USER_SQL = "INSERT users(login, pass_hash, `name`, lastname, email, `role`) VALUES (?,?,?,?,?,?)";
    private static final String SIGN_IN_SQL = "SELECT u.*, r.name as roleName FROM users u INNER JOIN roles r ON u.role = r.id where u.login = ? and u.pass_hash = ?";
    private static final String SELECT_USER_SQL = "SELECT u.*, r.name as roleName FROM users u INNER JOIN roles r ON u.role = r.id";
    private static final String FIND_IN_RANGE_SQL = "SELECT u.*, r.name as roleName FROM users u INNER JOIN roles r ON u.role = r.id LIMIT ? OFFSET ?";

    /**
     * Method that receives all users from DB.
     *
     * @return List of all users.
     * @throws DAOException Thrown when a DB connection exception or DB exception occurs.
     */
    @Override
    public List<User> getUsers() throws DAOException {
        PreparedStatement ps;
        Connection connection = null;
        ResultSet rs;
        //TODO: во время финиш тест когда меняешь язык, остаются аттрибуты, сделать чтоб было так же при добавлении entity
        try {
            connection = connectionPool.takeConnection();
            ps = connection.prepareStatement(SELECT_USER_SQL);
            rs = ps.executeQuery();
            if(rs == null)
                return null;

            List<User> users = new ArrayList<>();
            while(rs.next()) {
                users.add(new User(rs.getInt(DB_COLUMN_ID), rs.getString(DB_COLUMN_LOGIN), rs.getString(DB_COLUMN_NAME),
                           rs.getString(DB_COLUMN_LASTNAME), rs.getString(DB_COLUMN_EMAIL), rs.getString(DB_COLUMN_ROLE)));
            }
            ps.close();
            rs.close();
            return users;
        }
        catch (ConnectionPoolException e) {
            throw new DAOException("Error in connection pool while getting users", e);
        }
        catch (SQLException e) {
            throw new DAOException("Error while getting users", e);
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }

    /**
     * Method that receives users from DB.
     *
     * @param limit Number of users returned.
     * @param offset Offset of records in the database.
     * @return List of users with size = limit.
     * @throws DAOException Thrown when a DB connection exception or DB exception occurs.
     */
    @Override
    public List<User> getUsersFromTo(int limit, int offset) throws DAOException {
        ResultSet resultSet;
        PreparedStatement ps;
        Connection connection = null;

        List<User> users = new ArrayList<>();
        try {
            connection = connectionPool.takeConnection();
            ps = connection.prepareStatement(FIND_IN_RANGE_SQL);
            ps.setInt(1, limit);
            ps.setInt(2, offset);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                users.add(new User(resultSet.getInt(DB_COLUMN_ID), resultSet.getString(DB_COLUMN_LOGIN), resultSet.getString(DB_COLUMN_NAME),
                        resultSet.getString(DB_COLUMN_LASTNAME), resultSet.getString(DB_COLUMN_EMAIL), resultSet.getString(DB_COLUMN_ROLE)));
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException("Error in connection pool while getting users", e);
        }
        catch (SQLException e) {
            throw new DAOException("Error while getting users", e);
        }
        finally {
            connectionPool.returnConnection(connection);
        }

        return users;
    }

    /**
     * Method that deletes user by ID.
     *
     * @param userId User ID, to which user relate.
     * @throws DAOException Thrown when a DB connection exception or DB exception occurs.
     */
    @Override
    public void deleteUser(int userId) throws DAOException {
        PreparedStatement ps;
        Connection connection = null;

        try {
            connection = connectionPool.takeConnection();
            ps = connection.prepareStatement(DELETE_USER_SQL);
            ps.setInt(1, userId);

            ps.executeUpdate();
            ps.close();
        }
        catch (ConnectionPoolException e) {
            throw new DAOException("Error in connection pool while deleting user", e);
        }
        catch (SQLException e) {
            throw new DAOException("Error while deleting user", e);
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }

    /**
     * Authorization method, returns the user
     * with the specified username and password, if such exists.
     *
     * @param login User login.
     * @param password User password.
     * @return User with specified username and password, if such exists.
     * @throws DAOException Thrown when a DB connection exception or DB exception occurs.
     */
    @Override
    public User signIn(String login, byte[] password) throws DAOException {
        PreparedStatement ps;
        Connection connection = null;
        ResultSet rs;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        try {
            connection = connectionPool.takeConnection();
            ps = connection.prepareStatement(SIGN_IN_SQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, login);
            ps.setString(2, userService.getMD5Hash(password));

            rs = ps.executeQuery();
            if(rs == null)
                return null;

            rs.last();
            if(rs.getRow() == 1) {
                User user = new User(rs.getInt(DB_COLUMN_ID), rs.getString(DB_COLUMN_LOGIN), rs.getString(DB_COLUMN_NAME),
                        rs.getString(DB_COLUMN_LASTNAME), rs.getString(DB_COLUMN_EMAIL), rs.getString(DB_COLUMN_ROLE));
                ps.close();
                rs.close();
                return user;
            }
        }
        catch (ConnectionPoolException e) {
            throw new DAOException("Error in connection pool while authorizing user", e);
        }
        catch (SQLException e) {
            throw new DAOException("Error while authorizing user", e);
        }
        catch (NoSuchAlgorithmException e) {
            throw new DAOException("Password hashing error", e);
        }
        finally {
            connectionPool.returnConnection(connection);
        }
        return null;
    }

    /**
     * This method registers user with parameters passed to the method.
     *
     * @param login User login.
     * @param password User password.
     * @param name User name.
     * @param lastname User lastname.
     * @param email User email.
     * @param roleId User role (1 = tutor, 2 = student, 3 = admin).
     * @throws DAOException Thrown when a DB connection exception or DB exception occurs.
     */
    @Override
    public void signUp(String login, byte[] password, String name, String lastname, String email, int roleId) throws DAOException {

        PreparedStatement ps;
        Connection connection = null;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        try {
            connection = connectionPool.takeConnection();
            ps = connection.prepareStatement(INSERT_USER_SQL);
            ps.setString(1, login);
            ps.setString(2, userService.getMD5Hash(password));
            ps.setString(3, name);
            ps.setString(4, lastname);
            ps.setString(5, email);
            ps.setInt(6, roleId);

            ps.executeUpdate();
            ps.close();
        }
        catch (ConnectionPoolException e) {
            throw new DAOException("Error in connection pool while registering a new user", e);
        }
        catch (SQLIntegrityConstraintViolationException e) {
            throw new DAOUserAlreadyExistsException("Login already exists", e);
        }
        catch (SQLException e) {
            throw new DAOException("Error while registering a new user", e);
        }
        catch (NoSuchAlgorithmException e) {
            throw new DAOException("Password hashing error", e);
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }
}
