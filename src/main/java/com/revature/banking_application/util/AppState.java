package com.revature.banking_application.util;

import com.revature.banking_application.menus.RegisterMenu;
import com.revature.banking_application.menus.WelcomeMenu;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppState {

    private static boolean isRunning;
    private WelcomeMenu welcomeMenu;
    private RegisterMenu registerMenu;

    public AppState(){
        System.out.println("Generating instance of AppState.");
        isRunning = true;
        BufferedReader terminalReader = new BufferedReader(new InputStreamReader(System.in));

        this.welcomeMenu = new WelcomeMenu(terminalReader);
        this.registerMenu = new RegisterMenu(terminalReader);
    }

    public void startup(){
        try{
            while(isRunning) {
                System.out.println("Application successfully started");
                //registerMenu.render();
                welcomeMenu.render();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void shutdown() {
        isRunning = false;
        System.out.println("Application shutting down...");
    }
}


