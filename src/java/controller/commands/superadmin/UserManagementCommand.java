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
public class UserManagementCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessionAdmin = request.getSession(false);
        Locale locale = (Locale) sessionAdmin.getAttribute(LOCALE_SESSION_KEY);
        request.setAttribute("loginField", sessionAdmin.getAttribute("login"));
        request.setAttribute("admins", userService.getAdminUsers());
        request.setAttribute("usersSimple", userService.getSimpleUsers());
        request.setAttribute("usersAll", userService.getAllUsers());
        request.setAttribute("usersUnblocked", userService.getUnblockedUsers());
        request.setAttribute("usersBlocked", userService.getBlockedUsers());
        request.setAttribute("logoutSubmit", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_LOGOUT_SUBMIT));
        request.setAttribute("periodicalsManagementSubmit", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_PERIODICALS_MANAGEMENT));
        request.setAttribute("setAdminRightsMessage", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_PERIODICALS_SET_ADMIN_RIGHTS));
        request.setAttribute("setAdminSubmit", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_SET_ADMIN_SUBMIT));
        request.setAttribute("unsetAdminSubmit", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_UNSET_ADMIN_SUBMIT));
        request.setAttribute("deleteUserMessage", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_DELETE_USER));
        request.setAttribute("unblockSubmit", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_UNBLOCK_SUBMIT));
        request.setAttribute("blockSubmit", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_BLOCK_SUBMIT));
        request.setAttribute("deleteSubmit", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_DELETE_SUBMIT));
        request.setAttribute("payMessage", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_PAY_MESSAGE));
        request.setAttribute("paySubmit", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_PAY_SUBMIT));
        request.setAttribute("blockMessage", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_BLOCK_USER));
        return "superadminuser.jsp";
    }
}
