/***************************************************************
 * Author       :	 
 * Created Date :	
 * Version      : 	
 * History  :	
 * *************************************************************/
package com.jackson.jwt_auth.with_refreh_token.service;

import com.jackson.jwt_auth.with_refreh_token.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

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


}
