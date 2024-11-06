# Doctoral Registration Management Backend

This is the backend for the Doctoral Registration Management System, designed for the University Abdelmalek Essaâdi. It
is built with **Spring Boot** and integrates with **PostgreSQL** for data management.

## Features

- RESTful APIs for handling doctoral registration processes
- Manages doctoral candidate, professor, and CED data
- Supports role-based actions (e.g., CED, professors, candidates)
- Integration with frontend (Angular) via secure endpoints
- Jwt Authentication and Role-based Authorization
## Requirements

- Java 22
- Spring Boot
- PostgreSQL
- Docker (optional for deployment)

## Setup and Installation

1. Clone this repository:
   ```bash
   git clone https://github.com/MedEZZOUAK/springapi_init.git
   cd springapi_init
   ```
2. Run the docker-compose file to start the PostgreSQL database and pgAdmin:
   ```bash
   docker-compose up -d
   ```
3. Build the project:
   ```bash
   mvn clean package -DskipTests
   ```
4. Run the project:
   ```bash
   java -jar target/doctoral-registration-0.0.1-SNAPSHOT.jar
   ```
5. Test The Authentication using Postman:
   ```
    POST http://localhost:8080/api/v1/auth/register
   {
    "nom": "",
    "prenom": "",
    "email": "",
    "cin": "",
    "telephone": "",
    "password": ""
    }
    ```
6. Check the database using pgAdmin:
   - Open a browser and go to `http://localhost:5050`
   - Login with the credentials `      PGADMIN_DEFAULT_EMAIL: admin@admin.com
     PGADMIN_DEFAULT_PASSWORD: root
   - Add a new server with the following credentials:
     ```
     Host: db
     Port: 5432
     Maintenance database: postgres
     Username: postgres
     Password: root
     ```
   - You can now view the database and its tables
7. You can now start using the APIs by sending requests to `http://localhost:8080/api/v1/...`
8. To stop the containers, run:
    ```bash
    docker-compose down
    ```
## Front End
- The frontend for this project is built with Angular and can be found [here](https://github.com/SalmaBenaouda/frontDoc.git)
- Clone the repository and follow the instructions in the README file to run the frontend
- The frontend will be available at `http://localhost:4200`
- You can now interact with the backend APIs through the frontend
- The frontend is secured with JWT authentication and role-based authorization

## Contributors
I would like to thank my team members for their contributions to this project:
- [Salma Benaouda](https://github.com/SalmaBenaouda)
- [Madani Ouail](https://github.com/wail00222)
- [Bamhaouch Fatima-zahra](https://github.com/Fatibam)

## Rapport
- [Rapport](GestionDoctorat.pdf)


