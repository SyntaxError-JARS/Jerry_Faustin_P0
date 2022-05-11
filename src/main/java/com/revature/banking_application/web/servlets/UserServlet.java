package com.revature.banking_application.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.banking_application.daos.UserDao;
import com.revature.banking_application.services.UserServices;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet {

    private final UserServices userServices = new UserServices(new UserDao());
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
