package com.svalero.happDeporte.exception;

public class UserNotFoundException extends Exception {

    public UserNotFoundException() {
        super("Username not found");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
