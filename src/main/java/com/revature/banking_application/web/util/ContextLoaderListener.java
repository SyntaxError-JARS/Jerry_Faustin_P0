package com.revature.banking_application.web.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.banking_application.daos.AccountDao;
import com.revature.banking_application.daos.UserDao;
import com.revature.banking_application.services.AccountServices;
import com.revature.banking_application.services.UserServices;
import com.revature.banking_application.web.servlets.AccountServlet;
import com.revature.banking_application.web.servlets.AuthServlet;
import com.revature.banking_application.web.servlets.UserServlet;
import com.revature.banking_application.web.servlets.WithdrawServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce){
        ObjectMapper mapper = new ObjectMapper();

        AccountDao accountDao = new AccountDao();
        UserDao userDao = new UserDao();

        AccountServices accountServices = new AccountServices(accountDao);
        UserServices userServices = new UserServices(userDao);

        WithdrawServlet withdrawServlet = new WithdrawServlet(accountServices,mapper);
        AuthServlet authServlet = new AuthServlet(userServices, mapper);
        AccountServlet accountServlet = new AccountServlet(accountServices, mapper);
        UserServlet userServlet = new UserServlet(userServices, mapper);

        ServletContext context = sce.getServletContext();
        context.addServlet("WithdrawServlet", withdrawServlet).addMapping("/with");
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");
        context.addServlet("AccountServlet", accountServlet).addMapping("/accounts/*");
        context.addServlet("UserServlet", userServlet).addMapping("/users/*");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce){ ServletContextListener.super.contextDestroyed(sce); }



}
