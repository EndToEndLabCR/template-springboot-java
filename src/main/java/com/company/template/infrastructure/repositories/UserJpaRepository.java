package com.company.template.infrastructure.repositories;

import com.company.template.infrastructure.adapters.persistence.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for UserJpaEntity.
 * This provides basic CRUD operations and custom queries.
 */
@Repository
public interface UserJpaRepository extends JpaRepository<UserJpaEntity, String> {
    
    Optional<UserJpaEntity> findByEmail(String email);
    
    boolean existsByEmail(String email);
    
    @Query("SELECT u FROM UserJpaEntity u WHERE u.active = true")
    List<UserJpaEntity> findAllActive();
}