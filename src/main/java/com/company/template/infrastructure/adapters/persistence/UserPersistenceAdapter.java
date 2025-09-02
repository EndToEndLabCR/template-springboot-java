package com.company.template.infrastructure.adapters.persistence;

import com.company.template.application.ports.output.UserRepositoryPort;
import com.company.template.domain.entities.User;
import com.company.template.domain.valueobjects.Email;
import com.company.template.domain.valueobjects.UserId;
import com.company.template.infrastructure.repositories.UserJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Persistence adapter that implements the UserRepositoryPort.
 * This adapter translates between domain entities and JPA entities,
 * and delegates to the Spring Data JPA repository.
 * 
 * This is the implementation of the output port defined in the application layer.
 */
@Component
public class UserPersistenceAdapter implements UserRepositoryPort {
    
    private final UserJpaRepository userJpaRepository;
    
    public UserPersistenceAdapter(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }
    
    @Override
    public User save(User user) {
        UserJpaEntity jpaEntity = mapToJpaEntity(user);
        UserJpaEntity savedEntity = userJpaRepository.save(jpaEntity);
        return mapToDomainEntity(savedEntity);
    }
    
    @Override
    public Optional<User> findById(UserId userId) {
        return userJpaRepository.findById(userId.getValue())
                .map(this::mapToDomainEntity);
    }
    
    @Override
    public Optional<User> findByEmail(Email email) {
        return userJpaRepository.findByEmail(email.getValue())
                .map(this::mapToDomainEntity);
    }
    
    @Override
    public List<User> findAll() {
        return userJpaRepository.findAll()
                .stream()
                .map(this::mapToDomainEntity)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<User> findAllActive() {
        return userJpaRepository.findAllActive()
                .stream()
                .map(this::mapToDomainEntity)
                .collect(Collectors.toList());
    }
    
    @Override
    public void deleteById(UserId userId) {
        userJpaRepository.deleteById(userId.getValue());
    }
    
    @Override
    public boolean existsByEmail(Email email) {
        return userJpaRepository.existsByEmail(email.getValue());
    }
    
    // Mapping methods
    private UserJpaEntity mapToJpaEntity(User user) {
        return new UserJpaEntity(
                user.getId().getValue(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail().getValue(),
                user.isActive(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
    
    private User mapToDomainEntity(UserJpaEntity jpaEntity) {
        UserId userId = UserId.of(jpaEntity.getId());
        Email email = Email.of(jpaEntity.getEmail());
        
        // Since we're recreating from persistence, we need to use a different constructor
        // or create the User and set the internal state. For simplicity, we'll create a new user
        // and manually set the state (in a real application, you might have a factory method)
        User user = new User(userId, jpaEntity.getFirstName(), jpaEntity.getLastName(), email);
        
        // Note: In a real application, you might need to restore the full state including
        // createdAt, updatedAt, and active status. This would require either:
        // 1. A more complex constructor in the domain entity
        // 2. A factory method for reconstruction from persistence
        // 3. Package-private setters for state restoration
        
        if (!jpaEntity.isActive()) {
            user.deactivate();
        }
        
        return user;
    }
}