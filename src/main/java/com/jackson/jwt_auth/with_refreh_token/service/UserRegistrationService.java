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
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserRegistrationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserEntity registerUser(RegistrationRequestDto requestDto, MultipartFile file){

        String fileName = null;

        if(userRepository.existsByUserName(requestDto.userName()) ||
                userRepository.existsByUserEmail(requestDto.userEmail())){
            throw new ValidationException("UserName or Email Already Exist.!");
        }

        if(file != null && !file.isEmpty()){
            fileName = storeProfileImage(file);
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUserEmail(requestDto.userEmail());
        userEntity.setUserName(requestDto.userName());
        userEntity.setUserAddress(requestDto.userAddress());
        userEntity.setEmailVerified(false);
        userEntity.setEmailVerificationRequired(requestDto.isEmailVerificationRequired());
        userEntity.setUserPassword(passwordEncoder.encode(requestDto.userPassword()));
        userEntity.setProfileImage(fileName);
        return userRepository.save(userEntity);

    }

    private String storeProfileImage(MultipartFile file) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        Path savePath = Paths.get("src/main/resources/img/", fileName);

        try {
            Files.write(savePath, file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Failed to saved file", e);
        }
        return fileName;
    }

}
