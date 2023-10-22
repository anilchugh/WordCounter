package com.wordcounter.exception;

public class ValidationException extends Exception {

    private static final long serialVersionUID = 7441683465635919264L;
    private String message;

    public ValidationException(String word) {
        message = "Words must be alphanumeric: " + word;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
