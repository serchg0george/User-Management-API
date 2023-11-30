# User Management API

## Table of Contents
- [Introduction](#introduction)
- [Database Schema](#database-schema)
    - [T_PEOPLE Table](#t_people-table)
    - [T_MAILS Table](#t_mails-table)
    - [T_ADDRESSES Table](#t_addresses-table)
- [Functionality](#functionality)
    - [Create Record](#create-record)
    - [Edit Record](#edit-record)
    - [Delete Record](#delete-record)
- [Tech-stack](#tech-stack)

## Introduction

User Management API is a Java web application built using Spring Boot and PostgreSQL.
This API manages user data, provides creating, updating, and deleting user records functionality for each table.

## Database Schema


### T_PEOPLE Table

| Column     | Type     | Length | Constraints              |
|------------|----------|--------|--------------------------|
| ID         | Number   | 10     | Primary Key              |
| FULL_NAME  | String   | 90     | Not Null                 |
| PIN        | String   | 10     | Nullable, 10 digits      |
| ADDRESS    | Number   | 10     | Foreign Key (T_ADDRESSES)|
| MAILS      | Number   | 10     | Foreign Key (T_MAILS)    |

### T_MAILS Table

| Column      | Type     | Length | Constraints                |
|-------------|----------|--------|----------------------------|
| ID          | Number   | 10     | Primary Key                |
| T_PEOPLE_ID | Number   | 10     | Foreign Key (T_PEOPLE)     |
| EMAIL_TYPE  | Enum     | 5      | Not Null (e.g., WORK, PERSN, UNI)|
| EMAIL       | String   | 40     | Nullable, valid email format|

### T_ADDRESSES Table

| Column      | Type     | Length | Constraints         |
|-------------|----------|--------|---------------------|
| ID          | Number   | 10     | Primary Key         |
| ADDR_TYPE   | String   | 5      | Not Null            |
| ADDR_INFO   | String   | 300    | Nullable            |

## Functionality

### Create Record

Allows the creation of a new entry in each database table. Constraints defined at the web service level and the presence of validation issues are visualized with appropriate messages. Displays a message upon successful creation.

### Edit Record

Enables the modification of an existing entry. Validation errors are handled similar to the create operation. Displays an informative message after the successful modification of a record.

### Delete Record

Permits the deletion of a selected entry. Displays an informative message after the successful deletion of a record.

## Tech-stack

- **Java**
- **Spring Boot**
- **Spring Data JPA**
- **MapStruct**
- **Lombok**
- **Springdoc-OpenAPI**
- **PostgreSQL**
- **Docker containers**
- **OAuth2**
