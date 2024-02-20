package com.example.springsecurity.controller;

import com.example.springsecurity.service.AuthService;
import java.util.Collection;
import java.util.Iterator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final AuthService authService;
    @GetMapping("/")
    public String home(Model model) {
        String authId = authService.getAuthId();
        String authRole = authService.getAuthRole();

        model.addAttribute("id", authId);
        model.addAttribute("role", authRole);

        return "home";
    }
}
