# 💰 Finance Dashboard Backend

## 📌 Overview

This project is a backend system for a **Finance Dashboard Application**. It allows users to manage financial records, enforce role-based access control, and view aggregated financial insights through dashboard APIs.

The system is designed with a focus on **clean architecture, proper data flow, and maintainable backend logic**.

---

## 🚀 Features

### 👤 User Management

* Create, update, delete, and retrieve users
* Assign roles (ADMIN, ANALYST, VIEWER)
* Manage user status (ACTIVE / INACTIVE)

---

### 🔐 Role-Based Access Control

* **ADMIN**

  * Full access (manage users and records)
* **ANALYST**

  * View financial records and dashboard
* **VIEWER**

  * View dashboard only

Access control is enforced at the **service layer**.

---

### 💰 Financial Records Management

* Create, update, delete financial records
* Fields include:

  * Amount
  * Type (INCOME / EXPENSE)
  * Category
  * Date
  * Notes

---

### 📊 Dashboard Summary

Provides aggregated financial insights for the **current user**:

* Total Income
* Total Expense
* Net Balance
* Category-wise totals

Dashboard data is **computed dynamically** from financial records (not stored in DB).

---

## 🧱 Tech Stack

* Java
* Spring Boot
* Spring Data JPA (Hibernate)
* MySQL / (or your DB, update if needed)
* Maven

---

## 🏗️ Project Structure

<img width="829" height="947" alt="image" src="https://github.com/user-attachments/assets/b9ebdff4-08a8-474f-9582-b758709362c9" />

```
com.financeapp
│
├── controller       → API endpoints
├── service          → Business logic & role validation
├── repository       → Database access
├── model            → Entity classes (User, FinancialRecord)
├── dto              → Request & Response objects
├── exception        → Global error handling
└── config           → (Optional configurations)
```

---

## 🔄 API Endpoints

### 👤 Users

* `POST /users` → Create user
* `GET /users/{id}` → Get specific user
* `PUT /users/{id}` → Update user

---

### 👤 Admin


* `GET /users` → Get all users
* `PUT /users/{targetUserId}/user-role` → Change user role
* `PUT /users/{targetUserId}/user-status`→ Change user status
* `DELETE /users/{targetUserId}/admin` → Delete user 

---

### 💰 Records

* `POST /records` → Create record
* `GET /records` → Get all records
* `GET /record/user/{userId}` → Get all records of user
* `PUT /records/{id}` → Update record
* `DELETE /records/{id}` → Delete record

---

### 📊 Dashboard

* `GET /dashboard/users/{userId}` → Get financial summary of user

---

## 🔐 Access Control Design

* Role validation is handled in the **service layer**
* Each request includes a **userId** to identify the requester
* Permissions are checked before performing any operation

---

## ⚠️ Error Handling

* Centralized using a **Global Exception Handler**
* Handles:

  * Invalid input
  * Unauthorized access
  * Resource not found
* Returns appropriate HTTP status codes

---

## 🧠 Assumptions

* Authentication (JWT/session) is not implemented
* User identity is passed via `userId` in requests
* Dashboard is **user-specific** (each user sees their own data)
* No advanced multi-user data sharing implemented

---

## ▶️ How to Run

1. Clone the repository
2. Open in IDE (IntelliJ recommended)
3. Configure database in `application.properties`
4. Run the Spring Boot application
5. Test APIs using Postman

---

## 🧪 Testing

Basic structure for tests is included. (Optional tests can be added for service and controller layers.)

---

## 📌 Future Improvements

* JWT-based authentication
* Pagination & search
* Advanced analytics (monthly trends, charts)
* Deployment on cloud

---

## 📬 Submission Notes

This project is built as part of a backend assignment to demonstrate:

* Backend architecture design
* Clean code practices
* Business logic implementation
* Role-based access control

---

## 👨‍💻 Author

Aditya Santosh Gupta

---
