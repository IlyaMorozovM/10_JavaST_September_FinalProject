package by.training.testing.dao;

import by.training.testing.bean.User;
import by.training.testing.dao.exception.DAOException;

import java.util.List;

public interface UserDAO {
    List<User> getUsers() throws DAOException;
    List<User> getUsersFromTo(int limit, int offset) throws DAOException;
    void deleteUser(int userId) throws DAOException;
    User signIn(String login, byte[] password) throws DAOException;
    void signUp(String login, byte[] password, String name, String lastname, String email, int roleId) throws DAOException;
}
