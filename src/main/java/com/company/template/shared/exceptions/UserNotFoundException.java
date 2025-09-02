package com.company.template.shared.exceptions;

/**
 * Exception thrown when a user is not found.
 * This is a domain-specific exception used across layers.
 */
public class UserNotFoundException extends RuntimeException {
    
    public UserNotFoundException(String message) {
        super(message);
    }
    
    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}