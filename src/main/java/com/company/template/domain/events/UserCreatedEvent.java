package com.company.template.domain.events;

import com.company.template.domain.valueobjects.UserId;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Domain event representing the creation of a new user.
 * Domain events represent something important that happened in the domain.
 */
public class UserCreatedEvent {
    private final UserId userId;
    private final String userFullName;
    private final LocalDateTime occurredOn;

    public UserCreatedEvent(UserId userId, String userFullName) {
        this.userId = Objects.requireNonNull(userId, "User ID cannot be null");
        this.userFullName = Objects.requireNonNull(userFullName, "User full name cannot be null");
        this.occurredOn = LocalDateTime.now();
    }

    public UserId getUserId() {
        return userId;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public LocalDateTime getOccurredOn() {
        return occurredOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCreatedEvent that = (UserCreatedEvent) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(occurredOn, that.occurredOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, occurredOn);
    }

    @Override
    public String toString() {
        return "UserCreatedEvent{" +
                "userId=" + userId +
                ", userFullName='" + userFullName + '\'' +
                ", occurredOn=" + occurredOn +
                '}';
    }
}