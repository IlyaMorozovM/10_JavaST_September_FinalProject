package by.training.testing.controller.command.impl;

import by.training.testing.bean.Answer;
import by.training.testing.bean.Question;
import by.training.testing.bean.User;
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

/**
 * This class contains method, that directs student to the next question.
 *
 * @author Ilya Morozov
 * @version	1.0
 * @since	2020-12-14
 */
public class GoToNextQuestionCommand implements Command {

    private static final String NEXT_PAGE_URI = "WEB-INF/jsp/test.jsp";
    private static final String FINISH_TEST_PAGE_URI = "WEB-INF/jsp/finishTest.jsp";

    private static final String REQUEST_PARAM_RADIO = "answer";
    private static final String REQUEST_PARAM_FINISH = "finishTest";
    private static final String QUESTIONS_SESSION_ATTR = "questions";
    private static final String USER_SESSION_ATTR = "user";
    private static final String TEST_SESSION_ATTR = "testId";
    private static final String RIGHT_ANSWERS_SESSION_ATTR = "rightAnswers";
    private static final String CURRENT_QUESTION_SESSION_ATTR = "currQuestion";

    private static final String REDIRECT_COMMAND_ERROR_RESULT = "Controller?command=go_to_main&error=question";

    /**
     * Method, that directs student to the next question.
     *
     * @param req Request from client.
     * @param resp Response to client.
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession(true);


        Question currQuestion = ((List<Question>)session.getAttribute(QUESTIONS_SESSION_ATTR)).
                get(((Integer)session.getAttribute(CURRENT_QUESTION_SESSION_ATTR)));

        if(currQuestion.getRightAnswers() == 1) {
            int answerId;
            if (req.getParameter(REQUEST_PARAM_RADIO) == null){
                answerId = -1;
            } else {
                answerId = Integer.parseInt(req.getParameter(REQUEST_PARAM_RADIO));
            }
            int rightAnswerId = 0;

            for(Answer answer : currQuestion.getAnswers())
                if(answer.getRight()) {
                    rightAnswerId = answer.getAnswerId();
                    break;
                }

            if(answerId == rightAnswerId)
                session.setAttribute(RIGHT_ANSWERS_SESSION_ATTR, (Integer)session.getAttribute(RIGHT_ANSWERS_SESSION_ATTR) + 1);
        }
        else {
            int rightAnswers = 0;

            for (Answer answer : currQuestion.getAnswers()) {
                if(req.getParameter(String.valueOf(answer.getAnswerId())) != null)
                    if(answer.getRight()) {
                        rightAnswers++;
                    } else {
                        rightAnswers = 0;
                        break;
                    }
            }

            if(rightAnswers == currQuestion.getRightAnswers())
                session.setAttribute(RIGHT_ANSWERS_SESSION_ATTR, (Integer)session.getAttribute(RIGHT_ANSWERS_SESSION_ATTR) + 1);
        }


        if(req.getParameter(REQUEST_PARAM_FINISH) != null) {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            ResultService resultService = serviceFactory.getResultService();

            try {
                User currentUser = (User)session.getAttribute(USER_SESSION_ATTR);
                boolean result = resultService.addResult((Integer)session.getAttribute(TEST_SESSION_ATTR),
                        currentUser.getLogin(),(Integer)session.getAttribute(RIGHT_ANSWERS_SESSION_ATTR));
                if (result) {
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher(FINISH_TEST_PAGE_URI);
                    requestDispatcher.forward(req, resp);
                } else {
                    resp.sendRedirect(REDIRECT_COMMAND_ERROR_RESULT);
                }
            }
            catch (ServiceException e) {
                resp.sendRedirect(REDIRECT_COMMAND_ERROR_RESULT);
            }
        }
        else {
            session.setAttribute(CURRENT_QUESTION_SESSION_ATTR, (Integer)session.getAttribute(CURRENT_QUESTION_SESSION_ATTR) + 1);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(NEXT_PAGE_URI);
            requestDispatcher.forward(req, resp);
        }
    }
}
