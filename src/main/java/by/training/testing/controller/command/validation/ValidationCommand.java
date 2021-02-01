package by.training.testing.controller.command.validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ValidationCommand {
    boolean isValid(HttpServletRequest request, HttpServletResponse response);
}
