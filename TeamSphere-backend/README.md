# TeamSphere

[![Java](https://img.shields.io/badge/Java-21-blue)](https://www.java.com/)  [![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.2-brightgreen)](https://spring.io/projects/spring-boot)  [![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue)](https://www.postgresql.org/)  [![JWT](https://img.shields.io/badge/JWT-Security-orange)](https://jwt.io/)

## Overview

TeamSphere is a RESTful web application designed to manage multiple aspects of a user system. Built using Java 21 and Spring Boot, it leverages PostgreSQL for persistence and offers robust authentication with JWT and role-based access control. The application provides comprehensive CRUD operations and dynamic search capabilities across various domains such as positions, projects, roles, timesheets, companies, departments, and employees.

## Features

- **Authentication & Security**  
  - JWT-based authentication with endpoints for registration and login.
  - Role-based access control (e.g., ROLE_ADMIN and ROLE_USER).

- **CRUD Operations**  
  - Full Create, Read, Update, and Delete operations for positions, projects, roles, timesheets, companies, departments, and employees.

- **Dynamic Search**  
  - Search endpoints that use JPA Criteria Builder for flexible, type-safe queries.
  
- **Tools**  
  - Lombok to reduce boilerplate code.
  - Integrated validation using Spring Boot Starter Validation.

- **Documentation**  
  - Swagger/OpenAPI integrated for interactive API documentation.

## Tech Stack

- **Backend:**  
  - Java 21, Spring Boot 3.4.2  
  - Spring Security, Spring Data JPA, Springdoc OpenAPI/Swagger  
  - JWT (JSON Web Tokens)

- **Database:**  
  - PostgreSQL

- **Build Tool:**  
  - Maven

- **Libraries & Tools:**  
  - Lombok, JPA Criteria Builder

## Installation & Setup

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-repo/user-management-api.git
   cd user-management-api
   ```
2. **Configure the database:**  
   Update the `application.properties` with your PostgreSQL settings.

3. **Build and run the application:**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **API Documentation:**  
   Access the Swagger UI at:  
   ```
   http://localhost:8080/swagger-ui.html
   ```

---

## API Endpoints

Each controller has a dedicated table listing its endpoints and a short description.

### Authentication Controller

| HTTP Method | Endpoint                        | Description                                                   |
|-------------|---------------------------------|---------------------------------------------------------------|
| POST        | `/api/v1/auth/register`         | Registers a new user and returns a JWT token.                 |
| POST        | `/api/v1/auth/authenticate`     | Authenticates a user and returns a JWT token.                 |

### Position Controller

| HTTP Method | Endpoint                          | Description                                                                                 |
|-------------|-----------------------------------|---------------------------------------------------------------------------------------------|
| POST        | `/api/v1/position/search`         | Searches positions using dynamic criteria (e.g., by name or years of experience).            |
| POST        | `/api/v1/position`                | Creates a new position. (Admin only)                                                        |
| GET         | `/api/v1/position/{id}`           | Retrieves details of a specific position.                                                   |
| GET         | `/api/v1/position`                | Returns a paginated list of all positions.                                                  |
| PUT         | `/api/v1/position/{id}`           | Updates a specific position. (Admin only)                                                   |
| DELETE      | `/api/v1/position/{id}`           | Deletes a specific position. (Admin only)                                                   |

### Project Controller

| HTTP Method | Endpoint                          | Description                                                                                   |
|-------------|-----------------------------------|-----------------------------------------------------------------------------------------------|
| POST        | `/api/v1/project/search`          | Searches projects based on criteria (e.g., name, description, dates, or status).               |
| POST        | `/api/v1/project`                 | Creates a new project. (Admin only)                                                           |
| GET         | `/api/v1/project/{id}`            | Retrieves details of a specific project.                                                      |
| GET         | `/api/v1/project`                 | Provides a paginated list of projects.                                                        |
| PUT         | `/api/v1/project/{id}`            | Updates a project’s details. (Admin only)                                                     |
| DELETE      | `/api/v1/project/{id}`            | Deletes a project. (Admin only)                                                               |

### Role Controller

| HTTP Method | Endpoint                        | Description                                                                                   |
|-------------|---------------------------------|-----------------------------------------------------------------------------------------------|
| POST        | `/api/v1/role/search`            | Searches roles based on role name or description.                                              |
| POST        | `/api/v1/role`                   | Creates a new role. (Admin only)                                                               |
| GET         | `/api/v1/role/{id}`              | Retrieves details of a specific role.                                                         |
| GET         | `/api/v1/role`                   | Returns a paginated list of roles.                                                            |
| PUT         | `/api/v1/role/{id}`              | Updates role details. (Admin only)                                                            |
| DELETE      | `/api/v1/role/{id}`              | Deletes a role. (Admin only)                                                                  |

### Timesheet Controller

| HTTP Method | Endpoint                           | Description                                                                                   |
|-------------|------------------------------------|-----------------------------------------------------------------------------------------------|
| POST        | `/api/v1/timesheet/search`          | Searches timesheets by task description or time spent using dynamic queries.                   |
| POST        | `/api/v1/timesheet`                 | Creates a new timesheet record. (Admin only)                                                   |
| GET         | `/api/v1/timesheet/{id}`            | Retrieves a specific timesheet.                                                               |
| GET         | `/api/v1/timesheet`                 | Returns a paginated list of timesheets.                                                       |
| PUT         | `/api/v1/timesheet/{id}`            | Updates a timesheet record. (Admin only)                                                      |
| DELETE      | `/api/v1/timesheet/{id}`            | Deletes a timesheet. (Admin only)                                                             |

### Company Controller

| HTTP Method | Endpoint                           | Description                                                                                   |
|-------------|------------------------------------|-----------------------------------------------------------------------------------------------|
| POST        | `/api/v1/company/search`            | Searches companies by name, industry, address, or email using dynamic queries.                 |
| POST        | `/api/v1/company`                   | Creates a new company. (Admin only)                                                           |
| GET         | `/api/v1/company/{id}`              | Retrieves company details.                                                                    |
| GET         | `/api/v1/company`                   | Provides a paginated list of companies.                                                       |
| PUT         | `/api/v1/company/{id}`              | Updates company information. (Admin only)                                                     |
| DELETE      | `/api/v1/company/{id}`              | Deletes a company. (Admin only)                                                               |

### Department Controller

| HTTP Method | Endpoint                            | Description                                                                                   |
|-------------|-------------------------------------|-----------------------------------------------------------------------------------------------|
| POST        | `/api/v1/department/search`          | Searches departments based on group name or description.                                      |
| POST        | `/api/v1/department`                 | Creates a new department. (Admin only)                                                        |
| GET         | `/api/v1/department/{id}`            | Retrieves details of a specific department.                                                   |
| GET         | `/api/v1/department`                 | Returns a paginated list of departments.                                                      |
| PUT         | `/api/v1/department/{id}`            | Updates department details. (Admin only)                                                      |
| DELETE      | `/api/v1/department/{id}`            | Deletes a department. (Admin only)                                                            |

### Employee Controller

| HTTP Method | Endpoint                           | Description                                                                                   |
|-------------|------------------------------------|-----------------------------------------------------------------------------------------------|
| POST        | `/api/v1/employee/search`           | Searches for employees based on dynamic criteria.                                            |
| POST        | `/api/v1/employee`                  | Creates a new employee record. (Admin only)                                                   |
| GET         | `/api/v1/employee/{id}`             | Retrieves details of a specific employee.                                                     |
| GET         | `/api/v1/employee`                  | Returns a paginated list of employees.                                                        |
| PUT         | `/api/v1/employee/{id}`             | Updates employee information. (Admin only)                                                    |
| DELETE      | `/api/v1/employee/{id}`             | Deletes an employee record. (Admin only)                                                      |

---

## Technologies in Detail

- **Spring Boot & Spring Security:**  
  These frameworks form the backbone of the application. Spring Security manages authentication and role-based authorization, ensuring that only authenticated users with proper roles can access sensitive endpoints.

- **JWT:**  
  JSON Web Tokens are used for stateless authentication. After login, users receive a token that must be included in subsequent requests.

- **JPA & Hibernate:**  
  The API uses Spring Data JPA along with Hibernate for ORM. All CRUD operations are handled through JPA repositories.

- **Criteria Builder:**  
  For search endpoints, the API uses the JPA Criteria Builder to create type-safe and dynamic queries at runtime. This allows flexible filtering of data without hardcoding SQL queries.

- **Lombok:**  
  Lombok to reduce boilerplate code.

- **Maven:**  
  Dependency management and build processes are handled with Maven.
