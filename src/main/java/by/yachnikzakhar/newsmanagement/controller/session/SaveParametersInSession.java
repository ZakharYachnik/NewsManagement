package by.yachnikzakhar.newsmanagement.controller.session;

import jakarta.servlet.http.HttpSession;

import java.util.List;

public class SaveParametersInSession {

    public static void saveAuthorisationParametersInSession(HttpSession session, String userName, List<String> roles, boolean isAdmin) {
        session.setAttribute(SessionConstants.USER_LOGIN, userName);
        session.setAttribute(SessionConstants.ROLES, roles);
        session.setAttribute(SessionConstants.IS_ADMIN, isAdmin);
    }
}
