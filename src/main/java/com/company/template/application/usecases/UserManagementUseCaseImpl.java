package com.company.template.application.usecases;

import com.company.template.application.dto.CreateUserRequestDto;
import com.company.template.application.dto.UserResponseDto;
import com.company.template.application.ports.input.UserManagementUseCase;
import com.company.template.application.ports.output.UserRepositoryPort;
import com.company.template.domain.entities.User;
import com.company.template.domain.services.UserDomainService;
import com.company.template.domain.valueobjects.Email;
import com.company.template.domain.valueobjects.UserId;
import com.company.template.shared.exceptions.UserNotFoundException;
import com.company.template.shared.exceptions.DuplicateEmailException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Use case implementation for user management operations.
 * This is the application service that orchestrates domain logic
 * and coordinates with external adapters through ports.
 * 
 * This class contains no business logic itself, but orchestrates
 * domain objects and services to fulfill use cases.
 */
public class UserManagementUseCaseImpl implements UserManagementUseCase {
    
    private final UserRepositoryPort userRepository;
    private final UserDomainService userDomainService;
    
    public UserManagementUseCaseImpl(UserRepositoryPort userRepository, 
                                   UserDomainService userDomainService) {
        this.userRepository = userRepository;
        this.userDomainService = userDomainService;
    }
    
    @Override
    public UserResponseDto createUser(CreateUserRequestDto request) {
        // Validate business rules
        Email email = Email.of(request.getEmail());
        if (userRepository.existsByEmail(email)) {
            throw new DuplicateEmailException("Email already exists: " + request.getEmail());
        }
        
        // Create domain entity
        UserId userId = UserId.of(UUID.randomUUID().toString());
        User user = new User(userId, request.getFirstName(), request.getLastName(), email);
        
        // Save through repository port
        User savedUser = userRepository.save(user);
        
        // Return DTO
        return mapToDto(savedUser);
    }
    
    @Override
    public UserResponseDto getUserById(String userId) {
        User user = findUserByIdOrThrow(userId);
        return mapToDto(user);
    }
    
    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<UserResponseDto> getAllActiveUsers() {
        return userRepository.findAllActive()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public UserResponseDto updateUser(String userId, CreateUserRequestDto request) {
        User user = findUserByIdOrThrow(userId);
        Email newEmail = Email.of(request.getEmail());
        
        // Check business rules using domain service
        if (!userDomainService.canChangeEmail(user, newEmail)) {
            throw new IllegalStateException("Cannot change email for this user");
        }
        
        // Check for duplicate email (excluding current user)
        if (!user.getEmail().equals(newEmail) && userRepository.existsByEmail(newEmail)) {
            throw new DuplicateEmailException("Email already exists: " + request.getEmail());
        }
        
        // Update using domain method
        user.updatePersonalInfo(request.getFirstName(), request.getLastName(), newEmail);
        
        User updatedUser = userRepository.save(user);
        return mapToDto(updatedUser);
    }
    
    @Override
    public UserResponseDto activateUser(String userId) {
        User user = findUserByIdOrThrow(userId);
        user.activate();
        User activatedUser = userRepository.save(user);
        return mapToDto(activatedUser);
    }
    
    @Override
    public UserResponseDto deactivateUser(String userId) {
        User user = findUserByIdOrThrow(userId);
        
        // Check business rules using domain service
        if (!userDomainService.canDeactivateUser(user)) {
            throw new IllegalStateException("Cannot deactivate this user");
        }
        
        user.deactivate();
        User deactivatedUser = userRepository.save(user);
        return mapToDto(deactivatedUser);
    }
    
    @Override
    public void deleteUser(String userId) {
        UserId userIdValue = UserId.of(userId);
        if (!userRepository.findById(userIdValue).isPresent()) {
            throw new UserNotFoundException("User not found with ID: " + userId);
        }
        userRepository.deleteById(userIdValue);
    }
    
    // Helper methods
    private User findUserByIdOrThrow(String userId) {
        UserId userIdValue = UserId.of(userId);
        return userRepository.findById(userIdValue)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
    }
    
    private UserResponseDto mapToDto(User user) {
        return new UserResponseDto(
                user.getId().getValue(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail().getValue(),
                user.isActive(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}