package com.example.petstore.exception;

public class UserDoNotHaveEnoughFoundsException extends PetStoreException {

    public UserDoNotHaveEnoughFoundsException(String message) {
        super(message);
    }
}
