@echo off
setlocal EnableDelayedExpansion

REM ==========================================
REM START TEAMSPHERE BACKEND AND FRONTEND
REM ==========================================

echo Starting TeamSphere Backend and Frontend...

REM ------------------------------------------
REM 1) Start the Backend
REM ------------------------------------------
echo Starting the Backend...
start "TeamSphere Backend" java -DSECRET_KEY="66aeed6753c7e6e329d1d40fff87bfda8d01166894ae78edeed669f4f0b6b671" -jar "D:\IntelijProject\TeamSphere-Public\TeamSphere-backend\target\teamsphere-1.jar"

REM ------------------------------------------
REM 2) Start the Frontend
REM ------------------------------------------
echo Starting the Frontend...
pushd "D:\IntelijProject\TeamSphere-Public\TeamSphere-frontend" || (
    echo ERROR: Frontend folder not found.
    exit /b 1
)
start "TeamSphere Frontend" cmd /k "npm run dev -- --host 192.168.100.60"

REM ------------------------------------------
REM 3) Open the frontend in the browser
REM ------------------------------------------
timeout /t 5 /nobreak >nul
start "" "http://192.168.100.60:5173"

popd
echo TeamSphere Backend and Frontend started successfully.
exit /b 0
