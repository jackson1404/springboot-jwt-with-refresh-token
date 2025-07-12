package com.jackson.jwt_auth.with_refreh_token.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jackson.jwt_auth.with_refreh_token.dto.RegistrationRequestDto;
import com.jackson.jwt_auth.with_refreh_token.dto.RegistrationResponseDto;
import com.jackson.jwt_auth.with_refreh_token.entity.UserEntity;
import com.jackson.jwt_auth.with_refreh_token.mapper.UserRegistrationMapper;
import com.jackson.jwt_auth.with_refreh_token.service.UserRegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserRegistrationController {

    private final UserRegistrationService userRegistrationService;

    private final UserRegistrationMapper mapper;

    @PostMapping("/userSignUp")
    public ResponseEntity<RegistrationResponseDto> signUpUser(
            @Valid @RequestBody final RegistrationRequestDto requestDto
            ){

        UserEntity userEntity = userRegistrationService.registerUser(requestDto);

        return ResponseEntity.ok(
                mapper.toRegistrationResponseDto(userEntity));
    }


}
