# LearnRestAPI

A Spring Boot REST API application for managing student records with full CRUD operations.

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technologies](#technologies)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Project Structure](#project-structure)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)

## 🔍 Overview

LearnRestAPI is a RESTful web service built with Spring Boot that provides complete Create, Read, Update, and Delete (CRUD) operations for student management. The application uses PostgreSQL as its database and follows industry-standard design patterns including DTO pattern and service layer architecture.

## ✨ Features

- **Complete CRUD Operations**: Create, Read, Update, and Delete student records
- **RESTful API Design**: Following REST principles with proper HTTP methods
- **DTO Pattern**: Separation of entity and data transfer objects
- **Partial Updates**: Support for PATCH requests to update specific fields
- **PostgreSQL Integration**: Persistent data storage with JPA/Hibernate
- **Model Mapping**: Automatic entity-DTO conversion using ModelMapper
- **Validation**: Request validation using Spring Boot Validation
- **Clean Architecture**: Layered architecture with controller, service, and repository layers

## 🛠 Technologies

- **Java 21**
- **Spring Boot 4.0.2**
- **Spring Data JPA** - Database operations
- **PostgreSQL** - Relational database
- **Lombok** - Boilerplate code reduction
- **ModelMapper 3.2.4** - Object mapping
- **Maven** - Build and dependency management

## 📦 Prerequisites

Before running this application, ensure you have:

- Java 21 or higher installed
- PostgreSQL installed and running
- Maven 3.6+ installed
- Your favorite IDE (IntelliJ IDEA, Eclipse, VS Code)

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone <repository-url>
cd LearnRestAPI
```

### 2. Configure Database

Update the database configuration in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3. Build the Project

```bash
./mvnw clean install
```

### 4. Run the Application

```bash
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080/api`

## 📡 API Endpoints

Base URL: `http://localhost:8080/api/student`

| Method | Endpoint | Description | Request Body |
|--------|----------|-------------|--------------|
| GET | `/student` | Get all students | - |
| GET | `/student/{id}` | Get student by ID | - |
| POST | `/student` | Create a new student | `AddStudentRequestDTO` |
| PUT | `/student/{id}` | Update student completely | `AddStudentRequestDTO` |
| PATCH | `/student/{id}` | Partially update student | `Map<String, Object>` |
| DELETE | `/student/{id}` | Delete student by ID | - |

### Example Request Bodies

**Create/Update Student (POST/PUT)**
```json
{
  "name": "John Doe",
  "email": "john.doe@example.com"
}
```

**Partial Update (PATCH)**
```json
{
  "name": "Jane Doe"
}
```

### Example Response

```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john.doe@example.com"
}
```

## 📁 Project Structure

```
LearnRestAPI/
├── src/
│   ├── main/
│   │   ├── java/com/suryanshu/LearnRestAPI/
│   │   │   ├── config/
│   │   │   │   └── MapperConfig.java          # ModelMapper configuration
│   │   │   ├── controller/
│   │   │   │   └── StudentController.java     # REST endpoints
│   │   │   ├── DTO/
│   │   │   │   ├── AddStudentRequestDTO.java  # Request DTO
│   │   │   │   └── StudentDTO.java            # Response DTO
│   │   │   ├── entity/
│   │   │   │   └── Student.java               # JPA entity
│   │   │   ├── repositery/
│   │   │   │   └── StudentRepositery.java     # Data access layer
│   │   │   ├── service/
│   │   │   │   ├── StudentService.java        # Service interface
│   │   │   │   └── implimentation/
│   │   │   │       └── StudentServiceImpl.java # Service implementation
│   │   │   └── LearnRestApiApplication.java   # Main application class
│   │   └── resources/
│   │       └── application.properties          # Application configuration
│   └── test/
│       └── java/com/suryanshu/LearnRestAPI/
│           └── LearnRestApiApplicationTests.java
├── pom.xml                                     # Maven configuration
└── README.md
```

## ⚙️ Configuration

### Application Properties

Key configurations in `application.properties`:

```properties
# Application name
spring.application.name=LearnRestAPI

# Database configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=your_username
spring.datasource.password=your_password

# JPA/Hibernate configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Server configuration
server.servlet.context-path=/api
```

## 🏃 Running the Application

### Using Maven Wrapper

```bash
# On macOS/Linux
./mvnw spring-boot:run

# On Windows
mvnw.cmd spring-boot:run
```

### Using JAR file

```bash
./mvnw clean package
java -jar target/LearnRestAPI-0.0.1-SNAPSHOT.jar
```

## 🧪 Testing

Run tests using:

```bash
./mvnw test
```

## 📝 Notes

- The application uses Hibernate's `ddl-auto=update` which automatically updates the database schema. For production, consider using migration tools like Flyway or Liquibase.
- Make sure PostgreSQL is running before starting the application.
- The context path is set to `/api`, so all endpoints are prefixed with `/api`.

## 🤝 Contributing

Contributions, issues, and feature requests are welcome!

## 📄 License

This project is for learning purposes.

## 👤 Author

**Suryanshu Urai**

---

Happy Coding! 🚀
