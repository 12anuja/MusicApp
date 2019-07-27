package com.stackroute.MuzixApplication.exception;

//Custom Exception Class
public class TrackAlreadyExistsException extends Exception {

    private String message;
    public TrackAlreadyExistsException() {
    }

    public TrackAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
}

