package by.training.testing.controller.command.impl;

import by.training.testing.service.exception.ServiceException;
import by.training.testing.bean.Question;
import by.training.testing.bean.User;
import by.training.testing.controller.command.Command;
import by.training.testing.service.QuestionService;
import by.training.testing.service.factory.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * This class contains method, that directs client to the "questions" page.
 *
 * @author Ilya Morozov
 * @version	1.0
 * @since	2020-12-14
 */
public class GoToQuestionsCommand implements Command {
    private static final String QUESTIONS_PAGE_URI = "WEB-INF/jsp/questions.jsp";
    private static final String START_TEST_PAGE_URI = "WEB-INF/jsp/test.jsp";

    private static final String QUESTIONS_SESSION_ATTR = "questions";
    private static final String TESTS_SESSION_ATTR = "tests";
    private static final String REQUEST_PARAMETER_TESTID = "testId";
    private static final String REQUEST_PARAM_ENTITY = "entity";
    private static final String REQUEST_PARAM_ID = "id";
    private static final String REQUEST_PARAM_ISRIGHT = "isRight";
    private static final String TESTID_SESSION_ATTR = "testId";
    private static final String USER_SESSION_ATTR = "user";
    private static final String NUMBER_OF_QUESTIONS_SESSION_ATTR = "numOfQuestions";
    private static final String RIGHT_ANSWERS_SESSION_ATTR = "rightAnswers";
    private static final String CURRENT_QUESTION_SESSION_ATTR = "currQuestion";

    private static final String REDIRECT_COMMAND_ERROR = "Controller?command=go_to_tests&error=error";

    /**
     * Method, that directs client to the "questions" page.
     * "Questions" page contains questions, that refer to a specific test.
     *
     * @param req Request from client.
     * @param resp Response to client.
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        HttpSession session = req.getSession(true);
        session.removeAttribute(REQUEST_PARAM_ENTITY);
        session.removeAttribute(REQUEST_PARAM_ID);
        session.removeAttribute(REQUEST_PARAM_ISRIGHT);
        session.removeAttribute(TESTS_SESSION_ATTR);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        QuestionService questionService = serviceFactory.getQuestionService();

        int testId;
        if(session.getAttribute(TESTID_SESSION_ATTR) == null) {
            testId = Integer.parseInt(req.getParameter(REQUEST_PARAMETER_TESTID));
            session.setAttribute(TESTID_SESSION_ATTR, testId);
        } else {
            testId = (int) session.getAttribute(TESTID_SESSION_ATTR);
        }

        List<Question> questions = null;
        try {
            questions = questionService.getQuestions(testId);
        } catch (ServiceException e) {
            resp.sendRedirect(REDIRECT_COMMAND_ERROR);
        }
        session.setAttribute(QUESTIONS_SESSION_ATTR, questions);
        session.setAttribute(NUMBER_OF_QUESTIONS_SESSION_ATTR, questions.size());

        if(((User)session.getAttribute(USER_SESSION_ATTR)).getRoleName().equals("student")) {
            session.setAttribute(NUMBER_OF_QUESTIONS_SESSION_ATTR, questions.size());
            session.setAttribute(RIGHT_ANSWERS_SESSION_ATTR, 0);
            session.setAttribute(CURRENT_QUESTION_SESSION_ATTR, 0);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(START_TEST_PAGE_URI);
            requestDispatcher.forward(req, resp);
        }
        else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(QUESTIONS_PAGE_URI);
            requestDispatcher.forward(req, resp);
        }
    }
}
