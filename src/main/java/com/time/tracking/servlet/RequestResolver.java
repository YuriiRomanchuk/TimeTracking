package com.timeTracking.servlet;

import com.timeTracking.config.ComponentInitializer;
import com.timeTracking.controller.Controller;
import view.RedirectView;
import view.View;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class RequestResolver {

    private static final String VIEW_ATTRIBUTE = "VIEW_ATTRIBUTE";

    private Map<String, Function<HttpServletRequest, View>> getControllers = new HashMap<>();
    private Map<String, Function<HttpServletRequest, View>> postControllers = new HashMap<>();

    public RequestResolver(ComponentInitializer ComponentInitializer) {

       /* List<Controller> controllers = ComponentInitializer.receiveObjectsByType();*/




    }

    public void resolveGetRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        reference(request, response, getControllers);
    }

    public void resolvePostRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        reference(request, response, postControllers);
    }

    private void reference(HttpServletRequest request, HttpServletResponse response, Map<String, Function<HttpServletRequest, View>> controller) throws IOException, ServletException {
        try {
            reference(getView(request, controller), request, response);
        } catch (Exception e) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
            request.setAttribute("error", e);
            requestDispatcher.forward(request, response);
        }
    }

    private void reference(View view, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (view instanceof RedirectView) {
            request.getSession().setAttribute(VIEW_ATTRIBUTE, view.getView());
            response.sendRedirect(request.getContextPath() + "/main/" + view.getPageUrl());
        } else if (view != null) {
            view.getParameters().forEach(request::setAttribute);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/" + view.getPageUrl());
            requestDispatcher.forward(request, response);
        }
    }

    private View getView(HttpServletRequest request, Map<String, Function<HttpServletRequest, View>> sourceController) {
        String requestURI = request.getRequestURI().replace(request.getContextPath() + "/main", "");
        View originView = (View) request.getSession().getAttribute(VIEW_ATTRIBUTE);
        request.getSession().removeAttribute(VIEW_ATTRIBUTE);

        View destinationView = Optional.ofNullable(sourceController.get(requestURI)).map(f -> f.apply(request)).orElse(null);
        if (originView != null && destinationView != null) {
            originView.getParameters().forEach(destinationView::addParameter);
        }
        return destinationView;
    }

}
