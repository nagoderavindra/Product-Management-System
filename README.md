# 🛒 Product Management System

A Spring Boot REST API project for managing Products, Categories, and Users with JWT Authentication.

---

## 🚀 Technologies Used

- Java 17
- Spring Boot 3
- Spring Security
- JWT Authentication
- Spring Data JPA
- Hibernate
- MySQL
- Caffeine Cache
- Swagger OpenAPI
- Lombok
- Maven

---

## ✨ Features

- User Registration
- User Login using JWT
- Role Based Authentication
- Category CRUD
- Product CRUD
- Pagination
- Caching
- Validation
- Exception Handling
- Logging
- Swagger API Documentation

---

## 📂 Project Structure

```
src
 ├── controller
 ├── service
 ├── serviceImpl
 ├── repository
 ├── entity
 ├── dto
 ├── security
 ├── config
 ├── exception
 └── util
```

---

## 🔐 Authentication

JWT Token Based Authentication

```
Authorization: Bearer <JWT_TOKEN>
```

---

## 📌 API Endpoints

### Authentication

| Method | Endpoint |
|---------|-----------|
| POST | /api/auth/register |
| POST | /api/auth/login |

### Categories

| Method | Endpoint |
|---------|-----------|
| GET | /api/categories |
| GET | /api/categories/{id} |
| POST | /api/categories |
| PUT | /api/categories/{id} |
| DELETE | /api/categories/{id} |

### Products

| Method | Endpoint |
|---------|-----------|
| GET | /api/products |
| GET | /api/products/{id} |
| POST | /api/products |
| PUT | /api/products/{id} |
| DELETE | /api/products/{id} |

---

## 📖 Swagger UI

```
http://localhost:8080/swagger-ui/index.html
```

---

## ⚙️ Database

MySQL

Update your database configuration inside

```
application.properties
```

---

## ▶️ Run Project

Clone Repository

```bash
git clone https://github.com/nagoderavindra/Product-Management-System.git
```

Go to project

```bash
cd Product-Management-System
```

Run

```bash
mvn spring-boot:run
```

---

## 👨‍💻 Developed By

**Ravindra Nagode**

Backend Java Developer
