/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands.user;

import controller.commands.Command;
import static controller.constants.ConstantsController.PASSWORD_SALT;
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
public class LoginCommand implements Command {
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessionUser = request.getSession(false);
        Locale locale = (Locale) sessionUser.getAttribute(LOCALE_SESSION_KEY);
        if (userService.checkLoginFields(request.getParameter("email"), request.getParameter("password"))){
            if (userService.checkLogin(request.getParameter("email"), (request.getParameter("password") + PASSWORD_SALT).hashCode())){
                sessionUser.setAttribute("isLogined", true);
                sessionUser.setAttribute("isWriter", userService.getWriterRights(request.getParameter("email")));
                sessionUser.setAttribute("isAdmin", userService.getAdminRights(request.getParameter("email")));
                sessionUser.setAttribute("username", userService.getFullUserName(request.getParameter("email")));
                sessionUser.setAttribute("email", request.getParameter("email"));
                return userPageCommand.execute(request, response);
            } else{
                request.setAttribute("fieldFailInfo", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_LOGIN_NOT_CORRECT));
                return homePageCommand.execute(request, response);
            }
        } else {
            request.setAttribute("fieldFailInfo", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_VALUES_NOT_CORRECT));
            return homePageCommand.execute(request, response);
        }
    }
    
}
