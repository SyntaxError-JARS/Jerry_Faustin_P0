package com.revature.banking_application.services;

public interface Serviceable<U> {

    // Create

    U create(U newObject);

    // Read
    U[] readAll();
     U readByEmail(String username);

    // Update
    U deposit(U updatedObject);
    U withdraw(U updatedObject);

    // Delete
    //boolean delete(String id);

    boolean validateInput(U object);
}
