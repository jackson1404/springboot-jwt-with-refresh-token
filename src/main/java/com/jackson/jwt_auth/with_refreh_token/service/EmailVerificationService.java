/***************************************************************
 * Author       :	 
 * Created Date :	
 * Version      : 	
 * History  :	
 * *************************************************************/
package com.jackson.jwt_auth.with_refreh_token.service;

import com.jackson.jwt_auth.with_refreh_token.entity.UserEntity;
import com.jackson.jwt_auth.with_refreh_token.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * EmailVerificationService Class.
 * <p>
 * </p>
 *
 * @author
 */

@Service
@RequiredArgsConstructor
public class EmailVerificationService {

    private final OtpService otpService;
    private final UserRepository userRepository;
    private final JavaMailSender javaMailSender;

    @Async
    public void sendVerificationToken(final Long userId, String userEmail) {

        final String token = otpService.generateAndStoreOtp(userId);
        final var verificationLinkUrl =
                "http://localhost:8080/api/v1/auth/email/verify?uid=%s&token=%s"
                        .formatted(userId, token);

        final var emailBodyText = "Click the link to verify your email: " + verificationLinkUrl;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("SYSTEM");
        message.setTo(userEmail);
        message.setSubject("Email Verification");
        message.setText(emailBodyText);

        javaMailSender.send(message);
    }

    public void resendVerificationToken(String userEmail){
        UserEntity user = userRepository.findByEmail(userEmail)
                .filter(userEntity -> !userEntity.isEmailVerified() && userEntity.isEmailVerificationRequired())
                .orElseThrow(() -> new RuntimeException("Email not found"));

        sendVerificationToken(user.getUserId(), user.getUserEmail());

    }

    @Transactional
    public UserEntity verifyEmail(final Long userId, final String token){

        if(!otpService.isOtpValid(userId, token)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Token or Expired");
        }

        otpService.deleteOtp(userId);

        final var user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found or deleted"));

        if(user.isEmailVerified()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already verified");
        }

        user.setEmailVerified(true);

        return user;
    }


}
