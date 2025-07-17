package com.skcc.oversea.user.controller;

import com.skcc.oversea.user.domain.SecurityPolicy;
import com.skcc.oversea.user.dto.ApiResponse;
import com.skcc.oversea.user.service.SecurityPolicyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * 보안 정책 컨트롤러
 */
@RestController
@RequestMapping("/api/security-policies")
@RequiredArgsConstructor
@Slf4j
public class SecurityPolicyController {
    
    private final SecurityPolicyService securityPolicyService;
    
    /**
     * 보안 정책 생성
     */
    @PostMapping
    public ApiResponse<SecurityPolicy> createPolicy(@RequestBody SecurityPolicy securityPolicy) {
        log.info("Creating security policy: {}", securityPolicy);
        SecurityPolicy createdPolicy = securityPolicyService.createPolicy(securityPolicy);
        return ApiResponse.ok(createdPolicy);
    }
    
    /**
     * 비밀번호 정책 생성
     */
    @PostMapping("/password")
    public ApiResponse<SecurityPolicy> createPasswordPolicy(
            @RequestParam String policyValue,
            @RequestParam String description) {
        log.info("Creating password policy: {}", policyValue);
        SecurityPolicy policy = securityPolicyService.createPasswordPolicy(policyValue, description);
        return ApiResponse.ok(policy);
    }
    
    /**
     * 세션 정책 생성
     */
    @PostMapping("/session")
    public ApiResponse<SecurityPolicy> createSessionPolicy(
            @RequestParam String policyValue,
            @RequestParam String description) {
        log.info("Creating session policy: {}", policyValue);
        SecurityPolicy policy = securityPolicyService.createSessionPolicy(policyValue, description);
        return ApiResponse.ok(policy);
    }
    
    /**
     * IP 화이트리스트 정책 생성
     */
    @PostMapping("/ip-whitelist")
    public ApiResponse<SecurityPolicy> createIpWhitelistPolicy(
            @RequestParam String policyValue,
            @RequestParam String description) {
        log.info("Creating IP whitelist policy: {}", policyValue);
        SecurityPolicy policy = securityPolicyService.createIpWhitelistPolicy(policyValue, description);
        return ApiResponse.ok(policy);
    }
    
    /**
     * 정책 조회
     */
    @GetMapping("/{id}")
    public ApiResponse<SecurityPolicy> getPolicyById(@PathVariable Long id) {
        log.info("Getting security policy by id: {}", id);
        Optional<SecurityPolicy> policy = securityPolicyService.getPolicyById(id);
        return policy.map(ApiResponse::ok)
                .orElse(ApiResponse.error("Policy not found"));
    }
    
    /**
     * 정책 타입별 조회
     */
    @GetMapping("/type/{policyType}")
    public ApiResponse<List<SecurityPolicy>> getPoliciesByType(@PathVariable String policyType) {
        log.info("Getting security policies by type: {}", policyType);
        List<SecurityPolicy> policies = securityPolicyService.getPoliciesByType(policyType);
        return ApiResponse.ok(policies);
    }
    
    /**
     * 활성화된 정책 조회
     */
    @GetMapping("/active")
    public ApiResponse<List<SecurityPolicy>> getActivePolicies() {
        log.info("Getting active security policies");
        List<SecurityPolicy> policies = securityPolicyService.getActivePolicies();
        return ApiResponse.ok(policies);
    }
    
    /**
     * 정책 타입별 활성화된 정책 조회
     */
    @GetMapping("/active/type/{policyType}")
    public ApiResponse<List<SecurityPolicy>> getActivePoliciesByType(@PathVariable String policyType) {
        log.info("Getting active security policies by type: {}", policyType);
        List<SecurityPolicy> policies = securityPolicyService.getActivePoliciesByType(policyType);
        return ApiResponse.ok(policies);
    }
    
