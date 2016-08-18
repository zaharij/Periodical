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
import java.util.ResourceBundle;

/**
 *
 * @author Zakhar
 */
public class AdminPageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessionAdmin = request.getSession(false);
        Locale locale = (Locale) sessionAdmin.getAttribute(LOCALE_SESSION_KEY);
        request.setAttribute(LOGIN_FIELD, sessionAdmin.getAttribute(LOGIN));
        request.setAttribute(PERIODICALS, periodicalsService.getAllPeriodicalsNames());
        request.setAttribute(ARTICLES_NOT_PERMITTED, articlesService.getNotPermittedArticles());
        request.setAttribute(OBJECT_LIST, articlesService.getNotPermittedArticles());
        request.setAttribute("createNewPublicationMessage", getResourceBundle(locale, LOCALE_KEY_CREATE_PERIODICAL));
        request.setAttribute("deletePeriodicalArticleMessage", getResourceBundle(locale, LOCALE_KEY_DELETE_PERIODICAL_ARTICLE));
        request.setAttribute("permitArticleMessage", getResourceBundle(locale, LOCALE_KEY_PERMIT_ARTICLE));
        request.setAttribute("titleMessage", getResourceBundle(locale, LOCALE_KEY_SUBJECT));
        request.setAttribute("priceMessage", getResourceBundle(locale, LOCALE_KEY_PRICE));
        request.setAttribute("logoutSubmit", getResourceBundle(locale, LOCALE_KEY_LOGOUT_SUBMIT));
        request.setAttribute("userManagementSubmit", getResourceBundle(locale, LOCALE_KEY_USER_MANAGEMENT_BUTTON));
        request.setAttribute("createSubmit", getResourceBundle(locale, LOCALE_KEY_CREATE_SUBMIT));
        request.setAttribute("deleteSubmit", getResourceBundle(locale, LOCALE_KEY_DELETE_SUBMIT));
        request.setAttribute("deleteArticleSubmit", getResourceBundle(locale, LOCALE_KEY_DELETE_ARTICLE_SUBMIT));
        request.setAttribute("viewSubmit", getResourceBundle(locale, LOCALE_KEY_VIEW_SUBMIT));
        request.setAttribute("permitSubmit", getResourceBundle(locale, LOCALE_KEY_PERMIT_SUBMIT));
        return "superadmin.jsp";
    }
 
    /**
     * getResourceBundle
     * returns localized String
     * @param locale - Locale object
     * @param localeKey - string's key
     * @return localized String
     */
    public String getResourceBundle(Locale locale, String localeKey){
        return ResourceBundle.getBundle(BUNDLE_DIR, locale).getString(localeKey);
    }
}
