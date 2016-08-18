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
public class NameStatusCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessionUser = request.getSession(false);
        Locale locale = (Locale) sessionUser.getAttribute(LOCALE_SESSION_KEY);
        request.setAttribute("nameField", sessionUser.getAttribute("username"));
        request.setAttribute("account", userService.getUserMoney((String) sessionUser.getAttribute("email")));
        if ((boolean) sessionUser.getAttribute("isWriter")){
            request.setAttribute("isAuthorField", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_AUTHOR));
        }
        if ((boolean) sessionUser.getAttribute("isAdmin")){
            request.setAttribute("isAdminField", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_ADMIN));
        }
        return null;
    }
    
}
