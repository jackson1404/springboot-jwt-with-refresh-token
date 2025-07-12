package com.jackson.jwt_auth.with_refreh_token.mapper;

import com.jackson.jwt_auth.with_refreh_token.dto.UserProfileDto;
import com.jackson.jwt_auth.with_refreh_token.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserProfileMapper {

    public UserProfileDto toProfileDto(final UserEntity userEntity){
        return new UserProfileDto(userEntity.getUserEmail(), userEntity.getUserName());
    }

}
