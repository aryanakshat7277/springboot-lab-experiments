# Spring Boot & Spring Security Lab Experiments Collection 🚀

A comprehensive, production-ready collection of **Spring Boot 3.x** lab experiments covering REST APIs, Dependency Injection, Global Exception Handling, Spring Security (Role-Based Access Control), and Spring Data JPA with H2 / MySQL databases.

---

## 📁 Included Experiments

| Project Directory | Description | Primary Features & Endpoints |
|---|---|---|
| 🔐 **`SpringSecurityDemo`** | Role-Based Access Control (RBAC) & Spring Security | `/welcome` (Public), `/user` (`ROLE_USER`), `/admin` (`ROLE_ADMIN`), 403 Forbidden handling |
| 🎓 **`StudentCRUD`** | Spring Data JPA CRUD Operations & Interactive Web UI | H2 Database (`/h2-console`), REST endpoints (`/api/students`), Interactive Web REST Client (`/`) |
| 🏗️ **`ConstructorSetter`** | Spring Core Dependency Injection | Demonstrates Constructor Injection vs Setter Injection patterns |
| 🌐 **`CreateaSimpleHelloWorldProgramusingSpringBoot`** | First Spring Boot REST Web Application | Controller mapping, HTTP GET responses, starter setup |
| ⚠️ **`ExceptionHandlerInRestApi`** | REST Exception Handling | `@RestControllerAdvice`, `@ExceptionHandler`, standard error response payloads |
| 🛠️ **`RestApiDemo`** | Full REST API Service Layer Architecture | Controller, Service, and Repository layer separation |
| ⚡ **`-1`** | Exception Handling Foundation | Custom exception throwing and HTTP status mappings |

---

## 🚀 Quick Start Guide

### Prerequisites
* **Java**: JDK 21 or higher
* **IDE**: [Spring Tools for Eclipse (STS 4/5)](https://spring.io/tools) or IntelliJ IDEA / VS Code
* **Build Tool**: Apache Maven 3.8+

---

## 📥 How to Import into Spring Tools for Eclipse (STS)

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/<your-username>/<your-repo-name>.git
   cd <your-repo-name>
   ```

2. **Import Projects into STS**:
   * Open **Spring Tools for Eclipse (STS)**.
   * Click **File** (top menu bar) → **Import...**
   * Expand **Maven** → select **Existing Maven Projects** → click **Next**.
   * Click **Browse...** and select the cloned root folder (or any specific experiment folder).
   * Click **Finish**. All projects will appear in your **Package Explorer**!

---

## ▶️ Running an Experiment in STS

1. In **Package Explorer**, expand the desired project (e.g. `SpringSecurityDemo`).
2. Expand `src/main/java` → find the main application class (e.g. `SpringSecurityDemoApplication.java`).
3. **Right-click** on the main class file → **Run As** → **Spring Boot App**.
4. Check the **Console** panel at the bottom to verify server startup on port `8080`.

---

## ▶️ Running via Terminal / Command Line

You can also run any project directly from the terminal without an IDE:

```bash
# Navigate to any experiment directory
cd StudentCRUD

# Run with Maven Wrapper or installed Maven
mvn spring-boot:run
```

---

## 🔑 Credentials & Endpoints Summary

### 1. `SpringSecurityDemo` (Port 8080)
* **Public Welcome**: `http://localhost:8080/welcome`
* **User Endpoint** (`http://localhost:8080/user`):
  * Username: **`user`** | Password: **`user`**
* **Admin Endpoint** (`http://localhost:8080/admin`):
  * Username: **`admin`** | Password: **`admin`**
* **Log Out Endpoint**: `http://localhost:8080/logout`

### 2. `StudentCRUD` (Port 8080)
* **Interactive Web REST Client**: `http://localhost:8080/`
* **H2 Database Console**: `http://localhost:8080/h2-console`
  * JDBC URL: `jdbc:h2:mem:studentdb`
  * Username: `sa` | Password: *(blank)*
* **REST API Endpoints**:
  * `GET /api/students` (Get all students)
  * `POST /api/students` (Create student)
  * `GET /api/students/{id}` (Get student by ID)
  * `PUT /api/students/{id}` (Update student)
  * `DELETE /api/students/{id}` (Delete student)

---

## 📄 License
This repository is licensed under the **MIT License**. Free for educational and lab learning purposes.
