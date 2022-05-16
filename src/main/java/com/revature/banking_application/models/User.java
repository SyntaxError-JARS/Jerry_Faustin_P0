package com.revature.banking_application.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {

    private String email;
    private String fname;
    private String lname;
    //@JsonIgnore
    private String password;
    private String dob;

    public User(String email, String fname, String lname, String password, String dob) {
        super();
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.password = password;
        this.dob = dob;
    }

    public User(String password) { this.password = password;}
    public User(){
    }

    // Getters and Setters//
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email;}

    public String getFname() { return fname;}
    public void setFname(String fname) { this.fname = fname;}

    public String getLname() { return lname;}
    public void setLname(String lname) { this.lname = lname;}

    public String getPassword() { return password;}
    public void setPassword(String password) { this.password = password;}

    public String getDob() { return dob;}
    public void setDob(String dob) {this.dob = dob;}

    public String toFileString() {

        StringBuilder mutableString = new StringBuilder();
        mutableString
                .append(email).append(",")
                .append(fname).append(".")
                .append(lname).append(".")
                .append(password).append(".")
                .append(dob);
        return mutableString.toString();
}
        @Override
        public String toString(){
            return "User{" +
                    "email='" + email + '\'' +
                    ", fname='" + fname + '\'' +
                    ", lname='" + lname + '\'' +
                    ", password='" + password + '\'' +
                    ", dob='" + dob + '\'' +
                    '}'; }
}