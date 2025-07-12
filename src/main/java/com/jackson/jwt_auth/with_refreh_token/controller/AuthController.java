package com.jackson.jwt_auth.with_refreh_token.controller;

import com.jackson.jwt_auth.with_refreh_token.dto.AuthenticationRequestDto;
import com.jackson.jwt_auth.with_refreh_token.dto.AuthenticationResponseDto;
import com.jackson.jwt_auth.with_refreh_token.dto.RegistrationRequestDto;
import com.jackson.jwt_auth.with_refreh_token.dto.RegistrationResponseDto;
import com.jackson.jwt_auth.with_refreh_token.entity.UserEntity;
import com.jackson.jwt_auth.with_refreh_token.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserAuthService userAuthService;

    @PostMapping("/signUp")
    public ResponseEntity<AuthenticationResponseDto> signUpUser(@RequestBody final AuthenticationRequestDto requestDto){

        return ResponseEntity.ok(userAuthService.authenticate(requestDto));
    }


}
