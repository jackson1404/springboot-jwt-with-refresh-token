package com.jackson.jwt_auth.with_refreh_token.controller;

import com.jackson.jwt_auth.with_refreh_token.dto.RegistrationRequestDto;
import com.jackson.jwt_auth.with_refreh_token.dto.UserDto;
import com.jackson.jwt_auth.with_refreh_token.dto.UserProfileDto;
import com.jackson.jwt_auth.with_refreh_token.entity.UserEntity;
import com.jackson.jwt_auth.with_refreh_token.mapper.UserDtoMapper;
import com.jackson.jwt_auth.with_refreh_token.mapper.UserProfileMapper;
import com.jackson.jwt_auth.with_refreh_token.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserInfoController {

    private final Path uploadPath = Paths.get("src/main/resources/img");

    private final UserService userService;
    private final UserProfileMapper userProfileMapper;
    private final UserDtoMapper userDtoMapper;

    @GetMapping("/profile")
    public ResponseEntity<UserProfileDto> getProfile(
            final Authentication authentication){

        UserEntity userEntity = userService.getUserByUsername(authentication.getName());

        return ResponseEntity.ok(userProfileMapper.toProfileDto(userEntity));

    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserEntity> userEntities = userService.findAllUsers();
        return ResponseEntity.ok(userDtoMapper.toUserDtoList(userEntities));
    }

    @GetMapping("/getUserById")
    public ResponseEntity<UserDto> getUserById(@RequestParam("userId") Long userId){
        return ResponseEntity.ok(userDtoMapper.toUserDto(userService.getUserById(userId)));
    }

    @PutMapping("/updateUser")
    public ResponseEntity<UserDto> updateUser(@RequestParam("userId") Long userId, RegistrationRequestDto requestDto){
        return ResponseEntity.ok(userService.updateUser(userId, requestDto));
    }

//    @DeleteMapping("/deleteUser")
//    public ResponseEntity<?> deleteUser(@RequestParam("userId") Long userId){
//        return ResponseEntity.ok(userService.deleteUser(userId));
//    }


}
