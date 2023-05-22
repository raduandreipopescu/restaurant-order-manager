package com.personal.restaurantordermanager.controller;

import com.personal.restaurantordermanager.exception.ResourceAlreadyExistsException;
import com.personal.restaurantordermanager.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
        return new ErrorResponse(resourceNotFoundException.getMessage());
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.ALREADY_REPORTED)
    public ErrorResponse handleResourceAlreadyExistsException(ResourceAlreadyExistsException resourceAlreadyExistsException) {
        return new ErrorResponse(resourceAlreadyExistsException.getMessage());
    }

    record ErrorResponse(String message) {
    }
}
