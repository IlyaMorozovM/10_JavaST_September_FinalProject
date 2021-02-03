package by.training.testing.controller.command.impl;

import by.training.testing.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class contains method, that directs admin to the "sign up" page.
 *
 * @author Ilya Morozov
 * @version	1.0
 * @since	2020-12-14
 */
public class GoToSignUpPageCommand implements Command {

    private static final String SIGNUP_PAGE_URI = "WEB-INF/jsp/signUp.jsp";

    /**
     * Method, that directs admin to the "sign up" page.
     * Only admin has access to the "sign up" page.
     *
     * @param req Request from client.
     * @param resp Response to client.
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(SIGNUP_PAGE_URI);
        dispatcher.forward(req, resp);
    }
}
