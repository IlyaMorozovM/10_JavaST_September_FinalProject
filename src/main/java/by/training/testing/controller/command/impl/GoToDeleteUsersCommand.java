package by.training.testing.controller.command.impl;

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
import java.util.List;

public class GoToDeleteUsersCommand implements Command {

    private static final String DELETE_USERS_PAGE_URI = "WEB-INF/jsp/deleteUsers.jsp";
    private static final String USERS_SESSION_ATTR = "users";
    private static final String REDIRECT_COMMAND_ERROR = "Controller?command=go_to_main&error=error";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {


        HttpSession session = req.getSession(true);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        List<User> users = null;
        try {
            users = userService.getUsers();
        } catch (ServiceException e) {
            e.printStackTrace();
            resp.sendRedirect(REDIRECT_COMMAND_ERROR);
        }
        session.setAttribute(USERS_SESSION_ATTR, users);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(DELETE_USERS_PAGE_URI);
        requestDispatcher.forward(req, resp);
    }
}
