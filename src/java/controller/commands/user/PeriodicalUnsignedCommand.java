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
public class PeriodicalUnsignedCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessionUser = request.getSession(false);
        Locale locale = (Locale) sessionUser.getAttribute(LOCALE_SESSION_KEY);
        nameStatusCommand.execute(request, response);
        if (request.getParameter("subject") != null){
            sessionUser.setAttribute("subject", Command.decodeParameter(request.getParameter("subject")));
        }
        if (!homePageCommand.isChangingLanguage()){
            sessionUser.setAttribute("articleName", Command.decodeParameter(request.getParameter("articleName")));
        }
        request.setAttribute("titlePeriodical", sessionUser.getAttribute("subject"));
        request.setAttribute("currentArticleTitle", sessionUser.getAttribute("articleName"));
        request.setAttribute("review", articlesService.getAboutArticleText((String) sessionUser.getAttribute("articleName")));
        request.setAttribute("pricePeriodical", periodicalsService.getPeriodicalPrice((String) sessionUser.getAttribute("subject")));
        request.setAttribute("articlesNum", periodicalsService.getArticlesNumberBySubject((String) sessionUser.getAttribute("subject")));
        request.setAttribute("followersNum", periodicalsService.getFollowersNumberBySubject((String) sessionUser.getAttribute("subject")));
        request.setAttribute("articles", articlesService.getArticleNamesByPeriodical((String) sessionUser.getAttribute("subject")));
        request.setAttribute("monthesCost", periodicalsService.getPeriodicalsMonthesCost((String) sessionUser.getAttribute("subject")));
        request.setAttribute("replenishAccountSubmit", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_REPLENISH_ACCOUNT_SUBMIT));
        request.setAttribute("logoutSubmit", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_LOGOUT_SUBMIT));
        request.setAttribute("signeUpPageInfo", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_SIGNE_UP));
        request.setAttribute("addonSignePageInfo", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_ADDON_SIGNE_UP_INFO));
        request.setAttribute("signeUpSubmit", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_SIGNE_UP_SUBMIT));
        request.setAttribute("backSubmit", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_BACK_SUBMIT));
        request.setAttribute("addonInfo", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_ADDON_INFO));
        request.setAttribute("subjectPageInfo", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_SUBJECT_INFO));
        request.setAttribute("pricePageInfo", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_PRICE_INFO));
        request.setAttribute("articlesNumberPageInfo", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_ARTICLES_NUMBER_INFO));
        request.setAttribute("followersNumberPageInfo", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_FOLLOWERS_NUMBER_INFO));
        request.setAttribute("availableArticlesPageInfo", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_AVAILABLE_ARTICLES_INFO));
        request.setAttribute("getAcquintedSubmit", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_GET_ACQUINTED_INFO));      
        homePageCommand.setChangingLanguage(false);
        return "periodicalunsigned.jsp";
    }
}
