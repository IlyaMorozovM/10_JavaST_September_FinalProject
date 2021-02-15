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

    private static final String INDEX_PAGE_URI = "index.jsp";
    private static final String MAIN_PAGE_URI = "WEB-INF/jsp/main.jsp";
    private static final String USER_SESSION_ATTR = "user";
    private static final String SUBJECTS_SESSION_ATTR = "subjects";
    private static final String SUBJECTID_SESSION_ATTR = "subjectId";
    private static final String TESTS_SESSION_ATTR = "tests";
    private static final String REQUEST_PARAMETER_CURRENT_PAGE = "currentPage";
    private static final String REQUEST_ATTR_SUBJECTS = "subjects";
    private static final String REQUEST_ATTR_MAX_PAGE = "maxPage";
    private static final String REQUEST_PARAM_ENTITY = "entity";
    private static final String REQUEST_PARAM_ID = "id";
    private static final String REQUEST_PARAM_ISRIGHT = "isRight";
    private static final int SUBJECT_AMOUNT_ON_PAGE = 6;

    /**
     * Method, that directs client to the "main" page.
     *
     * @param req Request from client.
     * @param resp Response to client.
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        RequestDispatcher requestDispatcherIndex = req.getRequestDispatcher(INDEX_PAGE_URI);
        HttpSession session = req.getSession(true);
        session.removeAttribute(SUBJECTID_SESSION_ATTR);
        session.removeAttribute(TESTS_SESSION_ATTR);
        session.removeAttribute(REQUEST_PARAM_ENTITY);
        session.removeAttribute(REQUEST_PARAM_ID);
        session.removeAttribute(REQUEST_PARAM_ISRIGHT);

        if(session.getAttribute(USER_SESSION_ATTR) != null) {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            SubjectService subjectService = serviceFactory.getSubjectService();
            List<Subject> subjects = null;
            try {
                subjects = subjectService.getSubjects();
            } catch (ServiceException e) {
                requestDispatcherIndex.forward(req, resp);
            }
            //session.setAttribute(SUBJECTS_SESSION_ATTR, subjects);

            //----------------------------------------------------------------
            int page = 1;
            try {
                if (req.getParameter(REQUEST_PARAMETER_CURRENT_PAGE) != null) {
                    page = Integer.parseInt(req.getParameter(REQUEST_PARAMETER_CURRENT_PAGE));
                }
            } catch (NumberFormatException ex) {
                //TODO: log
            }
            List<Subject> subjects1 = null;
            try {
                subjects1 = subjectService.getSubjectsFromTo(SUBJECT_AMOUNT_ON_PAGE, (page - 1) * SUBJECT_AMOUNT_ON_PAGE);
            } catch (ServiceException e) {
                //TODO : perform
            }
            req.setAttribute(REQUEST_ATTR_SUBJECTS, subjects1);
            int countOfSubjects = subjects.size();
            if (countOfSubjects % SUBJECT_AMOUNT_ON_PAGE != 0) {
                req.setAttribute(REQUEST_ATTR_MAX_PAGE, countOfSubjects / SUBJECT_AMOUNT_ON_PAGE + 1);
            } else {
                req.setAttribute(REQUEST_ATTR_MAX_PAGE, countOfSubjects / SUBJECT_AMOUNT_ON_PAGE);
            }
            if (req.getParameter(REQUEST_PARAMETER_CURRENT_PAGE) != null) {
                req.setAttribute(REQUEST_PARAMETER_CURRENT_PAGE, req.getParameter(REQUEST_PARAMETER_CURRENT_PAGE));
            } else {
                req.setAttribute(REQUEST_PARAMETER_CURRENT_PAGE, 1);
            }
            //-------------------------------------------------------------------
            RequestDispatcher requestDispatcherMain = req.getRequestDispatcher(MAIN_PAGE_URI);
            requestDispatcherMain.forward(req, resp);
        } else {
            requestDispatcherIndex.forward(req, resp);
        }
    }
}
