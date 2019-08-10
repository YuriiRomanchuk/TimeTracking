package com.time.tracking.servlet.filter;

import com.time.tracking.config.UserAuthorization;
import com.time.tracking.model.enums.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class UserPageFilter implements Filter {

    private List<String> forbiddenPagesUser;
    private List<String> forbiddenPagesAdmin;
    private List<String> forbiddenPagesUnknown;
    private List<String> allPages;

    @Override
    public void init(FilterConfig filterConfig) {
        forbiddenPagesUser = Arrays.asList(filterConfig.getInitParameter("forbiddenPagesUser").split(","));
        forbiddenPagesUnknown = Arrays.asList(filterConfig.getInitParameter("forbiddenPagesUnknown").split(","));
        forbiddenPagesAdmin = Arrays.asList(filterConfig.getInitParameter("forbiddenPagesAdmin").split(","));
        allPages = Arrays.asList(filterConfig.getInitParameter("allPages").split(","));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        String sessionId = httpRequest.getSession().getId();
        Map<String, UserAuthorization> usersAuthorization = (Map<String, UserAuthorization>) httpRequest.getServletContext().getAttribute("usersAuthorization");

        UserAuthorization userAuthorization = usersAuthorization.get(sessionId);

        boolean redirect = false;

        List<Function<HttpServletRequest, Boolean>> functions = receiveRulesByPage(userAuthorization);

        for (Function<HttpServletRequest, Boolean> function : functions) {
            redirect = function.apply(httpRequest);
            if (redirect || userAuthorization == null) {
                break;
            }
        }

        if (!redirect) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            redirectRules(userAuthorization, servletResponse, httpRequest);
        }

    }

    private List<Function<HttpServletRequest, Boolean>> receiveRulesByPage(UserAuthorization userAuthorization) {
        List<Function<HttpServletRequest, Boolean>> rules = new ArrayList<>();
        rules.add(request -> (userAuthorization == null) && pageIsLocked(request, forbiddenPagesUnknown));
        rules.add(request -> (userAuthorization.getRole().equals(Role.USER)) && pageIsLocked(request, forbiddenPagesUser));
        rules.add(request -> (userAuthorization.getRole().equals(Role.ADMIN)) && pageIsLocked(request, forbiddenPagesAdmin));

        return rules;
    }

    private boolean pageIsLocked(HttpServletRequest httpRequest, List<String> forbiddenPages) {
        return forbiddenPages.stream().anyMatch(charSequence -> httpRequest.getRequestURI().contains(charSequence.trim()));
    }

    private void redirectRules(UserAuthorization userAuthorization, ServletResponse servletResponse, HttpServletRequest httpRequest) throws IOException {

        if (userAuthorization == null) {
            ((HttpServletResponse) servletResponse).sendRedirect(httpRequest.getContextPath() + "/main/login");
        } else if (userAuthorization.getRole().equals(Role.USER)) {
            ((HttpServletResponse) servletResponse).sendRedirect(httpRequest.getContextPath() + "/main/user-personal-area");
        } else {
            ((HttpServletResponse) servletResponse).sendRedirect(httpRequest.getContextPath() + "/main/admin-personal-area");
        }
    }

    @Override
    public void destroy() {

    }
}
