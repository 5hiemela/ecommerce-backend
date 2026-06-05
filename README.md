# E-Commerce Backend API

A REST API built with Java Spring Boot and MySQL that handles the core business logic, database management, and data routing for a full-stack e-commerce store.

This application acts as the backend server, securely managing data and exposing clean communication points (endpoints) for a frontend user interface to connect to.

---

## Tech Stack

* **Language:** Java 17+
* **Framework:** Spring Boot 3.x
    * **Spring Web:** For handling web routing and creating REST API endpoints.
    * **Spring Data JPA:** For connecting Java to the database without raw SQL.
* **Database:** MySQL Server
* **Build Tool:** Maven (Manages dependencies and project compilation)

---

## System Architecture

This backend implements a clean **Layered (N-Tier) Architecture** to isolate logic, enforce data integrity, and handle cross-origin traffic from a React client application.

* 1. **Frontend Interface (React JS):** The user interacts with the screen, and the frontend sends a web request over the network carrying data formatted as JSON.
* 2. **Controller Layer (The Gates):** This is the entry point of the backend. It listens for incoming web requests, verifies that the incoming data is valid, and decides which internal Java service should handle it.
* 3. **Service Layer (The Brains):** This is where the actual business rules of the store live. It handles tasks like calculating prices, checking if an item is still in stock, and organizing data before it gets saved.
* 4. **Repository Layer (The Translator):** Powered by Spring Data JPA and Hibernate. Instead of writing raw, complex SQL strings inside the Java code, this layer automatically translates standard Java objects directly into database tables and actions.
* 5. **Database Tier (MySQL):** The final storage destination. It securely saves products, user accounts, and customer orders in organized relational tables.

---

## API Reference (Planned Endpoints)

Once the backend logic is complete, the application will expose these core web URLs for the React frontend to communicate with:

| HTTP Method | URL Endpoint | What It Does | Data Expected (Payload) |
| :--- | :--- | :--- | :--- |
| GET | /api/products | Retrieves a list of all products for the store catalog | None |
| GET | /api/products/{id} | Retrieves details for one specific product | None |
| POST | /api/products | Adds a brand new product to the database | Product Details (JSON) |
| POST | /api/orders | Processes a customer checkout and saves the order | Order Details (JSON) |

---

## Project Roadmap

- [x] Initial Spring Boot repository setup
- [x] Secured database credentials using environment variables
- [x] Established successful connection to local MySQL server
- [ ] Create core Java Models (Product, Category, User, Order)
- [ ] Build Repository interfaces for database communication
- [ ] Implement Service classes to handle store logic
- [ ] Build Controller endpoints to expose data via URLs
- [ ] Enable CORS to safely connect the React frontend
- [ ] Add input validation and clear error handling