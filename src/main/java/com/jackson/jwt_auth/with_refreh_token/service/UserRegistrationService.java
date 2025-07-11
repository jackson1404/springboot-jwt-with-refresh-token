package com.jackson.jwt_auth.with_refreh_token.service;

import com.jackson.jwt_auth.with_refreh_token.dto.RegistrationRequestDto;
import com.jackson.jwt_auth.with_refreh_token.entity.UserEntity;
import com.jackson.jwt_auth.with_refreh_token.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Validation;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRegistrationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserEntity registerUser(RegistrationRequestDto requestDto){

        if(userRepository.existsByUserName(requestDto.userName()) ||
                userRepository.existsByUserEmail(requestDto.userEmail())){
            throw new ValidationException("UserName or Email Already Exist.!")
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUserEmail(requestDto.userEmail());
        userEntity.setUserName(requestDto.userName());
        userEntity.setUserPassword(passwordEncoder.encode(requestDto.userPassword()));

        return userRepository.save(userEntity);

    }

}
