# Microservices-based-Hotel-Rating-System
Designed and deployed a cloud-ready microservices system with Eureka discovery, OKTA-secured API Gateway, Resilience4j Circuit Breakers, polyglot persistence (MySQL/PostgreSQL/MongoDB), Dockerized deployments, and performance tuning with JMeter.
# Overview
This project demonstrates a complete Microservices Architecture designed to manage hotels,users,and their ratings.
Each microservice runs independently with its own database (Polyglot Persistence), and all services register dynamically using Netflix Eureka Service Discovery.
# Architecture
The system consists of the following microservices:
# User Service
  Built with Spring Boot & MySQL
  Manages user data such as profile, userId, email, etc.
  Communicates with Rating Service & Hotel Service via REST APIs.
# Rating Service
  Built with Spring Boot & MongoDB (Mongo Shell)
  Stores hotel ratings and user feedback.
  Exposes APIs for rating creation, fetching, and user/hotel-based queries.
# Hotel Service
  Built with Spring Boot & PostgreSQL (Docker Container)
  Manages hotel info (hotelId, location, description, etc.)
  Uses Dockerized PostgreSQL for easy deployment.
# Service Registry (Eureka Server)
  Acts as a central Service Registry
  Each microservice registers itself on startup
  Supports dynamic instance registration and discovery-based communication

# Tech Stack
# Backend & Microservices:
Spring Boot, Java
RESTful APIs
Netflix Eureka (Service Registry)
AWS API Gateway
Resilience4j (Circuit Breaker)
Spring Security, OKTA Authentication
Databases (Polyglot Persistence):
MySQL (User Service)
PostgreSQL + Docker (Hotel Service)
MongoDB Shell (Rating Service)

# Tools & Others:
JMeter (Load Testing, Rate Limiting)
Docker
Maven
Postman
# Security & API Gateway
# AWS API Gateway
  Acts as the single entry point for all microservices
  Provides centralized routing & monitoring
# OKTA Authentication
  Integrated at the API gateway layer
  Ensures OAuth2-based secure API access
# Spring Security
  Applied at the service level for authentication & authorization

# Resilience & Fault Tolerance
  Resilience4j - Circuit Breaker Pattern
  Prevents cascading failures across services
  Ensures graceful fallback responses
  Improves service reliability
# Load Testing & Rate Limiting
# JMeter
  Performed high-traffic load simulation
  Configured rate limiting to protect microservices
  Validated system stability & response time
# Inter-Service Communication
  Uses REST APIs and logical routing via service names
  Managed by Eureka + API Gateway
  Contains proper exception handling and retry logic
# Project Structure
  /UserService
  /HotelService
  /RatingService
  /ServiceRegistry
# How to Run the Project
# Start Eureka Server
  cd ServiceRegistry
  mvn spring-boot:run
# Start Each Microservice
  cd UserService
  mvn spring-boot:run
  cd HotelService
  mvn spring-boot:run
  cd RatingService
  mvn spring-boot:run
# Start PostgreSQL via Docker
  docker run --name hotel-postgres -e POSTGRES_PASSWORD=root -p 5432:5432 -d postgres
# Access API Gateway (AWS)
  All microservices are routed through AWS API Gateway with OKTA authentication.
# Conclusion
  This project demonstrates:
  Modern microservices architecture
  Scalable polyglot persistence
  Cloud-native patterns
  Secure, resilient API-driven communication
  Real-world microservices deployment practices





