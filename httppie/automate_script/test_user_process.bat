@echo off

echo Running User Process automated test...

:: User Sign Up
http POST http://localhost:8080/api/v1/auth/userSignUp userName="win11" userEmail="win11@gmail.com" userPassword="win00" userAddress="ygn" isEmailVerificationRequired=false > response_create.json

echo Created User
type response_create.json

pause