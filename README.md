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
}git
```

---

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