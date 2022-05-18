package com.revature.banking_application.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.banking_application.models.Account;
import com.revature.banking_application.models.User;
import com.revature.banking_application.services.AccountServices;
import com.revature.banking_application.util.Logger.Logger;
import com.revature.banking_application.web.dto.ViewAccount;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AccountServlet extends HttpServlet implements Authable {

    private final AccountServices accountServices;

    private final ObjectMapper mapper;

    private final Logger logger = Logger.getLogger();

    public AccountServlet(AccountServices accountServices, ObjectMapper mapper){
        this.accountServices = accountServices;
        this.mapper = mapper;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(!Authable.checkAuth(req, resp)) return;

        Account newAccount = mapper.readValue(req.getInputStream(), Account.class);
        Account persistedAccount = accountServices.create(newAccount);

        String payload = mapper.writeValueAsString(persistedAccount);
        resp.getWriter().write("You have successfully created a money account ");
        resp.getWriter().write(payload);
        resp.setStatus(201);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        if(!Authable.checkAuth(req, resp)) return;

//        ViewAccount viewAccount = mapper.readValue(req.getInputStream(), ViewAccount.class);
//
//        Account readAccount = accountServices.readByEmail(viewAccount.getUsername());
//        HttpSession httpSession = req.getSession(true); // this makes sure that if the session doesn't exist, it will generate it. otherwise it will go ahead and overwrite current session
//        httpSession.setAttribute("readAccount", readAccount);
//
//        if(req.getParameter("username") != null){
//            Account account = accountServices.readByEmail(req.getParameter("username"));
//            String payload = mapper.writeValueAsString(account);
//            resp.getWriter().write("Here is the current balance for the user as shown below \n");
//            resp.getWriter().write(payload);
//
//        }else {
//            resp.getWriter().write("There is something wrong with your code here Jerry");
//        }
        ViewAccount viewAccount = mapper.readValue(req.getInputStream(), ViewAccount.class);
        Account account = accountServices.readByEmail(viewAccount.getUsername());

        String payload = mapper.writeValueAsString(account);

        resp.getWriter().write("Here is your account balance");
        resp.getWriter().write(payload);
        resp.setStatus(201);

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        if(!Authable.checkAuth(req, resp)) return;

        Account newUpdate = mapper.readValue(req.getInputStream(), Account.class);
        Account updatedAccount = accountServices.deposit(newUpdate);

        String payload = mapper.writeValueAsString(updatedAccount);
        resp.getWriter().write("You have successfully deposited into your account");
        // resp.getWriter().write(payload);




    }


}

