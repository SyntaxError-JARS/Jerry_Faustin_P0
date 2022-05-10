package com.revature.banking_application.services;

import com.revature.banking_application.models.User;
import com.revature.banking_application.daos.UserDao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class UserServices {

    private UserDao userDao = new UserDao();

    public void readUsers(){
        System.out.println("Begin reading Users in our file database.");
        User[] users = new User[0];
        try {
            users = userDao.findAll();
            System.out.println("Users have been found, here are the results: \n");
            for (int i = 0; i < users.length; i++) {
                User user = users[i];
                System.out.println(user.toString());
            }
        } catch (IOException | NullPointerException e){
            // e.printStackTrace();
        }
    }

    // This is to check that the email is not already in our database
    public boolean validateEmailNotUsed(String email){
        userDao.checkEmail(email);
        return false;
    }

    public boolean registerUser(User newUser) {
        System.out.println("User trying to be registered: " + newUser);
        if(!validateUserInput(newUser)){
            System.out.println("User was not validated");
            throw new RuntimeException();
        }
      validateEmailNotUsed(newUser.getEmail());

        User persistedUser = UserDao.create(newUser);

        if (persistedUser == null){
            throw new RuntimeException();
        }
        System.out.println("User has been persisted: " + newUser);
        return true;
    }

    private boolean validateUserInput(User newUser) {
        System.out.println("Validating User: " + newUser);
        if(newUser == null) return false;
        if(newUser.getEmail() == null || newUser.getEmail().trim().equals("")) return false;
        if(newUser.getFname() == null || newUser.getFname().trim().equals("")) return false;
        if(newUser.getLname() == null || newUser.getLname().trim().equals("")) return false;
        if(newUser.getPassword() == null || newUser.getPassword().trim().equals("")) return false;
        return newUser.getDob() != null || !newUser.getDob().trim().equals("");
    }



}
