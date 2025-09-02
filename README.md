# Spring Boot Clean Architecture Template

A comprehensive Spring Boot template project implementing Clean Architecture principles with Hexagonal Architecture (Ports & Adapters), Screaming Architecture, and Vertical Slicing patterns.


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

## 🏛️ Architecture Principles

### 1. Domain Layer (Core Business Logic)

- **Entities**: Rich domain models with business behavior
- **Value Objects**: Immutable objects with validation rules
- **Domain Services**: Complex business logic that doesn't fit in entities
- **Domain Events**: Represent important business events
- **No dependencies** on external frameworks or infrastructure

### 2. Application Layer (Use Cases)


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



## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes following the architectural patterns
4. Add tests for new functionality
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
