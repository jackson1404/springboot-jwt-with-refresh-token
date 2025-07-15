package com.jackson.jwt_auth.with_refreh_token.controller;

import com.jackson.jwt_auth.with_refreh_token.dto.AuthenticationRequestDto;
import com.jackson.jwt_auth.with_refreh_token.dto.AuthenticationResponseDto;
import com.jackson.jwt_auth.with_refreh_token.dto.RegistrationRequestDto;
import com.jackson.jwt_auth.with_refreh_token.dto.RegistrationResponseDto;
import com.jackson.jwt_auth.with_refreh_token.entity.UserEntity;
import com.jackson.jwt_auth.with_refreh_token.exception.EmailVerificationException;
import com.jackson.jwt_auth.with_refreh_token.service.EmailVerificationService;
import com.jackson.jwt_auth.with_refreh_token.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserAuthService userAuthService;

    private final EmailVerificationService emailVerificationService;

    @PostMapping("/signIn")
    public ResponseEntity<AuthenticationResponseDto> signUpUser(@RequestBody final AuthenticationRequestDto requestDto){

        return ResponseEntity.ok(userAuthService.authenticate(requestDto));
    }

    @GetMapping("/emailVerify")
    public ResponseEntity<?> verifyEmail(
            @RequestParam("uid") Long userId, @RequestParam("token") String token){

        UserEntity user = emailVerificationService.verifyEmail(userId, token);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/resendVerification")
    public ResponseEntity<String> resendVerification(@RequestParam("userEmail") String userEmail){
        emailVerificationService.resendVerificationToken(userEmail);
        return ResponseEntity.ok("Email resent.!");
    }

    @PostMapping("/token/refresh")
    public ResponseEntity<?> getRefreshToken(@RequestParam("refreshTokenId") Long refreshTokenId){
        AuthenticationResponseDto responseDto = userAuthService.getRefreshToken(refreshTokenId);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> revokeToken(@RequestParam Long refreshToken) {
        userAuthService.revokeRefreshToken(refreshToken);
        return ResponseEntity.noContent().build();
    }

}
