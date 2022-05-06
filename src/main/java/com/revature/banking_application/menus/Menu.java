package com.revature.banking_application.menus;

import java.io.BufferedReader;

public abstract class Menu {

    //these are protected so any child class can utilize them
    protected String name;
    protected String route;
    protected BufferedReader terminalReader;

    public Menu(String name, String route, BufferedReader terminalReader) {
        super();
        this.name = name;
        this.route = route;
        this.terminalReader = terminalReader;
    }

    public String getName() { return name;}

    public String getRoute() { return route;}

    public abstract void render() throws Exception;
}
