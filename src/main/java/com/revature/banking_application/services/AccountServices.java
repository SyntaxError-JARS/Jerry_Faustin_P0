package com.revature.banking_application.services;

import com.revature.banking_application.daos.AccountDao;
import com.revature.banking_application.daos.Crudable;
import com.revature.banking_application.exceptions.AuthenticationException;
import com.revature.banking_application.exceptions.InvalidRequestException;
import com.revature.banking_application.exceptions.ResourcePersistanceException;
import com.revature.banking_application.models.Account;
import com.revature.banking_application.models.User;
import com.revature.banking_application.util.Logger.Logger;

import java.io.IOException;

public class AccountServices implements Serviceable<Account> {

        private AccountDao accountDao;
        private Logger logger = Logger.getLogger();

        public AccountServices(AccountDao accountDao) {this.accountDao = accountDao;}


    @Override
    public Account[] readAll(){
        logger.info("Begin reading Users in our file database.");

        try {
            Account[] accounts = accountDao.findAll();
            logger.info("Accounts have been found, here are the results: \n");

            return accounts;
        } catch (IOException | NullPointerException e){
            e.printStackTrace();
            return null;
        }
    }
    public Account withdraw(Account newUpdate) {

//          Account  withDrawTest = accountDao.withdraw(newUpdate);
//          int ab = newUpdate.getAccountBalance();
//          // int ac
//          if(ab < 0){
//              throw new InvalidRequestException("Account could not withdraw due to an insufficient amount of cash");
//          }
            return accountDao.withdraw(newUpdate);
        }

    @Override
    public Account deposit(Account newUpdate) {
        logger.info("User trying to update: " + newUpdate);



     //   Account updatableAccount = accountDao.update(newUpdate);
//
//        Integer ab = newUpdate.getAccountBalance(); //original value
//        Integer bd = newUpdate.getAccountBalance(); //original value that will change based on a deposit
//        Integer bw = newUpdate.getAccountBalance(); //original value that will change based on a withdrawal
//        String aa = newUpdate.getAccountAction();
//        if(aa.equals("withdraw")) {
//
//            int fw = ab - bw; // original value - withdrawal amount
//            if (fw < 0){
//                System.out.println("You do not have enough funds in your account balance.");
//            }
//
//            newUpdate.getAccountBalance() = fw;
//        }else if (aa.equals("deposit")){
//
//            Integer fw = ab + bd;
//        }else {
//            System.out.println("You did not properly specify if you want a withdrawal or deposit");
//        }



        //return updatableAccount;
        return accountDao.deposit(newUpdate);
        // return accountDao.update(newUpdate);
        }

    @Override
    public Account readByEmail(String username) {
        if (username == null || username.trim().equals("")){
            throw new InvalidRequestException("Username is an invalid entry. Please try again ");
        }

        Account readAccount = accountDao.findByEmail(username);

        if (readAccount == null) {
            throw new AuthenticationException("Unauthenticated username, information provided was not consistent with our database.");
        }



        return readAccount;
    }
//
//    @Override
//    public boolean validateInput(Account newAccount) { return false;}

    @Override
    public Account create(Account newAccount) {
        logger.info("Account trying to be made: " + newAccount);
        return accountDao.create(newAccount);

        }
//        if(validateEmailNotUsed(newAccount.getEmail())){
//            throw new InvalidRequestException("User email is already in use. Please try again with another email or login into previous made account.");
//        }

    @Override
    public boolean validateInput(Account object) { return false;}




    }


