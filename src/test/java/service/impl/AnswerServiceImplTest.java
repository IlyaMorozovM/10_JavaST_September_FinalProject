package service.impl;

import by.training.testing.bean.Answer;
import by.training.testing.dao.impl.connection.ConnectionPool;
import by.training.testing.dao.impl.connection.ConnectionPoolException;
import by.training.testing.dao.impl.connection.DBParameter;
import by.training.testing.service.AnswerService;
import by.training.testing.service.exception.ServiceException;
import by.training.testing.service.factory.ServiceFactory;
import dao.impl.connection.DBResourceManagerTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class AnswerServiceImplTest {

    private static final int POOL_SIZE = 5;

    AnswerService answerService = ServiceFactory.getInstance().getAnswerService();

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

    @DataProvider(name = "get_answers_good")
    public Object[][] createPositiveDataGet() {
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer(13, 6, "987", true));
        answers.add(new Answer(14, 6, "1000", false));
        answers.add(new Answer(15, 6, "500", false));
        List<Answer> answers2 = new ArrayList<>();
        answers2.add(new Answer(16, 7, "North", true));
        answers2.add(new Answer(17, 7, "South", false));
        answers2.add(new Answer(18, 7, "West", false));
        answers2.add(new Answer(19, 7, "East", false));
        return new Object[][]{
                {6, answers},
                {7, answers2},
                {-1, null}
        };
    }

    @DataProvider(name = "add_answer_good")
    public Object[][] createPositiveDataAdd() {
        return new Object[][]{
                {2,  "11", false, true},
                {7, "", true, false}
        };
    }

    @DataProvider(name = "delete_answer_good")
    public Object[][] createPositiveDataDelete() {
        return new Object[][]{
                {6, true},
                {-1, false}
        };
    }

    @DataProvider(name = "edit_answer_good")
    public Object[][] createPositiveDataEdit() {
        return new Object[][]{
                {5, "9", true, true},
                {-1, "5", false, false}
        };
    }

    @DataProvider(name = "get_parser_bad")
    public Object[][] createNegativeDataBuild(){
        return null;
    }

    @Test(description = "Positive scenario of getting answers",
            dataProvider = "get_answers_good")
    public void getAnswersTest(int questionId, List<Answer> expected) throws ServiceException {
        List<Answer> actual = answerService.getAnswers(questionId);
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "Positive scenario of adding answer",
            dataProvider = "add_answer_good")
    public void addAnswerTest(int questionId, String answer, boolean isRight, boolean expected) throws ServiceException {
        boolean actual = answerService.addAnswer(questionId, answer, isRight);
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "Positive scenario of deleting answer",
            dataProvider = "delete_answer_good")
    public void deleteAnswerTest(int answerId, boolean expected) throws ServiceException {
        boolean actual = answerService.deleteAnswer(answerId);
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "Positive scenario of deleting answer",
            dataProvider = "edit_answer_good")
    public void editAnswerTest(int answerId, String answer, boolean isRight, boolean expected) throws ServiceException {
        boolean actual = answerService.editAnswer(answerId, answer, isRight);
        Assert.assertEquals(actual, expected);
    }
}
