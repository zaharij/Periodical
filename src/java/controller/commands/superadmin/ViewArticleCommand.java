/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands.superadmin;

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
public class ViewArticleCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessionAdmin = request.getSession(false);
        Locale locale = (Locale) sessionAdmin.getAttribute(LOCALE_SESSION_KEY);
        sessionAdmin.setAttribute("articlesNotPermittedName", Command.decodeParameter(request.getParameter("articlesNotPermitted")));
        request.setAttribute("articleAuthorNameField", userService.getAuthorFullName((String) sessionAdmin.getAttribute("articlesNotPermittedName")));
        request.setAttribute("articleDateField", articlesService.getArticleDate((String) sessionAdmin.getAttribute("articlesNotPermittedName")));
        request.setAttribute("articleNameField", sessionAdmin.getAttribute("articlesNotPermittedName"));
        request.setAttribute("articleReviewField", articlesService.getAboutArticleText((String) sessionAdmin.getAttribute("articleName")));
        request.setAttribute("articleTextField", articlesService.getArticleText((String) sessionAdmin.getAttribute("articlesNotPermittedName")));
        request.setAttribute("permitUnchecked", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_PERMIT_SUBMIT));
        request.setAttribute("deleteUnchecked", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_DELETE_SUBMIT));
        return adminPageCommand.execute(request, response);
    }
}
