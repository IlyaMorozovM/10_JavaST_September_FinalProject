package service.impl;

import by.training.testing.bean.Result;
import by.training.testing.dao.impl.connection.ConnectionPool;
import by.training.testing.dao.impl.connection.ConnectionPoolException;
import by.training.testing.dao.impl.connection.DBParameter;
import by.training.testing.service.TestService;
import by.training.testing.service.exception.ServiceException;
import by.training.testing.service.factory.ServiceFactory;
import dao.impl.connection.DBResourceManagerTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class TestServiceImplTest {

    private static final int POOL_SIZE = 5;

    TestService testService = ServiceFactory.getInstance().getTestService();

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

    @DataProvider(name = "get_tests_good")
    public Object[][] createPositiveDataGet() {
        List<by.training.testing.bean.Test> tests = new ArrayList<>();
        tests.add(new by.training.testing.bean.Test(6, 4, "Belarus"));
        return new Object[][]{
                {4, tests},
                {-1, null}
        };
    }


    @Test(description = "Positive scenario of getting tests",
            dataProvider = "get_tests_good")
    public void getTestsTest(int subjectId, List<by.training.testing.bean.Test> expected) throws ServiceException {
        List<by.training.testing.bean.Test> actual = testService.getTests(subjectId);
        Assert.assertEquals(actual, expected);
    }

}
