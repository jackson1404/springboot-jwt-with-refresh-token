package com.jackson.jwt_auth.with_refreh_token.service;

import com.jackson.jwt_auth.with_refreh_token.entity.UserEntity;
import com.jackson.jwt_auth.with_refreh_token.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return userRepository.findByUserName(username)
                .map(userEntity -> User.builder()
                        .username(username)
                        .password(userEntity.getUserPassword())
                        .build())
                .orElseThrow(()-> new UsernameNotFoundException(
                        "User Not found with this username [%s] ".formatted(username)
                ));
    }
}
