
# SmartDesk – Employee Task & Attendance Manager

**Java | JDBC | MySQL | Swing UI**

SmartDesk is a Java-based employee management system designed to handle **role-based authentication**, **task assignment**, and **daily attendance tracking** using a **layered architecture (DAO–Service)** with **MySQL database integration**.

This project demonstrates core backend development concepts and basic desktop UI integration using **Java Swing**.

---

## 🚀 Features

### 🔐 Authentication

* Secure login system using JDBC
* Role-based access:

  * **ADMIN**
  * **EMPLOYEE**

### 🧑‍💼 Admin Module

* Assign tasks to employees
* Set task priority (HIGH / MEDIUM / LOW)
* View task assignment confirmation

### 👨‍💻 Employee Module

* View assigned tasks
* Sort tasks by priority
* Filter tasks by status (PENDING / DONE)
* Update task status
* Mark daily attendance (PRESENT / ABSENT)
* View attendance history

### 🖥 Swing UI

* Basic Swing-based interface for login and interactions
* Console support retained for clarity and testing

### 🛠 Logging

* File-based logging using `LoggerUtil`
* Logs:

  * Login attempts
  * Task assignment actions

---

## 🏗 Project Architecture

```
smartdesk/
│
├── config/        → Database connection
├── dao/           → Data Access Objects (JDBC logic)
├── service/       → Business logic
├── model/         → Entity classes
├── exception/     → Custom exceptions
├── util/          → Logger & helper utilities
├── ui/            → Swing UI components
└── Main.java      → Application entry point
```

---

## 🗄 Database Schema

### Database

```sql
CREATE DATABASE smartdesk;
USE smartdesk;
```

### Employee Table

```sql
CREATE TABLE employee (
  emp_id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50),
  email VARCHAR(50),
  role VARCHAR(20),
  username VARCHAR(30),
  password VARCHAR(50)
);
```

### Task Table

```sql
CREATE TABLE task (
  task_id INT PRIMARY KEY AUTO_INCREMENT,
  emp_id INT,
  title VARCHAR(100),
  priority VARCHAR(10),
  status VARCHAR(20),
  FOREIGN KEY (emp_id) REFERENCES employee(emp_id)
);
```

### Attendance Table

```sql
CREATE TABLE attendance (
  att_id INT PRIMARY KEY AUTO_INCREMENT,
  emp_id INT,
  date DATE,
  status VARCHAR(10),
  FOREIGN KEY (emp_id) REFERENCES employee(emp_id)
);
```

---

## ⚙️ Tech Stack

* **Java (JDK 22)**
* **JDBC**
* **MySQL 8**
* **Swing (UI)**
* **IntelliJ IDEA**
* **Git & GitHub**

---

## ▶️ How to Run

1. Clone the repository
2. Import project into IntelliJ IDEA
3. Add MySQL Connector JAR to project libraries
4. Update DB credentials in `DBConnection.java`
5. Run `Main.java`

---

## 📌 Sample Admin Credentials

```
Username: admin
Password: admin123
```

---

## 🎯 Learning Outcomes

* JDBC database connectivity
* Layered architecture (DAO–Service)
* Role-based access control
* Java Collections & Streams
* Exception handling
* Logging & debugging
* Swing UI basics

---

## 📎 Author

**Shambhavi Gupta**
Backend Developer (Java)
