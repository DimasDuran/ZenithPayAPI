# ZenithPayAPI

A Spring Boot banking API for managing user accounts, fund transfers, and transactions.

## Overview

The ZenithPay API provides a set of REST endpoints for secure banking operations including user registration, PIN management, cash deposits/withdrawals, fund transfers, and transaction history.

## Features

- **User Registration**: Register new users with name, email, address, and phone number
- **PIN Management**: Create and update PINs for account security
- **Cash Operations**: Deposit and withdraw cash from accounts
- **Fund Transfer**: Transfer funds between accounts within the system
- **Transaction History**: View transaction history per account
- **Authentication**: Spring Security with JWT authentication
- **OTP Verification**: One-time password based login flow
- **Geolocation**: Login location tracking

## Technology Stack

- **Java 17**
- **Spring Boot 4.1.1**
- **Spring Security**
- **Spring Data JPA**
- **MySQL**
- **Lombok**
- **Redis** (for caching)

## Prerequisites

- Java 17 or higher
- MySQL 8.0 or higher
- Maven or Gradle

## Setup

1. Clone the repository
2. Configure MySQL database in `src/main/resources/application.properties`
3. Run the application:
   ```bash
   ./gradlew bootRun
   ```
   
   Or using Maven:
   ```bash
   mvn spring-boot:run
   ```

## API Endpoints

### Authentication
- `POST /api/auth/login` - User login
- `POST /api/auth/register` - User registration

### Account
- `GET /api/account/pin/check` - Check if PIN is created
- `POST /api/account/pin/create` - Create PIN
- `POST /api/account/pin/update` - Update PIN
- `POST /api/account/deposit` - Deposit cash
- `POST /api/account/withdraw` - Withdraw cash
- `POST /api/account/fund-transfer` - Transfer funds
- `GET /api/account/transactions` - Get transaction history
- `GET /api/account/send-statement` - Send bank statement

### User
- `GET /api/users/profile` - Get user profile
- `PUT /api/users/profile` - Update user profile

## Configuration

Update `src/main/resources/application.properties` with your database and server settings:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/zenithpay
spring.datasource.username=root
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
server.port=8080
```