package com.example.springsecurity.service;

import com.example.springsecurity.domain.User;
import com.example.springsecurity.dto.UserJoinDTO;
import com.example.springsecurity.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JoinService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public Long joinMember(UserJoinDTO userJoinDTO) {
        if(userRepository.existsByUserName(userJoinDTO.userName())){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

        String encodePassword = bCryptPasswordEncoder.encode(userJoinDTO.password());

        UserJoinDTO joinDTO = new UserJoinDTO(userJoinDTO.userName(), encodePassword, userJoinDTO.email(), userJoinDTO.role());

        User user = joinDTO.toEntity();
        user.setRole("ROLE_ADMIN");
        return userRepository.save(user).getId();
    }
}
