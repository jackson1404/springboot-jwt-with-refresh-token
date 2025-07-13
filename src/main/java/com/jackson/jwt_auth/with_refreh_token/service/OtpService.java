package com.jackson.jwt_auth.with_refreh_token.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Duration;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OtpService {

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    private final RedisTemplate<String, String> redisTemplate;

    public String generateAndStoreOtp(final Long userId){

        final var otp = generateOtp("ABCDEFG123456789", 10);
        final var cacheKey = getCacheKey(userId);

        redisTemplate.opsForValue().set(cacheKey, otp, Duration.ofMinutes(5));

        return otp;
    }

    public boolean isOtpValid(final Long userId, String otp){
        final var cacheKey = getCacheKey(userId);
        return Objects.equals(
                redisTemplate.opsForValue().get(cacheKey), otp);
    }

    public void deleteOtp(final Long userId){
        final var cacheKey = getCacheKey(userId);
        redisTemplate.delete(cacheKey);
    }

    public String getCacheKey(final Long userId){
        return "otp:%s".formatted(userId);
    }

    public String generateOtp(String character, int length){
        StringBuilder otp = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = SECURE_RANDOM.nextInt(character.length());
            otp.append(character.charAt(index));
        }
        return otp.toString();
    }

}
