package by.training.testing.service.impl;

import by.training.testing.bean.User;
import by.training.testing.dao.UserDAO;
import by.training.testing.dao.exception.DAOException;
import by.training.testing.dao.exception.DAOUserAlreadyExistsException;
import by.training.testing.dao.factory.DAOFactory;
import by.training.testing.service.UserService;
import by.training.testing.service.exception.ServiceException;
import by.training.testing.service.exception.ServiceUserAlreadyExistsException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class contains methods, that interacts with entity "user".
 *
 * @author Ilya Morozov
 * @version	1.0
 * @since	2020-12-14
 */
public class UserServiceImpl implements UserService {

    UserDAO userDAO;

    public UserServiceImpl() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        userDAO = daoFactory.getUserDao();
    }

    /**
     * This method calculates the password hash code using MD-5 algorithm.
     *
     * @param password User password.
     * @return Hash code of the password, calculated by the MD-5 algorithm.
     * @throws NoSuchAlgorithmException This exception is thrown when a particular
     * cryptographic algorithm is requested but is not available in the environment.
     */
    @Override
    public String getMD5Hash(byte[] password) throws NoSuchAlgorithmException {
        String generatedPassword;

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password);
        byte[] bytes = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }
        generatedPassword = sb.toString();
        return generatedPassword;
    }

    /**
     * Method that receives all users from DB.
     *
     * @return List of all users.
     * @throws ServiceException Thrown when DAO exception is caught.
     */
    @Override
    public List<User> getUsers() throws ServiceException {
        try {
            return userDAO.getUsers();
        }
        catch (DAOException e) {
            throw new ServiceException("Error while getting users", e);
        }
    }

    /**
     * Method that receives users from DB.
     *
     * @param limit Number of users returned.
     * @param offset Offset of records in the database.
     * @return List of users with size = limit.
     * @throws ServiceException Thrown when DAO exception is caught.
     */
    @Override
    public List<User> getUsersFromTo(int limit, int offset) throws ServiceException {
        try {
            return userDAO.getUsersFromTo(limit, offset);
        }
        catch (DAOException e) {
            throw new ServiceException("Error while getting users", e);
        }
    }

    /**
     * Method that receives user from users list by login.
     *
     * @param users Users list.
     * @param login Login of the received user.
     * @return List, that contains 1 received user.
     */
    @Override
    public List<User> getUser(List<User> users, String login) {
        List<User> oneUser = new ArrayList<>();
        for (User user: users){
            if (user.getLogin().equals(login)){
                oneUser.add(user);
                break;
            }
        }
        return oneUser;
    }

    /**
     * Method that deletes user by ID.
     *
     * @param userId User ID, to which user relate.
     * @return True - if operation was successful, otherwise - false.
     * @throws ServiceException Thrown when DAO exception is caught.
     */
    @Override
    public boolean deleteUser(int userId) throws ServiceException {
        if(userId == 0)
            return false;
///TODO: убрать эти false и поменять возвр значения на void, так как уже есть проверка
        try {
            userDAO.deleteUser(userId);
        }
        catch (DAOException e) {
            throw new ServiceException("Error while deleting user", e);
        }
        return true;
    }

    /**
     * Authorization method, returns the user
     * with the specified username and password, if such exists.
     *
     * @param login User login.
     * @param password User password.
     * @return User with specified username and password, if such exists.
     * @throws ServiceException Thrown when DAO exception is caught.
     */
    @Override
    public User signIn(String login, byte[] password) throws ServiceException {
        if(login.equals("") || password.length == 0)
            return null;

        try {
            return userDAO.signIn(login, password);
        }
        catch (DAOException e) {
            throw new ServiceException("Error while signing in", e);
        }
    }

    /**
     * This method registers user with parameters passed to the method.
     *
     * @param login User login.
     * @param password User password.
     * @param name User name.
     * @param lastname User lastname.
     * @param email User email.
     * @param role User role.
     * @return True - if operation was successful, otherwise - false.
     * @throws ServiceException Thrown when DAO exception is caught.
     */
    @Override
    public boolean signUp(String login, byte[] password, String name, String lastname, String email, String role) throws ServiceException {
        if(login.equals("") || Arrays.toString(password).equals("") || name.equals("") || lastname.equals("") || email.equals("") || role.equals(""))
            return false;

        try {
            userDAO.signUp(login, password, name, lastname, email, Integer.parseInt(role));
        }
        catch (DAOUserAlreadyExistsException e) {
            throw new ServiceUserAlreadyExistsException("User with such login already exists", e);
        }
        catch (DAOException e) {
            throw new ServiceException("Error while singing up", e);
        }
        catch (NumberFormatException e) {
            throw new ServiceException("Role should be a number", e);
        }
        return true;
    }
}
