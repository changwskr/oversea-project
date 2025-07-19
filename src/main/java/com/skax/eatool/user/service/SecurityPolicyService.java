package com.skax.eatool.user.service;

import com.skax.eatool.user.domain.SecurityPolicy;
import com.skax.eatool.user.service.port.SecurityPolicyRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 보안 정책 서비스
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SecurityPolicyService {

    private final SecurityPolicyRepositoryPort securityPolicyRepositoryPort;

    /**
     * 보안 정책 생성
     */
    @Transactional
    public SecurityPolicy createPolicy(SecurityPolicy securityPolicy) {
        log.info("Creating security policy: {}", securityPolicy);
        return securityPolicyRepositoryPort.save(securityPolicy);
    }

    /**
     * 비밀번호 정책 생성
     */
    @Transactional
    public SecurityPolicy createPasswordPolicy(String policyValue, String description) {
        log.info("Creating password policy: {}", policyValue);
        SecurityPolicy policy = SecurityPolicy.createPasswordPolicy(policyValue, description);
        return securityPolicyRepositoryPort.save(policy);
    }

    /**
     * 세션 정책 생성
     */
    @Transactional
    public SecurityPolicy createSessionPolicy(String policyValue, String description) {
        log.info("Creating session policy: {}", policyValue);
        SecurityPolicy policy = SecurityPolicy.createSessionPolicy(policyValue, description);
        return securityPolicyRepositoryPort.save(policy);
    }

    /**
     * IP 화이트리스트 정책 생성
     */
    @Transactional
    public SecurityPolicy createIpWhitelistPolicy(String policyValue, String description) {
        log.info("Creating IP whitelist policy: {}", policyValue);
        SecurityPolicy policy = SecurityPolicy.createIpWhitelistPolicy(policyValue, description);
        return securityPolicyRepositoryPort.save(policy);
    }

    /**
     * 정책 조회
     */
    public Optional<SecurityPolicy> getPolicyById(Long id) {
        log.info("Getting security policy by id: {}", id);
        return securityPolicyRepositoryPort.findById(id);
    }

    /**
     * 정책 타입별 조회
     */
    public List<SecurityPolicy> getPoliciesByType(String policyType) {
        log.info("Getting security policies by type: {}", policyType);
        return securityPolicyRepositoryPort.findByPolicyType(policyType);
    }

    /**
     * 활성화된 정책 조회
     */
    public List<SecurityPolicy> getActivePolicies() {
        log.info("Getting active security policies");
        return securityPolicyRepositoryPort.findActivePolicies();
    }

    /**
     * 정책 타입별 활성화된 정책 조회
     */
    public List<SecurityPolicy> getActivePoliciesByType(String policyType) {
        log.info("Getting active security policies by type: {}", policyType);
        return securityPolicyRepositoryPort.findByPolicyTypeAndActive(policyType);
    }

    /**
     * 정책명으로 조회
     */
    public Optional<SecurityPolicy> getPolicyByName(String policyName) {
        log.info("Getting security policy by name: {}", policyName);
        return securityPolicyRepositoryPort.findByPolicyName(policyName);
    }

    /**
     * 활성화된 비밀번호 정책 조회
     */
    public List<SecurityPolicy> getActivePasswordPolicies() {
        log.info("Getting active password policies");
        return securityPolicyRepositoryPort.findActivePasswordPolicies();
    }

    /**
     * 활성화된 세션 정책 조회
     */
    public List<SecurityPolicy> getActiveSessionPolicies() {
        log.info("Getting active session policies");
        return securityPolicyRepositoryPort.findActiveSessionPolicies();
    }

    /**
     * 활성화된 IP 화이트리스트 정책 조회
     */
    public List<SecurityPolicy> getActiveIpWhitelistPolicies() {
        log.info("Getting active IP whitelist policies");
        return securityPolicyRepositoryPort.findActiveIpWhitelistPolicies();
    }

    /**
     * 모든 정책 조회
     */
    public List<SecurityPolicy> getAllPolicies() {
        log.info("Getting all security policies");
        return securityPolicyRepositoryPort.findAll();
    }

    /**
     * 정책 업데이트
     */
    @Transactional
    public SecurityPolicy updatePolicy(SecurityPolicy securityPolicy) {
        log.info("Updating security policy: {}", securityPolicy);
        SecurityPolicy updatedPolicy = SecurityPolicy.builder()
                .id(securityPolicy.getId())
                .policyName(securityPolicy.getPolicyName())
                .policyType(securityPolicy.getPolicyType())
                .policyValue(securityPolicy.getPolicyValue())
                .description(securityPolicy.getDescription())
                .isActive(securityPolicy.isActive())
                .createdAt(securityPolicy.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .build();
        return securityPolicyRepositoryPort.save(updatedPolicy);
    }

    /**
     * 정책 활성화/비활성화
     */
    @Transactional
    public SecurityPolicy updatePolicyStatus(Long id, boolean isActive) {
        log.info("Updating security policy status: id={}, isActive={}", id, isActive);
        return securityPolicyRepositoryPort.updateActiveStatus(id, isActive);
    }

    /**
     * 정책 삭제
     */
    @Transactional
    public void deletePolicy(Long id) {
        log.info("Deleting security policy with id: {}", id);
        securityPolicyRepositoryPort.deleteById(id);
    }

    /**
     * 비밀번호 정책 검증
     */
    public boolean validatePassword(String password) {
        List<SecurityPolicy> passwordPolicies = getActivePasswordPolicies();
        // 기본 비밀번호 정책: 최소 8자, 영문+숫자+특수문자 포함
        if (passwordPolicies.isEmpty()) {
            return password != null && password.length() >= 8 &&
                    password.matches(".*[a-zA-Z].*") &&
                    password.matches(".*[0-9].*") &&
                    password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");
        }

        // 활성화된 비밀번호 정책에 따라 검증
        for (SecurityPolicy policy : passwordPolicies) {
            // 정책 값에 따른 검증로직 구현
            if (!validatePasswordByPolicy(password, policy)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 정책별 비밀번호 검증
     */
    private boolean validatePasswordByPolicy(String password, SecurityPolicy policy) {
        // 정책 값에 따른 검증로직
        // 예:
        // "minLength:8,maxLength:20,requireUppercase:true,requireLowercase:true,requireNumber:true,requireSpecial:true"
        String[] rules = policy.getPolicyValue().split(",");

        for (String rule : rules) {
            String[] parts = rule.split(":");
            if (parts.length == 2) {
                String key = parts[0].trim();
                String value = parts[1].trim();

                switch (key) {
                    case "minLength":
                        if (password.length() < Integer.parseInt(value)) {
                            return false;
                        }
                        break;
                    case "maxLength":
                        if (password.length() > Integer.parseInt(value)) {
                            return false;
                        }
                        break;
                    case "requireUppercase":
                        if ("true".equals(value) && !password.matches(".*[A-Z].*")) {
                            return false;
                        }
                        break;
                    case "requireLowercase":
                        if ("true".equals(value) && !password.matches(".*[a-z].*")) {
                            return false;
                        }
                        break;
                    case "requireNumber":
                        if ("true".equals(value) && !password.matches(".*[0-9].*")) {
                            return false;
                        }
                        break;
                    case "requireSpecial":
                        if ("true".equals(value)
                                && !password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
                            return false;
                        }
                        break;
                }
            }
        }
        return true;
    }
}
