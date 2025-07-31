package com.jackson.jwt_auth.with_refreh_token.service;

import com.jackson.jwt_auth.with_refreh_token.dto.RegistrationRequestDto;
import com.jackson.jwt_auth.with_refreh_token.dto.UserDto;
import com.jackson.jwt_auth.with_refreh_token.entity.UserEntity;
import com.jackson.jwt_auth.with_refreh_token.mapper.UserDtoMapper;
import com.jackson.jwt_auth.with_refreh_token.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.GONE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserDtoMapper userDtoMapper;

    public UserEntity getUserByUsername(final String username) {
        return userRepository.findByUserName(username)
                .orElseThrow(() -> new ResponseStatusException(GONE,
                        "The user account has been deleted or inactivated"));
    }

    public List<UserEntity> findAllUsers(){
        return userRepository.findAll();
    }

    public UserEntity getUserById(Long userId) {
        System.out.println("The user Id " + userId);
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                        "User Not Found"));
    }

    public UserDto updateUser(Long userId, RegistrationRequestDto requestDto) {

         UserEntity user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                        "User Not Found"));

         return userDtoMapper.toUserDto(user);
    }

//    public void deleteUser(Long userId) {
//        userRepository.deleteById(userId);
//    }
}
