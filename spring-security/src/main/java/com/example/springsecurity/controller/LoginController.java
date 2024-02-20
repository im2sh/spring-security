package com.example.springsecurity.controller;

import com.example.springsecurity.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final AuthService authService;
    @GetMapping("/login")
    public String login() {

        return "login";
    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = authService.getAuthentication();
        if (authentication != null) {
            authService.logout(request, response);
        }
        return "redirect:/";
    }
}
