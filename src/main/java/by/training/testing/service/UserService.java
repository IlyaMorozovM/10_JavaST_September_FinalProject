package by.training.testing.service;

import by.training.testing.service.exception.ServiceException;
import by.training.testing.bean.User;

public interface UserService {
        User signIn(String login, byte[] password) throws ServiceException;
        boolean signUp(String login, byte[] password, String name, String lastname, String email, String role) throws ServiceException;
}
