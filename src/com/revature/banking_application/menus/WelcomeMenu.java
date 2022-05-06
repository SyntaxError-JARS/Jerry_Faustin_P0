package com.revature.banking_application.menus;

import java.io.BufferedReader;

import static com.revature.banking_application.util.AppState.shutdown;

public class WelcomeMenu extends Menu {

    public BufferedReader terminalReader;

    public WelcomeMenu(BufferedReader terminalReader){
        super("Welcome", "/welcome", terminalReader);
    }
    @Override
    public void render() throws Exception{

        //this is my intro
        System.out.println("This is the start of Jerry's Banking Application");

        //This is the welcome message for users
        String welcome = "Welcome to JBank!";
        String option1 = " 1) Login";
        String option2 = " 2) Register)";
        // String()calls the constructor for the string class
        // new keyword instantiates it
        //String option3 = new String();

       // String goodbye;
       // goodbye = "Have a wonderful day";
        String option3 = " 3) Exit the Banking Application";

        System.out.printf("%s \n %s \n %s \n %s", welcome, option1, option2, option3).println();
        System.out.print("\n Select number from above\n >");
        // BufferedReader terminalReader = null;
        String userSelection = terminalReader.readLine();

        switch (userSelection) {
            case "1":
                System.out.println("User has selected login...");
                break;
            case "2":
                System.out.println("User has selected register...");
                break;
            case "3":
                System.out.println("User has selected exit");
                System.out.println("Have a Great Day!");
                shutdown(); //imported from AppState
                break;
            default:
                System.out.println("No valid user input provided");
                break;

        }
    }
}
