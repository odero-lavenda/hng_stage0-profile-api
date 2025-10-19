# HNG- Stage 0: Dynamic Profile API (Java/Spring Boot)

A simple RESTful API built with **Spring Boot** that returns your profile information along with a **dynamic cat fact** fetched from an external API.

---

## 🚀 Features

- `GET /me` → Returns profile info + random cat fact  
- `GET /health` → Health check endpoint  
- Dynamic **UTC timestamp (ISO 8601 format)**  
- Integrates with [CatFact Ninja API](https://catfact.ninja/fact)  
- Handles API errors gracefully

---

## ⚙️ Requirements

- Java 17+
- Maven 3.8+
- Internet connection (for fetching cat facts)

---

## 🧰 Setup & Run

1. **Clone the repository**
   ```bash
   git clone <your-repo-url>
   cd stagezero
Run the app

bash
Copy code
mvn spring-boot:run
Access in your browser

bash
Copy code
http://localhost:8080/me
📡 Example Response
json
Copy code
{
  "status": "success",
  "user": {
    "email": "example@mail.com",
    "name": "John Doe",
    "stack": "Java/Spring Boot"
  },
  "timestamp": "2025-10-18T19:28:07.517Z",
  "fact": "Cats sleep for 70% of their lives."
}
🧱 Tech Stack
Java 17

Spring Boot 3

Lombok

RestTemplate

Maven

👤 Author
John Doe
📧 example@mail.com
💻 Backend (Java/Spring Boot)


---







