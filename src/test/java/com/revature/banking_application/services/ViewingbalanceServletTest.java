package com.revature.banking_application.services;

import com.revature.banking_application.daos.AccountDao;
import com.revature.banking_application.daos.UserDao;
import com.revature.banking_application.models.Account;
import com.revature.banking_application.web.dto.ViewAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ViewingbalanceServletTest {

    AccountServices sut;


    @BeforeEach
    public void testprep() {
        sut = new AccountServices(new AccountDao());
    }

    @Test
    public void viewingBalanceServletTest_returnString() {

        Account actualResults = sut.readByEmail("wp@mail.com");
        System.out.println(actualResults);

    }

}
