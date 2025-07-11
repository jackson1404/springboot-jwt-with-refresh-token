package com.jackson.jwt_auth.with_refreh_token.controller;

import com.jackson.jwt_auth.with_refreh_token.dto.RegistrationRequestDto;
import com.jackson.jwt_auth.with_refreh_token.dto.RegistrationResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserRegistrationController {

    @PostMapping("/userSignUp")
    public ResponseEntity<RegistrationResponseDto> signUpUser(
            @Valid @RequestBody final RegistrationRequestDto requestDto
            ){


        return new ResponseEntity<>(null);
    }
}
