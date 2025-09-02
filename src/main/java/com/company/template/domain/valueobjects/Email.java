package com.company.template.domain.valueobjects;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Value object representing an email address.
 * Encapsulates validation rules and ensures invariants.
 */
public class Email {
    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$");
    
    private final String value;

    private Email(String value) {
        this.value = value;
    }

    public static Email of(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        
        String trimmedValue = value.trim().toLowerCase();
        if (!EMAIL_PATTERN.matcher(trimmedValue).matches()) {
            throw new IllegalArgumentException("Invalid email format: " + value);
        }
        
        return new Email(trimmedValue);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(value, email.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Email{" + "value='" + value + '\'' + '}';
    }
}