package com.revature.banking_application.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.banking_application.daos.UserDao;
import com.revature.banking_application.models.User;
import com.revature.banking_application.services.UserServices;

import javax.management.relation.InvalidRelationIdException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {

    private final UserServices userServices = new UserServices(new UserDao());
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            User reqUser = mapper.readValue(req.getInputStream(), User.class);
            User authUser = userServices.authenticateUser(reqUser.getEmail(), reqUser.getPassword());

            HttpSession httpSession = req.getSession(true);
            httpSession.setAttribute("authUser", authUser);

            resp.setStatus(200); // default is 200
            resp.getWriter().write("You have successfully logged in");

        } catch (AuthenticationException | InvalidRelationIdException e) {
            resp.setStatus(404);
            resp.getWriter().write(e.getMessage());
        }
         catch (Exception e) {
            resp.setStatus(409);
            resp.getWriter().write(e.getMessage());
        }


    }
}