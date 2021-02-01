package by.training.testing.controller.command.impl;

import by.training.testing.controller.command.validation.UserValidationCommand;
import by.training.testing.controller.command.validation.ValidationCommand;
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
    private static final String REDIRECT_COMMAND_ERROR_LOGIN = "Controller?command=go_to_signup&error=login";
    private static final String REDIRECT_COMMAND_ERROR_EMAIL = "Controller?command=go_to_signup&error=email";
    private static final String REDIRECT_COMMAND_ERROR_PASSWORD = "Controller?command=go_to_signup&error=password";
    private static final String REDIRECT_COMMAND_ERROR_NAME = "Controller?command=go_to_signup&error=name";
    private static final String REDIRECT_COMMAND_ERROR_LASTNAME = "Controller?command=go_to_signup&error=lastname";
    private static final String REDIRECT_COMMAND_ERROR_ROLE = "Controller?command=go_to_signup&error=role";
    private static final String REDIRECT_COMMAND_ERROR_DUPLICATE = "Controller?command=go_to_signup&error=unique";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        ValidationCommand userValidationCommand = new UserValidationCommand();
        if (userValidationCommand.isValid(req, resp)) {

            String login = req.getParameter(REQUEST_PARAM_LOGIN);
            String email = req.getParameter(REQUEST_PARAM_EMAIL);
            String name = req.getParameter(REQUEST_PARAM_NAME);
            String lastname = req.getParameter(REQUEST_PARAM_LASTNAME);
            String password = req.getParameter(REQUEST_PARAM_PASSWORD);
            String role = req.getParameter(REQUEST_PARAM_ROLE);

            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            UserService userService = serviceFactory.getUserService();

            boolean registrationResult;

            try {
                registrationResult = userService.signUp(login, password.getBytes(), name, lastname, email, role);

                if (registrationResult)
                    resp.sendRedirect(REDIRECT_COMMAND_SUCCESS);
                else
                    resp.sendRedirect(REDIRECT_COMMAND_ERROR);
            } catch (ServiceUserAlreadyExistsException e) {
                resp.sendRedirect(REDIRECT_COMMAND_ERROR_DUPLICATE);
            } catch (ServiceException e) {
                resp.sendRedirect(REDIRECT_COMMAND_ERROR);
            }
        } else {
            if (req.getAttribute("invalidLogin") != null && req.getAttribute("invalidLogin").equals(true)){
                resp.sendRedirect(REDIRECT_COMMAND_ERROR_LOGIN);
            } else if (req.getAttribute("invalidEmail") != null && req.getAttribute("invalidEmail").equals(true)){
                resp.sendRedirect(REDIRECT_COMMAND_ERROR_EMAIL);
            } else if (req.getAttribute("invalidPassword") != null && req.getAttribute("invalidPassword").equals(true)){
                resp.sendRedirect(REDIRECT_COMMAND_ERROR_PASSWORD);
            } else if (req.getAttribute("invalidName") != null && req.getAttribute("invalidName").equals(true)){
                resp.sendRedirect(REDIRECT_COMMAND_ERROR_NAME);
            } else if (req.getAttribute("invalidLastname") != null && req.getAttribute("invalidLastname").equals(true)){
                resp.sendRedirect(REDIRECT_COMMAND_ERROR_LASTNAME);
            } else if (req.getAttribute("invalidRole") != null && req.getAttribute("invalidRole").equals(true)){
                resp.sendRedirect(REDIRECT_COMMAND_ERROR_ROLE);
            } else {
                resp.sendRedirect(REDIRECT_COMMAND_ERROR);
            }
        }
    }
}
