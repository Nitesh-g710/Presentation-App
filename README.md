# Presentation App  

## Overview  
The **Presentation App** is a role-based management system that allows **admins** to assign presentations, update statuses, and rate student presentations. **Students** can fetch their assigned presentations, update statuses, and view ratings. The application follows **a structured response format, includes global exception handling, and ensures smooth API interactions.**  

## Features  

### User Management  
- **Register & Login** – Users can register and log in using their email and password.  
- **Role-Based Access** – Only **active users** can perform actions.  
- **Admin Controls** – Admins can view all users and update user status (active/inactive).  

### Presentation Management  
- **Assign Presentation** – Admins can assign presentations to students.  
- **Fetch Presentations** – Students can view assigned presentations.  
- **Update Status** – Students can update the status of their presentations.  
- **Scoring System** – Admins can rate and assign scores to presentations.  

### Rating System  
- **Rate Presentations** – Admins can rate presentations based on student and presentation ID.  
- **View Ratings** – Fetch ratings of individual or all presentations of a student.  

## Technologies Used  
- **Spring Boot** – Backend framework for building RESTful APIs.  
- **JPA & MySQL** – Database management for storing user, presentation, and rating data.  
- **Postman** – API testing and validation.  

## Project Highlights  
✔ **Implemented structured API responses** for consistency.  
✔ **Integrated global exception handling** to improve reliability.  
✔ **Designed a dynamic rating and scoring system** for student presentations.  

## Setup & Installation  
1. Clone the repository:  
   ```sh
   git clone <repository-url>
   cd presentation-app
   
2.Configure the MySQL database in application.properties.

3. Run the Spring Boot application:
  mvn spring-boot:run

4. Test API endpoints using Postman.
