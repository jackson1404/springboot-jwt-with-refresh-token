package com.jackson.jwt_auth.with_refreh_token.dto;

public record AuthenticationResponseDto(String authToken, String refreshToken) {
}
