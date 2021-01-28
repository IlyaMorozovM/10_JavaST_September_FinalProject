package service.impl;

import by.training.testing.bean.Result;
import by.training.testing.bean.Subject;
import by.training.testing.dao.impl.connection.ConnectionPool;
import by.training.testing.dao.impl.connection.ConnectionPoolException;
import by.training.testing.dao.impl.connection.DBParameter;
import by.training.testing.service.SubjectService;
import by.training.testing.service.exception.ServiceException;
import by.training.testing.service.factory.ServiceFactory;
import dao.impl.connection.DBResourceManagerTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class SubjectServiceImplTest {

    private static final int POOL_SIZE = 5;

    SubjectService subjectService = ServiceFactory.getInstance().getSubjectService();

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

    @DataProvider(name = "get_subjects_good")
    public Object[][] createPositiveDataGet() {
        List<Subject> subjects = new ArrayList<>();
        subjects.add(new Subject(1, "Math"));
        subjects.add(new Subject(2, "Russian"));
        subjects.add(new Subject(4, "History"));
        subjects.add(new Subject(5, "Biology"));
        return new Object[][]{
                {subjects}
        };
    }

    @Test(description = "Positive scenario of getting subjects",
            dataProvider = "get_subjects_good")
    public void getSubjectsTest(List<Subject> expected) throws ServiceException {
        List<Subject> actual = subjectService.getSubjects();
        Assert.assertEquals(actual, expected);
    }

}
