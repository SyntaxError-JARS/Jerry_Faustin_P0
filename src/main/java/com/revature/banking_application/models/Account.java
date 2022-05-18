package com.revature.banking_application.models;

public class Account {

    private int accountBalance;
    private String accountAction;
    private String username;


    public Account(){
        super();
    }

    public Account(int accountBalance, String accountAction, String username) {

        this.accountBalance = accountBalance;
        this.accountAction = accountAction;
        this.username = username;
    }

    public int getAccountBalance() { return accountBalance;}
    public void setAccountBalance(int accountBalance) {this.accountBalance = accountBalance;}

    public String getAccountAction() { return accountAction;}
    public void setAccountAction(String accountAction) {this.accountAction = accountAction;}

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    @Override
    public String toString() {
        return "Account{" +
                "accountBalance='" + accountBalance + '\'' +
                ", accountAction='" + accountAction + '\'' +
                ", username='" + username + '\'' +
                '}';
    }



}
