package com.company.template.infrastructure.adapters.web;

import com.company.template.application.dto.CreateUserRequestDto;
import com.company.template.application.dto.UserResponseDto;
import com.company.template.application.ports.input.UserManagementUseCase;
import com.company.template.shared.constants.ApplicationConstants;
import com.company.template.shared.exceptions.DuplicateEmailException;
import com.company.template.shared.exceptions.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for user management operations.
 * This is an infrastructure adapter that translates HTTP requests
 * to application use case calls.
 * 
 * Following hexagonal architecture, this adapter depends on
 * the application layer through the input port interface.
 */
@RestController
@RequestMapping(ApplicationConstants.API_BASE_PATH + ApplicationConstants.USERS_ENDPOINT)
public class UserController {
    
    private final UserManagementUseCase userManagementUseCase;
    
    public UserController(UserManagementUseCase userManagementUseCase) {
        this.userManagementUseCase = userManagementUseCase;
    }
    
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody CreateUserRequestDto request) {
        try {
            UserResponseDto response = userManagementUseCase.createUser(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (DuplicateEmailException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
    
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable String userId) {
        try {
            UserResponseDto response = userManagementUseCase.getUserById(userId);
            return ResponseEntity.ok(response);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> users = userManagementUseCase.getAllUsers();
        return ResponseEntity.ok(users);
    }
    
    @GetMapping("/active")
    public ResponseEntity<List<UserResponseDto>> getAllActiveUsers() {
        List<UserResponseDto> users = userManagementUseCase.getAllActiveUsers();
        return ResponseEntity.ok(users);
    }
    
    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable String userId,
            @Valid @RequestBody CreateUserRequestDto request) {
        try {
            UserResponseDto response = userManagementUseCase.updateUser(userId, request);
            return ResponseEntity.ok(response);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (DuplicateEmailException | IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PatchMapping("/{userId}/activate")
    public ResponseEntity<UserResponseDto> activateUser(@PathVariable String userId) {
        try {
            UserResponseDto response = userManagementUseCase.activateUser(userId);
            return ResponseEntity.ok(response);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PatchMapping("/{userId}/deactivate")
    public ResponseEntity<UserResponseDto> deactivateUser(@PathVariable String userId) {
        try {
            UserResponseDto response = userManagementUseCase.deactivateUser(userId);
            return ResponseEntity.ok(response);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        try {
            userManagementUseCase.deleteUser(userId);
            return ResponseEntity.noContent().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}