package com.skcc.oversea.user.service.port;

import com.skcc.oversea.user.domain.SecurityPolicy;

import java.util.List;
import java.util.Optional;

/**
 * 보안 정책 Repository Port
 */
public interface SecurityPolicyRepositoryPort {
    
    // 보안 정책 저장
    SecurityPolicy save(SecurityPolicy securityPolicy);
    
    // ID로 조회
    Optional<SecurityPolicy> findById(Long id);
    
    // 정책 타입별 조회
    List<SecurityPolicy> findByPolicyType(String policyType);
    
    // 활성화된 정책 조회
    List<SecurityPolicy> findActivePolicies();
    
    // 정책 타입별 활성화된 정책 조회
    List<SecurityPolicy> findByPolicyTypeAndActive(String policyType);
    
    // 정책명으로 조회
    Optional<SecurityPolicy> findByPolicyName(String policyName);
    
    // 정책 타입과 정책명으로 조회
    Optional<SecurityPolicy> findByPolicyTypeAndPolicyName(String policyType, String policyName);
    
    // 활성화된 비밀번호 정책 조회
    List<SecurityPolicy> findActivePasswordPolicies();
    
    // 활성화된 세션 정책 조회
    List<SecurityPolicy> findActiveSessionPolicies();
    
    // 활성화된 IP 화이트리스트 정책 조회
    List<SecurityPolicy> findActiveIpWhitelistPolicies();
    
    // 모든 정책 조회
    List<SecurityPolicy> findAll();
    
    // 정책 삭제
    void deleteById(Long id);
    
    // 정책 활성화/비활성화
    SecurityPolicy updateActiveStatus(Long id, boolean isActive);
} 