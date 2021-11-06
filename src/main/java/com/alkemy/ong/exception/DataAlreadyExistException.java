package com.alkemy.ong.exception;


public class DataAlreadyExistException extends Exception {
    
    private final static String DATA_ALREADY_EXIST_MESSAGE = "Data already exist.";

    public DataAlreadyExistException() {
        super(DATA_ALREADY_EXIST_MESSAGE);
    }
}

