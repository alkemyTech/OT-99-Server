package com.alkemy.ong.exception;

public class InvalidCredentialsException extends Exception{
    private final static String INVALID_CREDENTIALS = "The data entered are invalid.";
    public InvalidCredentialsException() {
        super(INVALID_CREDENTIALS);
    }
}
