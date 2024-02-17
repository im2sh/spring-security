package com.example.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /*
    * requestMatchers("/", "/login").permitAll(): "/" 및 "/login" 요청은 모든 사용자에게 허용된다.
      requestMatchers("/admin/**").hasRole("ADMIN"): "/admin/**" 패턴의 요청은 "ADMIN" 역할을 가진 사용자에게만 허용된다.
      requestMatchers("/my/**").hasAnyRole("ADMIN", "USER"): "/my/**" 패턴의 요청은 "ADMIN" 또는 "USER" 역할을 가진 사용자에게만 허용된다.
      anyRequest().authenticated(): 그 외의 모든 요청은 인증된 사용자에게만 허용된다.
     */

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((auth) -> auth.
                        requestMatchers("/", "/login").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated()
                );

        return http.build();
    }

}
