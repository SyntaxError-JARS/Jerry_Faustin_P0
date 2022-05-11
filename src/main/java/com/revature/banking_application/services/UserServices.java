package com.revature.banking_application.services;

import com.revature.banking_application.exceptions.AuthenticationException;
import com.revature.banking_application.exceptions.InvalidRequestException;
import com.revature.banking_application.exceptions.ResourcePersistanceException;
import com.revature.banking_application.models.User;
import com.revature.banking_application.daos.UserDao;
import com.revature.banking_application.util.Logger.Logger;


import java.io.IOException;


public class UserServices implements Serviceable<User> {

    private UserDao userDao;

    private Logger logger = Logger.getLogger();

    public UserServices(UserDao userDao) { this.userDao = userDao;}
    @Override
    public User[] readAll(){
        logger.info("Begin reading Users in our file database.");

        try {
            User[] users = userDao.findAll();
            logger.info("Users have been found, here are the results: \n");

            return users;
        } catch (IOException | NullPointerException e){
             e.printStackTrace();
             return null;
        }
    }

    @Override
    public User update(User updatedObject) { return null;}

    //@Override
    //public boolean delete(String id) { return false;}

    // This is to check that the email is not already in our database
    public boolean validateEmailNotUsed(String email){
        return userDao.checkEmail(email);
    }

    public User create(User newUser) {
        logger.info("User trying to be registered: " + newUser);
        if(!validateInput(newUser)){
            throw new InvalidRequestException("User was not validated");
        }
        if(validateEmailNotUsed(newUser.getEmail())){
            throw new InvalidRequestException("User email is already in use. Please try again with another email or login into previous made account.");
        }

        User persistedUser = userDao.create(newUser);

        if (persistedUser == null){
            throw new ResourcePersistanceException("User was not persisted to the database upon registration");
        }
        logger.info("User has been persisted: " + newUser);
        return persistedUser;
    }

    public boolean validateInput(User newUser) {
        logger.debug("Validating User: " + newUser);
        if(newUser == null) return false;
        if(newUser.getEmail() == null || newUser.getEmail().trim().equals("")) return false;
        if(newUser.getFname() == null || newUser.getFname().trim().equals("")) return false;
        if(newUser.getLname() == null || newUser.getLname().trim().equals("")) return false;
        if(newUser.getPassword() == null || newUser.getPassword().trim().equals("")) return false;
        return newUser.getDob() != null || !newUser.getDob().trim().equals("");
    }

    public User authenticateUser(String email, String password) {

        if (password == null || password.trim().equals("") || password == null || password.trim().equals("")){
            throw new InvalidRequestException("Either username or password is an invalid entry. Please try logging in again");
        }

        User authenticatedUser = userDao.authenticateUser(email, password);

        if (authenticatedUser == null) {
            throw new AuthenticationException("Unauthenticated user, information provided was not consistent with our database.");
        }

        return authenticatedUser;
    }



}
