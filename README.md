# Flight Booking App - Setup & Run Instructions

## Prerequisites
1. **Java 17+**: You have Java 24 installed.
2. **Maven**: You need to install Maven or have it in your PATH.
3. **MySQL**: Ensure MySQL is running and accessible.

## 1. Database Setup
1. Open your MySQL Client (Workbench, Command Line, etc.).
2. Run the script `database_setup.sql` found in the project root.
   - It creates `flightdb` database.
   - It creates necessary tables.
   - **Note**: The application is configured to use `root` / `root` as credentials. If yours are different, update `src/main/resources/application.properties`.

## 2. Build and Run
Open a terminal in the project folder: `d:\Projects\For resume\Fast API microservices\FlightBookingApp`

### If you have Maven installed:
```sh
mvn clean install
mvn spring-boot:run
```

### Access the App
Go to: [http://localhost:8080](http://localhost:8080)

## 3. Features
- **Search Flights**: Enter Source (e.g., "Delhi"), Destination (e.g., "Mumbai"), and Date.
- **Login**: 
  - **Admin**: `admin` / `admin123`
  - **User**: Register or use sample data if added.
- **Admin Dashboard**: Login as admin to add flights and view bookings.

## 4. Default Data
On first run, the app will attempt to create an Admin user and some sample flights if the database is empty.
