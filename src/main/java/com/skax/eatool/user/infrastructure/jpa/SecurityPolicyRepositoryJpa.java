package com.skax.eatool.user.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 보안 정책 JPA Repository
 */
@Repository
public interface SecurityPolicyRepositoryJpa extends JpaRepository<SecurityPolicyEntity, Long> {

    // 정책 타입별 조회
    List<SecurityPolicyEntity> findByPolicyType(String policyType);

    // 활성화된 정책 조회
    List<SecurityPolicyEntity> findByIsActiveTrue();

    // 정책 타입별 활성화된 정책 조회
    List<SecurityPolicyEntity> findByPolicyTypeAndIsActiveTrue(String policyType);

    // 정책명으로 조회
    Optional<SecurityPolicyEntity> findByPolicyName(String policyName);

    // 정책 타입과 정책명으로 조회
    Optional<SecurityPolicyEntity> findByPolicyTypeAndPolicyName(String policyType, String policyName);

    // 활성화된 비밀번호 정책 조회
    @Query("SELECT sp FROM SecurityPolicyEntity sp WHERE sp.policyType = 'PASSWORD' AND sp.isActive = true")
    List<SecurityPolicyEntity> findActivePasswordPolicies();

    // 활성화된 세션 정책 조회
    @Query("SELECT sp FROM SecurityPolicyEntity sp WHERE sp.policyType = 'SESSION' AND sp.isActive = true")
    List<SecurityPolicyEntity> findActiveSessionPolicies();

    // 활성화된 IP 화이트리스트 정책 조회
    @Query("SELECT sp FROM SecurityPolicyEntity sp WHERE sp.policyType = 'IP_WHITELIST' AND sp.isActive = true")
    List<SecurityPolicyEntity> findActiveIpWhitelistPolicies();
}
