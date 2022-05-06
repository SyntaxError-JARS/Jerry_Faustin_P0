package com.revature.banking_application.menus;

import java.io.BufferedReader;

public class LoginMenu {

    public BufferedReader terminalReader;

    public void render() throws Exception {
        System.out.println("What is your email?");
        String email = terminalReader.readLine();

        System.out.println("What is your password?");
        String password = terminalReader.readLine();
    }
}
