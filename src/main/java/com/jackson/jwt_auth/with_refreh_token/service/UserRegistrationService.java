package com.jackson.jwt_auth.with_refreh_token.service;

import com.jackson.jwt_auth.with_refreh_token.dto.RegistrationRequestDto;
import com.jackson.jwt_auth.with_refreh_token.entity.UserEntity;
import com.jackson.jwt_auth.with_refreh_token.repository.UserRepository;
import jakarta.transaction.Transactional;
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

    private final Path UPLOAD_PATH = Paths.get("src/main/resources/img");

    @Transactional
    public UserEntity registerUser(RegistrationRequestDto requestDto, MultipartFile file) throws IOException {

        String profileImageName = null;

        validateUser(requestDto);

        if(file != null && !file.isEmpty()){
            profileImageName = storeProfileImage(file);
        }

        UserEntity userEntity = buildUserEntity(requestDto, profileImageName);
        return userRepository.save(userEntity);

    }

    private void validateUser(RegistrationRequestDto requestDto) {
        if(userRepository.existsByUserName(requestDto.userName()) ||
                userRepository.existsByUserEmail(requestDto.userEmail())){
            throw new ValidationException("UserName or Email Already Exist.!");
        }
    }

    private UserEntity buildUserEntity(RegistrationRequestDto requestDto, String fileName) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserEmail(requestDto.userEmail());
        userEntity.setUserName(requestDto.userName());
        userEntity.setUserAddress(requestDto.userAddress());
        userEntity.setEmailVerified(false);
        userEntity.setEmailVerificationRequired(requestDto.isEmailVerificationRequired());
        userEntity.setUserPassword(passwordEncoder.encode(requestDto.userPassword()));
        userEntity.setProfileImage(fileName);
        return userEntity;
    }

    private String storeProfileImage(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        checkDirectoryExist();

        Path destinationPath = UPLOAD_PATH.resolve(fileName);

        try {
            Files.write(destinationPath, file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Failed to saved file", e);
        }
        return fileName;
    }

    private void checkDirectoryExist() throws IOException {
        if(!Files.exists(UPLOAD_PATH)){
             Files.createDirectories(UPLOAD_PATH);
        }
    }
}
