package by.training.testing.controller.command.validation;

import by.training.testing.service.validator.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class contains method, that validates params (login, email,
 * name, lastname, password, role), when admin registers users.
 *
 * @author Ilya Morozov
 * @version	1.0
 * @since	2020-12-14
 */
public class UserValidationCommand implements ValidationCommand{

    private static final String REQUEST_PARAM_LOGIN = "login";
    private static final String REQUEST_PARAM_EMAIL = "email";
    private static final String REQUEST_PARAM_NAME = "name";
    private static final String REQUEST_PARAM_LASTNAME = "lastname";
    private static final String REQUEST_PARAM_PASSWORD = "password";
    private static final String REQUEST_PARAM_ROLE = "role";

    /**
     * Method, that validates params (login, email, name, lastname, password, role),
     * when admin registers users.
     *
     * @param request Request from client.
     * @param response Response to client.
     */
    @Override
    public boolean isValid(HttpServletRequest request, HttpServletResponse response) {
        UserValidator validator = new UserValidator();

        if (!validator.isValidLogin(request.getParameter(REQUEST_PARAM_LOGIN))){
            request.setAttribute("invalidLogin", true);
            return false;
        }

        if (!validator.isValidEmail(request.getParameter(REQUEST_PARAM_EMAIL))){
            request.setAttribute("invalidEmail", true);
            return false;
        }

        if (!validator.isValidName(request.getParameter(REQUEST_PARAM_NAME))){
            request.setAttribute("invalidName", true);
            return false;
        }

        if (!validator.isValidLastname(request.getParameter(REQUEST_PARAM_LASTNAME))){
            request.setAttribute("invalidLastname", true);
            return false;
        }

        if (!validator.isValidPassword(request.getParameter(REQUEST_PARAM_PASSWORD))){
            request.setAttribute("invalidPassword", true);
            return false;
        }

        if (!validator.isValidRole(request.getParameter(REQUEST_PARAM_ROLE))){
            request.setAttribute("invalidRole", true);
            return false;
        }

        return true;
    }
}
