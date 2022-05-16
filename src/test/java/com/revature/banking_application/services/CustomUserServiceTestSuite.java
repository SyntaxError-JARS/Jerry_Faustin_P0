package com.revature.banking_application.services;

import com.revature.banking_application.daos.UserDao;
import com.revature.banking_application.exceptions.AuthenticationException;
import com.revature.banking_application.services.UserServices;
import com.revature.banking_application.models.User;
import org.checkerframework.dataflow.qual.TerminatesExecution;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class CustomUserServiceTestSuite {

    UserServices sut;
    // UserDao tut;

    @BeforeEach
    public void testPrep(){
        sut = new UserServices(new UserDao());
    }

    @Test
    public void testValidPasswordUsernameLogin_returnString() throws AuthenticationException {
       // User[] actualResult = sut.readAll();
        //  System.out.println(actualResult);

        //User user = new User("test", "test", "test", "test", "test");
        User actualResult = sut.authenticateUser("jf@mail.com", "password");
        System.out.println("");
        System.out.println(actualResult);


      //boolean actualResult = sut.validateInput(user);
      //Assertions.assertTrue(actualResult);
    }

}
