package com.company.template.shared.constants;

/**
 * Application-wide constants.
 * Contains configuration values and magic numbers used across the application.
 */
public final class ApplicationConstants {
    
    // API Constants
    public static final String API_VERSION = "v1";
    public static final String API_BASE_PATH = "/api/" + API_VERSION;
    
    // User Management Constants
    public static final String USERS_ENDPOINT = "/users";
    
    // Validation Constants
    public static final int MIN_NAME_LENGTH = 1;
    public static final int MAX_NAME_LENGTH = 50;
    public static final int MAX_EMAIL_LENGTH = 255;
    
    // Error Messages
    public static final String USER_NOT_FOUND_MESSAGE = "User not found";
    public static final String DUPLICATE_EMAIL_MESSAGE = "Email already exists";
    public static final String INVALID_EMAIL_MESSAGE = "Invalid email format";
    
    // Private constructor to prevent instantiation
    private ApplicationConstants() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
}