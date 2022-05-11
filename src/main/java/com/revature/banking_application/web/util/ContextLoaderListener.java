package com.revature.banking_application.web.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.banking_application.daos.UserDao;
import com.revature.banking_application.models.User;
import com.revature.banking_application.services.UserServices;
import com.revature.banking_application.web.servlets.AuthServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce){
        ObjectMapper mapper = new ObjectMapper();
        UserDao userDao = new UserDao();
        UserServices userServices = new UserServices(userDao);

        AuthServlet authServlet = new AuthServlet(userServices, mapper);

        ServletContext context = sce.getServletContext();
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");

    }

    public void contextDestroyed(ServletContextEvent sce){


    }
}
