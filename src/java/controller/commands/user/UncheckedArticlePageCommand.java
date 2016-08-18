/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands.user;

import controller.commands.Command;
import static controller.constants.ConstantsController.LOCALE_SESSION_KEY;
import java.io.IOException;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static controller.constants.ConstantsController.*;

/**
 *
 * @author Zakhar
 */
public class UncheckedArticlePageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessionUser = request.getSession(false);
        Locale locale = (Locale) sessionUser.getAttribute(LOCALE_SESSION_KEY);
        if (!homePageCommand.isChangingLanguage()){
            sessionUser.setAttribute("articlesNotPermittedName", Command.decodeParameter(request.getParameter("articlesNotPermitted")));
        }
        request.setAttribute("articleAuthorNameField", userService.getAuthorFullName((String) sessionUser.getAttribute("articlesNotPermittedName")));
        request.setAttribute("articleDateField", articlesService.getArticleDate((String) sessionUser.getAttribute("articlesNotPermittedName")));
        request.setAttribute("articleNameField", sessionUser.getAttribute("articlesNotPermittedName"));
        request.setAttribute("articleReviewField", articlesService.getAboutArticleText((String) sessionUser.getAttribute("articlesNotPermittedName")));
        request.setAttribute("articleTextField", articlesService.getArticleText((String) sessionUser.getAttribute("articlesNotPermittedName")));
        request.setAttribute("permitUnchecked", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_PERMIT_SUBMIT));
        request.setAttribute("deleteUnchecked", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_DELETE_SUBMIT));
        request.setAttribute("closeUnchecked", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_CLOSE_SUBMIT));
        homePageCommand.setChangingLanguage(false);
        return userPageCommand.execute(request, response);
    }
    
}
