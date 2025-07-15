package com.jackson.jwt_auth.with_refreh_token.dto;

public record RegistrationRequestDto(String userName, String userEmail, String userPassword, String userAddress, boolean isEmailVerificationRequired) {
}
