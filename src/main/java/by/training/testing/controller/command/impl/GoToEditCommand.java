package by.training.testing.controller.command.impl;

import by.training.testing.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class contains method, that directs client to the "edit" page.
 *
 * @author Ilya Morozov
 * @version	1.0
 * @since	2020-12-14
 */
public class GoToEditCommand implements Command {

    private static final String EDIT_PAGE_URI = "WEB-INF/jsp/editEntity.jsp";

    /**
     * Method, that directs client to the "edit" page.
     *
     * @param req Request from client.
     * @param resp Response to client.
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        RequestDispatcher dispatcher = req.getRequestDispatcher(EDIT_PAGE_URI);
        dispatcher.forward(req, resp);
    }
}
