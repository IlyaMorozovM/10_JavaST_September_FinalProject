package by.training.testing.controller.command.impl;

import by.training.testing.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * This class contains method, that changes language.
 *
 * @author Ilya Morozov
 * @version	1.0
 * @since	2020-12-14
 */
public class ChangeLanguageCommand implements Command {

    private static final String LANGUAGE = "language";
    private static final String DEFAULT_LANG = "";
    private static final String EN_LANG = "en_EN";
    private static final String RU_LANG = "ru_RU";

    private static final String WEB_INF_JSP = "WEB-INF/jsp/";
    private static final String INDEX_JSP = "index.jsp";
    private static final String EMPTY_PAGE = "";

    /**
     * Method, that change language.
     *
     * @param req Request from client.
     * @param resp Response to client.
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String lang = req.getParameter("lang");

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

        RequestDispatcher requestDispatcher;
        if (pageName.equals(INDEX_JSP) || pageName.equals(EMPTY_PAGE)){
            requestDispatcher = req.getRequestDispatcher(pageName);
        } else {
            requestDispatcher = req.getRequestDispatcher(WEB_INF_JSP + pageName);
        }
        requestDispatcher.forward(req, resp);

    }
}
