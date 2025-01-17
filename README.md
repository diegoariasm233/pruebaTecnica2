# Price Query

This is a project for querying product price information. It is a modular application based on **Hexagonal Architecture**, using **H2** as an in-memory database with initial data.

## Requirements

- **Java 17**
- **Maven 3.9.9**

## Description

This project follows the **Hexagonal Architecture** pattern, which allows for a clear separation of concerns between the domain and external interfaces (such as databases, APIs, etc.). The database used is **H2**, which is configured to store initial test data when the application is run.

## Setup

1. Clone this repository:

   ```bash
   git clone https://github.com/diegoariasm233/pruebaTecnica2

2. Compile the project using Maven:

    ```bash
    mvn clean install

3. Run the application:

   If you wish to run the application directly, you can use the following Maven command:

    ```bash
   mvn spring-boot:run -f infrastructure/pom.xml

This will start the application, and the H2 database will be initialized with the preconfigured data defined in the data.sql file.

# Access to the H2 Console
You can access the H2 web console to inspect the database at:

http://localhost:8080/h2-console

## Default Credentials:

 - JDBC URL: jdbc:h2:mem:testdb
 - Username: sa
 - Password: (empty)