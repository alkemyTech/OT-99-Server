package com.alkemy.ong.exception;

public class NonExistingUser extends Exception {
    private final static String MESSAGE = "The user is not registered.";
    public NonExistingUser() {
        super(MESSAGE);
    }
}
