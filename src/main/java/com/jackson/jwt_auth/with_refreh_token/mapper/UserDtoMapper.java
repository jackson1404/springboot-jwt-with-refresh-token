package com.jackson.jwt_auth.with_refreh_token.mapper;

import com.jackson.jwt_auth.with_refreh_token.dto.UserDto;
import com.jackson.jwt_auth.with_refreh_token.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoMapper {

    public UserDto toUserDto(UserEntity userEntity){
        return UserDto.builder()
                .userName(userEntity.getUserName())
                .userEmail(userEntity.getUserEmail())
                .userAddress(userEntity.getUserAddress())
                .createdAt(userEntity.getCreatedAt())
                .isEmailVerified(userEntity.isEmailVerified())
                .isEmailVerificationRequired(userEntity.isEmailVerificationRequired())
                .updatedAt(userEntity.getUpdatedAt())
                .build();
    }

    public List<UserDto> toUserDtoList(List<UserEntity> userEntities) {
        return userEntities.stream().map(user ->
                UserDto.builder()
                        .userName(user.getUserName())
                        .userEmail(user.getUserEmail())
                        .userAddress(user.getUserAddress())
                        .createdAt(user.getCreatedAt())
                        .isEmailVerified(user.isEmailVerified())
                        .isEmailVerificationRequired(user.isEmailVerificationRequired())
                        .updatedAt(user.getUpdatedAt())
                        .build()
        ).collect(Collectors.toList());
    }
}

