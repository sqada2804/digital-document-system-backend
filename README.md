# Digital Document Delivery System

A backend project built to explore microservices, event-driven architecture, cloud storage, and DevOps practices.

The system manages digital letters and packages through independent services that communicate using REST APIs and Kafka events.

## Overview

The current flow allows a user to create a letter or package, validate its eligibility, generate a document, store it in S3-compatible storage, and notify the receiver by email.

<img width="6476" height="3260" alt="Untitled-2026-06-03-1008 (1)" src="https://github.com/user-attachments/assets/57f073cc-a9d5-4c55-875b-da36ca297a21" />

## Main Services

- **API Gateway** — Main entry point for the system.
- **Auth Service** — Authentication and JWT security.
- **Users Service** — User management.
- **Letters Service** — Letter creation and management.
- **Package Service** — Package creation and management.
- **Eligibility Service** — Processes business rules asynchronously.
- **Notification Service** — Generates documents, stores them and sends email notifications.

## Technologies

- Java & Spring Boot
- Spring Cloud Gateway
- Spring Security
- PostgreSQL
- Apache Kafka
- Docker & Docker Compose
- LocalStack / S3
- Thymeleaf
- Mailgun

## Current Status

The main end-to-end workflow is already working:

- Authentication
- Letter and package creation
- Kafka event communication
- Eligibility processing
- Email notifications
- HTML document generation
- S3-compatible document storage
- Local infrastructure with Docker

The project currently works as a functional MVP.

## Next Steps

The next stage will focus less on adding features and more on making the system more reliable and production-ready.

Planned improvements include:

- Dockerizing all microservices
- Separate development and production environments
- Kafka retries and Dead Letter Queues
- Event idempotency
- Document status and lifecycle tracking
- Database migrations with Flyway
- Audit logs
- Secure document downloads
- Health checks and monitoring
- CI/CD with GitHub Actions
- HTTPS and public deployment
- Automated backups
- Cloud deployment using mainly free or low-cost services

## Production Direction

The production version will prioritize simple and affordable infrastructure.

The initial goal is to run the system using Docker Compose on a small cloud server instead of introducing Kubernetes or expensive managed services too early.

```text
Internet
   ↓
HTTPS / Reverse Proxy
   ↓
API Gateway
   ↓
Microservices
   ↓
PostgreSQL + Kafka
   ↓
External Services
   ├── S3-compatible storage
   └── Email provider
```

LocalStack will remain the S3 implementation for development, while a compatible cloud storage provider can be used in production.

Kubernetes may be explored later as an optional DevOps evolution once the Docker-based deployment is stable.

## Goal

The long-term goal is to evolve the project from a functional microservices MVP into a small production-like distributed system while keeping infrastructure costs as low as possible.

The project is mainly focused on learning and applying concepts related to:

**Backend Engineering · Microservices · Event-Driven Systems · Cloud · DevOps**
