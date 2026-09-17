# Spring Boot & Spring Security Lab Experiments Collection 🚀

A comprehensive, production-ready collection of **Spring Boot 3.x** lab experiments covering REST APIs, Dependency Injection, Global Exception Handling, Spring Security (Role-Based Access Control), Spring Data JPA Multi-Databases, Unit Testing with JUnit 5 & Mockito, TDD Integration Testing with H2 & TestRestTemplate, and Spring Health Monitoring.

---

## 📁 Included Experiments

| Project Directory | Description | Primary Features & Endpoints |
|---|---|---|
| 🎓 **`StudentApiExceptionDemo`** *(New Experiment)* | Student REST API with Global Exception Handling & Validation | Custom `StudentNotFoundException` (404), `DuplicateStudentException` (409), Input Validation (`@Valid` -> 400), `@RestControllerAdvice`, standard JSON error response payloads |
| 🏢 **`BankServicesApp`** | Bank Pune Branches, Services Tables & Health Check | `/branches` (10 Pune Branches HTML Table), `/services` (Bank Services HTML Table), `/health` (`status: UP`) |
| 🏦 **`BankServicesDemo`** | Bank REST Services & Auto-Configured Beans Console Printer | `GET /name` (Bank Name String), `GET /address` (Bank Address String), Console Bean Listing (`CommandLineRunner`) |
| 🚀 **`IntegrationTestDemo`** *(JavaTechie Video)* | TDD Integration Testing with JUnit 5 & H2 | `@SpringBootTest(webEnvironment = RANDOM_PORT)`, `TestRestTemplate`, `H2` embedded database verification (`POST`, `GET`, `PUT`, `DELETE`) |
| 🧪 **`JUnitDemo`** *(CodeSnippetJava Part 1)* | Unit Testing with JUnit 5 & Spring Data JPA | `@DataJpaTest`, `@BeforeEach`, `@AfterEach`, `@BeforeAll`, `@AfterAll`, Custom JPQL & Native SQL testing |
| 🎭 **`MockitoDemo`** *(CodeSnippetJava Part 2)* | Advanced Unit Testing with Mockito Framework | Service & Controller mocking (`@ExtendWith(MockitoExtension.class)`, `@Mock`, `@InjectMocks`, `@MockBean`, `willDoNothing()`, `assertThrows()`) |
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

1. In **Package Explorer**, expand the desired project (e.g. `StudentApiExceptionDemo`).
2. Expand `src/main/java` → find the main application class (e.g. `StudentApiExceptionDemoApplication.java`).
3. **Right-click** on the main class file → **Run As** → **Spring Boot App**.
4. Check the **Console** panel at the bottom to verify server startup on port `8080`.

---

## 🧪 Running Unit & Exception Tests in STS / Maven

### 1. `StudentApiExceptionDemo` Test Suite:
```bash
cd StudentApiExceptionDemo
mvn clean test
```

### 2. `BankServicesApp` Test Suite:
```bash
cd BankServicesApp
mvn clean test
```

Or in STS: Right-click any test class → **Run As** → **JUnit Test**.

---

## 🔑 Credentials & Endpoints Summary

### 1. `StudentApiExceptionDemo` (Port 8080)
* **GET All Students**: `http://localhost:8080/api/students` (200 OK)
* **GET Student by ID**: `http://localhost:8080/api/students/{id}` (200 OK or 404 Not Found)
* **POST Create Student**: `http://localhost:8080/api/students` (201 Created, 400 Bad Request, 409 Conflict)
* **Interactive Exception Tester Dashboard**: `http://localhost:8080/`

### 2. `BankServicesApp` (Port 8080)
* **1st RESTful URL**: `http://localhost:8080/branches` (Returns HTML page with 10 Pune Branches Table)
* **2nd RESTful URL**: `http://localhost:8080/services` (Returns HTML page with Bank Services Table)
* **Health Endpoint**: `http://localhost:8080/health` (Returns `{"status":"UP"}`)

---

## 📄 License
This repository is licensed under the **MIT License**. Free for educational and lab learning purposes.
