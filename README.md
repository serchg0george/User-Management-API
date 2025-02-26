# User Management API

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.2-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue)
![JWT](https://img.shields.io/badge/JWT-Security-orange)

## 📌 Overview
User Management API is a RESTful service for managing users, emails, and addresses. It provides authentication, CRUD operations for users and associated data, and search capabilities.

## 🚀 Features
- User authentication (registration, login) with JWT
- CRUD operations for users, emails, and addresses
- Role-based access control (admin, user)
- Search functionality for users, emails, and addresses
- Exception handling with meaningful responses
- Database persistence using PostgreSQL

## 🛠️ Tech Stack
- **Backend**: Java 21, Spring Boot 3.4.2, Spring Security
- **Database**: PostgreSQL
- **Security**: JWT Authentication
- **ORM**: Hibernate, JPA
- **Dependency Management**: Maven

## 📂 Database Models & Schema

### User Table
```plaintext
+----+-----------+----------+----------------------+----------+
| ID | firstName | lastName | email                | role     |
+----+-----------+----------+----------------------+----------+
| 1  | John      | Doe      | john@example.com     | ROLE_USER|
| 2  | Jane      | Smith    | jane@example.com     | ROLE_ADMIN|
+----+-----------+----------+----------------------+----------+
```

### People Table
```plaintext
+----+-----------+----------+------+
| ID | fullName  | pin      | addr |
+----+-----------+----------+------+
| 1  | John Doe  | 123456789| 1    |
| 2  | Alice Bob | 987654321| 2    |
+----+-----------+----------+------+
```

### Mail Table
```plaintext
+----+-----------+--------------------+--------+
| ID | emailType | email              | people |
+----+-----------+--------------------+--------+
| 1  | WORK      | john@company.com   | 1      |
| 2  | PERSONAL  | alice@mail.com     | 2      |
+----+-----------+--------------------+--------+
```

### Address Table
```plaintext
+----+----------+----------------------+
| ID | addrType | addrInfo             |
+----+----------+----------------------+
| 1  | HOME     | 123 Street, City     |
| 2  | WORK     | 456 Business Rd      |
+----+----------+----------------------+
```

## 🔧 Installation & Setup
1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo/user-management-api.git
   cd user-management-api
   ```
2. Configure **application.properties** with your PostgreSQL database.
3. Build and run the application:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

## 📌 API Endpoints

### Authentication
- **Register User**
  ```http
  POST /api/v1/auth/register
  ```
  **Request Body:**
  ```json
  {
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "password": "password123"
  }
  ```
  **Response:**
  ```json
  {
    "token": "jwt-token-here"
  }
  ```

- **Authenticate User**
  ```http
  POST /api/v1/auth/authenticate
  ```
  **Request Body:**
  ```json
  {
    "email": "john.doe@example.com",
    "password": "password123"
  }
  ```
  **Response:**
  ```json
  {
    "token": "jwt-token-here"
  }
  ```

### Users Controller
- **Get All Users (Admin Only)**
  ```http
  GET /api/v1/users
  ```
  **Headers:**
  ```json
  {
    "Authorization": "Bearer your-jwt-token"
  }
  ```
  **Response:**
  ```json
  [
    {
      "id": 1,
      "firstName": "John",
      "lastName": "Doe",
      "email": "john@example.com",
      "role": "ROLE_USER"
    }
  ]
  ```

- **Get User by ID**
  ```http
  GET /api/v1/users/{id}
  ```
  **Response:**
  ```json
  {
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "email": "john@example.com",
    "role": "ROLE_USER"
  }
  ```

- **Update User**
  ```http
  PUT /api/v1/users/{id}
  ```
  **Request Body:**
  ```json
  {
    "firstName": "Updated Name",
    "lastName": "Updated LastName"
  }
  ```
  **Response:**
  ```json
  {
    "id": 1,
    "firstName": "Updated Name",
    "lastName": "Updated LastName",
    "email": "john@example.com",
    "role": "ROLE_USER"
  }
  ```

- **Delete User**
  ```http
  DELETE /api/v1/users/{id}
  ```
  **Response:**
  ```json
  {
    "message": "User deleted successfully."
  }
  ```

### Emails Controller
- **Create Email**
  ```http
  POST /api/v1/mails
  ```
  **Request Body:**
  ```json
  {
    "emailType": "WORK",
    "email": "user@example.com",
    "peopleId": 1
  }
  ```
  **Response:**
  ```json
  {
    "id": 1,
    "emailType": "WORK",
    "email": "user@example.com",
    "people": {
      "id": 1,
      "fullName": "John Doe",
      "pin": "123456789"
    }
  }
  ```

- **Get All Emails**
  ```http
  GET /api/v1/mails
  ```
  **Response:**
  ```json
  [
    {
      "id": 1,
      "emailType": "WORK",
      "email": "user@example.com",
      "people": {
        "id": 1,
        "fullName": "John Doe"
      }
    }
  ]
  ```

- **Get Email by ID**
  ```http
  GET /api/v1/mails/{id}
  ```
  **Response:**
  ```json
  {
    "id": 1,
    "emailType": "WORK",
    "email": "user@example.com",
    "people": {
      "id": 1,
      "fullName": "John Doe"
    }
  }
  ```

- **Update Email**
  ```http
  PUT /api/v1/mails/{id}
  ```
  **Request Body:**
  ```json
  {
    "emailType": "PERSONAL",
    "email": "new.email@example.com"
  }
  ```
  **Response:**
  ```json
  {
    "id": 1,
    "emailType": "PERSONAL",
    "email": "new.email@example.com",
    "people": {
      "id": 1,
      "fullName": "John Doe"
    }
  }
  ```

- **Delete Email**
  ```http
  DELETE /api/v1/mails/{id}
  ```
  **Response:**
  ```json
  {
    "message": "Email deleted successfully."
  }
  ```

- **Search Emails**
  ```http
  POST /api/v1/mails/search
  ```
  **Request Body:**
  ```json
  {
    "query": "example.com"
  }
  ```
  **Response:**
  ```json
  [
    {
      "id": 1,
      "emailType": "WORK",
      "email": "user@example.com",
      "people": {
        "id": 1,
        "fullName": "John Doe"
      }
    }
  ]
  ```

### Addresses Controller
- **Create Address**
  ```http
  POST /api/v1/addresses
  ```
  **Request Body:**
  ```json
  {
    "addrType": "HOME",
    "addrInfo": "123 Street, City"
  }
  ```
  **Response:**
  ```json
  {
    "id": 1,
    "addrType": "HOME",
    "addrInfo": "123 Street, City"
  }
  ```

- **Get All Addresses**
  ```http
  GET /api/v1/addresses
  ```
  **Response:**
  ```json
  [
    {
      "id": 1,
      "addrType": "HOME",
      "addrInfo": "123 Street, City"
    }
  ]
  ```

- **Get Address by ID**
  ```http
  GET /api/v1/addresses/{id}
  ```
  **Response:**
  ```json
  {
    "id": 1,
    "addrType": "HOME",
    "addrInfo": "123 Street, City"
  }
  ```

- **Update Address**
  ```http
  PUT /api/v1/addresses/{id}
  ```
  **Request Body:**
  ```json
  {
    "addrType": "WORK",
    "addrInfo": "456 Business Rd"
  }
  ```
  **Response:**
  ```json
  {
    "id": 1,
    "addrType": "WORK",
    "addrInfo": "456 Business Rd"
  }
  ```

- **Delete Address**
  ```http
  DELETE /api/v1/addresses/{id}
  ```
  **Response:**
  ```json
  {
    "message": "Address deleted successfully."
  }
  ```

- **Search Addresses**
  ```http
  POST /api/v1/addresses/search
  ```
  **Request Body:**
  ```json
  {
    "query": "Street"
  }
  ```
  **Response:**
  ```json
  [
    {
      "id": 1,
      "addrType": "HOME",
      "addrInfo": "123 Street, City"
    }
  ]
  ```

## 🔒 Security
All sensitive endpoints require authentication via JWT. Add the `Authorization: Bearer <token>` header to your requests.

## 🎯 License
MIT License. Free to use and modify.
