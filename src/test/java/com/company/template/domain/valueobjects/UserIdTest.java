package com.company.template.domain.valueobjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for UserId value object.
 */
class UserIdTest {

    @Test
    void shouldCreateValidUserId() {
        // Given
        String validId = "123e4567-e89b-12d3-a456-426614174000";
        
        // When
        UserId userId = UserId.of(validId);
        
        // Then
        assertEquals(validId, userId.getValue());
    }

    @Test
    void shouldThrowExceptionForNullValue() {
        // When & Then
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> UserId.of(null)
        );
        assertEquals("UserId cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForEmptyValue() {
        // When & Then
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> UserId.of("")
        );
        assertEquals("UserId cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForBlankValue() {
        // When & Then
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> UserId.of("   ")
        );
        assertEquals("UserId cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldBeEqualWhenSameValue() {
        // Given
        String id = "test-id";
        UserId userId1 = UserId.of(id);
        UserId userId2 = UserId.of(id);
        
        // When & Then
        assertEquals(userId1, userId2);
        assertEquals(userId1.hashCode(), userId2.hashCode());
    }

    @Test
    void shouldNotBeEqualWhenDifferentValue() {
        // Given
        UserId userId1 = UserId.of("id1");
        UserId userId2 = UserId.of("id2");
        
        // When & Then
        assertNotEquals(userId1, userId2);
    }
}