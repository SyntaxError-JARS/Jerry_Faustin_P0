package com.revature.banking_application.menus;

import java.io.BufferedReader;
import com.revature.banking_application.models.User;

public class RegisterMenu extends Menu {

    public BufferedReader terminalReader;

    public RegisterMenu(BufferedReader terminalReader){
        super("Register", "/register", terminalReader);
    }
    @Override
    public void render() throws Exception {

        System.out.println("What is your email?");
        String email = terminalReader.readLine();

        System.out.println("What is your first name?");
        String fname = terminalReader.readLine();

        System.out.println("What is your last name?");
        String lname = terminalReader.readLine();

        System.out.println("What is your password?");
        String password = terminalReader.readLine();

        System.out.println("Re-enter password");
        String passwordCheck = terminalReader.readLine();

        System.out.println("What is your Date of Birth?");
        String dob = terminalReader.readLine();

        // splitting String flName into a String array by " " spaces
        //String[] nameArray = flName.split(" ");
        // string

        if (!password.equals(passwordCheck)) { //checking if password does not equal passwordCheck
            System.out.println("Passwords do not match");
            return; // this breaks this method and returns us to main
        }
        User newUser = new User(email, fname, lname, password, dob);
        System.out.println("Here is the user that was added to JBank: " + newUser);
        // userServices.registerUser(newUser);
    }
}