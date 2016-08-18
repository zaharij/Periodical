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
public class PeriodicalPageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessionUser = request.getSession(false);
        Locale locale = (Locale) sessionUser.getAttribute(LOCALE_SESSION_KEY);
        nameStatusCommand.execute(request, response);
        if (request.getParameter("subjectOpen") != null){
            sessionUser.setAttribute("subjectOpen", Command.decodeParameter(request.getParameter("subjectOpen")));
        }
        request.setAttribute("titlePeriodical", sessionUser.getAttribute("subjectOpen"));
        request.setAttribute("subscriptionDate", periodicalsService.getDateEndSubscription((String) sessionUser.getAttribute("email"), (String) sessionUser.getAttribute("subjectOpen")));
        request.setAttribute("articles", articlesService.getArticleNamesByPeriodical((String) sessionUser.getAttribute("subjectOpen")));
        request.setAttribute("replenishAccountSubmit", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_REPLENISH_ACCOUNT_SUBMIT));
        request.setAttribute("logoutSubmit", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_LOGOUT_SUBMIT));
        request.setAttribute("backSubmit", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_BACK_SUBMIT));
        request.setAttribute("dateSubscriptionPageInfo", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_SUBSCRIPTION_DATE_INFO));
        request.setAttribute("availableArticlesPageInfo", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_AVAILABLE_ARTICLES_INFO));
        request.setAttribute("openSubmit", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_OPEN_SUBMIT));
        return "periodical.jsp";
    }
}
