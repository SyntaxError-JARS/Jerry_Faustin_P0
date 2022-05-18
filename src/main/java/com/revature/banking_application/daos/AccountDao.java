package com.revature.banking_application.daos;

import com.revature.banking_application.exceptions.InvalidRequestException;
import com.revature.banking_application.exceptions.ResourcePersistanceException;
import com.revature.banking_application.models.Account;
import com.revature.banking_application.util.ConnectionFactory;
import com.revature.banking_application.util.Logger.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDao implements Crudable <Account> {

    private Logger logger = Logger.getLogger();

    @Override
    public Account create(Account newAccount) {

        try(Connection conn = ConnectionFactory.getInstance().getConnection();){

            String sql = "insert into account values (default, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, newAccount.getAccountBalance());
            ps.setString(2, newAccount.getAccountAction());
            ps.setString(3, newAccount.getUsername());

            int checkInsert = ps.executeUpdate();

            if(checkInsert ==0){
                throw new ResourcePersistanceException("Account could not be entered into the database due to some issue.");
            }

            int ab = newAccount.getAccountBalance();

            if(ab < 0){
                throw new InvalidRequestException("Account could not deposit due to a negative deposit amount");
            }
            String aa = newAccount.getAccountAction();

            if(aa.equals("deposit")) {
                return newAccount;
            }else {
                throw new InvalidRequestException("Account could not deposit due to inaccurate account_action");

            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Account[] findAll() throws IOException{
        return null;
    }

    @Override
    public Account findByEmail(String username) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "select account_balance, username from account where username = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                throw new ResourcePersistanceException("Username was not found in the database.");
            }

            Account accountBalance = new Account();
            accountBalance.setAccountBalance(rs.getInt("account_balance"));
//           accountBalance.setAccountAction(rs.getString("account_action"));
           accountBalance.setUsername(rs.getString("username"));

            return accountBalance;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }

    }


    public Account withdraw(Account newUpdate) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "Update account Set account_balance = account_balance - ?, account_action = ? where username = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, newUpdate.getAccountBalance());
            ps.setString(2, newUpdate.getAccountAction());
            ps.setString(3, newUpdate.getUsername());

            int checkInsert = ps.executeUpdate();

            if(checkInsert ==0){
                throw new RuntimeException();
            }
            int ab = newUpdate.getAccountBalance();

            if(ab < 0){
                throw new InvalidRequestException("Account could not withdraw due to a negative withdraw amount");
            }
            String aa = newUpdate.getAccountAction();
            if(aa.equals("withdraw")) {
                return newUpdate;
            }else {
                throw new InvalidRequestException("Account could not withdraw due to inaccurate account_action");
            }

        }catch (SQLException e) {
            e.printStackTrace();
            return null;

        }

    }



    // @Override
   // public  Account update(String account_balance, String account_action, String username) {}
    public  Account deposit(Account newUpdate) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "Update account Set account_balance = account_balance + ?, account_action = ? where username = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setInt(1, Integer.parseInt(account_balance));
//            ps.setString(2, account_action);
//            ps.setString(3, username);
            ps.setInt(1, newUpdate.getAccountBalance());
            ps.setString(2, newUpdate.getAccountAction());
            ps.setString(3, newUpdate.getUsername());

            int checkInsert = ps.executeUpdate();

            if(checkInsert ==0){
                throw new RuntimeException();
            }
            int ab = newUpdate.getAccountBalance();

            if(ab < 0){
                throw new InvalidRequestException("Account could not deposit due to a negative deposit amount");
            }
            String aa = newUpdate.getAccountAction();

            if(aa.equals("deposit")) {
                return newUpdate;
            }else {
                 throw new InvalidRequestException("Account could not deposit due to inaccurate account_action");

            }
//            String aa = newUpdate.getAccountAction();
//            if(aa.equals("withdraw")) {
//
//            }else if (aa.equals("deposit")){
//
//            }else {
//                System.out.println("You did not properly specify if you want a withdrawal or deposit");
//            }
//            Account newAccountBalance = new Account();
//
//            newAccountBalance.setAccountBalance(newUpdate.getAccountBalance());

            }catch (SQLException e) {
            e.printStackTrace();
             return null;

        }

    }

}

