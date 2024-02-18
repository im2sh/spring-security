package com.example.springsecurity.dto;

import com.example.springsecurity.domain.User;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public record UserJoinDTO(String userName, String password, String email, String role) {

    @Builder
    public User toEntity() {
        return User.builder()
                .userName(userName)
                .password(password)
                .email(email)
                .role(role)
                .build();
    }
}
