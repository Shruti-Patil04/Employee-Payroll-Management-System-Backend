# ☕ Employee Payroll Management System - Backend

## 📌 Overview

The Backend of the Employee Payroll Management System is developed using Spring Boot and MySQL. It provides secure REST APIs for authentication, employee management, payroll processing, leave management, department management, and role-based access control.

---

## 🚀 Live Backend

Backend URL:

https://employee-management-system-backend-99hu.onrender.com

Swagger Documentation:

https://employee-management-system-backend-99hu.onrender.com/swagger-ui.html

---

## 🔑 Default Admin Credentials

The system automatically creates an Admin account during initialization.

| Username | Password | Role  |
| -------- | -------- | ----- |
| admin    | admin123 | ADMIN |

### Employee Registration

Employees can create their own account from the frontend Register page.

No admin intervention is required for employee registration.

---

## ✨ Features

### Authentication

* JWT Authentication
* Secure Login
* User Registration
* Role Based Authorization
* Spring Security Integration

### Employee Management

* Create Employee
* Update Employee
* Delete Employee
* View Employee Details
* Employee Profile Management

### Department Management

* Create Department
* Update Department
* Delete Department
* Department Employee Mapping

### Job Role Management

* Create Job Roles
* Assign Base Salary
* Update Salary Structure

### Leave Management

* Apply Leave
* Approve Leave
* Reject Leave
* Leave History
* Leave Tracking

### Payroll Management

* Generate Payroll
* Process Monthly Salaries
* Salary Calculations
* Employee Payroll History

---

## 🛠️ Technology Stack

### Backend

* Java 17
* Spring Boot 3
* Spring Security
* JWT
* Hibernate
* Spring Data JPA
* Lombok
* Maven

### Database

* MySQL

### Deployment

* Render

---

## 📂 Project Structure

src/main/java

├── controller

├── service

├── repository

├── entity

├── dto

├── config

├── security

├── exception

└── PayrollManagementSystemApplication.java

---

## ⚙️ Installation

### Clone Repository

git clone <backend-repository-url>

### Configure Database

application.properties

spring.datasource.url=jdbc:mysql://localhost:3306/payroll_management_system

spring.datasource.username=root

spring.datasource.password=your_password

### Run Application

mvn spring-boot:run

OR

Run PayrollManagementSystemApplication.java

---

## 🔐 Security

Authentication Type:

JWT (JSON Web Token)

Authorization:

* ADMIN
* EMPLOYEE

Protected APIs require a valid JWT token.

---

## 📊 API Endpoints

### Authentication

POST /api/v1/auth/login

POST /api/v1/auth/register

POST /api/v1/auth/register/admin

GET /api/v1/auth/me

POST /api/v1/auth/refresh

---

### Employees

GET /api/v1/employees

POST /api/v1/employees

PUT /api/v1/employees/{id}

DELETE /api/v1/employees/{id}

GET /api/v1/employees/{id}

---

### Departments

GET /api/v1/departments

POST /api/v1/departments

PUT /api/v1/departments/{id}

DELETE /api/v1/departments/{id}

---

### Job Roles

GET /api/v1/jobs

POST /api/v1/jobs

PUT /api/v1/jobs/{id}

DELETE /api/v1/jobs/{id}

---

### Leave Management

GET /api/v1/leaves

POST /api/v1/leaves

PATCH /api/v1/leaves/{id}/status

DELETE /api/v1/leaves/{id}

---

### Payroll

GET /api/v1/payroll

POST /api/v1/payroll

PATCH /api/v1/payroll/{id}/process

DELETE /api/v1/payroll/{id}

---

## 🌐 Deployment

Backend Hosted On Render

Production URL:

https://employee-management-system-backend-99hu.onrender.com

---

## 🧪 Testing

### API Testing

* Swagger UI
* Postman

### Unit Testing

* JUnit
* Spring Boot Test

---

## 📈 Future Enhancements

* Email Notifications
* PDF Payslip Generation
* Attendance Management
* Docker Support
* AWS Deployment
* Razorpay Integration

---

## 👩‍💻 Developed By

Shruti Patil

BE Computer Science

KLE College of Engineering and Technology, Chikodi

CGPA: 9.2+

Skills:

Java | Spring Boot | React.js | MySQL | REST APIs | JWT Authentication
