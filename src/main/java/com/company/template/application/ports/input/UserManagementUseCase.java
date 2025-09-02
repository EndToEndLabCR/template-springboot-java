package com.company.template.application.ports.input;

import com.company.template.application.dto.CreateUserRequestDto;
import com.company.template.application.dto.UserResponseDto;

import java.util.List;

/**
 * Input port (interface) for user management use cases.
 * This defines the contract for user-related operations
 * that can be triggered from the outside (web controllers, CLI, etc.).
 * 
 * This follows the hexagonal architecture pattern where
 * the application core defines the interfaces (ports)
 * that external adapters implement or use.
 */
public interface UserManagementUseCase {
    
    /**
     * Creates a new user.
     */
    UserResponseDto createUser(CreateUserRequestDto request);
    
    /**
     * Retrieves a user by their unique identifier.
     */
    UserResponseDto getUserById(String userId);
    
    /**
     * Retrieves all users.
     */
    List<UserResponseDto> getAllUsers();
    
    /**
     * Retrieves all active users.
     */
    List<UserResponseDto> getAllActiveUsers();
    
    /**
     * Updates a user's personal information.
     */
    UserResponseDto updateUser(String userId, CreateUserRequestDto request);
    
    /**
     * Activates a user.
     */
    UserResponseDto activateUser(String userId);
    
    /**
     * Deactivates a user.
     */
    UserResponseDto deactivateUser(String userId);
    
    /**
     * Deletes a user.
     */
    void deleteUser(String userId);
}