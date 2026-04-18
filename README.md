# Online Complaint Management System

## Tech Stack
- **Backend:** Spring Boot 3, Spring Security, JWT, JPA
- **Database:** PostgreSQL
- **Frontend:** React, Axios, React Router

## Setup Instructions

### 1. PostgreSQL Setup
```sql
CREATE DATABASE complaint_db;
```
Update `backend/src/main/resources/application.properties` with your DB password.

### 2. Run Backend
```bash
cd backend
mvn spring-boot:run
```
Backend runs on: http://localhost:8080

### 3. Seed Admin User
After backend starts (tables are auto-created), run `setup.sql` in pgAdmin or psql:
```
Admin credentials: username=admin / password=admin123
```

### 4. Run Frontend
```bash
cd frontend
npm start
```
Frontend runs on: http://localhost:3000

## API Endpoints

| Method | Endpoint | Access |
|--------|----------|--------|
| POST | /api/auth/register | Public |
| POST | /api/auth/login | Public |
| POST | /api/complaints | User |
| GET | /api/complaints | User (own) |
| DELETE | /api/complaints/{id} | User (own) |
| GET | /api/admin/complaints | Admin |
| PUT | /api/admin/complaints/{id}/status | Admin |

## Features
- JWT-based authentication
- Role-based access (USER / ADMIN)
- Submit complaints with category
- Track complaint status: OPEN → IN_PROGRESS → RESOLVED / REJECTED
- Admin dashboard with stats and complaint management
- Admin can respond to complaints
