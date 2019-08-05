package com.time.tracking.filter;

import com.time.tracking.config.ComponentInitializer;
import com.time.tracking.config.UserAuthorization;
import com.time.tracking.converter.dtoConverter.UserLoginDtoConverter;
import com.time.tracking.model.dto.user.UserLoginDto;
import com.time.tracking.model.enums.Role;
import com.time.tracking.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger(AuthorizationFilter.class);
    private HttpServletRequest httpRequest;
    private String sessionId;
    private Map<String, UserAuthorization> usersAuthorization;
    private UserLoginDtoConverter userLoginDtoConverter;
    private UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        userService = ComponentInitializer.getInstance().receiveObjectByType(UserService.class);
        userLoginDtoConverter = ComponentInitializer.getInstance().receiveObjectByType(UserLoginDtoConverter.class);

        Map<String, UserAuthorization> usersAuthorization = new HashMap<>();
        ServletContext context = filterConfig.getServletContext();
        context.setAttribute("usersAuthorization", usersAuthorization);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        httpRequest = (HttpServletRequest) servletRequest;
        sessionId = httpRequest.getSession().getId();
        usersAuthorization = (Map<String, UserAuthorization>) httpRequest.getServletContext().getAttribute("usersAuthorization");

        if (httpRequest.getSession().getAttribute("role") == null) {
            setSessionAttribute("role", Role.UNKNOWN);
        }

        authorizationUser();
        logout();

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private <T> void setSessionAttribute(String name, T value) {
        httpRequest.getSession().setAttribute(name, value);
    }

    private void authorizationUser() {

        String email = httpRequest.getParameter("email");
        String loginPageName = "login";
        if (httpRequest.getRequestURI().contains(loginPageName) && (email != null)) {

            String foreignUserSession = userSessionOtherSession(email);

            if (foreignUserSession != null) {
                removeUserAuthorization(foreignUserSession);
            }
            createUserAuthorization(email);
        }

    }

    private void logout() {
        String logoutPageName = "logout";
        if (httpRequest.getRequestURI().contains(logoutPageName)) {
            removeUserAuthorization(sessionId);
            setSessionAttribute("role", Role.UNKNOWN);
            LOGGER.debug("user logout " + sessionId);
        }
    }

    private String userSessionOtherSession(String email) {
        for (Map.Entry<String, UserAuthorization> stringUserAuthorizationEntry : usersAuthorization.entrySet()) {
            String key = stringUserAuthorizationEntry.getKey();
            UserAuthorization value = stringUserAuthorizationEntry.getValue();
            if (value.getEmail().equals(email) && !sessionId.equals(key)) {
                return key;
            }
        }
        return null;
    }

    private void removeUserAuthorization(String UserSession) {
        usersAuthorization.remove(UserSession);
    }

    private void createUserAuthorization(String email) {

        UserLoginDto userLoginDto = userLoginDtoConverter.convert(httpRequest);

        Role role = userService.receiveUserRole(userLoginDto);
        if (!role.equals(Role.UNKNOWN)) {
            int userId = userService.receiveUserId(userLoginDto);

            UserAuthorization userAuthorization = new UserAuthorization();
            userAuthorization.setEmail(email);
            userAuthorization.setRole(role);
            usersAuthorization.put(httpRequest.getSession().getId(), userAuthorization);
            setSessionAttribute("role", role);
            setSessionAttribute("user_id", userId);
            LOGGER.debug("user login: " + userId + " session: " + sessionId);
        }
    }

    @Override
    public void destroy() {

    }
}
