package com.wordcounter.controller;

import com.wordcounter.exception.ValidationException;
import com.wordcounter.model.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {ValidationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleException(ValidationException exception) {
        return new ApiErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

}
