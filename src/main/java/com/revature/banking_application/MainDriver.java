package com.revature.banking_application;

import com.revature.banking_application.util.AppState;
public class MainDriver {

    public static void main(String[] args) {

        System.out.println("1. AppState instantiated");
        AppState appState = new AppState();

        System.out.println("Jerry's Banking Application is starting up....");
        appState.startup();

    }
}
