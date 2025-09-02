# Spring Boot Clean Architecture Template

A comprehensive Spring Boot template project implementing Clean Architecture principles with Hexagonal Architecture (Ports & Adapters), Screaming Architecture, and Vertical Slicing patterns.

## 🏗️ Architecture Overview

This template demonstrates a well-structured approach to building maintainable, testable, and scalable Spring Boot applications by implementing:

### Core Architectural Patterns

- **Hexagonal Architecture (Ports & Adapters)**: Clear separation between business logic and external concerns
- **Screaming Architecture**: Package structure that clearly communicates the domain and business capabilities  
- **Vertical Slicing**: Features organized by business capability rather than technical layers
- **Clean Architecture**: Dependency inversion with business logic at the center

### Layer Structure

```
src/main/java/com/company/template/
├── application/           # Application Layer (Use Cases & Ports)
│   ├── usecases/         # Application services and use case implementations
│   ├── ports/            # Interfaces for external dependencies
│   │   ├── input/        # Input ports (use case interfaces)
│   │   └── output/       # Output ports (repository interfaces)
│   └── dto/              # Data Transfer Objects
├── domain/               # Domain Layer (Business Logic)
│   ├── entities/         # Business entities with behavior
│   ├── services/         # Domain services for complex business logic
│   ├── valueobjects/     # Value objects with validation
│   └── events/           # Domain events
├── infrastructure/       # Infrastructure Layer (Adapters)
│   ├── adapters/         # External system adapters
│   │   ├── web/          # REST controllers
│   │   ├── persistence/  # Database adapters
│   │   └── external/     # External service clients
│   ├── configuration/    # Spring configuration classes
│   └── repositories/     # JPA repositories
└── shared/               # Shared Layer (Cross-cutting concerns)
    ├── utils/            # Utility classes
    ├── exceptions/       # Custom exceptions
    └── constants/        # Application constants
```

## 🚀 Quick Start

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

### Running the Application

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd template-springboot-java
   ```

2. **Build the project**
   ```bash
   mvn clean compile
   ```

3. **Run tests**
   ```bash
   mvn test
   ```

4. **Start the application**
   ```bash
   mvn spring-boot:run
   ```

5. **Access the application**
   - API Base URL: `http://localhost:8080/api/v1`
   - H2 Console: `http://localhost:8080/h2-console`
   - Health Check: `http://localhost:8080/actuator/health`

## 📋 API Endpoints

The template includes a complete User Management API demonstrating the architecture:

### User Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/v1/users` | Create a new user |
| GET | `/api/v1/users/{id}` | Get user by ID |
| GET | `/api/v1/users` | Get all users |
| GET | `/api/v1/users/active` | Get all active users |
| PUT | `/api/v1/users/{id}` | Update user |
| PATCH | `/api/v1/users/{id}/activate` | Activate user |
| PATCH | `/api/v1/users/{id}/deactivate` | Deactivate user |
| DELETE | `/api/v1/users/{id}` | Delete user |

### Example Requests

**Create User:**
```bash
curl -X POST http://localhost:8080/api/v1/users \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe", 
    "email": "john.doe@example.com"
  }'
```

**Get All Users:**
```bash
curl http://localhost:8080/api/v1/users
```

## 🏛️ Architecture Principles

### 1. Domain Layer (Core Business Logic)

- **Entities**: Rich domain models with business behavior
- **Value Objects**: Immutable objects with validation rules
- **Domain Services**: Complex business logic that doesn't fit in entities
- **Domain Events**: Represent important business events
- **No dependencies** on external frameworks or infrastructure

### 2. Application Layer (Use Cases)

- **Input Ports**: Interfaces defining what the application can do
- **Output Ports**: Interfaces defining what the application needs
- **Use Cases**: Orchestrate domain objects to fulfill business scenarios
- **DTOs**: Data transfer objects for application boundaries

### 3. Infrastructure Layer (External Concerns)

- **Web Adapters**: REST controllers implementing input ports
- **Persistence Adapters**: Database implementations of output ports
- **External Adapters**: Third-party service integrations
- **Configuration**: Spring configuration and dependency injection

### 4. Shared Layer (Cross-cutting Concerns)

- **Exceptions**: Domain-specific exception types
- **Utilities**: Common helper functions
- **Constants**: Application-wide constants

## 🔧 Key Features

