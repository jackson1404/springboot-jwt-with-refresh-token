package com.jackson.jwt_auth.with_refreh_token.controller;

import com.jackson.jwt_auth.with_refreh_token.dto.UserProfileDto;
import com.jackson.jwt_auth.with_refreh_token.entity.UserEntity;
import com.jackson.jwt_auth.with_refreh_token.mapper.UserProfileMapper;
import com.jackson.jwt_auth.with_refreh_token.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserProfileController {

    private final UserService userService;
    private final UserProfileMapper userProfileMapper;

    @GetMapping("/profile")
    public ResponseEntity<UserProfileDto> getProfile(
            final Authentication authentication){

        UserEntity userEntity = userService.getUserByUsername(authentication.getName());

        return ResponseEntity.ok(userProfileMapper.toProfileDto(userEntity));

    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserEntity>> getAllUsers(){
        List<UserEntity> userEntities = userService.findAllUsers();
        return ResponseEntity.ok(userEntities);
    }


}
