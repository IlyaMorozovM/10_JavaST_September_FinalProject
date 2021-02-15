package by.training.testing.controller.command.impl;

import by.training.testing.bean.Test;
import by.training.testing.bean.User;
import by.training.testing.controller.command.Command;
import by.training.testing.service.UserService;
import by.training.testing.service.exception.ServiceException;
import by.training.testing.service.factory.ServiceFactory;

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

        List<User> users;
        List<User> oneUser = new ArrayList<>();

        if ( req.getParameter(REQUEST_PARAM_ONE_USER) == null ||
                req.getParameter(REQUEST_PARAM_ONE_USER).equals("false")) {
            try {
                users = userService.getUsers();
                //session.setAttribute(USERS_SESSION_ATTR, users);
                req.setAttribute(USERS_SESSION_ATTR, users);
                session.removeAttribute(REQUEST_PARAM_LOGIN);
            } catch (ServiceException e) {
                resp.sendRedirect(REDIRECT_COMMAND_ERROR);
            }
        } else {
//            String login = req.getParameter(REQUEST_PARAM_LOGIN);
            String login;
            if (req.getParameter(REQUEST_PARAM_LOGIN) != null) {
                login = req.getParameter(REQUEST_PARAM_LOGIN);
                session.setAttribute(REQUEST_PARAM_LOGIN, login);
            } else {
                login = (String)session.getAttribute(REQUEST_PARAM_LOGIN);
            }
            try {
                oneUser.add(userService.getOneUser(login));
                //session.setAttribute(ONE_USER_SESSION_ATTR, oneUser);
                req.setAttribute(ONE_USER_SESSION_ATTR, oneUser);
            } catch (ServiceException e) {
                resp.sendRedirect(REDIRECT_COMMAND_ERROR);
            }
        }
//        session.setAttribute(USERS_SESSION_ATTR, users);

//TODO: пагинация + разобраться с сессией!

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(DELETE_USERS_PAGE_URI);
        requestDispatcher.forward(req, resp);
    }
}
