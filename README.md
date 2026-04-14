# SOEN345_CacheMoney
The Ticket Reservation Application is a Java-based web system designed to simplify the process of browsing, reserving, and managing event tickets. It enables users to explore a wide range of events while providing features to search and filter events by date, location, or category. Users can register using their email or phone number, reserve tickets, and receive digital confirmations by email upon successful booking.

The system is designed to serve two main user groups: customers and event administrators. While customers can manage their reservations and receive confirmations via email, administrators are responsible for creating, updating, and canceling events. Built with scalability and usability in mind, the application supports concurrent users and provides a simple, user-friendly interface for an efficient booking experience.

## E2E Tests (Maestro)

**Install Maestro:** https://docs.maestro.dev/get-started/quickstart

**Prerequisites**
- Android emulator running (or device connected via ADB)
- Backend running and reachable from the device

**Run all tests**
```bash
maestro test .\.maestro\
```

## Setup

Clone the repository and follow the steps:

**Backend (Spring boot)**
### 1. Requirements
Java 17+

Maven

### 2. Configuration
The backend uses PostgreSQL (Supabase) and requires no local DB setup by default.

Some features make use of mail services, which are configured for Gmail SMTP

The structure of your application.properties (in backend/src/main/resources) file should looklike this:

```
# Application name
spring.application.name=TicketReserve

# PostgreSQL connection (Supabase)
spring.datasource.url=jdbc:postgresql://<HOST>:5432/<DB_NAME>?sslmode=require
spring.datasource.username=<DB_NAME>
spring.datasource.password=<DB_PASSWORD>
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Server
server.port=8080

spring.jpa.open-in-view=false

# Mail services

# Gmail SMTP settings
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=<YOUR_GMAIL>
spring.mail.password=<YOUR_APP_PASSWORD>
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```
### 3. Run the backend

- On Windows
```
cd backend
.\mvnw spring-boot:run
```

- On Linux/macOS
```
cd backend
./mvnw spring-boot:run
```
---

Frontend (Android)

### 1. Requirements
- Android Studio (Recommended)
- JDK 11+
- Gradle

### 2. Build and Run
- Open the *client* folder in Android Studio
- Use the Gradle wrapper or Android Studio's build/run buttons
- Minimum SDK: 24, Target SDK: 36


