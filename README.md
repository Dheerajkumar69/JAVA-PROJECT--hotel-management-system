# Hotel Management System

A modern Java-based Hotel Management System with an intuitive user interface built using Java Swing.

## Features

### Staff Dashboard
- **Room Management**: Add, edit, and delete rooms with detailed information
- **Room Allocation**: Assign rooms to customers and create bookings
- **Bookings**: View and manage all bookings with check-in/check-out functionality
- **Customer Management**: Add, edit, and view customer information
- **Reports**: Access to various operational reports with statistics

### Customer Dashboard
- **Browse Rooms**: Search and view available rooms with filtering options
- **Booking Management**: Make new bookings and manage existing reservations
- **My Profile**: Update personal information

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher

### Running the Application
1. Compile the Java files:
   ```
   javac -d . src/Main.java src/SimpleHotelSystem.java
   ```
2. Run the application:
   ```
   java Main
   ```
   or
   ```
   java SimpleHotelSystem
   ```

## Login Credentials

### Staff Login
- Username: admin
- Password: admin123

### Customer Login
- Username: C001, C002, C003, or C004 (sample customer IDs)
- Password: user123

## Project Structure

- `Main.java`: Contains the main application logic, UI components, and model classes
- `SimpleHotelSystem.java`: Entry point that redirects to Main
- `UIUtils.java`: Utility class for styling UI components (if you implement it)

## Model Classes

The application has these key model classes:

1. **Room**: Manages room information including number, type, price, and status
2. **Customer**: Stores customer details including ID, name, contact info
3. **Booking**: Tracks bookings with details about customer, room, dates, and status

## Implementation Details

The application maintains all data in memory using ArrayLists:
- `Main.rooms`: List of all rooms
- `Main.customers`: List of all customers
- `Main.bookings`: List of all bookings

This implementation ensures that there are no duplicate class files generated at runtime - everything is compiled once and runs from memory.

## Working with the Application

### Room Management
- Add new rooms with unique room numbers
- Edit room details including type, price, and status
- Delete rooms (if they have no associated bookings)
- Allocate rooms to customers with check-in/check-out dates

### Booking Management
- Create new bookings for customers
- Check-in and check-out functionality
- View booking details
- Cancel bookings

### Customer Management
- Add new customers with unique IDs
- Edit customer information
- View customer bookings

### Reports System
- Occupancy Report: Shows room occupancy statistics
- Revenue Report: Displays revenue by room type
- Booking Statistics: Provides booking status breakdown
- Customer Statistics: Shows top customers by booking count

## Styling

The application uses a modern, responsive UI with the following features:
- Gradient headers
- Custom-styled components (buttons, text fields, tables)
- Rounded corners on panels and buttons
- Consistent color scheme throughout the application

## Recent Bug Fixes and Improvements

- Fixed issues with non-final variables in lambda expressions and inner classes
- Added null checks and defensive coding to prevent NullPointerExceptions
- Enhanced error handling with try-catch blocks in event handlers
- Fixed chart rendering in the Reports section
- Improved window drag functionality on the login screen
- Added keyboard support (Enter key) for login
- Fixed variable references in anonymous inner classes

## Future Improvements

- Database integration for persistent data storage
- User registration functionality
- Online payment processing
- Room availability calendar
- Mobile application interface

## License

This project is licensed under the MIT License - see the LICENSE file for details.