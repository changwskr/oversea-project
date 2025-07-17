package com.skcc.oversea.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

/**
 * 보안 정책 도메인
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SecurityPolicy {
    
    private Long id;
    private String policyName;
    private String policyType; // PASSWORD, SESSION, IP_WHITELIST, etc.
    private String policyValue;
    private String description;
    private boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public static SecurityPolicy createPasswordPolicy(String policyValue, String description) {
        return SecurityPolicy.builder()
                .policyName("Password Policy")
                .policyType("PASSWORD")
                .policyValue(policyValue)
                .description(description)
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
    
    public static SecurityPolicy createSessionPolicy(String policyValue, String description) {
        return SecurityPolicy.builder()
                .policyName("Session Policy")
                .policyType("SESSION")
                .policyValue(policyValue)
                .description(description)
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
    
    public static SecurityPolicy createIpWhitelistPolicy(String policyValue, String description) {
        return SecurityPolicy.builder()
                .policyName("IP Whitelist")
                .policyType("IP_WHITELIST")
                .policyValue(policyValue)
                .description(description)
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
} 