package by.training.testing.controller.command.impl;

import by.training.testing.bean.Test;
import by.training.testing.bean.User;
import by.training.testing.controller.command.Command;
import by.training.testing.service.UserService;
import by.training.testing.service.exception.ServiceException;
import by.training.testing.service.factory.ServiceFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains method, that directs client to the "delete" page.
 *
 * @author Ilya Morozov
 * @version	1.0
 * @since	2020-12-14
 */
public class GoToDeleteUsersCommand implements Command {

    private static final String DELETE_USERS_PAGE_URI = "WEB-INF/jsp/deleteUsers.jsp";
    private static final String USERS_SESSION_ATTR = "allUsers";
    private static final String ONE_USER_SESSION_ATTR = "oneUser";
    private static final String REQUEST_PARAM_LOGIN = "login";
    private static final String REQUEST_PARAM_ONE_USER = "showOneUser";
    private static final String REQUEST_PARAM_CURRENT_PAGE = "currentPage";
    private static final String REQUEST_ATTR_MAX_PAGE = "maxPage";
    private static final String REDIRECT_COMMAND_ERROR = "Controller?command=go_to_main&error=error";
    private static final int USER_AMOUNT_ON_PAGE = 5;

    private static final Logger LOGGER = LogManager.getLogger(GoToDeleteUsersCommand.class);

    /**
     * Method, that directs client to the "delete" page.
     *
     * @param req Request from client.
     * @param resp Response to client.
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {


        HttpSession session = req.getSession(true);
        session.removeAttribute(ONE_USER_SESSION_ATTR);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        int page = 1;
        try {
            if (req.getParameter(REQUEST_PARAM_CURRENT_PAGE) != null) {
                page = Integer.parseInt(req.getParameter(REQUEST_PARAM_CURRENT_PAGE));
            }
        } catch (NumberFormatException ex) {
            LOGGER.warn(ex);
        }

        List<User> users;
        List<User> allUsers;
        List<User> oneUser = new ArrayList<>();

        int countOfUsers = 0;

        if (req.getParameter(REQUEST_PARAM_ONE_USER) == null ||
                req.getParameter(REQUEST_PARAM_ONE_USER).equals("false")) {
            try {
                allUsers = userService.getUsers();
                countOfUsers = allUsers.size();
                users = userService.getUsersFromTo(USER_AMOUNT_ON_PAGE, (page - 1) * USER_AMOUNT_ON_PAGE);
                session.setAttribute(USERS_SESSION_ATTR, users);
                session.removeAttribute(REQUEST_PARAM_LOGIN);
                session.removeAttribute(ONE_USER_SESSION_ATTR);
            } catch (ServiceException e) {
                resp.sendRedirect(REDIRECT_COMMAND_ERROR);
            }
        } else {
            String login;
            if (req.getParameter(REQUEST_PARAM_LOGIN) != null) {
                login = req.getParameter(REQUEST_PARAM_LOGIN);
                session.setAttribute(REQUEST_PARAM_LOGIN, login);
            } else {
                login = (String)session.getAttribute(REQUEST_PARAM_LOGIN);
            }
            try {
                oneUser.add(userService.getOneUser(login));
                session.setAttribute(ONE_USER_SESSION_ATTR, oneUser);
                session.removeAttribute(USERS_SESSION_ATTR);
                countOfUsers = 1;
            } catch (ServiceException e) {
                resp.sendRedirect(REDIRECT_COMMAND_ERROR);
            }
        }

        //----------------------------------------------------------------

        if (countOfUsers % USER_AMOUNT_ON_PAGE != 0) {
            session.setAttribute(REQUEST_ATTR_MAX_PAGE, countOfUsers / USER_AMOUNT_ON_PAGE + 1);
        } else {
            session.setAttribute(REQUEST_ATTR_MAX_PAGE, countOfUsers / USER_AMOUNT_ON_PAGE);
        }
        if (req.getParameter(REQUEST_PARAM_CURRENT_PAGE) != null) {
            req.setAttribute(REQUEST_PARAM_CURRENT_PAGE, req.getParameter(REQUEST_PARAM_CURRENT_PAGE));
        } else {
            req.setAttribute(REQUEST_PARAM_CURRENT_PAGE, 1);
        }
        //-------------------------------------------------------------------


        RequestDispatcher requestDispatcher = req.getRequestDispatcher(DELETE_USERS_PAGE_URI);
        requestDispatcher.forward(req, resp);
    }
}
