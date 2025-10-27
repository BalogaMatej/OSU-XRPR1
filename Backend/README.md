# Car Management System

A REST API for managing cars built with Spring Boot 3.5.7 and Java 21.

## 📚 API Documentation

The project includes interactive API documentation powered by SpringDoc OpenAPI (Swagger).

### Access the Documentation

Once the application is running, you can access the API documentation at:

- **Swagger UI (Interactive):** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **Alternative Swagger UI:** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- **OpenAPI JSON Specification:** [http://localhost:8080/api-docs](http://localhost:8080/api-docs)
- **OpenAPI YAML Specification:** [http://localhost:8080/api-docs.yaml](http://localhost:8080/api-docs.yaml)

The Swagger UI provides:
- Complete API endpoint documentation
- Request/response schema definitions
- Interactive "Try it out" functionality to test endpoints
- Example values for all fields
- HTTP status code descriptions

## 🚀 Getting Started

### Prerequisites

- Java 21
- Maven 3.6+

### Running the Application

```bash
# Using Maven wrapper
./mvnw spring-boot:run

# Or build and run
./mvnw clean install
java -jar target/car-management-system-0.0.1-SNAPSHOT.jar
```

The application will start on **port 8080** by default.

## 📦 Project Dependencies

### Core Framework
- **Spring Boot 3.5.7** - Main framework
- **Java 21** - Programming language

### Spring Boot Starters
- **spring-boot-starter-web** - RESTful web services and MVC support
- **spring-boot-starter-data-jpa** - JPA and Hibernate for database operations
- **spring-boot-starter-data-rest** - REST repositories support

### Database
- **H2 Database** - In-memory database for development/testing
- **Flyway** - Database migration tool

### Architecture & Modularity
- **Spring Modulith Starter Core** - Modular monolith support
- **Spring Modulith Starter JPA** - JPA support for modulith

### Documentation
- **SpringDoc OpenAPI 2.6.0** - OpenAPI 3 & Swagger UI integration

### Development Tools
- **Spring Boot DevTools** - Hot reload and development utilities
- **Lombok** - Reduces boilerplate code (getters, setters, builders, etc.)
- **Spring Boot Configuration Processor** - Configuration metadata generation

### Testing
- **spring-boot-starter-test** - Testing framework (JUnit, Mockito, etc.)
- **spring-modulith-starter-test** - Testing support for modulith architecture

## 🏗️ Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── cz/osu/carmanagementsystem/
│   │       ├── CarManagementSystemApplication.java
│   │       ├── controller/
│   │       │   └── CarController.java
│   │       ├── dto/
│   │       │   ├── CarDTO.java
│   │       │   └── CarMapper.java
│   │       ├── model/
│   │       │   └── Car.java
│   │       ├── persistance/
│   │       │   └── CarRepository.java
│   │       └── service/
│   │           └── CarService.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/
        └── cz/osu/carmanagementsystem/
            └── CarManagementSystemApplicationTests.java
```

## 🔌 API Endpoints

All endpoints are available under `/api/cars`:

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/cars` | Create a new car |
| GET | `/api/cars` | Get all cars |
| GET | `/api/cars/{id}` | Get car by ID |
| GET | `/api/cars/vin/{vin}` | Get car by VIN |
| PUT | `/api/cars/{id}` | Update a car |
| DELETE | `/api/cars/{id}` | Delete a car |

For detailed request/response schemas and examples, visit the Swagger UI.

## 💾 Database Configuration

The application uses an H2 in-memory database with the following configuration:

- **URL:** `jdbc:h2:mem:testdb`
- **Username:** `sa`
- **Password:** `password`
- **Dialect:** H2

## 📝 License

Apache 2.0

