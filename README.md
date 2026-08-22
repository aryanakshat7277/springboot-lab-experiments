# Spring Boot & Spring Security Lab Experiments Collection 🚀

A comprehensive, production-ready collection of **Spring Boot 3.x** lab experiments covering REST APIs, Dependency Injection, Global Exception Handling, Spring Security (Role-Based Access Control), Spring Data JPA Multi-Databases, and Unit Testing with JUnit 5 & Mockito.

---

## 📁 Included Experiments

| Project Directory | Description | Primary Features & Endpoints |
|---|---|---|
| 🧪 **`JUnitDemo`** *(Part 1 Video)* | Unit Testing with JUnit 5 & Spring Data JPA | `@DataJpaTest`, `@BeforeEach`, `@AfterEach`, `@BeforeAll`, `@AfterAll`, Custom JPQL & Native SQL testing |
| 🎭 **`MockitoDemo`** *(Part 2 Video)* | Advanced Unit Testing with Mockito Framework | Service & Controller mocking (`@ExtendWith(MockitoExtension.class)`, `@Mock`, `@InjectMocks`, `@MockBean`, `willDoNothing()`, `assertThrows()`) |
| ⚡ **`MultiDatabases`** | Connecting to Multiple DataSources in Spring Data JPA | Dual H2 Databases (`bookdb` & `studentdb`), `@Primary` configuration, `/api/all`, `/api/db1/books`, `/api/db2/students` |
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
   git clone https://github.com/aryanakshat7277/springboot-lab-experiments.git
   cd springboot-lab-experiments
   ```

2. **Import Projects into STS**:
   * Open **Spring Tools for Eclipse (STS)**.
   * Click **File** (top menu bar) → **Import...**
   * Expand **Maven** → select **Existing Maven Projects** → click **Next**.
   * Click **Browse...** and select the cloned root folder (or any specific experiment folder).
   * Click **Finish**. All projects will appear in your **Package Explorer**!

---

## ▶️ Running an Experiment in STS

1. In **Package Explorer**, expand the desired project (e.g. `MockitoDemo`).
2. Expand `src/main/java` → find the main application class (e.g. `MockitoDemoApplication.java`).
3. **Right-click** on the main class file → **Run As** → **Spring Boot App**.
4. Check the **Console** panel at the bottom to verify server startup on port `8080`.

---

## 🧪 Running Unit Tests in STS / Maven

### 1. `JUnitDemo` (Part 1 - JUnit 5 Suite):
```bash
cd JUnitDemo
mvn clean test
```

### 2. `MockitoDemo` (Part 2 - Mockito Suite):
```bash
cd MockitoDemo
mvn clean test
```

Or in STS: Right-click any test class → **Run As** → **JUnit Test**.

---

## 🔑 Credentials & Endpoints Summary

### 1. `JUnitDemo` (Port 8080)
* **H2 Console**: `http://localhost:8080/h2-console`
* **Test Suite**: `EmployeeRepositoryTests.java` (Tests save, find, JPQL, Native SQL queries)

### 2. `MockitoDemo` (Port 8080)
* **Interactive Dashboard**: `http://localhost:8080/`
* **REST Endpoints**: `/api/employees` (GET, POST, PUT, DELETE)
* **Test Suites**: `EmployeeServiceTests.java` & `EmployeeControllerTests.java`

### 3. `MultiDatabases` (Port 8080)
* **Interactive Dashboard**: `http://localhost:8080/`
* **Combined Dual DB Endpoint**: `http://localhost:8080/api/all`

### 4. `SpringSecurityDemo` (Port 8080)
* **Public Welcome**: `http://localhost:8080/welcome`
* **User Endpoint**: `http://localhost:8080/user` *(Username: `user` \| Password: `user`)*
* **Admin Endpoint**: `http://localhost:8080/admin` *(Username: `admin` \| Password: `admin`)*
* **Logout**: `http://localhost:8080/logout`

---

## 📄 License
This repository is licensed under the **MIT License**. Free for educational and lab learning purposes.
