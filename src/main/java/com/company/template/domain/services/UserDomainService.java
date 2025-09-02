package com.company.template.domain.services;

import com.company.template.domain.entities.User;
import com.company.template.domain.valueobjects.Email;

/**
 * Domain service for user-related business logic that doesn't naturally belong to any entity.
 * Domain services contain business logic that involves multiple entities or doesn't naturally fit in an entity.
 */
public class UserDomainService {

    /**
     * Checks if a user can be deactivated based on business rules.
     * This is an example of business logic that could involve complex rules
     * and multiple entities in a real application.
     */
    public boolean canDeactivateUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        
        // Business rule: Cannot deactivate already inactive users
        if (!user.isActive()) {
            return false;
        }
        
        // Add more complex business rules here, e.g.:
        // - Check if user has pending transactions
        // - Check if user is an admin with responsibilities
        // - Check business-specific constraints
        
        return true;
    }

    /**
     * Validates if an email change is allowed based on business rules.
     */
    public boolean canChangeEmail(User user, Email newEmail) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (newEmail == null) {
            throw new IllegalArgumentException("New email cannot be null");
        }
        
        // Business rule: Cannot change email if user is inactive
        if (!user.isActive()) {
            return false;
        }
        
        // Business rule: Cannot change to the same email
        if (user.getEmail().equals(newEmail)) {
            return false;
        }
        
        return true;
    }

    /**
     * Generates a user display name based on business rules.
     */
    public String generateDisplayName(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        
        if (!user.isActive()) {
            return "[Inactive] " + user.getFullName();
        }
        
        return user.getFullName();
    }
}