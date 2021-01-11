package by.training.testing.controller.command.impl;

import by.training.testing.service.UserService;
import by.training.testing.service.exception.ServiceException;
import by.training.testing.service.exception.ServiceUserAlreadyExistsException;
import by.training.testing.controller.command.Command;
import by.training.testing.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpCommand implements Command {

    private static final String REQUEST_PARAM_LOGIN = "login";
    private static final String REQUEST_PARAM_EMAIL = "email";
    private static final String REQUEST_PARAM_NAME = "name";
    private static final String REQUEST_PARAM_LASTNAME = "lastname";
    private static final String REQUEST_PARAM_PASSWORD = "password";
    private static final String REQUEST_PARAM_ROLE = "role";
    private static final String REDIRECT_COMMAND_SUCCESS = "Controller?command=go_to_signup&register=success";
    private static final String REDIRECT_COMMAND_ERROR = "Controller?command=go_to_signup&error=error";
    private static final String REDIRECT_COMMAND_ERROR_DUPLICATE = "Controller?command=go_to_signup&error=unique";
    private static final int DEFAULT_ROLE_ID = 2;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String login = req.getParameter(REQUEST_PARAM_LOGIN);
        String email = req.getParameter(REQUEST_PARAM_EMAIL);
        String name = req.getParameter(REQUEST_PARAM_NAME);
        String lastname = req.getParameter(REQUEST_PARAM_LASTNAME);
        String password = req.getParameter(REQUEST_PARAM_PASSWORD);
        String role = req.getParameter(REQUEST_PARAM_ROLE);
        //int roleId = DEFAULT_ROLE_ID;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        boolean registrationResult;

        try {
//            registrationResult = userService.signUp(login, password.getBytes(), name, lastname, email, roleId);
            registrationResult = userService.signUp(login, password.getBytes(), name, lastname, email, role);

            if(registrationResult)
                resp.sendRedirect(REDIRECT_COMMAND_SUCCESS);
            else
                resp.sendRedirect(REDIRECT_COMMAND_ERROR);
        }
        catch (ServiceUserAlreadyExistsException e) {
            resp.sendRedirect(REDIRECT_COMMAND_ERROR_DUPLICATE);
        }
        catch (ServiceException e) {
            resp.sendRedirect(REDIRECT_COMMAND_ERROR);
        }
    }
}
