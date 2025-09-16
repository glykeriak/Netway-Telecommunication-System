# Netway-Telecommunication-System

#Overview

This project is a web application for managing customers, sellers, and mobile service packages.
It supports multiple user roles with different permissions (Client, Seller, Administrator) and provides functionality such as:

Issuing and paying bills

Managing customers and sellers

Managing service packages

Viewing call history

The application is developed using Java Servlets with a MySQL database, HTML/CSS frontend, and deployed on Apache Tomcat.

System Architecture

The overall system architecture is divided into four main parts:

Frontend (HTML/CSS/JS)

Provides the user interface for all three roles (Client, Seller, Admin).

Includes pages like index.html, AdminPage.html, ClientPage.html, SellerPage.html.

Backend (Java Servlets)

Implements logic for login/logout, session management, and CRUD operations on customers, sellers, packages, and bills.

Servlets include: LoginServlet, AddPacketServlet, DeleteSellerServlet, PayBillServlet, InvoiceServlet.

Database Manager

Handles all SQL operations towards the database.

Supports secure login with salted+hashed passwords.

Database (MySQL)

Tables: User, Client, Seller, Admin, Packet, Bill, Program, PhoneNumber, Call.

Roles & Functionality
Client

View bills

Pay bills

View call history

View available programs

Seller

Register new clients

Link client to a program

Issue bills

View clients

Delete subscriptions

Administrator

Create new sellers

Create new service packages

Search/update packages

Delete sellers

View all clients and sellers

Security

Passwords stored with Salted + Hashed (SHA-256).

Session management for user tracking.

Session invalidation & cookie deletion upon logout.

Deployment

Create a MySQL database using the project’s schema.

Configure DatabaseManager.java with database connection details.

Install and run Apache Tomcat 9+.

Deploy the .war file to Tomcat.

Access the application via browser:

http://localhost:8080/<project-name>

Examples
Example: Client

Logs in via ClientPage.html

Views pending bills

Selects "Pay" → triggers PayBillServlet → database updated

Example: Administrator

Logs in via AdminPage.html

Creates a new package via AddPacketServlet

Views all available packages on DisplayPackets.html
