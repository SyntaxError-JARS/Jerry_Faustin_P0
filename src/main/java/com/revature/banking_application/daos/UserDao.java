package com.revature.banking_application.daos;

import com.revature.banking_application.models.User;
import com.revature.banking_application.util.ConnectionFactory;
import java.io.*;
import java.sql.*;

public class UserDao implements Crudable<User> {

    //@Override
    public static User create(User newUser){

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
                throw new RuntimeException();
            }


        }catch (SQLException | RuntimeException e){
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
                User user = new User;

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

    public void checkEmail(String email) {
        String sql = "select email from user where email = " + email;
    }

}







