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
public class RegisterFormCommand implements Command {
    private static String imgNameId;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessionUser = request.getSession(false);
        Locale locale = (Locale) sessionUser.getAttribute(LOCALE_SESSION_KEY);
        imgNameId = userService.getRandomLogImgName();
        request.setAttribute("logimg", getImgNameId() + ".jpg");
        request.setAttribute("firstNamePageInfo", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_FIRST_NAME));
        request.setAttribute("middleNamePageInfo", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_MIDDLE_NAME));
        request.setAttribute("lastNamePageInfo", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_LAST_NAME));
        request.setAttribute("emailPageInfo", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_EMAIL_REG));
        request.setAttribute("passwordPageInfo", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_PASSWORD_REG));
        request.setAttribute("repeatPasswordPageInfo", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_REPEAT_PASS_REG));
        request.setAttribute("enterFromPicPageInfo", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_ENTER_FROM_PIC));
        request.setAttribute("requiredFieldsPageInfo", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_REQUIRED_FIELDS));
        request.setAttribute("registerSubmit", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_REGISTER_SUBMIT));
        request.setAttribute("mainSubmit", adminPageCommand.getResourceBundle(locale, LOCALE_KEY_MAIN_SUBMIT));
        return "register.jsp";
    }

    /**
     * @return the imgNameId
     */
    public String getImgNameId() {        
        return imgNameId;
    }
}
