package com.endtoendlab.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot application class.
 * Entry point for the Spring Boot Clean Architecture Template.
 */
@SpringBootApplication
public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        logger.info("=================================================================");
        logger.info("Application Started Successfully!");
        logger.info("=================================================================");
    }
}