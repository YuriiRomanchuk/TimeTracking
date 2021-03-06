package com.time.tracking.servlet;

import com.time.tracking.config.ComponentInitializer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/main/*")
public class Servlet extends HttpServlet {

    RequestResolver requestResolver;

    @Override
    public void init() {
        requestResolver = ComponentInitializer.getInstance().getRequestResolver();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        requestResolver.resolveGetRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        requestResolver.resolvePostRequest(req, resp);
    }

}
