package com.revature.banking_application;

import com.revature.banking_application.util.AppState;
public class MainDriver {

    public static void main(String[] args) {

        try {
            System.out.println("AppState instantiated");
            AppState appState = new AppState();

            System.out.println("Jerry's Banking Application is starting up....");
            appState.startup();

        }catch (NullPointerException e ) {
            e.printStackTrace();
        }

    }
}
