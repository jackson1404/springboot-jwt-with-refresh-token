package com.jackson.jwt_auth.with_refreh_token.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    private Instant createdAt;
    private Instant updatedAt;
    private boolean isEmailVerified;
    private boolean isEmailVerificationRequired;
    private String userAddress;
}
