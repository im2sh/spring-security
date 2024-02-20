package com.example.springsecurity.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Iterator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

/**
 * 사용자가 로그인을 진행하면 사용자 정보는 SecurityContextHolder에 의해서 서버 세션에 관리된다.
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    /**
     * 인증된 사용자의 아이디를 반환한다.
     * @return
     */
    public String getAuthId(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    /**
     * 인증된 사용자의 권한을 반환한다.
     * @return
     */
    public String getAuthRole(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        return auth.getAuthority();
    }

    public Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, getAuthentication());
    }
}
