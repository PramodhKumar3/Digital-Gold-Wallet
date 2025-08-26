# 🏦 GoldWallet Microservices Application

The **GoldWallet Microservices Application** is the cloud-native version of the GoldWallet monolith backend. Each entity (User, Vendor, Address, etc.) is implemented as an independent **Spring Boot microservice**. Microservices communicate via **REST APIs** and **Feign Clients**, and the architecture supports **scalability, independent deployments, and centralized configuration**.

This README is beginner-friendly, explaining **project structure, endpoints, configuration, deployment, and future enhancements**.

> **Note:** Unlike the monolith, this project is modular, with each microservice having its own database and business logic.

---

## 📑 Table of Contents

1. [Project Overview](#-project-overview)
2. [Technology Stack](#-technology-stack)
3. [Microservices Architecture](#-microservices-architecture)
4. [Application Configuration](#-application-configuration)
5. [Database Setup](#-database-setup)
6. [Microservice Endpoints](#-microservice-endpoints)
7. [Feign Clients](#-feign-clients)
8. [Global Exception Handling](#-global-exception-handling)
9. [Prerequisites](#-prerequisites)
10. [Docker & Kubernetes Deployment](#-docker--kubernetes-deployment)
11. [Run Instructions](#-run-instructions)
12. [Future Enhancements](#-future-enhancements)
13. [Contact](#-contact)

---

## 📌 Project Overview

GoldWallet Microservices enables **secure digital gold transactions**. Each microservice handles a specific bounded context:

| Service                                | Description                              |
| -------------------------------------- | ---------------------------------------- |
| **User Service**                       | Manage users, profiles, and balances     |
| **Address Service**                    | Manage user/vendor addresses             |
| **Vendor Service**                     | Manage vendors                           |
| **Vendor Branch Service**              | Manage vendor branch details             |
| **Virtual Gold Holding Service**       | Track digital gold holdings per user     |
| **Physical Gold Transactions Service** | Handle physical gold purchases and sales |
| **Payments Service**                   | Manage payments for users and vendors    |
| **Transaction History Service**        | Store complete transaction history       |

**Infrastructure Services:**

- **API Gateway (7654)** → Single entry point for all services
- **Config Server (8888)** → Centralized configuration in github
- **Eureka Server (8761)** → Service discovery and registry

---

## 💻 Technology Stack

- **Java 17** – Modern LTS language
- **Spring Boot 3.x** – Rapid microservice development
- **Spring Cloud Netflix** – Eureka (discovery), Feign (service calls), Config, Gateway
- **Spring Data JPA** – ORM for database access
- **MySQL** – Each microservice uses its own schema
- **Maven** – Dependency management
- **Docker & Kubernetes** – Containerization and deployment

---

## 🏗 Microservices Architecture

```
                   ┌──────────────────────────┐
                   │   Config Server (8888)   │
                   └──────────┬───────────────┘
                              │
                   ┌──────────▼───────────────┐
                   │   Eureka Server (8761)   │
                   └──────────┬───────────────┘
                              │
                   ┌──────────▼───────────────┐
                   │   API Gateway (7654)     │
                   └──────────┬───────────────┘
          ┌───────────────┼───────────────┬──────────────────────────────────┐
          │               │               │                                  │
   User Service      Vendor Service   Address Service   ..........   Transaction History
    (8101)             (8103)           (8102)          ..........         (8105)
```

---

## ⚙ Application Configuration

Configuration for all services is centralized in the [Spring Cloud Config repo](https://github.com/PramodhKumar3/spring-cloud-config).

Each microservice reads its configuration (database, ports, etc.) from this repository at startup.

**OR**

All microservices read configuration values (database, ports, URLs) from the **Spring Cloud Config repository**:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/YOUR_DB
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
server.port=PORT_NO
```

---

## 🗄️ Database Setup

Each microservice uses a separate database:

```sql
CREATE DATABASE digitalgoldwallet_user;
CREATE DATABASE digitalgoldwallet_address;
CREATE DATABASE digitalgoldwallet_vendor;
CREATE DATABASE digitalgoldwallet_vendorbranch;
CREATE DATABASE digitalgoldwallet_transaction;
CREATE DATABASE digitalgoldwallet_payment;
CREATE DATABASE digitalgoldwallet_physicalgold;
CREATE DATABASE digitalgoldwallet_virtualgold;
```

---

## 🌐 Microservice Endpoints

Below are some end-points per microservice:

---

### 1️⃣ User Service (8101)

Base Path: `/api/v2/users`

| Endpoint                             | Method | Description                |
| ------------------------------------ | ------ | -------------------------- |
| `/`                                  | GET    | List all users             |
| `/{user_id}`                         | GET    | Get user by ID             |
| `/name/{user_name}`                  | GET    | Search users by name       |
| `/add`                               | POST   | Add a new user             |
| `/{user_id}/update_balance/{amount}` | PUT    | Update user wallet balance |

---

### 2️⃣ Address Service (8102)

Base Path: `/api/v2/address`

| Endpoint               | Method | Description                      |
| ---------------------- | ------ | -------------------------------- |
| `/`                    | GET    | Get all addresses                |
| `/{user_id}`           | GET    | Get addresses of a specific user |
| `/add`                 | POST   | Add a new address                |
| `/update/{address_id}` | PUT    | Update address details           |

---

### 3️⃣ Vendor Service (8103)

Base Path: `/api/v2/vendors`

| Endpoint              | Method | Description           |
| --------------------- | ------ | --------------------- |
| `/`                   | GET    | List all vendors      |
| `/{vendor_id}`        | GET    | Get vendor by ID      |
| `/add`                | POST   | Add a new vendor      |
| `/update/{vendor_id}` | PUT    | Update vendor details |

---

### 4️⃣ Vendor Branch Service (8104)

Base Path: `/api/v2/vendor-branches`

| Endpoint              | Method | Description              |
| --------------------- | ------ | ------------------------ |
| `/`                   | GET    | List all vendor branches |
| `/{branch_id}`        | GET    | Get branch by ID         |
| `/add`                | POST   | Add a new branch         |
| `/update/{branch_id}` | PUT    | Update branch details    |

---

### 5️⃣ Transaction History Service (8105)

Base Path: `/api/v2/transaction-history`

| Endpoint              | Method | Description                       |
| --------------------- | ------ | --------------------------------- |
| `/`                   | GET    | List all transactions             |
| `/{txn_id}`           | GET    | Get transaction by ID             |
| `/user/{user_id}`     | GET    | Transactions of a specific user   |
| `/vendor/{vendor_id}` | GET    | Transactions of a specific vendor |
| `/add`                | POST   | Record a new transaction          |

---

### 6️⃣ Payments Service (8106)

Base Path: `/api/v2/payments`

| Endpoint               | Method | Description            |
| ---------------------- | ------ | ---------------------- |
| `/`                    | GET    | List all payments      |
| `/{payment_id}`        | GET    | Payment details by ID  |
| `/add`                 | POST   | Add a new payment      |
| `/update/{payment_id}` | PUT    | Update payment details |

---

### 7️⃣ Physical Gold Transactions Service (8107)

Base Path: `/api/v2/physical-gold`

| Endpoint                   | Method | Description                         |
| -------------------------- | ------ | ----------------------------------- |
| `/`                        | GET    | List all physical gold transactions |
| `/{transaction_id}`        | GET    | Get transaction by ID               |
| `/add`                     | POST   | Add new physical gold transaction   |
| `/update/{transaction_id}` | PUT    | Update transaction details          |

---

### 8️⃣ Virtual Gold Holding Service (8108)

Base Path: `/api/v2/virtual-gold`

| Endpoint          | Method | Description                    |
| ----------------- | ------ | ------------------------------ |
| `/`               | GET    | List all virtual gold holdings |
| `/{holding_id}`   | GET    | Get holding by ID              |
| `/user/{user_id}` | GET    | Holdings of a specific user    |
| `/buy`            | POST   | Buy virtual gold               |

---

## 🌐 Feign Clients

Microservices communicate using Feign clients:

```java
@FeignClient(name = "payment-service", url = "http://localhost:8106")
public interface PaymentFeignClient {
    @GetMapping("/api/v2/payments")
    List<PaymentDTO> getAllPayments();
}
```

---

## 🛡 Global Exception Handling

All microservices have **@RestControllerAdvice** handling:

- `EntityNotFoundException`
- `ValidationException`
- `MethodArgumentNotValidException`
- Custom exceptions (`UserException`, `VendorException`, etc.)

All return consistent **JSON responses** for beginner-friendly API debugging.

---

## 📋 Prerequisites

- **Java 17+**
- **Maven 3.x**
- **MySQL Server** with 8 databases (one per service)
- **Spring Cloud Config repo cloned & running**
- **IDE: IntelliJ IDEA, Eclipse, or STS**

---

## 🐳 Docker & Kubernetes Deployment

Each microservice has a **Dockerfile** and Kubernetes deployment YAML. Example:

```dockerfile
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/<user-service>.jar <user-service>.jar
EXPOSE <YOUR_INTERNAL_PORT_NO>
ENTRYPOINT ["java", "-jar", "<user-service>.jar"]
```

Deploy with:

```bash
nano deployment.yaml
kubectl apply -f deployment.yaml
nano service.yaml
kubectl apply -f service.yaml
kubectl get pods
kubectl get deployment
kubectl get services
```

---

## ▶ Run Instructions

1. Start **Config Server** → Port 8888.
2. Start **Eureka Server** → Port 8761.
3. Start **API Gateway** → Port 7654.
4. Start each microservice individually:

```bash
mvn clean package && java -jar target/*.jar
```

Access services via Gateway:

```
http://localhost:7654/{service}/...
```

---

## 🚀 Future Enhancements

To keep pace with best practices and scalability, consider the following improvements:

### Role-Based Access Control (RBAC)

- Implement fine-grained authentication and authorization for Admin, Vendor, and User roles.
- Restrict sensitive operations like gold price updates, vendor approvals, and wallet adjustments to authorized roles only.

### Secure JWT-Based Authentication

- Replace session-based authentication with stateless JWT tokens to simplify scaling and avoid server-side session storage.
- Use short-lived access tokens with refresh tokens and embed role claims for efficient authorization checks.

### Circuit Breakers (Resilience4j)

- Prevent cascading failures by automatically opening circuits for failing downstream services and falling back gracefully.
- Improve system resilience with configurable thresholds, retry policies, and bulkhead isolation.

### API Rate Limiting

- Protect services from abuse and spikes by enforcing per-client and global request limits at the gateway.
- Improve fairness and availability with prioritized traffic shaping and exponential backoff responses.

### Centralized Logging & Tracing

- Correlate requests across services with distributed tracing (OpenTelemetry/Zipkin) to speed debugging and root-cause analysis.
- Centralize logs (ELK/EFK) for searchable observability, alerting, and long-term forensic analysis.

### Continuous Integration & Deployment (CI/CD)

- Automate builds, tests, and deployments using pipelines (GitHub Actions/Jenkins) to ensure reliable, repeatable releases.
- Include automated rollback and canary deployments to reduce deployment risk and speed recovery from failures.

---

## 📬 Contact

Created with 💻 by [Pramodh Kumar](https://www.linkedin.com/in/tamminaina-pramodh-kumar-6433a4242)

For contributions or issues, please raise a PR or contact directly.
