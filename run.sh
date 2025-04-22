#!/bin/bash
echo "Hotel Management System - Compilation and Execution Script"

echo "Cleaning up any previous class files..."
rm -f ./*.class
rm -f ./src/*.class

echo "Compiling the source files..."
javac -d . "./src/Main.java" "./src/SimpleHotelSystem.java"

if [ $? -ne 0 ]; then
    echo "Compilation failed! Please check the errors above."
    read -p "Press Enter to exit..."
    exit 1
fi

echo "Compilation successful!"
echo
echo "Starting the Hotel Management System..."
echo
echo "Login Credentials:"
echo "Staff: username=admin, password=admin123"
echo "Customer: username=C001, password=user123"
echo

java Main

read -p "Press Enter to exit..." 