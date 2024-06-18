# TypingLizard Backend

Welcome to the TypingLizard backend repository. This backend service is built with Spring Boot and connects to a PostgreSQL database. Below you will find instructions to set up and run the backend locally.

## Prerequisites

Ensure you have the following installed on your local machine:
- Java 11 or higher
- Maven
- PostgreSQL

## Setup Instructions

### 1. Clone the Repository

```sh
git clone https://github.com/yourusername/typing-lizard-backend.git
cd typing-lizard-backend
```

### 2. Configure the Database

Create a PostgreSQL database with the following credentials:

- **Database Name:** TypingLizard
- **Username:** postgres
- **Password:** postgres

You can create the database using the following PostgreSQL commands:

```sql
CREATE DATABASE TypingLizard;
CREATE USER postgres WITH ENCRYPTED PASSWORD 'postgres';
GRANT ALL PRIVILEGES ON DATABASE TypingLizard TO postgres;
```

### 3. Update Application Properties

Ensure the `src/main/resources/application.properties` file is configured with the following database connection details:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/TypingLizard
spring.datasource.username=postgres
spring.datasource.password=postgres
```
