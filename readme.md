# It-Academy Test Task

## Description

This is a multi-module Maven project implementing a REST API for managing users. It provides endpoints for adding users and retrieving a list of users with their basic information, such as Full Name, Email, and Role. The API follows the REST architectural style and returns data in JSON format.

## Project Stack

- Java 17 
- Spring Boot 3.1.2(Data Jpa, Web, Test, Docker Compose)
- Liquibase
- MySQL 8
- Testcontainers
- Log4j2
- Maven
- Git

## Setup

1. Clone the repository from GitHub:

```bash
git clone <repository_url>
```

2. Deploy the Docker Compose file:

```bash
docker-compose up -d
```

3. Build the project using Maven:

```bash
mvn clean install
```
4. Run the application using Spring Boot:

```bash
cd <module_name>
mvn spring-boot:run
```
