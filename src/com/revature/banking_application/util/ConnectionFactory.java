package com.revature.banking_application.util;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static final ConnectionFactory connectionFactory = new ConnectionFactory(); //Eager Singleton Pattern
    private Properties prop = new Properties();

    //specifically a singleton because of the private constructor
    private ConnectionFactory() {
        try {
            prop.load(new FileReader());

        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    static {
        try {
            Class.forName("org.postgresql.Driver");
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    // Way for out ConnectionFactory to be obtained by other classes
    public static ConnectionFactory getInstance() {
        return connectionFactory;
    }

    // Once we getInstance() we are able to execute getConnection to return a Connection to our database
    public Connection getConnection() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
