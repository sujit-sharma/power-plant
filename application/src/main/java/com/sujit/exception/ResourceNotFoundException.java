package com.sujit.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super("Failed to find the requested resource");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
