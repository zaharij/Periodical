/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands.user;

import controller.commands.Command;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static controller.constants.ConstantsController.*;
import java.util.Locale;

/**
 *
 * @author Zakhar
 */
public class WriteArticlePageCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessionUser = request.getSession(false);
        Locale locale = (Locale) sessionUser.getAttribute(LOCALE_SESSION_KEY);
        if (request.getParameter("subjectToWrite") != null){
            sessionUser.setAttribute("subjectToWrite", Command.decodeParameter(request.getParameter("subjectToWrite")));
        }
        nameStatusCommand.execute(request, response);
        request.setAttribute("titlePeriodical", sessionUser.getAttribute("subjectToWrite"));
        request.setAttribute("sendSubmit", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_SEND_SUBMIT));
        request.setAttribute("logoutSubmit", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_LOGOUT_SUBMIT));
        request.setAttribute("backSubmit", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_BACK_SUBMIT));
        request.setAttribute("annotationPageInfo", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_ANNOTATION));
        request.setAttribute("textPageInfo", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_TEXT));
        request.setAttribute("titlePageInfo", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_TITLE));
        return "writearticle.jsp";
    }
}
