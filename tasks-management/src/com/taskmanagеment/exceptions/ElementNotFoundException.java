package com.taskmanagеment.exceptions;

public class ElementNotFoundException extends RuntimeException{

    public ElementNotFoundException(String message) {
        super(message);
    }
}
