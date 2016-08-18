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
public class UserPageCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessionUser = request.getSession(false);
        Locale locale = (Locale) sessionUser.getAttribute(LOCALE_SESSION_KEY);
        nameStatusCommand.execute(request, response);
        request.setAttribute("periodicals", periodicalsService.getAllPeriodicalsNames());
        request.setAttribute("periodicalsPayed", periodicalsService.getAllPeriodicalsNames((String) sessionUser.getAttribute("email")));
        request.setAttribute("authNum", userService.getAuthorsNumber());       
        if (request.getParameter("backToUser") != null){
            sessionUser.setAttribute("articlesNotPermittedName", null);
        }
        if (homePageCommand.isChangingLanguage() && sessionUser.getAttribute("articlesNotPermittedName") != null){
            uncheckedArticlePageCommand.execute(request, response);
        }
        if ((boolean) sessionUser.getAttribute("isAdmin")){
            request.setAttribute("articlesNotPermitted", articlesService.getNotPermittedArticles());;
        }
        request.setAttribute("replenishAccountSubmit", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_REPLENISH_ACCOUNT_SUBMIT));
        request.setAttribute("logoutSubmit", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_LOGOUT_SUBMIT));
        request.setAttribute("periodicalsPageInfo", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_PERIODICALS));
        request.setAttribute("availablePageInfo", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_AVAILABLE));
        request.setAttribute("signedPageInfo", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_SIGNED));
        request.setAttribute("writeArticlePageInfo", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_WRITE_ARTICLE));
        request.setAttribute("addonInfoSubmit", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_ADDON_INFO_SUBMIT));
        request.setAttribute("openSubmit", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_OPEN_SUBMIT));
        request.setAttribute("chooseSubmit", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_CHOOSE_SUBMIT));
        homePageCommand.setChangingLanguage(false);
        return "user.jsp";
    }
}
