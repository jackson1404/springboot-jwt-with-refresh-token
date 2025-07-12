package com.jackson.jwt_auth.with_refreh_token.service;

import com.jackson.jwt_auth.with_refreh_token.dto.AuthenticationRequestDto;
import com.jackson.jwt_auth.with_refreh_token.dto.AuthenticationResponseDto;
import com.jackson.jwt_auth.with_refreh_token.dto.RegistrationRequestDto;
import com.jackson.jwt_auth.with_refreh_token.dto.RegistrationResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationResponseDto authenticate(final AuthenticationRequestDto requestDto) {

        final var authToken = UsernamePasswordAuthenticationToken
                .unauthenticated(requestDto.userName(), requestDto.userPassword());

        final var authentication = authenticationManager
                .authenticate(authToken);

        final var token = jwtService.generateToken(requestDto.userName());
        return new AuthenticationResponseDto(token);
    }
}
