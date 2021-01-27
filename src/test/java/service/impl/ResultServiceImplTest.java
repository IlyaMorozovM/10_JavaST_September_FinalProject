package service.impl;

import by.training.testing.bean.Answer;
import by.training.testing.bean.Question;
import by.training.testing.bean.Result;
import by.training.testing.dao.impl.connection.ConnectionPool;
import by.training.testing.dao.impl.connection.ConnectionPoolException;
import by.training.testing.dao.impl.connection.DBParameter;
import by.training.testing.service.ResultService;
import by.training.testing.service.exception.ServiceException;
import by.training.testing.service.factory.ServiceFactory;
import dao.impl.connection.DBResourceManagerTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ResultServiceImplTest {

    private static final int POOL_SIZE = 5;

    ResultService resultService = ServiceFactory.getInstance().getResultService();

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

    @DataProvider(name = "get_results_good")
    public Object[][] createPositiveDataGet() {
        List<Result> results = new ArrayList<>();
        results.add(new Result(6, "vasya", 2));
        return new Object[][]{
                {6, results},
                {-1, null}
        };
    }

    @DataProvider(name = "get_results_user_good")
    public Object[][] createPositiveDataGetUser() {
        List<Result> allResults = new ArrayList<>();
        allResults.add(new Result(6, "vasya", 2));
        allResults.add(new Result(6, "Dasha", 2));
        allResults.add(new Result(6, "vasya", 2));
        List<Result> userResults = new ArrayList<>();
        userResults.add(new Result(6, "Dasha", 2));
        return new Object[][]{
                {allResults, "Dasha", userResults}
        };
    }

    @DataProvider(name = "add_result_good")
    public Object[][] createPositiveDataAdd() {
        return new Object[][]{
                {1,  "vasya", 2, true},
                {7,  "", 5, false}
        };
    }


    @Test(description = "Positive scenario of getting results",
            dataProvider = "get_results_good")
    public void getResultsTest(int testId, List<Result> expected) throws ServiceException {
        List<Result> actual = resultService.getResults(testId);
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "Positive scenario of getting user results",
            dataProvider = "get_results_user_good")
    public void getUserResultsTest(List<Result> results, String login, List<Question> expected) throws ServiceException {
        List<Result> actual = resultService.getUserResults(results, login);
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "Positive scenario of adding result",
            dataProvider = "add_result_good")
    public void addResultTest(int testId, String studentLogin, int points, boolean expected) throws ServiceException {
        boolean actual = resultService.addResult(testId, studentLogin, points);
        Assert.assertEquals(actual, expected);
    }
}
