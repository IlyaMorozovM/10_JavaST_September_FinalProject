package by.training.testing.controller.command.impl;

import by.training.testing.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * This class contains method, that directs client to the "edit" page.
 *
 * @author Ilya Morozov
 * @version	1.0
 * @since	2020-12-14
 */
public class GoToEditCommand implements Command {

    private static final String REQUEST_PARAM_ENTITY = "entity";
    private static final String REQUEST_PARAM_ID = "id";
    private static final String REQUEST_PARAM_ANSWER = "answer";
    private static final String REQUEST_PARAM_IS_RIGHT = "isRight";
    private static final String EDIT_PAGE_URI = "WEB-INF/jsp/editEntity.jsp";

    /**
     * Method, that directs client to the "edit" page.
     *
     * @param req Request from client.
     * @param resp Response to client.
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession(true);

        String entity = req.getParameter(REQUEST_PARAM_ENTITY);
        session.setAttribute(REQUEST_PARAM_ENTITY, entity);

        if (entity.equals(REQUEST_PARAM_ANSWER)){
            String isRight = req.getParameter(REQUEST_PARAM_IS_RIGHT);
            session.setAttribute(REQUEST_PARAM_IS_RIGHT, isRight);
        }

        int id = Integer.parseInt(req.getParameter(REQUEST_PARAM_ID));
        session.setAttribute(REQUEST_PARAM_ID, id);

        RequestDispatcher dispatcher = req.getRequestDispatcher(EDIT_PAGE_URI);
        dispatcher.forward(req, resp);
    }
}
