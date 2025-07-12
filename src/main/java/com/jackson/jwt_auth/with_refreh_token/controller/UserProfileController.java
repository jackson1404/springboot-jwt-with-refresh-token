package com.jackson.jwt_auth.with_refreh_token.controller;

import com.jackson.jwt_auth.with_refreh_token.dto.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserProfileController {

    @GetMapping("/profile")
    public ResponseEntity<UserProfileDto> getProfile(
            final Authentication authentication){

    }
}
