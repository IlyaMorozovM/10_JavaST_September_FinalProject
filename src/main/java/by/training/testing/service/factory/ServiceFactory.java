package by.training.testing.service.factory;

import by.training.testing.service.*;
import by.training.testing.service.impl.*;

public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private static final UserService userService = new UserServiceImpl();
    private static final SubjectService subjectService = new SubjectServiceImpl();
    private static final TestService testService = new TestServiceImpl();
    private static final AnswerService answerService = new AnswerServiceImpl();
    private static final QuestionService questionService = new QuestionServiceImpl();
    private static final ResultService resultService = new ResultServiceImpl();

    private ServiceFactory() {}

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public SubjectService getSubjectService() {
        return subjectService;
    }

    public TestService getTestService() {
        return testService;
    }

    public AnswerService getAnswerService() {
        return answerService;
    }

    public QuestionService getQuestionService() {
        return questionService;
    }

    public ResultService getResultService() {
        return resultService;
    }
}
