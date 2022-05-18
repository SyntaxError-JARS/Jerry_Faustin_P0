package com.revature.banking_application.daos;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Crudable<U> {

    // Create
    U create(U newObject);

    //Read
    U[] findAll() throws IOException;
     U findByEmail(String username);

    //Update
    U deposit(U updatedObj);
    U withdraw(U updatedObj);

    //Delete
    //boolean delete(String id);
}
