package com.time.tracking.servlet.listener;

import com.time.tracking.config.UserAuthorization;
import com.time.tracking.model.enums.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashMap;
import java.util.Map;

public class SessionListener implements HttpSessionListener {

    private static final Logger LOGGER = LogManager.getLogger(SessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent se) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        ServletContext servletContext = httpSessionEvent.getSession().getServletContext();
        Map<String, UserAuthorization> usersAuthorization = (HashMap<String, UserAuthorization>) servletContext.getAttribute("usersAuthorization");
        usersAuthorization.remove(httpSessionEvent.getSession().getId());
        httpSessionEvent.getSession().setAttribute("role", Role.UNKNOWN);

        LOGGER.debug("user logout" + httpSessionEvent.getSession().getId());
    }
}
