package service.impl;

import by.training.testing.service.exception.ServiceException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AnswerServiceImplTest {

    @DataProvider(name = "get_parser_good")
    public Object[][] createPositiveDataBuild() throws ServiceException {
        return null;
    }

    @DataProvider(name = "get_parser_bad")
    public Object[][] createNegativeDataBuild(){
        return null;
    }

    @Test(description = "Positive scenario of parsing file",
            dataProvider = "get_parser_good")
    public void createCandieParserTest(String type, Class expected) throws ServiceException {

    }
}
