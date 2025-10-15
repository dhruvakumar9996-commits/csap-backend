CSAP - Customer Support Automation Platform
===========================================

CSAP (Customer Support Automation Platform) is a company-level Java Spring Boot backend project designed to manage customer support tickets and automatically categorize them into topics using NLP (Natural Language Processing).
It demonstrates modular architecture, event-driven design, and integration with containerized infrastructure.

------------------------------------------------------------
FEATURES
------------------------------------------------------------
- Create and manage support tickets
- Auto-topic detection using NLP (mocked service in this project)
- View tickets and their topics
- Modular codebase with layered architecture (Controller → Service → Repository)
- In-memory storage (can be replaced with a real database)
- Dockerized setup for deployment
- REST API with JSON responses

------------------------------------------------------------
TECH STACK
------------------------------------------------------------
```
Backend: Java 17, Spring Boot
Build Tool: Maven
API Framework: Spring Web
Data Layer: Spring Data JPA (with in-memory store for now)
NLP: Custom Mock NLP Service
Containerization: Docker, Docker Compose
Config: YAML-based configuration
```
------------------------------------------------------------
PROJECT STRUCTURE
------------------------------------------------------------
```
csap-backend/
├─ pom.xml
├─ src/main/java/com/csap/
│  ├─ CsapApplication.java
│  ├─ controller/
│  │  ├─ TicketController.java
│  │  └─ TopicController.java
│  ├─ dto/
│  │  ├─ CreateTicketRequest.java
│  │  └─ TicketResponse.java
│  ├─ model/
│  │  ├─ Ticket.java
│  │  └─ Topic.java
│  ├─ repository/
│  │  ├─ TicketRepository.java
│  │  └─ TopicRepository.java
│  ├─ service/
│  │  ├─ TicketService.java
│  │  ├─ NlpService.java
│  │  ├─ EventPublisher.java
│  │  └─ InMemoryEventPublisher.java
│  └─ util/
│     └─ TextUtils.java
├─ src/main/resources/
│  └─ application.yml
├─ docker-compose.yml
└─ README.md
```
------------------------------------------------------------
SETUP & RUN
------------------------------------------------------------

1. Clone the Repository
```
   git clone https://github.com/yourusername/csap-backend.git
   cd csap-backend
```
2. Build the Project
```  
   mvn clean install
```
3. Run the Application
```   
   mvn spring-boot:run
```

------------------------------------------------------------
API ENDPOINTS
------------------------------------------------------------
```
TICKET MANAGEMENT:
POST   /api/tickets        -> Create a new ticket
GET    /api/tickets        -> Get all tickets
GET    /api/tickets/{id}   -> Get ticket by ID

TOPIC MANAGEMENT:
GET    /api/topics         -> Get all topics
GET    /api/topics/{id}    -> Get topic by ID
```
------------------------------------------------------------
SAMPLE REQUEST
------------------------------------------------------------
```
POST /api/tickets
Request Body:
{
  "title": "Payment not processed",
  "description": "I made a payment yesterday but it still shows pending."
}

Response:
{
  "id": 1,
  "title": "Payment not processed",
  "description": "I made a payment yesterday but it still shows pending.",
  "topic": "Billing Issue"
}
```

------------------------------------------------------------
NLP FLOW (SIMPLIFIED)
------------------------------------------------------------
1. A ticket is created via API.
2. NlpService analyzes text (mocked using keyword detection).
3. Topic is determined and stored.
4. EventPublisher logs and broadcasts creation events.

------------------------------------------------------------
FUTURE ENHANCEMENTS
------------------------------------------------------------
- Integrate real NLP models using Python microservice or HuggingFace API
- Add database persistence (PostgreSQL)
- Implement JWT authentication
- Build frontend (React or Angular)
- Introduce Kafka for event-driven processing

------------------------------------------------------------
AUTHOR
------------------------------------------------------------
```
Dhruva
Java Developer — Tech Consultancy Project (Feb 2025 - Dec 2025)
Email: youremail@example.com
LinkedIn: https://linkedin.com/in/yourprofile
```
------------------------------------------------------------
LICENSE
------------------------------------------------------------
This project is licensed under the MIT License.
You are free to modify and use it for demonstration or production purposes.
