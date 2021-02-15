package by.training.testing.controller.command.impl;

import by.training.testing.bean.Question;
import by.training.testing.bean.Result;
import by.training.testing.bean.Subject;
import by.training.testing.controller.command.Command;
import by.training.testing.service.QuestionService;
import by.training.testing.service.ResultService;
import by.training.testing.service.exception.ServiceException;
import by.training.testing.service.factory.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * This class contains method, that directs tutor to the "results" page.
 *
 * @author Ilya Morozov
 * @version	1.0
 * @since	2020-12-14
 */
public class GoToResultsCommand implements Command {

    private static final String RESULTS_PAGE_URI = "WEB-INF/jsp/testResults.jsp";

    private static final String RESULTS_SESSION_ATTR = "allResults";
    private static final String USER_RESULTS_SESSION_ATTR = "userResults";
    private static final String REQUEST_PARAMETER_TESTID = "testId";
    private static final String REQUEST_PARAMETER_TEST_TITLE = "testTitle";
    private static final String REQUEST_PARAM_LOGIN = "login";
    private static final String REQUEST_PARAM_USER_RESULT = "isUserResult";
    private static final String REQUEST_PARAMETER_CURRENT_PAGE = "currentPage";
    private static final String REQUEST_ATTR_MAX_PAGE = "maxPage";
    private static final String REQUEST_ATTR_RESULTS = "allResults";
    private static final String REQUEST_ATTR_USER_RESULTS = "userResults";
    private static final String TESTID_SESSION_ATTR = "testId";
    private static final String TEST_TITLE_SESSION_ATTR = "testTitle";
    private static final String NUMBER_OF_QUESTIONS_SESSION_ATTR = "numOfQuestions";
    private static final int RESULT_AMOUNT_ON_PAGE = 5;

    private static final String REDIRECT_COMMAND_ERROR = "Controller?command=go_to_main&error=error";

    /**
     * Method, that directs tutor to the "results" page.
     * "Results" page contains results, that refer to a specific test.
     * Only tutor has access to the "results" page.
     *
     * @param req Request from client.
     * @param resp Response to client.
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession(true);
        session.removeAttribute(USER_RESULTS_SESSION_ATTR);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ResultService resultService = serviceFactory.getResultService();

        int testId;
        if (session.getAttribute(TESTID_SESSION_ATTR) == null) {
            testId = Integer.parseInt(req.getParameter(REQUEST_PARAMETER_TESTID));
            session.setAttribute(TESTID_SESSION_ATTR, testId);
        } else {
            testId = (int) session.getAttribute(TESTID_SESSION_ATTR);
        }

        if (req.getParameter(REQUEST_PARAMETER_TEST_TITLE) != null) {
            String testTitle = req.getParameter(REQUEST_PARAMETER_TEST_TITLE);
            session.setAttribute(TEST_TITLE_SESSION_ATTR, testTitle);
        }

        int page = 1;
        try {
            if (req.getParameter(REQUEST_PARAMETER_CURRENT_PAGE) != null) {
                page = Integer.parseInt(req.getParameter(REQUEST_PARAMETER_CURRENT_PAGE));
            }
        } catch (NumberFormatException ex) {
            //TODO: log
        }

        List<Result> results;
        List<Result> allResults;
        List<Result> userResults;
        List<Result> allUserResults;
        int countOfResults = 0;

        if (req.getParameter(REQUEST_PARAM_USER_RESULT) != null) {
            if (req.getParameter(REQUEST_PARAM_USER_RESULT).equals("true")) {
                session.setAttribute(REQUEST_PARAM_USER_RESULT, "true");
            } else {
                session.setAttribute(REQUEST_PARAM_USER_RESULT, "false");
            }
        }

        if ( session.getAttribute(REQUEST_PARAM_USER_RESULT) == null ||
                session.getAttribute(REQUEST_PARAM_USER_RESULT).equals("false")) {
            try {
                allResults = resultService.getResults(testId);
                countOfResults = allResults.size();
                results = resultService.getResultsFromTo(testId, RESULT_AMOUNT_ON_PAGE, (page - 1) * RESULT_AMOUNT_ON_PAGE);
                req.setAttribute(REQUEST_ATTR_RESULTS, results);
                session.removeAttribute(REQUEST_PARAM_LOGIN);
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
                allUserResults = resultService.getUserResults(testId, login);
                countOfResults = allUserResults.size();
                userResults = resultService.getUserResultsFromTo(testId, login, RESULT_AMOUNT_ON_PAGE, (page - 1) * RESULT_AMOUNT_ON_PAGE);
                req.setAttribute(REQUEST_ATTR_USER_RESULTS, userResults);
            } catch (ServiceException e) {
                resp.sendRedirect(REDIRECT_COMMAND_ERROR);
            }
        }

        QuestionService questionService = serviceFactory.getQuestionService();
        List<Question> questions = null;
        try {
            questions = questionService.getQuestions(testId);
        } catch (ServiceException e) {
            resp.sendRedirect(REDIRECT_COMMAND_ERROR);
        }
        //TODO: why only Controller в адресной строке, когда find
        session.setAttribute(NUMBER_OF_QUESTIONS_SESSION_ATTR, questions.size());
        //----------------------------------------------------------------

        if (countOfResults % RESULT_AMOUNT_ON_PAGE != 0) {
            req.setAttribute(REQUEST_ATTR_MAX_PAGE, countOfResults / RESULT_AMOUNT_ON_PAGE + 1);
        } else {
            req.setAttribute(REQUEST_ATTR_MAX_PAGE, countOfResults / RESULT_AMOUNT_ON_PAGE);
        }
        if (req.getParameter(REQUEST_PARAMETER_CURRENT_PAGE) != null) {
            req.setAttribute(REQUEST_PARAMETER_CURRENT_PAGE, req.getParameter(REQUEST_PARAMETER_CURRENT_PAGE));
        } else {
            req.setAttribute(REQUEST_PARAMETER_CURRENT_PAGE, 1);
        }
        //-------------------------------------------------------------------
//        session.setAttribute(RESULTS_SESSION_ATTR, results);
//        session.setAttribute(USER_RESULTS_SESSION_ATTR, userResults);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(RESULTS_PAGE_URI);
        requestDispatcher.forward(req, resp);
    }
}
