package com.revature.banking_application.web.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException { // 'ducking'
        resp.getWriter().write("<h1>Test Servlet is working as intended for banking_application</h1>");

    }
}