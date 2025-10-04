🏦 Bank Application

A secure Spring Boot-based banking application that allows account management, balance inquiries, and credit/debit operations. It integrates Swagger API documentation for seamless testing and includes email notifications for critical account activities.

📜 Table of Contents

Features

Tech Stack

Project Structure

API Documentation

Installation & Setup

Usage

Learning Outcomes

✨ Features

Create and manage customer accounts

Credit and debit transactions with validations

Balance inquiry and transaction history

Email notifications for account activities

RESTful APIs with Swagger UI documentation

Secure data persistence with transaction management

🛠️ Tech Stack

Backend: Java 17+, Spring Boot

Database: MS SQL Server

ORM: JPA / Hibernate

API: REST APIs with Swagger documentation

Version Control: Git

Testing: Postman for API testing

📂 Project Structure
/src
  /main
    /java/com/example/bankapp
        controller/   → REST Controllers (API endpoints)
        service/      → Business logic (transactions, validations)
        repository/   → Spring Data JPA Repositories
        model/        → Entity classes (Account, Customer, Transaction)
        config/       → Email/Swagger configurations
    /resources
        application.properties  → DB + mail configs
/test
  (Unit/Integration tests if any)

📑 API Documentation

Once the app is running, visit:

http://localhost:8080/swagger-ui/index.html


to explore and test all available REST APIs using Swagger UI.

🚀 Installation & Setup

Clone the repository

git clone https://github.com/yourusername/bank-application.git
cd bank-application


Configure the database in src/main/resources/application.properties:

spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=BankDB
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.SQLServerDialect

# Email alerts (example)
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your_email@gmail.com
spring.mail.password=your_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true


Build & Run the project using Maven:

mvn clean install
mvn spring-boot:run


Access APIs at http://localhost:8080/api/accounts (adjust path to your endpoints).

📖 Usage

Use Swagger UI at http://localhost:8080/swagger-ui/index.html to test the APIs interactively.

Or use Postman to send requests (GET, POST, PUT, DELETE) to endpoints.

Example endpoint:

POST /api/accounts/create
Body:
{
  "name": "John Doe",
  "email": "john@example.com",
  "initialBalance": 1000
}

📈 Learning Outcomes

Designed and implemented a secure banking backend using Spring Boot.

Practiced REST API design with integrated Swagger documentation.

Used JPA/Hibernate for transaction management and MS SQL Server for data persistence.

Integrated email notifications to improve user experience.

Managed version control with Git and tested APIs with Postman.
