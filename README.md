# Smart Contact Manager

A full-featured web application for managing personal and professional contacts, built with **Spring Boot** and **Java**.

---

## 🚀 Overview

Smart Contact Manager allows users to register, log in, and securely manage their contacts. It supports role-based access (Admin/User), CRUD operations for contacts, profile management, and secure authentication.

---

## ✨ Features

- **User Registration & Login:** Secure authentication with Spring Security.
- **Role-Based Access:** Separate dashboards for Admin and User.
- **Contact Management:** Add, edit, delete, and view contacts.
- **Profile Management:** Update user profile and profile picture.
- **Password Security:** Passwords are hashed using BCrypt.
- **Responsive UI:** Built with Thymeleaf templates.
- **Image Uploads:** Store and display contact images.
- **Pagination:** Efficiently browse large contact lists.
- **Error Handling:** User-friendly messages and alerts.

---

## 🛠️ Tech Stack

- **Java 17+**
- **Spring Boot**
- **Spring Security**
- **Spring Data JPA (Hibernate)**
- **Thymeleaf**
- **MySQL** (or H2 for development)
- **Maven**

---

## 📦 Folder Structure

```text
smartcontact/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/smart/smartcontact/
│   │   │       ├── SmartcontactApplication.java
│   │   │       ├── config/
│   │   │       ├── controller/
│   │   │       ├── dao/
│   │   │       ├── entity/
│   │   │       └── helper/
│   │   └── resources/
│   │       ├── static/
│   │       │   ├── css/
│   │       │   ├── img/
│   │       │   └── javascript/
│   │       ├── templates/
│   │       │   ├── about.html
│   │       │   ├── base.html
│   │       │   ├── home.html
│   │       │   ├── login.html
│   │       │   ├── signup.html
│   │       │   ├── admin/
│   │       │   └── normal/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── com/smart/smartcontact/
├── pom.xml
└── README.md
```

---

## ⚡ Getting Started

### Prerequisites

- Java 17 or higher
- Maven
- MySQL (or use H2 for testing)

### Clone the Repository

```bash
git clone https://github.com/Goluksharma/contact_manager_web_app.git
cd smartcontact
```

### Configure Database

Edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/contact_manager
spring.datasource.username=YOUR_DB_USERNAME
spring.datasource.password=YOUR_DB_PASSWORD
spring.jpa.hibernate.ddl-auto=update
```

### Build and Run

```bash
./mvnw spring-boot:run
```

Or with Maven:

```bash
mvn spring-boot:run
```

Access the app at [http://localhost:8080](http://localhost:8080)

---

## 🧑‍💻 Usage

- Register a new user via `/signup`
- Log in via `/signin`
- Manage contacts from the user dashboard
- Admin users can access `/admin/**` routes

---

## 📝 Contributing

Contributions are welcome! Please fork the repo and submit a pull request.

---

## 📄 License

This project is licensed under the Educational Purpose.

---

## 🏷️ Main Language

```java
// Example main class for language detection
public class SmartcontactApplication {
    public static void main(String[] args) {
        // Spring Boot entry point
    }
}
```

---

## 🙋‍♂️ Author

- [Golu k Sharma](https://www.linkedin.com/in/goluksharma/)
