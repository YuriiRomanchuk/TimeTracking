package com.time.tracking.servlet;

import com.time.tracking.config.ComponentInitializer;
import com.time.tracking.config.annotation.GetMessage;
import com.time.tracking.config.annotation.PostMessage;
import com.time.tracking.controller.Controller;
import com.time.tracking.converter.Converter;
import com.time.tracking.util.Reflection;
import com.time.tracking.view.RedirectView;
import com.time.tracking.view.View;
import com.time.tracking.view.ViewModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.function.Function;

public class RequestResolver {

    private static final String VIEW_ATTRIBUTE = "VIEW_ATTRIBUTE";

    private Map<String, Function<HttpServletRequest, View>> getControllers = new HashMap<>();
    private Map<String, Function<HttpServletRequest, View>> postControllers = new HashMap<>();

    public RequestResolver(ComponentInitializer componentInitializer) {

        //TODO: talk to Jo and rebuild
        List<Controller> controllers = componentInitializer.receiveObjectsByType(Controller.class);
        List<Converter> converters = componentInitializer.receiveObjectsByType(Converter.class);

        for (Controller controller : controllers) {
            Reflection.receiveAnnotatedMethods(controller.getClass(), GetMessage.class).forEach((method, path) -> getControllers.put(path.value(), request -> prepareMethodController(request, controller, method, converters)));
            Reflection.receiveAnnotatedMethods(controller.getClass(), PostMessage.class).forEach((method, path) -> postControllers.put(path.value(), request -> prepareMethodController(request, controller, method, converters)));
        }
    }

    private View prepareMethodController(HttpServletRequest request, Controller controller, Method method, List<Converter> converters) {
        try {
            List<Object> arguments = new ArrayList<>();
            for (Parameter parameter : method.getParameters()) {
                for (Converter converter : converters) {
                    Method currentMethod = Arrays.stream(converter.getClass().getDeclaredMethods())
                            .filter(m -> !m.getReturnType().isAssignableFrom(Object.class))
                            .findFirst().orElse(null);

                    if (currentMethod != null && currentMethod.getReturnType().isAssignableFrom(parameter.getType())) {
                        if (request.getClass().getTypeName().contains("RequestFacade")
                                & !currentMethod.getGenericParameterTypes()[0].getTypeName().contains("HttpServletRequest")) {
                            continue;
                        }
                        arguments.add(converter.convert(request));
                    }
                }
            }
            return (View) method.invoke(controller, arguments.toArray());
        } catch (Exception e) {
            View view = new ViewModel("/WEB-INF/jsp/error.jsp");
            view.addParameter("error", e);
            return view;
        }
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
