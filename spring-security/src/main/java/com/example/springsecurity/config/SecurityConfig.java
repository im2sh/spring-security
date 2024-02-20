package com.example.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /**
         requestMatchers("/", "/login").permitAll(): "/" 및 "/login" 요청은 모든 사용자에게 허용된다.
         requestMatchers("/admin/**").hasRole("ADMIN"): "/admin/**" 패턴의 요청은 "ADMIN" 역할을 가진 사용자에게만 허용된다.
         requestMatchers("/my/**").hasAnyRole("ADMIN", "USER"): "/my/**" 패턴의 요청은 "ADMIN" 또는 "USER" 역할을 가진 사용자에게만 허용된다.
         anyRequest().authenticated(): 그 외의 모든 요청은 인증된 사용자에게만 허용된다.
         */

        http
                .authorizeHttpRequests((auth) -> auth.
                        requestMatchers("/", "/login", "/join").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated()
                );

        /**
         http.formLogin((auth) -> auth: 이 부분은 폼 기반 로그인을 설정하는 메서드를 호출한다.
         formLogin() 메서드는 사용자에게 폼 기반으로 로그인할 수 있는 기능을 활성화한다.

         .loginPage("/login"): 로그인 페이지의 URL을 지정한다. 사용자가 로그인할 수 있는 폼이 표시되는 페이지다.

         .loginProcessingUrl("/login"): 로그인 처리 URL을 지정한다. 이 URL은 사용자가 제출한 로그인 정보를 처리하는 데 사용된다.

         .defaultSuccessUrl("/home", true): 로그인 성공 후에 리다이렉트할 URL을 지정한다. 성공적으로 로그인한 후에 사용자를 이 URL로 리다이렉트한다. 두 번째 매개변수 true는 항상 리다이렉트할 것인지에 대한 여부를 나타낸다.

         .failureUrl("/login?error=true"): 로그인 실패 후에 리다이렉트할 URL을 지정한다. 여기서 error=true 쿼리 파라미터는 로그인 페이지로 다시 이동할 때 오류가 발생했음을 나타낸다.

         .permitAll(): 로그인 페이지와 로그인 처리 URL은 모든 사용자에게 접근이 허용된다. 로그인 페이지 및 로그인 처리 URL에 대한 접근 제한이 없음을 의미한다.
         */

        http
                .formLogin((auth) -> auth.loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login?error=true")
                        .permitAll());

        // http.csrf((auth) -> auth.disable());

        /**
         * maximumSessions(1): 하나의 아이디에 대한 다중 로그인 허용 개수
         * maxSessionsPreventsLogin(true): 다중 로그인을 막을지 여부 (true : 초과시 새로운 로긍인을 차단, false : 초과시 기존 세션 하나 삭제)
         */
        http
                .sessionManagement((auth) -> auth
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true));

        /**
         * sessionFixation().changeSessionId(): 세션 고정 공격을 방어하기 위해 세션 ID를 변경하는 설정
         */
        http
                .sessionManagement((auth) -> auth
                        .sessionFixation().changeSessionId());

        http
                .logout((auth) -> auth.logoutUrl("/logout")
                        .logoutSuccessUrl("/"));

        return http.build();
    }

    /**
     * InmemoryUserDetailsManager를 사용하여 사용자 정보를 메모리에 저장한다.
     * 직접 생성한 User와 구분짓기 위해 org.springframework.security.core.userdetails의 경로를 명시
     * @return
     */

    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails user1 = org.springframework.security.core.userdetails.User.builder()
                .username("user1")
                .password(bCryptPasswordEncoder().encode("1234"))
                .roles("ADMIN")
                .build();

        UserDetails user2 = org.springframework.security.core.userdetails.User.builder()
                .username("user2")
                .password(bCryptPasswordEncoder().encode("1234"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }

}
