package service.impl;

import by.training.testing.bean.Answer;
import by.training.testing.bean.Question;
import by.training.testing.dao.impl.connection.ConnectionPool;
import by.training.testing.dao.impl.connection.ConnectionPoolException;
import by.training.testing.dao.impl.connection.DBParameter;
import by.training.testing.service.QuestionService;
import by.training.testing.service.exception.ServiceException;
import by.training.testing.service.factory.ServiceFactory;
import dao.impl.connection.DBResourceManagerTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class QuestionServiceImplTest {
    private static final int POOL_SIZE = 5;

    QuestionService questionService = ServiceFactory.getInstance().getQuestionService();

    @BeforeClass
    public void init(){
        DBResourceManagerTest dbResourceManager = DBResourceManagerTest.getInstance();
        String driverName = dbResourceManager.getValue(DBParameter.DB_DRIVER);
        String url = dbResourceManager.getValue(DBParameter.DB_URL);
        String user = dbResourceManager.getValue(DBParameter.DB_USER);
        String password = dbResourceManager.getValue(DBParameter.DB_PASSWORD);

        int poolSize;
        try {
            poolSize = Integer.parseInt(dbResourceManager.getValue(DBParameter.DB_POOL_SIZE));
        }
        catch (NumberFormatException e) {
            poolSize = POOL_SIZE;
        }
        ConnectionPool.getInstance().initParams(driverName, url, user, password, poolSize);
        try {
            ConnectionPool.getInstance().initPoolData();
        } catch (ConnectionPoolException e) {
        }
    }

    @DataProvider(name = "get_questions_good")
    public Object[][] createPositiveDataGet() {
        List<Question> questions = new ArrayList<>();
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer(30, 12, "kk", true));
        answers.add(new Answer(31, 12, "kkkk", false));
        questions.add(new Question(12, 2, "lll", answers));
        return new Object[][]{
                {2, questions},
                {-1, null}
        };
    }

    @DataProvider(name = "add_questions_good")
    public Object[][] createPositiveDataAdd() {
        return new Object[][]{
                {7,  "test question", true},
                {7, "", false}
        };
    }

    @DataProvider(name = "delete_questions_good")
    public Object[][] createPositiveDataDelete() {
        return new Object[][]{
                {13, true},
                {-1, false}
        };
    }

    @DataProvider(name = "edit_questions_good")
    public Object[][] createPositiveDataEdit() {
        return new Object[][]{
                {7, "Side?", true},
                {-1, "aaa", false},
                {7, "", false}
        };
    }

    @Test(description = "Positive scenario of getting questions",
            dataProvider = "get_questions_good")
    public void getQuestionsTest(int testId, List<Question> expected) throws ServiceException {
        List<Question> actual = questionService.getQuestions(testId);
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "Positive scenario of adding question",
            dataProvider = "add_questions_good")
    public void addQuestionTest(int testId, String question, boolean expected) throws ServiceException {
        boolean actual = questionService.addQuestion(testId, question);
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "Positive scenario of deleting question",
            dataProvider = "delete_questions_good")
    public void deleteQuestionTest(int questionId, boolean expected) throws ServiceException {
        boolean actual = questionService.deleteQuestion(questionId);
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "Positive scenario of deleting question",
            dataProvider = "edit_questions_good")
    public void editQuestionTest(int questionId, String question, boolean expected) throws ServiceException {
        boolean actual = questionService.editQuestion(questionId, question);
        Assert.assertEquals(actual, expected);
    }
}
