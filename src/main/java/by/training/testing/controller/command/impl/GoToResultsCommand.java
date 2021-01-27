package by.training.testing.controller.command.impl;

import by.training.testing.bean.Question;
import by.training.testing.bean.Result;
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

public class GoToResultsCommand implements Command {

    private static final String RESULTS_PAGE_URI = "WEB-INF/jsp/testResults.jsp";

    private static final String RESULTS_SESSION_ATTR = "allResults";
    private static final String USER_RESULTS_SESSION_ATTR = "userResults";
    private static final String REQUEST_PARAMETER_TESTID = "testId";
    private static final String REQUEST_PARAMETER_TEST_TITLE = "testTitle";
    private static final String REQUEST_PARAM_LOGIN = "login";
    private static final String REQUEST_PARAM_USER_RESULT = "isUserResult";
    private static final String TESTID_SESSION_ATTR = "testId";
    private static final String TEST_TITLE_SESSION_ATTR = "testTitle";
    private static final String NUMBER_OF_QUESTIONS_SESSION_ATTR = "numOfQuestions";

    private static final String REDIRECT_COMMAND_ERROR = "Controller?command=go_to_main&error=error";

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

        String testTitle = req.getParameter(REQUEST_PARAMETER_TEST_TITLE);
        session.setAttribute(TEST_TITLE_SESSION_ATTR, testTitle);


        List<Result> results = null;
        List<Result> userResults = null;

        if ( req.getParameter(REQUEST_PARAM_USER_RESULT) == null ||
                req.getParameter(REQUEST_PARAM_USER_RESULT).equals("false")) {
            try {
                results = resultService.getResults(testId);
                session.setAttribute(RESULTS_SESSION_ATTR, results);
            } catch (ServiceException e) {
                resp.sendRedirect(REDIRECT_COMMAND_ERROR);
            }
        } else {
            results = (List<Result>)session.getAttribute(RESULTS_SESSION_ATTR);
            String login = req.getParameter(REQUEST_PARAM_LOGIN);
            try {
                userResults = resultService.getUserResults(results, login);
                session.setAttribute(USER_RESULTS_SESSION_ATTR, userResults);
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
//        session.setAttribute(RESULTS_SESSION_ATTR, results);
//        session.setAttribute(USER_RESULTS_SESSION_ATTR, userResults);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(RESULTS_PAGE_URI);
        requestDispatcher.forward(req, resp);
    }
}
