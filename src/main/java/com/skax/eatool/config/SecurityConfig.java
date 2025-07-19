package com.skax.eatool.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
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

        @Autowired
        @Lazy
        private UserDetailsService userDetailsService;

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
                                .userDetailsService(userDetailsService)
                                .authorizeHttpRequests(authz -> authz
                                                // 루트 경로와 로그인 페이지 리다이렉트
                                                .requestMatchers(new AntPathRequestMatcher("/")).permitAll()

                                                // 홈 페이지는 인증된 사용자만 접근 가능
                                                .requestMatchers(new AntPathRequestMatcher("/home")).authenticated()

                                                // 기본 공통 리소스
                                                .requestMatchers(new AntPathRequestMatcher("/index")).permitAll()
                                                .requestMatchers(new AntPathRequestMatcher("/error")).permitAll()
                                                .requestMatchers(new AntPathRequestMatcher("/favicon.ico")).permitAll()

                                                // 정적 리소스
                                                .requestMatchers(new AntPathRequestMatcher("/css/**")).permitAll()
                                                .requestMatchers(new AntPathRequestMatcher("/js/**")).permitAll()
                                                .requestMatchers(new AntPathRequestMatcher("/images/**")).permitAll()
                                                .requestMatchers(new AntPathRequestMatcher("/static/**")).permitAll()
                                                .requestMatchers(new AntPathRequestMatcher("/webjars/**")).permitAll()

                                                // 인증 관리
                                                .requestMatchers(new AntPathRequestMatcher("/login")).permitAll()
                                                .requestMatchers(new AntPathRequestMatcher("/logout")).permitAll()
                                                .requestMatchers(new AntPathRequestMatcher("/signup")).permitAll()
                                                .requestMatchers(new AntPathRequestMatcher("/auth/**")).permitAll()

                                                // H2 콘솔
                                                .requestMatchers(new AntPathRequestMatcher("/h2-console/**"))
                                                .permitAll()

                                                // Swagger/OpenAPI
                                                .requestMatchers(new AntPathRequestMatcher("/swagger-ui/**"))
                                                .permitAll()
                                                .requestMatchers(new AntPathRequestMatcher("/swagger-ui.html"))
                                                .permitAll()
                                                .requestMatchers(new AntPathRequestMatcher("/api-docs/**")).permitAll()
                                                .requestMatchers(new AntPathRequestMatcher("/v3/api-docs/**"))
                                                .permitAll()

                                                // Cash Card 관리 - 인증 필요
                                                .requestMatchers(new AntPathRequestMatcher("/cashcard")).authenticated()
                                                .requestMatchers(new AntPathRequestMatcher("/cashcard/**"))
                                                .authenticated()

                                                // Deposit 관리 - 인증 필요
                                                .requestMatchers(new AntPathRequestMatcher("/deposit")).authenticated()
                                                .requestMatchers(new AntPathRequestMatcher("/deposit/**"))
                                                .authenticated()

                                                // Teller 관리 - 인증 필요
                                                .requestMatchers(new AntPathRequestMatcher("/teller")).authenticated()
                                                .requestMatchers(new AntPathRequestMatcher("/teller/**"))
                                                .authenticated()

                                                // User 관리 - 인증 필요
                                                .requestMatchers(new AntPathRequestMatcher("/user")).authenticated()
                                                .requestMatchers(new AntPathRequestMatcher("/user/**")).authenticated()
                                                .requestMatchers(new AntPathRequestMatcher("/user-management/**"))
                                                .authenticated()

                                                // EPlaton 관리 - 인증 필요
                                                .requestMatchers(new AntPathRequestMatcher("/eplaton/**"))
                                                .authenticated()

                                                // TechSpec 관리 - 인증 필요
                                                .requestMatchers(new AntPathRequestMatcher("/techspec/**"))
                                                .authenticated()

                                                // API 관리 - 대부분 인증 필요
                                                .requestMatchers(new AntPathRequestMatcher("/api/health")).permitAll()
                                                .requestMatchers(new AntPathRequestMatcher("/api/auth/**")).permitAll()
                                                .requestMatchers(new AntPathRequestMatcher("/api/**")).authenticated()
                                                .requestMatchers(new AntPathRequestMatcher("/rest/**")).authenticated()

                                                // 파일 관리 - 인증 필요
                                                .requestMatchers(new AntPathRequestMatcher("/file/**")).authenticated()
                                                .requestMatchers(new AntPathRequestMatcher("/upload/**"))
                                                .authenticated()
                                                .requestMatchers(new AntPathRequestMatcher("/download/**"))
                                                .authenticated()

                                                // 테스트 관리 - 개발 환경에서만 사용
                                                .requestMatchers(new AntPathRequestMatcher("/test/**")).permitAll()
                                                .requestMatchers(new AntPathRequestMatcher("/actuator/**")).permitAll()

                                                // 기타 모든 요청 - 인증 필요
                                                .anyRequest().authenticated())
                                .formLogin(form -> form
                                                .loginPage("/login")
                                                .defaultSuccessUrl("/home")
                                                .failureUrl("/login?error=true")
                                                .permitAll())
                                .logout(logout -> logout
                                                .logoutUrl("/logout")
                                                .logoutSuccessUrl("/login?logout=true")
                                                .invalidateHttpSession(true)
                                                .deleteCookies("JSESSIONID")
                                                .permitAll())
                                .sessionManagement(session -> session
                                                .maximumSessions(10) // 동시 세션 제한을 10개로 증가
                                                .expiredUrl("/login?expired=true"))
                                .csrf(csrf -> csrf.disable()) // CSRF 보호 비활성화
                                .headers(headers -> headers
                                                .frameOptions(frameOptions -> frameOptions.sameOrigin()) // H2 콘솔을 위한 설정
                                );

                return http.build();
        }
}
