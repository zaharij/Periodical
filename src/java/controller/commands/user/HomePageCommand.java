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
public class HomePageCommand implements Command {
    private boolean changingLanguage = false;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessionUser = request.getSession(false);
        Locale locale = null;
        if (sessionUser != null){
            locale = (Locale) sessionUser.getAttribute(LOCALE_SESSION_KEY);
        } else {
            locale = new Locale(LANGUAGE_EN, COUNTRY_US);
        }
        request.setAttribute("periodicals", periodicalsService.getAllPeriodicalsNames());
        request.setAttribute("authNum", userService.getAuthorsNumber());
        request.setAttribute("emailPageInfo", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_EMAIL_LOGIN_MESSAGE));
        request.setAttribute("passwordPageInfo", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_PASSWORD_MESSAGE));
        request.setAttribute("loginSubmit", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_LOGOIN_SUBMIT));
        request.setAttribute("registrationSubmit", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_REGISTRATION_SUBMIT));
        request.setAttribute("availablePeriodicalsPageInfo", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_AVAILABLE_PERIODICALS));
        request.setAttribute("authorsNumberPageInfo", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_AUTHORS_NUMBER));
        return "main.jsp";
    }

    /**
     * @return the changingLanguage
     */
    public boolean isChangingLanguage() {
        return changingLanguage;
    }

    /**
     * @param changingLanguage the changingLanguage to set
     */
    public void setChangingLanguage(boolean changingLanguage) {
        this.changingLanguage = changingLanguage;
    }
    
    
}
