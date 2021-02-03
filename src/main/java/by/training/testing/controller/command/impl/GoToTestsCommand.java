package by.training.testing.controller.command.impl;

import by.training.testing.service.exception.ServiceException;
import by.training.testing.bean.Test;
import by.training.testing.controller.command.Command;
import by.training.testing.service.TestService;
import by.training.testing.service.factory.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * This class contains method, that directs tutor to the "tests" page.
 *
 * @author Ilya Morozov
 * @version	1.0
 * @since	2020-12-14
 */
public class GoToTestsCommand implements Command {

    private static final String TESTS_PAGE_URI = "WEB-INF/jsp/tests.jsp";

    private static final String REQUEST_ATTR_TESTS = "tests";
    private static final String REQUEST_ATTR_MAX_PAGE = "maxPage";
    private static final String REQUEST_PARAMETER_SUBJECTID = "subjectId";
    private static final String REQUEST_PARAMETER_CURRENT_PAGE = "currentPage";
    private static final String SUBJECTID_SESSION_ATTR = "subjectId";
    private static final String TESTID_SESSION_ATTR = "testId";
    private static final String NUMBER_OF_QUESTIONS_SESSION_ATTR = "numOfQuestions";
    private static final String RIGHT_ANSWERS_SESSION_ATTR = "rightAnswers";
    private static final String CURRENT_QUESTION_SESSION_ATTR = "currQuestion";
    private static final int TEST_AMOUNT_ON_PAGE = 5;

    private static final String REDIRECT_COMMAND_ERROR = "Controller?command=go_to_main&error=error";

    /**
     * Method, that directs client to the "tests" page.
     * "Tests" page contains tests, that refer to a specific subject.
     *
     * @param req Request from client.
     * @param resp Response to client.
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        HttpSession session = req.getSession(true);
        session.removeAttribute(TESTID_SESSION_ATTR);
        session.removeAttribute(NUMBER_OF_QUESTIONS_SESSION_ATTR);
        session.removeAttribute(RIGHT_ANSWERS_SESSION_ATTR);
        session.removeAttribute(CURRENT_QUESTION_SESSION_ATTR);


        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        TestService testService = serviceFactory.getTestService();

        int subjectId;
        if(session.getAttribute(SUBJECTID_SESSION_ATTR) == null) {
            subjectId = Integer.parseInt(req.getParameter(REQUEST_PARAMETER_SUBJECTID));
            session.setAttribute(SUBJECTID_SESSION_ATTR, subjectId);
        }
        else
            subjectId = (int)session.getAttribute(SUBJECTID_SESSION_ATTR);

        List<Test> tests = null;
        try {
            tests = testService.getTests(subjectId);
        } catch (ServiceException e) {
            resp.sendRedirect(REDIRECT_COMMAND_ERROR);
        }
        //session.setAttribute(TESTS_SESSION_ATTR, tests);

        //----------------------------------------------------------------
        int page = 1;
        try {
            if (req.getParameter(REQUEST_PARAMETER_CURRENT_PAGE) != null) {
                page = Integer.parseInt(req.getParameter(REQUEST_PARAMETER_CURRENT_PAGE));
            }
        } catch (NumberFormatException ex) {
            //TODO: log
        }
        List<Test> tests1 = null;
        try {
            tests1 = testService.getTestsFromTo(subjectId, TEST_AMOUNT_ON_PAGE, (page - 1) * TEST_AMOUNT_ON_PAGE);
        } catch (ServiceException e) {
            //TODO : perform
        }
        req.setAttribute(REQUEST_ATTR_TESTS, tests1);
        int countOfTests = tests.size();
        req.setAttribute(REQUEST_ATTR_MAX_PAGE, countOfTests / TEST_AMOUNT_ON_PAGE + 1);
        if (req.getParameter(REQUEST_PARAMETER_CURRENT_PAGE) != null) {
            req.setAttribute(REQUEST_PARAMETER_CURRENT_PAGE, req.getParameter(REQUEST_PARAMETER_CURRENT_PAGE));
        } else {
            req.setAttribute(REQUEST_PARAMETER_CURRENT_PAGE, 1);
        }
        //-------------------------------------------------------------------

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(TESTS_PAGE_URI);
        requestDispatcher.forward(req, resp);
    }
}
