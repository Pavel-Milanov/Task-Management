package com.taskmanagement.exceptions;

public class InvalidUserInputException extends RuntimeException {

    public InvalidUserInputException(String message) {
        super(message);
    }
}