### Clean Architecture Benefits

- **Independence**: Business logic is independent of frameworks, UI, and databases
- **Testability**: Easy to unit test business logic without external dependencies
- **Flexibility**: Easy to change UI, databases, or external services
- **Maintainability**: Clear separation of concerns and dependencies

### Hexagonal Architecture (Ports & Adapters)

- **Ports**: Interfaces that define the application's boundaries
- **Adapters**: Implementations that connect the application to external systems
- **Dependency Inversion**: Core business logic doesn't depend on external details

### Sample Implementation

The template includes a complete User Management feature demonstrating:

- Domain entities with business rules (`User`, `UserId`, `Email`)
- Domain services with complex business logic (`UserDomainService`)
- Application use cases (`UserManagementUseCase`)
- REST API adapters (`UserController`)
- Database persistence adapters (`UserPersistenceAdapter`)
- Complete validation and error handling

## 🧪 Testing Strategy

### Test Structure

- **Unit Tests**: Test domain logic in isolation
- **Integration Tests**: Test application use cases with real dependencies
- **Contract Tests**: Test adapters conform to port interfaces
- **End-to-End Tests**: Test complete user scenarios

### Running Tests

```bash
# Run all tests
mvn test

# Run with coverage
mvn clean test jacoco:report

# Run integration tests only
mvn test -Dtest="*IntegrationTest"
```

## 📦 Dependencies

### Core Dependencies

- **Spring Boot 3.2.0**: Modern Spring Boot with Java 17 support
- **Spring Web**: REST API development
- **Spring Data JPA**: Database persistence
- **Spring Validation**: Input validation
- **Spring Actuator**: Production monitoring

### Database

- **H2 Database**: In-memory database for development and testing
- **Easily configurable** for PostgreSQL, MySQL, or other databases

### Testing

- **JUnit 5**: Modern testing framework
- **Spring Boot Test**: Integration testing support
- **Testcontainers**: Integration testing with real databases

## 🔧 Configuration

### Application Configuration

The application can be configured through `application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:h2:mem:testdb
    username: sa
    password: password
  
  jpa:
    hibernate:
      ddl-auto: create-drop
    show-sql: true

server:
  port: 8080
```

### Environment-Specific Configuration

Create environment-specific configuration files:

- `application-dev.yml` - Development environment
- `application-prod.yml` - Production environment
- `application-test.yml` - Testing environment

## 🚀 Getting Started for Development

### 1. Understanding the Architecture

Start by exploring the example User Management feature:

1. **Domain Layer**: Look at `User` entity and `Email`/`UserId` value objects
2. **Application Layer**: Examine `UserManagementUseCase` and its implementation
3. **Infrastructure Layer**: Study `UserController` and `UserPersistenceAdapter`

### 2. Adding New Features

To add a new feature (e.g., Product Management):

1. **Create Domain Objects**:
   ```java
   // Domain entities
   src/main/java/com/company/template/domain/entities/Product.java
   
   // Value objects  
   src/main/java/com/company/template/domain/valueobjects/ProductId.java
   ```

2. **Define Application Interfaces**:
   ```java
   // Input port
   src/main/java/com/company/template/application/ports/input/ProductManagementUseCase.java
   
   // Output port
   src/main/java/com/company/template/application/ports/output/ProductRepositoryPort.java
   ```

3. **Implement Use Cases**:
   ```java
   src/main/java/com/company/template/application/usecases/ProductManagementUseCaseImpl.java
   ```

4. **Create Infrastructure Adapters**:
   ```java
   // REST controller
   src/main/java/com/company/template/infrastructure/adapters/web/ProductController.java
   
   // Persistence adapter
   src/main/java/com/company/template/infrastructure/adapters/persistence/ProductPersistenceAdapter.java
   ```

### 3. Best Practices

- **Keep domain logic pure**: No framework dependencies in domain layer
- **Use value objects**: Encapsulate validation and behavior
- **Test extensively**: Write tests for each layer
- **Follow naming conventions**: Clear, intention-revealing names
- **Keep interfaces focused**: Single responsibility principle

## 📚 Further Reading

- [Clean Architecture by Robert C. Martin](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
- [Hexagonal Architecture](https://alistair.cockburn.us/hexagonal-architecture/)
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes following the architectural patterns
4. Add tests for new functionality
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
