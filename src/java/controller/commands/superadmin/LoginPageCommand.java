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
public class LoginPageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessionAdmin = request.getSession(false);
        Locale locale = null;
        if (sessionAdmin != null){
            locale = (Locale) sessionAdmin.getAttribute(LOCALE_SESSION_KEY);
        } else {
            locale = new Locale(LANGUAGE_EN, COUNTRY_US);
        }
        request.setAttribute("loginAsSuperadminMessage", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_LOGIN_AS_SUPERADMIN));
        request.setAttribute("loginMessage", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_LOGIN_MESSAGE));
        request.setAttribute("passwordMessage", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_PASSWORD_MESSAGE));
        request.setAttribute("logInSubmit", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_LOGOIN_SUBMIT));
        return "superadmin_login.jsp";
    }
}
