@echo off

REM Change to your repository directory
cd "C:\path\to\your\repository"

REM Initialize Git if not already done
git init

REM Set your Git username and email (modify accordingly)
git config user.name "Your Name"
git config user.email "yourname@example.com"

REM Set the interval (in seconds) to check for changes
set interval=1

REM Continuous loop to monitor changes
:monitor
cls

REM Pull changes from the remote repository
git pull origin master

REM Add all modified files
git add -A

REM Commit changes
git commit -m "Automated commit"

REM Push changes to the remote repository
git push origin master

REM Delay before checking for changes again
timeout /t %interval% > nul

REM Jump back to monitor changes
goto monitor
