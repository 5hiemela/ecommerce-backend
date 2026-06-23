# Workspace Essentials - Backend

The REST API built with Java Spring Boot and MySQL that handles the business logic, database management, and data routing for a full-stack tech store application.

This application acts as the backend server, securely managing data and exposing clean communication points (endpoints) for the frontend user interface to connect to.

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

## API Endpoints Reference

Once the backend logic is complete, the application will expose these core web URLs for the React frontend to communicate with:

| HTTP Method | URL Endpoint | What It Does | Data Expected (Payload) |
| :--- | :--- | :--- | :--- |
| **Products** | | | |
| GET | `/api/products` | Retrieves all store catalog products | None |
| GET | `/api/products/{id}` | Retrieves details for one specific product | None |
| POST | `/api/products` | Adds a brand new product to the database | Product Details (JSON) |
| PUT / DELETE | `/api/products/{id}` | Updates or permanently deletes a product | Product / None |
| **Categories** | | | |
| GET | `/api/categories` | Retrieves all available product categories | None |
| POST / PUT / DEL| `/api/categories` | Manages store categories (Create/Update/Delete) | Category Details / None |
| **Shopping Cart**| | | |
| GET | `/api/cart` | Retrieves active items in a user's cart | Query Param: `userId` |
| POST | `/api/cart/add` | Adds item to cart / manages stock checks | Query Params: `userId`, `productId`, `quantity` |
| DELETE | `/api/cart/items/{id}`| Removes an individual line item from the cart | None |
| **Users & Orders**| | | |
| POST | `/api/users/register`| Registers a brand-new user profile | User Details (JSON) |
| POST | `/api/orders` | Processes customer checkout and saves order | Order Details (JSON) |
---

## Project Roadmap

- [x] Initial Spring Boot repository setup
- [x] Secured database credentials using environment variables
- [x] Established successful connection to local MySQL server
- [X] Create core Java Models (Product, Category, User, Order)
- [X] Build Repository interfaces for database communication
- [X] Implement Service classes to handle store logic
- [X] Build Controller endpoints to expose data via URLs
- [X] Enable CORS to safely connect the React frontend
- [X] Add input validation and clear error handling

---

## How to Run

### Prerequisites
* Java Development Kit (JDK) 17 or higher
* MySQL Server

### Setup Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/5hiemela/workspace-essentials-backend
   ```

2. Configure the database:
   Open `src/main/resources/application.properties` and update the datasource configuration with your local MySQL credentials:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

3. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```