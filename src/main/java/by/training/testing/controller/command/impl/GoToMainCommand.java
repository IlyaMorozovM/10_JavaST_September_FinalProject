package by.training.testing.controller.command.impl;

import by.training.testing.service.SubjectService;
import by.training.testing.service.exception.ServiceException;
import by.training.testing.bean.Subject;
import by.training.testing.controller.command.Command;
import by.training.testing.service.factory.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * This class contains method, that directs client to the "main" page.
 *
 * @author Ilya Morozov
 * @version	1.0
 * @since	2020-12-14
 */
public class GoToMainCommand implements Command {

    private static final String MAIN_PAGE_URI = "index.jsp";
    private static final String USER_SESSION_ATTR = "user";
    private static final String SUBJECTS_SESSION_ATTR = "subjects";
    private static final String SUBJECTID_SESSION_ATTR = "subjectId";
    private static final String TESTS_SESSION_ATTR = "tests";

    /**
     * Method, that directs client to the "main" page.
     *
     * @param req Request from client.
     * @param resp Response to client.
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(MAIN_PAGE_URI);
        HttpSession session = req.getSession(true);
        session.removeAttribute(SUBJECTID_SESSION_ATTR);
        session.removeAttribute(TESTS_SESSION_ATTR);
        if(session.getAttribute(USER_SESSION_ATTR) != null) {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            SubjectService subjectService = serviceFactory.getSubjectService();
            List<Subject> subjects = null;
            try {
                subjects = subjectService.getSubjects();
            } catch (ServiceException e) {
                requestDispatcher.forward(req, resp);
            }
            session.setAttribute(SUBJECTS_SESSION_ATTR, subjects);
        }
        requestDispatcher.forward(req, resp);
    }
}
