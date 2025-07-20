# MVC Paradise Express System

A Java MVC application for sales and inventory management at Paradise Express.

## Entities and Fields

- **Client**: cedula_c, first_name, last_name, phone, address, city, email  
- **Vendor**: cedula_v, first_name, last_name, phone, city  
- **Invoice**: invoice_number, RUC, client_cedula, issue_date, total_amount, payment_method (Cash, Transfer)  
- **Product**: product_code, product_name, category (Detergent, Bleach, Disinfectant, Fabric Softener, Air Freshener), brand, unit_price, stock_quantity, expiration_date  
- **User**: cedula_u, first_name, last_name, phone, city, password, role  

## Features

- Manage clients, vendors, users, products, and invoices  
- Perform CRUD operations with validation  
- Data persistence using CSV files  
- Role-based user access control  
- User-friendly Java Swing graphical interface  

## Installation

1. Clone the repository:  
   ```bash
   git clone https://github.com/Kevin9430-art/MVC-ParadiseExpress-System.git