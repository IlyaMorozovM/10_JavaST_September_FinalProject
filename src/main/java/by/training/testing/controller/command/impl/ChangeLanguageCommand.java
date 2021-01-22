package by.training.testing.controller.command.impl;

import by.training.testing.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ResourceBundle;

public class ChangeLanguageCommand implements Command {

    private static final String LANGUAGE = "language";
    private static final String DEFAULT_LANG = "";
    private static final String EN_LANG = "en_EN";
    private static final String RU_LANG = "ru_RU";

    private static final String PAGE_URI = "WEB-INF/jsp/";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String lang = req.getParameter("lang");
        String page = req.getParameter("page");

        HttpSession session = req.getSession();

        Cookie langCookie = null;

        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals(LANGUAGE)) {
                langCookie = cookie;
                break;
            }
        }
        if (langCookie == null) {
            langCookie = new Cookie(LANGUAGE, DEFAULT_LANG);
        }

        if (lang.contains(EN_LANG) || lang.contains(RU_LANG)) {
            langCookie.setValue(lang);
            resp.addCookie(langCookie);
        } else {
            throw new UnsupportedOperationException("Unknown language: " + lang);
        }

        String uri = req.getParameter("page");
        String pageName = uri.substring(uri.lastIndexOf("/") + 1);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(PAGE_URI + pageName);
        requestDispatcher.forward(req, resp);

    }
}
