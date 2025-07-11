/***************************************************************
 * Author       :	 
 * Created Date :	
 * Version      : 	
 * History  :	
 * *************************************************************/
package com.jackson.jwt_auth.with_refreh_token.config;

import com.jackson.jwt_auth.with_refreh_token.service.JwtService;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Duration;

/**
 * JwtConfig Class.
 * <p>
 * </p>
 *
 * @author
 */

@Configuration
@Data
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {

    private RSAPrivateKey rsaPrivateKey;

    private RSAPublicKey rsaPublicKey;

    private Duration ttl;

    @Bean
    public JwtEncoder jwtEncoder(){
        final var jwk = new RSAKey.Builder(rsaPublicKey)
                .privateKey(rsaPrivateKey)
                .build();

        return new NimbusJwtEncoder(new ImmutableJWKSet<>(new JWKSet(jwk)));
    }

    @Bean
    public JwtDecoder jwtDecoder(){
        return NimbusJwtDecoder.withPublicKey(rsaPublicKey).build();
    }

    @Bean
    public JwtService jwtService(
            @Value("${spring.application.name}") final String appName,
            final JwtEncoder jwtEncoder) {

        return new JwtService(appName, ttl, jwtEncoder);
    }


}
