package service.validator;

import by.training.testing.service.validator.UserValidator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UserValidatorTest {

    UserValidator userValidator = new UserValidator();

    @BeforeClass
    public void init(){
    }

    @DataProvider(name = "email_validation")
    public Object[][] createPositiveDataEmail() {
        return new Object[][]{
                {"example@gmail.com", true},
                {"user@mail.ru", true},
                {"example@gmail..com", false},
                {"example@@gmail.com", false},
                {"example@gmail_com", false}
        };
    }

    @DataProvider(name = "password_validation")
    public Object[][] createPositiveDataPassword() {
        return new Object[][]{
                {"123456", true},
                {"qwerty", true},
                {"hardPassword123", true},
                {"short", false},
                {"123", false}
        };
    }

    @DataProvider(name = "login_validation")
    public Object[][] createPositiveDataLogin() {
        return new Object[][]{
                {"Lin Ngo", true},
                {"User Login", true},
                {"John Smith", true},
                {"short", false},
                {"123", false}
        };
    }

    @DataProvider(name = "name_validation")
    public Object[][] createPositiveDataName() {
        return new Object[][]{
                {"Lin", true},
                {"Name", true},
                {"John", true},
                {"12", false},
                {"aa", false}
        };
    }

    @DataProvider(name = "lastname_validation")
    public Object[][] createPositiveDataLastname() {
        return new Object[][]{
                {"Ngo", true},
                {"Lastname", true},
                {"Smith", true},
                {"aa", false},
                {"12", false}
        };
    }

    @DataProvider(name = "role_validation")
    public Object[][] createPositiveDataRole() {
        return new Object[][]{
                {"1", true},
                {"2", true},
                {"3", true},
                {"0", false},
                {"-1", false},
                {"4", false}
        };
    }

    @Test(description = "Positive scenario of email validation",
            dataProvider = "email_validation")
    public void isValidEmailTest(String email, boolean expected) {
        boolean actual = userValidator.isValidEmail(email);
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "Positive scenario of password validation",
            dataProvider = "password_validation")
    public void isValidPasswordTest(String password, boolean expected) {
        boolean actual = userValidator.isValidPassword(password);
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "Positive scenario of login validation",
            dataProvider = "login_validation")
    public void isValidLoginTest(String login, boolean expected) {
        boolean actual = userValidator.isValidLogin(login);
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "Positive scenario of name validation",
            dataProvider = "name_validation")
    public void isValidNameTest(String name, boolean expected) {
        boolean actual = userValidator.isValidName(name);
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "Positive scenario of lastname validation",
            dataProvider = "lastname_validation")
    public void isValidLastnameTest(String lastname, boolean expected) {
        boolean actual = userValidator.isValidLastname(lastname);
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "Positive scenario of role validation",
            dataProvider = "role_validation")
    public void isValidRoleTest(String role, boolean expected) {
        boolean actual = userValidator.isValidRole(role);
        Assert.assertEquals(actual, expected);
    }



}
