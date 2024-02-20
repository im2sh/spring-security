package com.example.springsecurity.controller;

import com.example.springsecurity.dto.UserJoinDTO;
import com.example.springsecurity.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class JoinController {
    private final JoinService joinService;

    @GetMapping("/join")
    public String join() {
        return "join";
    }

    @PostMapping("/join")
    public String joinMember(UserJoinDTO userJoinDTO) {
        joinService.joinMember(userJoinDTO);

        return "redirect:/login";
    }
}
