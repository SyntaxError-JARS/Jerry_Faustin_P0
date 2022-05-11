package com.revature.banking_application.services;

public interface Serviceable<U> {

    // Create

    U create(U newObject);

    // Read
    U[] readAll();
    // U readById(String id);

    // Update
    U update(U updatedObject);

    // Delete
    //boolean delete(String id);

    boolean validateInput(U object);
}
