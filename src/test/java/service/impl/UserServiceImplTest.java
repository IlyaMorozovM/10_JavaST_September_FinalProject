package service.impl;

import by.training.testing.bean.Result;
import by.training.testing.bean.User;
import by.training.testing.dao.impl.connection.ConnectionPool;
import by.training.testing.dao.impl.connection.ConnectionPoolException;
import by.training.testing.dao.impl.connection.DBParameter;
import by.training.testing.service.UserService;
import by.training.testing.service.exception.ServiceException;
import by.training.testing.service.factory.ServiceFactory;
import dao.impl.connection.DBResourceManagerTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UserServiceImplTest {

    private static final int POOL_SIZE = 5;

    UserService userService = ServiceFactory.getInstance().getUserService();

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

    @DataProvider(name = "get_users_good")
    public Object[][] createPositiveDataGet() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "admin", "Admin", "Admin", "admin@gmail.com", "admin"));
        users.add(new User(2, "student", "Student", "Student", "student@gmail.com", "student"));
        users.add(new User(3, "tutor", "Tutor", "Tutor", "tutor@gmail.com", "tutor"));
        users.add(new User(4, "vasya", "Vasya", "Pupkin", "vasya@gmail.com", "student"));
        users.add(new User(5, "Dasha", "Dasha", "Ivanova", "dasha@gmail.com", "student"));
        return new Object[][]{
                {users}
        };
    }

    @Test(description = "Positive scenario of getting users",
            dataProvider = "get_users_good")
    public void getUsersTest(List<User> expected) throws ServiceException {
        List<User> actual = userService.getUsers();
        Collections.sort(actual, Comparator.comparingInt(User::getUserId));
        Assert.assertEquals(actual, expected);
    }
}
