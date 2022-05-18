package com.revature.banking_application.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.banking_application.models.User;
import com.revature.banking_application.services.UserServices;
import com.revature.banking_application.util.Logger.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// @WebServlet("/user")
public class UserServlet extends HttpServlet implements Authable {

    private final UserServices userServices;

    private final ObjectMapper mapper;

    private final Logger logger = Logger.getLogger();

    public UserServlet(UserServices userServices, ObjectMapper mapper) {
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

//        User[] users = userServices.readAll();
//
//        String payload = mapper.writeValueAsString(users);
//
//        resp.getWriter().write(payload);
//
//    }

        if (!Authable.checkAuth(req, resp)) return;
        if (req.getParameter("id") != null && req.getParameter("email") != null) {
            resp.getWriter().write("Here is your id and email" + req.getParameter("id") + "" + req.getParameter("email"));
        }

//        if (req.getParameter("id") != null) {
//            User user;
//            try {
//                user = userServices.readByID(req.getParameter("id"));
//            } catch (ResourcePersistanceException e) {
//                logger.warn(e.getMessage());
//                resp.setStatus(404);
//                resp.getWriter().write(e.getMessage());
//                return;
//            }
//            String payload = mapper.writeValueAsString(user);
//            resp.getWriter().write(payload);
//            return;
//        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User newUser = mapper.readValue(req.getInputStream(), User.class);
        User persistedUser = userServices.create(newUser);

        String payload = mapper.writeValueAsString(persistedUser);
        resp.getWriter().write("You have successfully created a JBank account");
        resp.getWriter().write(payload);
        resp.setStatus(201);

//        try {
//
//            // User registerUser = userServices.create(new User("", "", "", "", ""));
//            // UserServices registerUser = mapper.readValue(req.getInputStream(), UserServices.class);
//           // User regUser = userServices.create(registerUser);
//            User registerUser = userServices.create(new User("", "", "", "", ""));
//
//
//            //User registerUser = userServices.create(registerCreds.setEmail(), registerCreds.setFname(), registerCreds.setLname(), registerCreds.setPassword(), registerCreds.setDob());
//            // User regUser = userServices.create(registerUser.create(new User("","","","","")));
//            //User registerUser = userServices.create(registerCreds.getEmail(), registerCreds.getFname(), registerCreds.getLname(), registerCreds.getPassword(), registerCreds.getDob());
//
//            HttpSession httpSession = req.getSession(true);
//            httpSession.setAttribute("registerUser", registerUser);
//
//            resp.getWriter().write("You have successfully created an account");
//        } catch (AuthenticationException | InvalidRequestException e) {
//            resp.setStatus(404);
//            resp.getWriter().write(e.getMessage());
//        }catch (Exception e) {
//            resp.setStatus(409);
//            resp.getWriter().write(e.getMessage());
//        }

    }
}
