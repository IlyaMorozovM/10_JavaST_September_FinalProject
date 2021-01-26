package by.training.testing.service;

import by.training.testing.bean.User;
import by.training.testing.service.exception.ServiceException;

import java.util.List;

public interface UserService {
        List<User> getUsers() throws ServiceException;
        List<User> getUser(List<User> users, String login) throws ServiceException;
        boolean deleteUser(int userId) throws ServiceException;
        User signIn(String login, byte[] password) throws ServiceException;
        boolean signUp(String login, byte[] password, String name, String lastname, String email, String role) throws ServiceException;
}
