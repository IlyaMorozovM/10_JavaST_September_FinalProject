package by.training.testing.service.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class contains methods, that validates user parameters.
 *
 * @author Ilya Morozov
 * @version	1.0
 * @since	2020-12-20
 */
public class UserValidator {
    
    private static final String EMAIL_PATTERN = "(\\w{3,})@(\\w+\\.)([a-z]{2,6})";

    /**+
     * This method validates user email.
     *
     * @param email User email.
     * @return Result of email validation (true - email valid, otherwise - false).
     */
    public boolean isValidEmail(String email){
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**+
     * This method validates user password.
     *
     * @param password User password.
     * @return Result of password validation (true - password valid, otherwise - false).
     */
    public boolean isValidPassword(String password){
        return password.length() >= 6 && password.length() < 250;
    }

    /**+
     * This method validates user login.
     *
     * @param login User login.
     * @return Result of login validation (true - login valid, otherwise - false).
     */
    public boolean isValidLogin(String login){
        return login.length() >= 6 && login.length() <= 50;
    }

    /**+
     * This method validates user name.
     *
     * @param name User name.
     * @return Result of name validation (true - name valid, otherwise - false).
     */
    public boolean isValidName(String name){
        return name.length() >= 3 && name.length() <= 50;
    }

    /**+
     * This method validates user lastname.
     *
     * @param lastname User lastname.
     * @return Result of lastname validation (true - lastname valid, otherwise - false).
     */
    public boolean isValidLastname(String lastname){
        return lastname.length() >= 3 && lastname.length() <= 50;
    }

    /**+
     * This method validates user role.
     *
     * @param role User role (1 = tutor, 2 = student, 3 = admin).
     * @return Result of role validation (true - role valid, otherwise - false).
     */
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
