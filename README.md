# 🎟️ BkTicketing - Backend (Spring Boot)

Welcome to the **BkTicketing** backend! This project is powered by **Spring Boot** and **MongoDB**, providing a robust API for event ticket booking.

## 🚀 Features

- **RESTful API**: Efficient communication with the frontend.
- **User Authentication**: JWT-based authentication.
- **Event & Ticket Management**: CRUD operations for events and reservations.
- **Secure Payments**: **Stripe** integration for handling transactions.
- **Email Notifications**: **JavaMailSender** for automated emails.
- **Scalable Database**: **MongoDB Atlas** for cloud storage.

## 🛠️ Technologies Used

- **Spring Boot** - Backend framework ([Spring Docs](https://spring.io/projects/spring-boot))
- **MongoDB** - NoSQL database ([MongoDB Docs](https://www.mongodb.com/docs/))
- **Spring Data MongoDB** - ORM for MongoDB
- **JWT (JSON Web Token)** - Authentication ([JWT Docs](https://jwt.io/))
- **Stripe** - Payment gateway ([Stripe Docs](https://stripe.com/docs))
- **JavaMailSender** - Email service


## 📦 Installation & Setup

### Prerequisites
- Java 17+ ([Download](https://www.oracle.com/java/technologies/javase-downloads.html))
- MongoDB ([MongoDB Atlas](https://www.mongodb.com/cloud/atlas))
- Maven ([Download](https://maven.apache.org/download.cgi))

### Steps
1. Clone the repository:
   ```sh
   git clone https://github.com/yourusername/bkticketing-backend.git
   ```
2. Go to file
   ```sh
   cd bkticketing-backend
   ```
3. Configure application.properties for smtp, mongodb and image/file uploads
   ```sh
   server.port=8080
   spring.data.mongodb.uri=mongodb+srv://username:password@cluster.mongodb.net/dbname
   jwt.secret=your_jwt_secret
   stripe.apiKey=your_stripe_secret_key
   mail.host=smtp.gmail.com
   mail.username=your_email@gmail.com
   mail.password=your_email_password
   ```
4. Run the application
   ```sh
   mvn spring-boot:run
   ```
