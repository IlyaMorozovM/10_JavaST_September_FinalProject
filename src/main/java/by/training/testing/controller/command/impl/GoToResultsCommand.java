package by.training.testing.controller.command.impl;

import by.training.testing.bean.Result;
import by.training.testing.controller.command.Command;
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

    private static final String RESULTS_SESSION_ATTR = "results";
    private static final String REQUEST_PARAMETER_TESTID = "testId";
    private static final String TESTID_SESSION_ATTR = "testId";

    private static final String REDIRECT_COMMAND_ERROR = "Controller?command=go_to_main&error=error";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession(true);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ResultService resultService = serviceFactory.getResultService();

        int testId;
        if (session.getAttribute(TESTID_SESSION_ATTR) == null) {
            testId = Integer.parseInt(req.getParameter(REQUEST_PARAMETER_TESTID));
            session.setAttribute(TESTID_SESSION_ATTR, testId);
        } else {
            testId = (int) session.getAttribute(TESTID_SESSION_ATTR);
        }

        List<Result> results = null;
        try {
            results = resultService.getResults(testId);
        } catch (ServiceException e) {
            //TODO: redirect on another page, may be
            resp.sendRedirect(REDIRECT_COMMAND_ERROR);
        }
        session.setAttribute(RESULTS_SESSION_ATTR, results);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(RESULTS_PAGE_URI);
        requestDispatcher.forward(req, resp);
    }
}
