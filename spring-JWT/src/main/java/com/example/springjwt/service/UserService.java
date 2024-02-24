package com.example.springjwt.service;

import com.example.springjwt.domain.User;
import com.example.springjwt.repository.UserRepository;
import com.example.springjwt.request.UserJoinRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long join(UserJoinRequest userJoinRequest) {
        if(userRepository.existsByUsername(userJoinRequest.getUsername())) {
            throw new IllegalArgumentException("이미 존재하는 사용자입니다.");
        }

        String encodePassword = bCryptPasswordEncoder.encode(userJoinRequest.getPassword());

        User user = User.builder()
                .username(userJoinRequest.getUsername())
                .password(encodePassword)
                .role("ROLE_ADMIN")
                .build();
        userRepository.save(user);

        return user.getId();
    }
}
