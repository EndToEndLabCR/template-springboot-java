package com.company.template.infrastructure.configuration;

import com.company.template.application.ports.input.UserManagementUseCase;
import com.company.template.application.ports.output.UserRepositoryPort;
import com.company.template.application.usecases.UserManagementUseCaseImpl;
import com.company.template.domain.services.UserDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for application layer beans.
 * This wires together the application services and use cases.
 * 
 * Following dependency injection principles, this configuration
 * assembles the application components and their dependencies.
 */
@Configuration
public class ApplicationConfiguration {
    
    @Bean
    public UserDomainService userDomainService() {
        return new UserDomainService();
    }
    
    @Bean
    public UserManagementUseCase userManagementUseCase(
            UserRepositoryPort userRepositoryPort,
            UserDomainService userDomainService) {
        return new UserManagementUseCaseImpl(userRepositoryPort, userDomainService);
    }
}