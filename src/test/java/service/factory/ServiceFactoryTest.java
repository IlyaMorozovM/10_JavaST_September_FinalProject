package service.factory;

import by.training.testing.service.UserService;
import by.training.testing.service.exception.ServiceException;
import by.training.testing.service.factory.ServiceFactory;
import by.training.testing.service.impl.UserServiceImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ServiceFactoryTest {

    ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @Test(description = "Positive scenario of getting user service")
    public void createCandieParserTest() {
        UserService actual = serviceFactory.getUserService();
        Assert.assertEquals(actual.getClass(), UserServiceImpl.class);
    }

}
