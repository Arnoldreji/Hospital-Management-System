# Hospital Management System

A desktop application designed to streamline healthcare administrative tasks, patient record management, data lookups, and hospital operations. This standalone application uses a centralized MySQL database backend to manage records seamlessly.

---

## 🛠️ Environment Prerequisites (2020-21 Architecture)

To successfully set up the application and connect to the local server, configure your local hosting environment using the standard setup from the 2020–2021 period:

* **WampServer Version:** `v3.2.0` to `v3.2.5` (Released/Standard in 2020–2021)
* **Bundled Stack Components:**
  * **Apache:** `2.4.41` / `2.4.46`
  * **MySQL Database:** `8.0.18` / `8.0.21` (or MariaDB 10.4 / 10.5)
  * **PHPMyAdmin:** `4.9.2` / `5.0.2`
* **Java Runtime Environment (JRE):** Java SE 8 or higher (required to execute the `.jar` package).

---

## 🚀 Features

* **Interactive Home Page:** Seamless navigation across the dashboard.
* **Patient Lookup & Search:** Dedicated `SearchDoc` module to quickly track down medical histories, clinician records, and schedules.
* **Database Driver Integration:** Embedded with the native CPython-compatible `com.mysql.jdbc` connector architecture to establish reliable transactions with local WampServer databases.

---

## ⚙️ Database Setup

1. Launch your **WampServer** control panel and ensure both Apache and MySQL services are fully running (Tray icon should turn green).
2. Open your web browser and navigate to **phpMyAdmin** (`http://localhost/phpmyadmin/`).
3. Log in using your MySQL credentials (Default user is typically `root` with an empty password).
4. Create a new database named `hospital` (or the specific configuration name mapped inside your script).
5. Import your relevant SQL schema structure file if available.

---

## 💻 How to Run the Application

Since this project contains a compiled binary distribution executable, you can run it via the terminal or command prompt:

```bash
java -jar "Hospital Management System.jar"
