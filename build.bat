@echo off
REM Windows Build Script for UGMC Smart Operations System
SETLOCAL EnableDelayedExpansion

IF EXIST out RMDIR /S /Q out
MKDIR out

SET "JAVAC_CMD=javac"
WHERE javac >nul 2>nul
IF %ERRORLEVEL% NEQ 0 (
    FOR /F "tokens=*" %%I IN ('DIR /B /S "%USERPROFILE%\.antigravity-ide\javac.exe" 2^>nul') DO (
        SET "JAVAC_CMD=%%I"
    )
)

echo Compiling Java sources...
DIR /B /S src\main\java\*.java > sources.txt
"%JAVAC_CMD%" -d out @sources.txt

IF %ERRORLEVEL% EQU 0 (
    echo.
    echo ====================================
    echo Build successful! Classes saved to out/
    echo Run using run.bat
    echo ====================================
) ELSE (
    echo.
    echo Build failed! Please check Java installation.
)