    /**
     * 정책명으로 조회
     */
    @GetMapping("/name/{policyName}")
    public ApiResponse<SecurityPolicy> getPolicyByName(@PathVariable String policyName) {
        log.info("Getting security policy by name: {}", policyName);
        Optional<SecurityPolicy> policy = securityPolicyService.getPolicyByName(policyName);
        return policy.map(ApiResponse::ok)
                .orElse(ApiResponse.error("Policy not found"));
    }
    
    /**
     * 활성화된 비밀번호 정책 조회
     */
    @GetMapping("/password/active")
    public ApiResponse<List<SecurityPolicy>> getActivePasswordPolicies() {
        log.info("Getting active password policies");
        List<SecurityPolicy> policies = securityPolicyService.getActivePasswordPolicies();
        return ApiResponse.ok(policies);
    }
    
    /**
     * 활성화된 세션 정책 조회
     */
    @GetMapping("/session/active")
    public ApiResponse<List<SecurityPolicy>> getActiveSessionPolicies() {
        log.info("Getting active session policies");
        List<SecurityPolicy> policies = securityPolicyService.getActiveSessionPolicies();
        return ApiResponse.ok(policies);
    }
    
    /**
     * 활성화된 IP 화이트리스트 정책 조회
     */
    @GetMapping("/ip-whitelist/active")
    public ApiResponse<List<SecurityPolicy>> getActiveIpWhitelistPolicies() {
        log.info("Getting active IP whitelist policies");
        List<SecurityPolicy> policies = securityPolicyService.getActiveIpWhitelistPolicies();
        return ApiResponse.ok(policies);
    }
    
    /**
     * 모든 정책 조회
     */
    @GetMapping
    public ApiResponse<List<SecurityPolicy>> getAllPolicies() {
        log.info("Getting all security policies");
        List<SecurityPolicy> policies = securityPolicyService.getAllPolicies();
        return ApiResponse.ok(policies);
    }
    
    /**
     * 정책 업데이트
     */
    @PutMapping("/{id}")
    public ApiResponse<SecurityPolicy> updatePolicy(@PathVariable Long id, @RequestBody SecurityPolicy securityPolicy) {
        log.info("Updating security policy: id={}, policy={}", id, securityPolicy);
        securityPolicy = SecurityPolicy.builder()
                .id(id)
                .policyName(securityPolicy.getPolicyName())
                .policyType(securityPolicy.getPolicyType())
                .policyValue(securityPolicy.getPolicyValue())
                .description(securityPolicy.getDescription())
                .isActive(securityPolicy.isActive())
                .createdAt(securityPolicy.getCreatedAt())
                .updatedAt(securityPolicy.getUpdatedAt())
                .build();
        SecurityPolicy updatedPolicy = securityPolicyService.updatePolicy(securityPolicy);
        return ApiResponse.ok(updatedPolicy);
    }
    
    /**
     * 정책 활성화/비활성화
     */
    @PatchMapping("/{id}/status")
    public ApiResponse<SecurityPolicy> updatePolicyStatus(
            @PathVariable Long id,
            @RequestParam boolean isActive) {
        log.info("Updating security policy status: id={}, isActive={}", id, isActive);
        SecurityPolicy updatedPolicy = securityPolicyService.updatePolicyStatus(id, isActive);
        return ApiResponse.ok(updatedPolicy);
    }
    
    /**
     * 정책 삭제
     */
    @DeleteMapping("/{id}")
    public ApiResponse<String> deletePolicy(@PathVariable Long id) {
        log.info("Deleting security policy with id: {}", id);
        securityPolicyService.deletePolicy(id);
        return ApiResponse.ok("Policy deleted successfully");
    }
    
    /**
     * 비밀번호 정책 검증
     */
    @PostMapping("/validate-password")
    public ApiResponse<Boolean> validatePassword(@RequestParam String password) {
        log.info("Validating password against security policies");
        boolean isValid = securityPolicyService.validatePassword(password);
        return ApiResponse.ok(isValid);
    }
} 