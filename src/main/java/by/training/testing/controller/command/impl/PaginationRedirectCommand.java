package by.training.testing.controller.command.impl;

import by.training.testing.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PaginationRedirectCommand implements Command {

    private static final String TEST_RESULTS_JSP = "testResults.jsp";
    private static final String MAIN_JSP = "main.jsp";
    private static final String TESTS_JSP = "tests.jsp";
    private static final String DELETE_USERS_JSP = "deleteUsers.jsp";
    private static final String ERROR_JSP = "WEB-INF/jsp/errorPage.jsp";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String uri = req.getParameter("requestPage");
        String pageName = uri.substring(uri.lastIndexOf("/") + 1);

        RequestDispatcher requestDispatcher;
        switch (pageName){
            case MAIN_JSP:
                GoToMainCommand goToMainCommand = new GoToMainCommand();
                goToMainCommand.execute(req, resp);
                break;
            case TEST_RESULTS_JSP:
                GoToResultsCommand goToResultsCommand = new GoToResultsCommand();
                goToResultsCommand.execute(req, resp);
                break;
            case TESTS_JSP:
                GoToTestsCommand goToTestsCommand = new GoToTestsCommand();
                goToTestsCommand.execute(req, resp);
                break;
            case DELETE_USERS_JSP:
                GoToDeleteUsersCommand goToDeleteUsersCommand = new GoToDeleteUsersCommand();
                goToDeleteUsersCommand.execute(req, resp);
                break;
            default:
                requestDispatcher = req.getRequestDispatcher(ERROR_JSP);
                requestDispatcher.forward(req, resp);
        }
    }
}
