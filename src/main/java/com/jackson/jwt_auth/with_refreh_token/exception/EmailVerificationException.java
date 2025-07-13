package com.jackson.jwt_auth.with_refreh_token.exception;

public class EmailVerificationException extends RuntimeException{

    public EmailVerificationException(String message){
        super(message);
    }
}
