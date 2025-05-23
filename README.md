# 📝 TODO App

A full-stack TODO application built with Java, Spring Boot, and Vanilla JS. Manage tasks with categories, deadlines, and a slick UI.

---

## 📦 Tech Stack

**Backend**
- Java 17
- Spring Boot
- PostgreSQL
- Spring Data JPA
- REST API

**Frontend**
- HTML5
- CSS3
- JavaScript (Vanilla)

---

## 🛠️ Features

- ✅ Create, view, and delete TODO cards
- 🗂️ Task categories: Divertimento, Lavoro, Studio
- 📅 Track deadlines and completion dates
- 🖱️ Hover and scroll effects
- 🧠 Clean and smooth UX without frameworks

---

## 🧰 Setup Instructions

### Prerequisites

- Java 17+
- Maven
- PostgreSQL

### 1. Database Setup

Create a PostgreSQL database:

```sql
CREATE DATABASE tododb;
````

Ensure categories exist:

```sql
INSERT INTO category(id, name) VALUES
(1, 'Divertimento'),
(2, 'Lavoro'),
(3, 'Studio');
```

### 2. Configure `application.properties`

Set your DB credentials in:

```
src/main/resources/application.properties
```

### 3. Build and Run

```bash
mvn clean install
mvn spring-boot:run
```

App runs on: `http://localhost:8080`

---

## 🧪 API Endpoints

| Method | Endpoint         | Description        |
| ------ | ---------------- | ------------------ |
| GET    | `/api/card`      | Get all TODO cards |
| POST   | `/api/card`      | Create a new card  |
| DELETE | `/api/card/{id}` | Delete card by ID  |

### Example JSON Payload

```json
{
  "title": "Fix README",
  "description": "Make it awesome",
  "creationDate": "2025-05-22",
  "deadline": "2025-05-30",
  "completedDate": null,
  "state": false,
  "categoryId": 2
}
```

---

## 🧑‍💻 Author

Built by [Zomerzz](https://github.com/Zomerzz)
Powered by caffeine, chaos, and cursed CSS selectors.

---

> “TODO or not TODO, that is the question.”
> — You, probably 2AM

FYI: readme generated by AI (couldn't be bothered)
