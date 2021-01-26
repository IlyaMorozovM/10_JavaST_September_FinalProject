package by.training.testing.service.impl;

import by.training.testing.bean.User;
import by.training.testing.dao.TestDAO;
import by.training.testing.dao.UserDAO;
import by.training.testing.dao.exception.DAOException;
import by.training.testing.dao.exception.DAOUserAlreadyExistsException;
import by.training.testing.dao.factory.DAOFactory;
import by.training.testing.service.UserService;
import by.training.testing.service.exception.ServiceException;
import by.training.testing.service.exception.ServiceUserAlreadyExistsException;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public List<User> getUsers() throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDao();

        try {
            return userDAO.getUsers();
        }
        catch (DAOException e) {
            throw new ServiceException("Error while getting users", e);
        }
    }

    @Override
    public List<User> getUser(List<User> users, String login) throws ServiceException {
        List<User> oneUser = new ArrayList<>();
        for (User user: users){
            if (user.getLogin().equals(login)){
                oneUser.add(user);
                break;
            }
        }
        return oneUser;
    }

    @Override
    public boolean deleteUser(int userId) throws ServiceException {
        if(userId == 0)
            return false;

        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDao();

        try {
            userDAO.deleteUser(userId);
        }
        catch (DAOException e) {
            throw new ServiceException("Error while deleting user", e);
        }
        return true;
    }

    @Override
    public User signIn(String login, byte[] password) throws ServiceException {

        if(login.equals("") || password.equals(""))
            return null;

        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDao();

        try {
            return userDAO.signIn(login, password);
        }
        catch (DAOException e) {
            throw new ServiceException("Error while signing in", e);
        }
    }

    @Override
    public boolean signUp(String login, byte[] password, String name, String lastname, String email, String role) throws ServiceException {

        if(login.equals("") || password.equals("") || name.equals("") || lastname.equals("") || email.equals("") || role.equals(""))
            return false;

        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDao();

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
