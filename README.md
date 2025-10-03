This project is a demo application for managing public tenders. 
It allows an admin to: 
See the list of ongoing tenders and their associated lots. 
Create new tenders. 
Modify existing ones. 
Retrieve a specific tender or the whole list.
Delete tenders. 

Every tender has: 
A unique protocolId. 
A description. 
One or more lots (at least one is required, and a lot cannot exist without a tender). 
An amount automatically calculated as the sum of all its lots. 
A type: OPEN, NEGOTIATED, RESTRICTED. 
A threshold: OPEN → above threshold if amount > 75.000. NEGOTIATED → above threshold if amount > 100.000. 
A deadline (date until which applications can be submitted). 
A responsible (one responsible can be associated with multiple tenders). 
A set of vendors (only for RESTRICTED tenders).

Tech Stack: 
Java 21 
Spring Boot 3.x 
Hibernate / JPA 
H2 Database (in-memory) 
Maven (build tool)

Database: 
H2 in-memory DB is used (tables are created on startup and dropped on shutdown). 
Demo data is automatically loaded at startup from data.sql.

Getting Started: 
Prerequisites 
Java 21 
Maven 3.9+ 
Run the application mvn spring-boot:run 
The app will start at: http://localhost:8080 
H2 Console: http://localhost:8080/h2-console

API Endpoints: 
Method Endpoint Description 
GET /api/tenders Get all tenders 
GET /api/tenders/{id} Get a tender by protocolId 
POST /api/tenders Create a new tender 
PUT /api/tenders/{id} Update an existing tender 
DELETE /api/tenders/{id} Delete a tender

Status: 
Backend: implemented (with entities, services, DTOs, validation, repositories, mappers, controller). 
Frontend: in progress (Angular).
