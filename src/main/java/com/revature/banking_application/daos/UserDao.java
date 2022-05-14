package com.revature.banking_application.daos;

import com.revature.banking_application.exceptions.ResourcePersistanceException;
import com.revature.banking_application.models.User;
import com.revature.banking_application.util.ConnectionFactory;
import com.revature.banking_application.util.Logger.Logger;
import java.io.*;
import java.sql.*;

public class UserDao implements Crudable<User> {

    private Logger logger = Logger.getLogger();

    //@Override
    public User create(User newUser){

        try(Connection conn = ConnectionFactory.getInstance().getConnection();){

            String sql = "insert into banking_info (email, fname, lname, password, dob) values (?, ?, ?, ?, ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            System.out.println(newUser.getFname());
            System.out.println(newUser.getLname());

            ps.setString(1, newUser.getEmail());
            ps.setString(2, newUser.getFname());
            ps.setString(3, newUser.getLname());
            ps.setString(4, newUser.getPassword());
            ps.setString(5, newUser.getDob());

            int checkInsert = ps.executeUpdate();

            if(checkInsert ==0){
                throw new ResourcePersistanceException("User was not entered into database due to some issue.");
            }


        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return newUser;

    }
    @Override
    public User[] findAll() throws IOException{

        User[] users = new User[10];
        int index = 0;

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "select * from banking_info";
            Statement s = conn.createStatement();

            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                User user = new User();

                user.setEmail(rs.getString("email"));
                user.setFname(rs.getString("fname"));
                user.setLname(rs.getString("lname"));
                user.setPassword(rs.getString("password"));
                user.setDob(rs.getString("dob"));

                System.out.println("Inserted user into index" + index);
                users[index] = user;
                index++;
                System.out.println("Going to the next Line for our following index.");
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        System.out.println("Returning user information.");
        return users;

        //@Override
        // public User findById(String id){
        //try(Connection conn = ConnectionFactory.getInstance().getConnection();){

        // String sql = "select * from"
        // }
    }
    @Override
    public boolean update(User updatedObj) {return false;}


    public User authenticateUser(String email, String password){

        try (Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "select * from banking_info where email = ? and password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if(!rs.next()) {
                return null;
            }

            User user = new User();

            user.setEmail(rs.getString("email"));
            user.setFname(rs.getString("fname"));
            user.setLname(rs.getString("lname"));
            user.setPassword(rs.getString("password"));
            user.setDob(rs.getString("dob"));

            return user;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    public boolean checkEmail(String email) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "select email from user where email = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (!rs.isBeforeFirst()) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}







