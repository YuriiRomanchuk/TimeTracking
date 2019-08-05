package com.time.tracking.servlet.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LocalizationFilter implements Filter {

    private final String defaultLanguage = "en";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        HttpSession session = httpRequest.getSession();

        if (session.getAttribute("lang") == null) {
            session.setAttribute("lang", defaultLanguage);
        }

        String languageRequest = httpRequest.getParameter("language");
        if (languageRequest != null && !session.getAttribute("lang").equals(languageRequest)) {
            session.setAttribute("lang", httpRequest.getParameter("language"));
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
