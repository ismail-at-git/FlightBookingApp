-- Create Database
CREATE DATABASE IF NOT EXISTS flightdb;
USE flightdb;

-- Users Table
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
);

-- Flights Table
CREATE TABLE IF NOT EXISTS flights (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    airline VARCHAR(100) NOT NULL,
    source VARCHAR(100) NOT NULL,
    destination VARCHAR(100) NOT NULL,
    flight_date DATE NOT NULL,
    flight_time TIME NOT NULL,
    total_seats INT NOT NULL,
    available_seats INT NOT NULL,
    price DECIMAL(10,2) NOT NULL
);

-- Bookings Table
CREATE TABLE IF NOT EXISTS bookings (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    flight_id BIGINT NOT NULL,
    passenger_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    seat_number INT NOT NULL,
    booking_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) DEFAULT 'CONFIRMED',
    FOREIGN KEY (flight_id) REFERENCES flights(id)
);

-- Insert Default Admin (Password: admin123 - bcrypt encoded)
-- Note: You might need to generate the Bcrypt password using the app or an online tool.
-- For now, we will insert it via the application startup or manually.
-- INSERT INTO users (username, password, role) VALUES ('admin', '$2a$10$D8...hashedpassword...', 'ADMIN');
