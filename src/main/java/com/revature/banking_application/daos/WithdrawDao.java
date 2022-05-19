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

public class WithdrawDao {

    private Logger logger = Logger.getLogger();
    public Account withdraw(Integer account_balance, String username, Account newUpdate ) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql2 = "select account_balance where username = ? ";
            PreparedStatement ps2 = conn.prepareStatement(sql2);

            ps2.setInt(1, account_balance);
            ps2.setString(2, username);

            ResultSet  rs = ps2.executeQuery(sql2);

            if (!rs.next()) {
                throw new ResourcePersistanceException("Username was not found in the database.");
            }
            int af = account_balance;
            int ag = newUpdate.getAccountBalance();
            if (af < ag) {
                throw new InvalidRequestException("Account could not withdraw due to insufficient funds ");
            }
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

}
