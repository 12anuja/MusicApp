package com.stackroute.MuzixApplication.controller;


import com.stackroute.MuzixApplication.exception.TrackAlreadyExistsException;
import com.stackroute.MuzixApplication.exception.TrackNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//ControllerAdvice class to handle exceptions globally
@ControllerAdvice("com.stackroute.MuzixApplication.controller")
public class TrackControllerAdvice extends ResponseEntityExceptionHandler {

    //Exception Handler for TrackNotFoundException
    @ExceptionHandler(value = {TrackNotFoundException.class})
    protected ResponseEntity<Object> handleNotFoundConflict(Exception ex, WebRequest request) {
        String bodyOfResponse = "Track not found!";
        return handleExceptionInternal(ex, bodyOfResponse,new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    //Exception Handler for TrackAlreadyExistsException
    @ExceptionHandler(value = {TrackAlreadyExistsException.class})
    protected ResponseEntity<Object> handleAlreadyExistsConflict(Exception ex, WebRequest request) {
        String bodyOfResponse = "Track already exists!";
        return handleExceptionInternal(ex, bodyOfResponse,new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}

