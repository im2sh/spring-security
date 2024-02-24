package com.example.springjwt.controller;

import com.example.springjwt.request.UserJoinRequest;
import com.example.springjwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JoinController {

    private final UserService userService;

    @PostMapping("/join")
    public String join(@RequestBody UserJoinRequest userJoinRequest) {
        System.out.println(userJoinRequest.getUsername());
        userService.join(userJoinRequest);
        return "OK";
    }
}
