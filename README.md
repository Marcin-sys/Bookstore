# Online Bookstore 📖
Project Description
This project is an online bookstore web application that allows users to browse,
add, and purchase books. The application was built using the Spring Framework 
and Hibernate, with frontend and backend written in Java. 
The project uses REST API for communication between the frontend and backend.

## Features  👍

- Browsing book catalog  
- Adding new books to the catalog  
- Purchasing books  
- Managing shopping cart  
- Admin panel for managing books and orders  

## Technologies  🚀

- Java  
- Spring Framework  
- Hibernate  
- REST API  
- HTML/CSS/JavaScript (Frontend)  
- JUnit and Mockito (Testing)  
- Maven (Dependency  Management)  

## Project Structure 📽️
bookstore-online/  
│  
├── src/  
│   ├── main/  
│   │   ├── java/  
│   │   │   └── com/example/bookstore/  
│   │   │       ├── config/  
│   │   │       ├── controller/  
│   │   │       ├── dao/  
│   │   │       ├── model/  
│   │   │       ├── repository/  
│   │   │       ├── service/  
│   │   │       └── BookstoreApplication.java  
│   │   │  
│   │   ├── resources/  
│   │   └── static/  
│   │       ├── WEB-JS/  
│   │       └── WEB-CSS/  
│   │   └── templates/  
│   │       └── WEB-HTML/  
│   │  
│   └── test/  
│       └── java/  
│           └── com/example/bookstore/  
│  
├── pom.xml  
└── README.md  


Migration from JDBC to Hibernate
The project initially used JDBC for database communication, 
but was migrated to Hibernate for better performance and
easier entity management. Old DAO classes were replaced 
with Hibernate repositories.
