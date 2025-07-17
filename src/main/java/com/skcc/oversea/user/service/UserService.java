package com.skcc.oversea.user.service;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import com.skcc.oversea.user.exception.CustomException;
import com.skcc.oversea.user.exception.ErrorCode;

import com.skcc.oversea.user.controller.port.UserServicePort;
import com.skcc.oversea.user.domain.User;
import com.skcc.oversea.user.domain.UserCreate;
import com.skcc.oversea.user.service.port.UserRepositoryPort;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Qualifier;

@Slf4j
@Service
public class UserService implements UserServicePort {

    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoder passwordEncoder;

    // 생성자에 @Qualifier 추가
    public UserService(
        @Qualifier("userRepositoryPortJpaCustomImpl") UserRepositoryPort userRepositoryPort,
        PasswordEncoder passwordEncoder
    ) {
        this.userRepositoryPort = userRepositoryPort;
        this.passwordEncoder = passwordEncoder;
    }

    // 회원가입 메서드
    @Override
    @Transactional
    public User signUp(UserCreate userCreate) {

        // 입력받은 이메일로 회원 존재 점검
        checkUserExistByEmail(userCreate.getEmail());
        User model = User.from(userCreate, passwordEncoder);
        // 기본권한 확인
        User savedModel = userRepositoryPort.save(model);

        return savedModel;
    }

    // 인증
    @Override
    public String authenticate(String email, String rawPassword) {
        User user = userRepositoryPort.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ELEMENT));
        boolean matches = passwordEncoder.matches(rawPassword, user.getPassword());
        if (!matches) {
            throw new CustomException(ErrorCode.NOT_MATCHED_PASSWORD);
        }
        log.info("Authentication successful for user: {}", user.getEmail());
        return "Authentication successful";
    }

    // 전체 사용자 조회
    @Override
    public List<User> findAllUsers() {
        return userRepositoryPort.findAll();
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepositoryPort.findAll(pageable);
    }

    @Override
    public User getById(Long id) {
        return userRepositoryPort.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ELEMENT));
    }

    @Override
    public Page<User> findAdminUsers(Pageable pageable) {
        log.info("[Service] findAdminUsers : {}", pageable);
        // Admin 사용자 조회 로직을 단순화 - 모든 사용자 반환
        return userRepositoryPort.findAll(pageable);
    }

    @Override
    public User updateUserStatus(User user) {

        // 조회
        User findUser = userRepositoryPort.findByEmail(user.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ELEMENT));

        // 상태값 없데이트
        User updateUser = findUser.updateStatus(user.getStatus());
        return userRepositoryPort.updateStatus(updateUser);
    }

    @Override
    public User updateUser(User user) {
        // 조회
        User findUser = userRepositoryPort.findByEmail(user.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ELEMENT));

        User updatedUser = findUser.updateUser(user, passwordEncoder);
        return userRepositoryPort.save(updatedUser);
    }

    public User update(User user) {
        User existingUser = userRepositoryPort.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        User updatedUser = existingUser.updateUser(user, passwordEncoder);
        return userRepositoryPort.save(updatedUser);
    }

    // 이메일로 존재여부 체크
    private void checkUserExistByEmail(String email) {
        Optional<User> optionalUser = userRepositoryPort.findByEmail(email);
        if (optionalUser.isPresent()) {
            throw new CustomException(ErrorCode.EXIST_ELEMENT);
        }
    }

    /**
     * SKCC Oversea Application
     *
     * Main Spring Boot application class for the SKCC Oversea banking system.
     * This application has been migrated from legacy J2EE/EJB architecture to Spring Boot.
     *
     * Features:
     * - Cash Card Management
     * - Deposit Services
     * - Common Services
     * - Teller Management
     * - User Management
     * - Transaction Logging
     *
     * @author SKCC Development Team
     * @version 1.0.0
     */
    @SpringBootApplication
    @ComponentScan(basePackages = {
        "com.skcc.oversea",
        "com.skcc.oversea.controller",
        "com.skcc.oversea.service",
        "com.skcc.oversea.config",
        "com.skcc.oversea.foundation",
        "com.skcc.oversea.eplatonframework"
    })
    @EntityScan(basePackages = {
        "com.skcc.oversea.cashCard.business.cashCard.entity",
        "com.skcc.oversea.deposit.entity",
        "com.skcc.oversea.common.entity",
        "com.skcc.oversea.teller.entity",
        "com.skcc.oversea.user.entity",
        "com.skcc.oversea.user.infrastructure.jpa",
        "com.skcc.oversea.eplatonframework.business.entity"
    })
    @EnableJpaRepositories(basePackages = {
        "com.skcc.oversea.cashCard.repository",
        "com.skcc.oversea.deposit.repository",
        "com.skcc.oversea.common.repository",
        "com.skcc.oversea.teller.repository",
        "com.skcc.oversea.user.repository",
        "com.skcc.oversea.user.infrastructure.jpa",
        "com.skcc.oversea.eplatonframework.business.repository"
    })
    @EnableTransactionManagement
    @EnableAsync
    @EnableScheduling
    public static class OverseaApplication {

        private static final Logger logger = LoggerFactory.getLogger(OverseaApplication.class);

        public static void main(String[] args) {
            logger.info("==================[OverseaApplication.main START]");
            try {
                logger.info("Starting SKCC Oversea Banking System...");
                logger.info("System Version: 1.0.0");
                logger.info("Spring Boot Version: 3.x");
                logger.info("Java Version: {}", System.getProperty("java.version"));

                SpringApplication.run(OverseaApplication.class, args);

                logger.info("SKCC Oversea Banking System started successfully!");
                logger.info("==================[OverseaApplication.main END]");
            } catch (Exception e) {
                logger.error("==================[OverseaApplication.main ERROR] - {}", e.getMessage(), e);
                System.exit(1);
            }
        }
    }
}