# Hotel Management System - Instructions

This document provides instructions on how to run the Hotel Management System application.

## System Requirements
- Java Development Kit (JDK) 8 or higher installed
- Operating System: Windows, macOS, or Linux

## Running the Application

### On Windows:
1. Double-click the `run.bat` file.
2. The script will compile the code and launch the application.
3. If you prefer to run from command line, open Command Prompt in the project folder and type:
   ```
   run.bat
   ```

### On macOS or Linux:
1. First, make the run script executable (one-time setup):
   ```
   chmod +x run.sh
   ```
2. Run the application by double-clicking `run.sh` or by typing in Terminal:
   ```
   ./run.sh
   ```

## Login Credentials

### Staff Login
- Username: `admin`
- Password: `admin123`

### Customer Login
- Username: `C001`, `C002`, `C003`, or `C004` (sample customer IDs)
- Password: `user123`

## Troubleshooting

1. **Java Not Found Error**: Make sure you have Java installed and that it's in your system's PATH.
   - To check if Java is installed, open a terminal/command prompt and type:
     ```
     java -version
     javac -version
     ```
   - Both commands should display version information if Java is properly installed.

2. **Compilation Errors**: If you encounter compilation errors, make sure all source files are in the correct locations:
   - `src/Main.java`
   - `src/SimpleHotelSystem.java`

3. **Class Not Found**: If you see "Class not found" errors when running, make sure the compilation step completed successfully.

## Features Overview

The application has two main sections:

### Staff Dashboard
- Room Management: Add, edit, delete, and allocate rooms
- Booking Management: View bookings, check-in/out guests
- Customer Management: Add and edit customer information
- Reports: Generate statistical reports

### Customer Dashboard
- Browse and book available rooms
- View and manage your bookings
- Update your profile information

Enjoy using the Hotel Management System! 