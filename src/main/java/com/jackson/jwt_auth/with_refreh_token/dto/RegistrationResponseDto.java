package com.jackson.jwt_auth.with_refreh_token.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

public record RegistrationResponseDto(String userName, String userEmail) { }
