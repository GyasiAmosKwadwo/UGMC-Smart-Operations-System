@echo off
REM Windows Test Script for UGMC Smart Operations System
SETLOCAL EnableDelayedExpansion

IF NOT EXIST out (
    echo Output directory out/ not found. Running build.bat first...
    call build.bat
) ELSE (
    call build.bat
)

SET "JAVA_CMD=java"
WHERE java >nul 2>nul
IF %ERRORLEVEL% NEQ 0 (
    FOR /F "tokens=*" %%I IN ('DIR /B /S "%USERPROFILE%\.antigravity-ide\java.exe" 2^>nul') DO (
        SET "JAVA_CMD=%%I"
    )
)

echo.
echo Running Automated Unit Test Suite...
echo.
"%JAVA_CMD%" -cp out com.ugmc.smartops.test.UnitTestRunner %*
