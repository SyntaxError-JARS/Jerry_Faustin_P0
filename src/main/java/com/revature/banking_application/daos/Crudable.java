package com.revature.banking_application.daos;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Crudable<U> {

    // Create
    U create(U newObject);

    //Read
    U[] findAll() throws IOException;
    //U findById(String id);

    //Update
    boolean update(U updatedObj);

    //Delete
    //boolean delete(String id);
}
