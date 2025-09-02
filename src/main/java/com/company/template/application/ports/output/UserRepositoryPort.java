package com.company.template.application.ports.output;

import com.company.template.domain.entities.User;
import com.company.template.domain.valueobjects.Email;
import com.company.template.domain.valueobjects.UserId;

import java.util.List;
import java.util.Optional;

/**
 * Output port (interface) for user persistence operations.
 * This is part of the application layer and defines the contract
 * that infrastructure adapters must implement.
 * 
 * Following the Dependency Inversion Principle, the application
 * layer defines the interface and the infrastructure layer implements it.
 */
public interface UserRepositoryPort {
    
    /**
     * Saves a user to the persistence layer.
     */
    User save(User user);
    
    /**
     * Finds a user by their unique identifier.
     */
    Optional<User> findById(UserId userId);
    
    /**
     * Finds a user by their email address.
     */
    Optional<User> findByEmail(Email email);
    
    /**
     * Finds all users.
     */
    List<User> findAll();
    
    /**
     * Finds all active users.
     */
    List<User> findAllActive();
    
    /**
     * Deletes a user by their unique identifier.
     */
    void deleteById(UserId userId);
    
    /**
     * Checks if a user with the given email exists.
     */
    boolean existsByEmail(Email email);
}