package com.jackson.jwt_auth.with_refreh_token.config;

import com.jackson.jwt_auth.with_refreh_token.exception.EmailVerificationException;
import com.jackson.jwt_auth.with_refreh_token.exception.ErrorResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailVerificationException.class)
    public ResponseEntity<ErrorResponse> handleEmailVerification(EmailVerificationException e){

        ErrorResponse response = new ErrorResponse(
                "EMAIL_VERIFICATION_ERROR",
                e.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

}
