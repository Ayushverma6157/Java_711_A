CREATE DATABASE IF NOT EXISTS banking_db;
USE banking_db;

CREATE TABLE IF NOT EXISTS Customer (
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS Account (
    account_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    account_type VARCHAR(30) NOT NULL,
    balance DOUBLE NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES Customer(customer_id)
);

CREATE TABLE IF NOT EXISTS Loan (
    loan_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    principal DOUBLE NOT NULL,
    annual_rate DOUBLE NOT NULL,
    months INT NOT NULL,
    emi DOUBLE NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES Customer(customer_id)
);

SELECT * FROM Customer;
SELECT * FROM Account;
SELECT * FROM Loan;
