package com.revature.banking_application.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.banking_application.exceptions.InvalidRequestException;
import com.revature.banking_application.models.Account;
import com.revature.banking_application.services.AccountServices;
import com.revature.banking_application.util.Logger.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WithdrawServlet extends HttpServlet implements Authable {
    private final AccountServices accountServices;

    private final ObjectMapper mapper;

    private final Logger logger = Logger.getLogger();

    public WithdrawServlet(AccountServices accountServices, ObjectMapper mapper){
        this.accountServices = accountServices;
        this.mapper = mapper;
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!Authable.checkAuth(req, resp)) return;

        Account newUpdate = mapper.readValue(req.getInputStream(), Account.class);
        Account updatedAccount = accountServices.withdraw(newUpdate);

        int ab = updatedAccount.getAccountBalance();
        if(ab < 0){
            throw new InvalidRequestException("Account could not withdraw due to an insufficient amount of cash");
        }

        String payload = mapper.writeValueAsString(updatedAccount);
        resp.getWriter().write("You have successfully withdrawn from your account");
        // resp.getWriter().write(payload);




    }
}
