package by.training.testing.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import by.training.testing.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Main class of application. Accepts GET and POST requests and processes them.
 *
 * @author	Ilya Morozov
 * @version	1.0
 * @since	2020-12-14
 */
public class Controller extends HttpServlet {

    private static final String REQUEST_PARAM_COMMAND = "command";
    private static final String LAST_REQUEST_PARAM = "lastRequest";

    private static final Logger LOGGER = LogManager.getLogger(Controller.class);

    public Controller() {
        super();

        PropertyConfigurator.configure(Controller.class.getClassLoader().getResource("log4j.properties"));
    }

    private final CommandProvider provider = new CommandProvider();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processGetRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processPostRequest(req, resp);
    }

    private void processGetRequest(HttpServletRequest req, HttpServletResponse resp) throws  ServletException, IOException {
        String commandName;
        Command executionCommand;

        commandName = req.getParameter(REQUEST_PARAM_COMMAND);

        executionCommand = provider.getCommand(commandName);
        try {
            executionCommand.execute(req, resp);
        }
        catch (Exception e) {
            LOGGER.debug(e);
        }


        req.getSession(true).setAttribute(LAST_REQUEST_PARAM, req.getRequestURI() + "?" + req.getQueryString());
    }

    private void processPostRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandName;
        Command executionCommand;

        commandName = req.getParameter(REQUEST_PARAM_COMMAND);

        executionCommand = provider.getCommand(commandName);
        try {
            executionCommand.execute(req, resp);
        }
        catch (Exception e) {
            LOGGER.debug(e);
        }

    }
}
