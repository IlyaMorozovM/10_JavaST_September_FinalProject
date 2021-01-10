package by.training.testing.dao;

import by.training.testing.bean.User;
import by.training.testing.dao.exception.DAOException;

public interface UserDAO {
    User signIn(String login, byte[] password) throws DAOException;
    void signUp(String login, byte[] password, String name, String lastname, String email, int roleId) throws DAOException;
}
