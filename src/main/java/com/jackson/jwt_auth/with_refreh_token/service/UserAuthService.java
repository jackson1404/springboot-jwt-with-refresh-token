package com.jackson.jwt_auth.with_refreh_token.service;

import com.jackson.jwt_auth.with_refreh_token.dto.AuthenticationRequestDto;
import com.jackson.jwt_auth.with_refreh_token.dto.AuthenticationResponseDto;
import com.jackson.jwt_auth.with_refreh_token.dto.RegistrationRequestDto;
import com.jackson.jwt_auth.with_refreh_token.dto.RegistrationResponseDto;
import com.jackson.jwt_auth.with_refreh_token.entity.RefreshTokenEntity;
import com.jackson.jwt_auth.with_refreh_token.repository.RefreshTokenRepository;
import com.jackson.jwt_auth.with_refreh_token.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class UserAuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    private final long refreshTokenTtlInSeconds = 7 * 24 * 60 * 60; // 7 days

    public AuthenticationResponseDto authenticate(final AuthenticationRequestDto requestDto) {

        final var authToken = UsernamePasswordAuthenticationToken
                .unauthenticated(requestDto.userName(), requestDto.userPassword());

        final var authentication = authenticationManager
                .authenticate(authToken);

        final var accessToken = jwtService.generateToken(requestDto.userName());

        final var user = userRepository.findByUserName(requestDto.userName())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

        RefreshTokenEntity refreshToken = new RefreshTokenEntity();
        refreshToken.setUser(user);
        refreshToken.setExpiredAt(Instant.now().plusSeconds(refreshTokenTtlInSeconds));
        refreshTokenRepository.save(refreshToken);

        return new AuthenticationResponseDto(accessToken, refreshToken.getRefreshTokenId());
    }
}
