# Smart IT Service Desk Ticketing System

A backend service desk ticketing system built using **Spring Boot**, **MySQL**, and **JPA**, designed to manage IT support tickets with real-world workflows such as ticket lifecycle management, agent assignment, and role-based operations.

---

## 🚀 Features

- User roles: **USER**, **AGENT**, **ADMIN**
- Ticket creation with priority and status
- Ticket lifecycle management (OPEN → IN_PROGRESS → RESOLVED → CLOSED)
- Agent assignment with role validation
- Ticket comments for discussion history
- Global exception handling with clean JSON responses
- RESTful API design (POST / PUT / GET)
- MySQL persistence using Hibernate & JPA
- DTO-based request handling
- Tested using **Postman**

---

## 🛠 Tech Stack

- **Backend**: Java 17, Spring Boot
- **Database**: MySQL
- **ORM**: Spring Data JPA (Hibernate)
- **Security (Phase 2)**: Spring Security (JWT planned)
- **Build Tool**: Maven
- **API Testing**: Postman

---

## ▶️ How to Run Locally

### Prerequisites
- Java 17 installed
- MySQL installed and running
- Maven (or use the Maven Wrapper included in the project)

### Steps

1. Clone the repository
```bash
git clone https://github.com/<your-username>/smart-it-service-desk.git
```

2. Create a MySQL database
```bash
CREATE DATABASE ticketing_system;
```

3. Update database configuration
Open src/main/resources/application.properties and update:
```bash
spring.datasource.url=jdbc:mysql://localhost:3306/ticketing_system
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
```

4. Run the application
```bash
./mvnw spring-boot:run
```
or run TicketingSystemApplication directly from IntelliJ.

5. Access the application
The server will start on:
```bash
http://localhost:8080
```

6. Test APIs using Postman
Use the endpoints listed in the API Endpoints section to create and manage tickets.

---

## 📂 Project Structure

```text
com.servicedesk.ticketing
├── controller
├── dto
├── entity
├── exception
├── repository
└── config
```

---

## 🔗 API Endpoints (Core)

### Create Ticket
```http
POST /api/tickets
```
```json
{
  "userId": 1,
  "title": "Printer not working",
  "description": "Office printer is not responding",
  "priority": "HIGH"
}
```

### Update Ticket Status
```http
PUT /api/tickets/{ticketId}/status?status=RESOLVED
```

### Assign Ticket to Agent
```http
PUT /api/tickets/{ticketId}/assign/{agentId}
```

### List Tickets
```http
GET /api/tickets/all
GET /api/tickets/user/{userId}
```

### Ticket Comments
```http
POST /api/tickets/comment
GET /api/tickets/{ticketId}/comments
```