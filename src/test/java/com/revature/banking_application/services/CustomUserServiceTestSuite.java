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
    @Disabled
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

    @Test
    public void test_create_givenValidUser_returnsUser(){
        // Arrange
        User user = new User("valid", "valid", "valid", "valid", "valid");
        // ensures that the services can continue execution and get expected results from the dao without any issues
        // when(mockUserDao.create(user)).thenReturn(user);

        // Act
        User actualUser = sut.create(user);

        // Assert
        Assertions.assertEquals("valid", actualUser.getEmail());
        Assertions.assertEquals("valid", actualUser.getFname());
        Assertions.assertEquals("valid", actualUser.getLname());
        Assertions.assertEquals("valid", actualUser.getPassword());
        Assertions.assertEquals("valid", actualUser.getDob());
        // verify(mockUserDao, times(1)).create(user);

    }

}
