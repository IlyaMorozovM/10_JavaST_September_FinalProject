package by.training.testing.service.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

    private static final String EMAIL_PATTERN = "(\\w{3,})@(\\w+\\.)([a-z]{2,6})";

    public boolean isValidEmail(String email){
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean isValidPassword(String password){
        return password.length() >= 6 && password.length() <= 50;
    }

    public boolean isValidLogin(String login){
        return login.length() >= 6 && login.length() <= 50;
    }

    public boolean isValidName(String name){
        return name.length() >= 3 && name.length() <= 50;
    }

    public boolean isValidLastname(String lastname){
        return lastname.length() >= 3 && lastname.length() <= 50;
    }

    public boolean isValidRole(String role){
        int roleInt;
        try {
            roleInt = Integer.parseInt(role);
        } catch (NumberFormatException e){
            return false;
        }
        return roleInt >= 1 && roleInt <= 3;
    }

}
