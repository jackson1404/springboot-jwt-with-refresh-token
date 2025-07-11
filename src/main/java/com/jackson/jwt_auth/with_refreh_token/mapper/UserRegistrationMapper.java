package com.jackson.jwt_auth.with_refreh_token.mapper;

import com.jackson.jwt_auth.with_refreh_token.dto.RegistrationRequestDto;
import com.jackson.jwt_auth.with_refreh_token.dto.RegistrationResponseDto;
import com.jackson.jwt_auth.with_refreh_token.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationMapper {

    public UserEntity toEntity(RegistrationRequestDto registrationRequestDto) {
        final var user = new UserEntity();

        user.setUserEmail(registrationRequestDto.userEmail());
        user.setUserName(registrationRequestDto.userName());
        user.setUserPassword(registrationRequestDto.userPassword());

        return user;
    }

    public RegistrationResponseDto toRegistrationResponseDto(
            final UserEntity user) {

        return new RegistrationResponseDto(
                user.getUserEmail(), user.getUserName());
    }
}
