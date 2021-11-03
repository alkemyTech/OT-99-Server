package com.alkemy.ong.exception;

public class EmailAlreadyExistException extends Exception {
    
    private final static String EMAIL_ALREADY_EXIST_MESSAGE = "Email already exist.";

    public EmailAlreadyExistException() {
        super(EMAIL_ALREADY_EXIST_MESSAGE);
    }
}
