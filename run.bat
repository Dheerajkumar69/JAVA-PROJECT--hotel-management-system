@echo off
echo Hotel Management System - Compilation and Execution Script

echo Cleaning up any previous class files...
del /Q "*.class" 2>nul
del /Q "src\*.class" 2>nul

echo Compiling the source files...
javac -d . "src\Main.java" "src\SimpleHotelSystem.java"

if %errorlevel% neq 0 (
    echo Compilation failed! Please check the errors above.
    pause
    exit /b 1
)

echo Compilation successful!
echo.
echo Starting the Hotel Management System...
echo.
echo Login Credentials:
echo Staff: username=admin, password=admin123
echo Customer: username=C001, password=user123
echo.

java Main

pause 