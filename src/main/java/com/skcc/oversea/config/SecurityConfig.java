package com.skcc.oversea.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Security Configuration for SKCC Oversea Application
 * 
 * Provides security-related beans and configurations
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * PasswordEncoder bean for password hashing
     * 
     * @return BCryptPasswordEncoder instance
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Security Filter Chain configuration
     * 
     * @param http HttpSecurity object
     * @return SecurityFilterChain
     * @throws Exception if configuration fails
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
                // 루트 경로는 로그인 페이지로 리다이렉트
                .requestMatchers(new AntPathRequestMatcher("/")).permitAll()
                
                // 홈 페이지는 인증된 사용자만 접근 가능
                .requestMatchers(new AntPathRequestMatcher("/home")).authenticated()
                
                // 기타 공통 리소스
                .requestMatchers(new AntPathRequestMatcher("/index")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/error")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/favicon.ico")).permitAll()
                
                // 정적 리소스
                .requestMatchers(new AntPathRequestMatcher("/css/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/js/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/images/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/static/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/webjars/**")).permitAll()
                
                // 인증 관련
                .requestMatchers(new AntPathRequestMatcher("/login")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/logout")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/signup")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/auth/**")).permitAll()
                
                // H2 콘솔
                .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
                
                // Swagger/OpenAPI
                .requestMatchers(new AntPathRequestMatcher("/swagger-ui/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/swagger-ui.html")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/api-docs/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/v3/api-docs/**")).permitAll()
                
                // Cash Card 관련
                .requestMatchers(new AntPathRequestMatcher("/cashcard")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/cashcard/**")).permitAll()
                
                // Deposit 관련
                .requestMatchers(new AntPathRequestMatcher("/deposit")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/deposit/**")).permitAll()
                
                // Teller 관련
                .requestMatchers(new AntPathRequestMatcher("/teller")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/teller/**")).permitAll()
                
                // User 관련
                .requestMatchers(new AntPathRequestMatcher("/user")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/user/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/user-management/**")).permitAll()
                
                // EPlaton 관련
                .requestMatchers(new AntPathRequestMatcher("/eplaton/**")).permitAll()
                
                // API 관련
                .requestMatchers(new AntPathRequestMatcher("/api/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/rest/**")).permitAll()
                
                // 파일 관련
                .requestMatchers(new AntPathRequestMatcher("/file/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/upload/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/download/**")).permitAll()
                
                // 테스트 관련
                .requestMatchers(new AntPathRequestMatcher("/test/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/actuator/**")).permitAll()
                
                // 기타 모든 요청 허용 (개발 환경용)
                .anyRequest().permitAll()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/home")
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .permitAll()
            )
            .csrf(csrf -> csrf.disable()) // CSRF 보호 비활성화
            .headers(headers -> headers
                .frameOptions(frameOptions -> frameOptions.sameOrigin()) // H2 콘솔을 위한 설정
            );

        return http.build();
    }
} 