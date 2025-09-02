package com.company.template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot application class.
 * Entry point for the Spring Boot Clean Architecture Template.
 * 
 * This template demonstrates:
 * - Hexagonal Architecture (Ports & Adapters)
 * - Screaming Architecture with clear package structure
 * - Vertical slicing by business capability
 * - Clean separation of concerns across layers
 */
@SpringBootApplication
public class SpringBootCleanArchitectureTemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCleanArchitectureTemplateApplication.class, args);
    }
}