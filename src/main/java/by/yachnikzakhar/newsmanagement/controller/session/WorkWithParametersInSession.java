package by.yachnikzakhar.newsmanagement.controller.session;

import jakarta.servlet.http.HttpSession;

import java.util.List;

public class WorkWithParametersInSession {

    public static void saveAuthorisationParametersInSession(HttpSession session, Integer userId ,String username, List<String> roles, boolean isAdmin) {
        session.setAttribute(SessionConstants.USER_ID, userId);
        session.setAttribute(SessionConstants.USER_LOGIN, username);
        session.setAttribute(SessionConstants.ROLES, roles);
        session.setAttribute(SessionConstants.IS_ADMIN, isAdmin);
    }

    public static void removeAuthorisationParametersInSession(HttpSession session) {
        session.removeAttribute(SessionConstants.USER_ID);
        session.removeAttribute(SessionConstants.USER_LOGIN);
        session.removeAttribute(SessionConstants.ROLES);
        session.removeAttribute(SessionConstants.IS_ADMIN);
    }
}
