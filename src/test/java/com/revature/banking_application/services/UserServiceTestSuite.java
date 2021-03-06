package com.revature.banking_application.services;

import com.revature.banking_application.daos.UserDao;
import com.revature.banking_application.models.User;
import org.checkerframework.dataflow.qual.TerminatesExecution;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class UserServiceTestSuite {

    UserServices sut;
    UserDao mockUserDao;

    @BeforeEach
    public void testPrep(){
        // In order to specify and mock a Dao
        mockUserDao = mock(UserDao.class);
        sut = new UserServices(new UserDao());
    }

    @Test
    public void test_validateInput_givenValidUser_returnTrue(){

        // Arrange
        User user = new User("valid", "valid", "valid", "valid", "valid");

        // Act
        boolean actualResult = sut.validateInput(user);

        // Assert
        Assertions.assertTrue(actualResult);

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

    @Test
    @Disabled
    public void test1(){

    }

}
