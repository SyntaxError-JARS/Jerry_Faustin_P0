package com.revature.banking_application.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.banking_application.daos.UserDao;
import com.revature.banking_application.models.User;
import com.revature.banking_application.services.UserServices;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserServlet extends HttpServlet {

    private final UserServices userServices;
    private final ObjectMapper mapper;

    public UserServlet(UserServices userServices, ObjectMapper mapper){
        this.userServices = userServices;
        this.mapper = mapper;

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //HttpSession httpSession = req.getSession();
        //if(httpSession.getAttribute("authTrainer") == null){
           // resp.getWriter().write("Unauthorized request - not logged in as registered user");
           // resp.setStatus(401); // Unauthorized
           // return;
        //}

        User[] users = userServices.readAll();

        String payload = mapper.writeValueAsString(users);

        resp.getWriter().write(payload);

    }

}
