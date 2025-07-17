package com.skcc.oversea.user.infrastructure;

import com.skcc.oversea.user.domain.SecurityPolicy;
import com.skcc.oversea.user.infrastructure.jpa.SecurityPolicyEntity;
import com.skcc.oversea.user.infrastructure.jpa.SecurityPolicyRepositoryJpa;
import com.skcc.oversea.user.service.port.SecurityPolicyRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 보안 정책 JPA Repository 구현체
 */
@Repository("securityPolicyRepositoryPortJpaImpl")
@RequiredArgsConstructor
@Slf4j
public class SecurityPolicyRepositoryPortJpaImpl implements SecurityPolicyRepositoryPort {
    
    private final SecurityPolicyRepositoryJpa securityPolicyRepositoryJpa;
    
    @Override
    public SecurityPolicy save(SecurityPolicy securityPolicy) {
        log.info("[SecurityPolicyRepositoryPortJpaImpl] save START - policyName: {}", securityPolicy.getPolicyName());
        
        SecurityPolicyEntity entity = SecurityPolicyEntity.fromDomain(securityPolicy);
        SecurityPolicyEntity savedEntity = securityPolicyRepositoryJpa.save(entity);
        SecurityPolicy result = savedEntity.toDomain();
        
        log.info("[SecurityPolicyRepositoryPortJpaImpl] save END - policyId: {}", result.getId());
        return result;
    }
    
    @Override
    public Optional<SecurityPolicy> findById(Long id) {
        log.info("[SecurityPolicyRepositoryPortJpaImpl] findById START - id: {}", id);
        
        Optional<SecurityPolicy> result = securityPolicyRepositoryJpa.findById(id)
                .map(SecurityPolicyEntity::toDomain);
        
        log.info("[SecurityPolicyRepositoryPortJpaImpl] findById END - found: {}", result.isPresent());
        return result;
    }
    
    @Override
    public List<SecurityPolicy> findByPolicyType(String policyType) {
        log.info("[SecurityPolicyRepositoryPortJpaImpl] findByPolicyType START - policyType: {}", policyType);
        
        List<SecurityPolicy> result = securityPolicyRepositoryJpa.findByPolicyType(policyType)
                .stream()
                .map(SecurityPolicyEntity::toDomain)
                .collect(Collectors.toList());
        
        log.info("[SecurityPolicyRepositoryPortJpaImpl] findByPolicyType END - count: {}", result.size());
        return result;
    }
    
    @Override
    public List<SecurityPolicy> findActivePolicies() {
        log.info("[SecurityPolicyRepositoryPortJpaImpl] findActivePolicies START");
        
        List<SecurityPolicy> result = securityPolicyRepositoryJpa.findByIsActiveTrue()
                .stream()
                .map(SecurityPolicyEntity::toDomain)
                .collect(Collectors.toList());
        
        log.info("[SecurityPolicyRepositoryPortJpaImpl] findActivePolicies END - count: {}", result.size());
        return result;
    }
    
    @Override
    public List<SecurityPolicy> findByPolicyTypeAndActive(String policyType) {
        log.info("[SecurityPolicyRepositoryPortJpaImpl] findByPolicyTypeAndActive START - policyType: {}", policyType);
        
        List<SecurityPolicy> result = securityPolicyRepositoryJpa.findByPolicyTypeAndIsActiveTrue(policyType)
                .stream()
                .map(SecurityPolicyEntity::toDomain)
                .collect(Collectors.toList());
        
        log.info("[SecurityPolicyRepositoryPortJpaImpl] findByPolicyTypeAndActive END - count: {}", result.size());
        return result;
    }
    
    @Override
    public Optional<SecurityPolicy> findByPolicyName(String policyName) {
        log.info("[SecurityPolicyRepositoryPortJpaImpl] findByPolicyName START - policyName: {}", policyName);
        
        Optional<SecurityPolicy> result = securityPolicyRepositoryJpa.findByPolicyName(policyName)
                .map(SecurityPolicyEntity::toDomain);
        
        log.info("[SecurityPolicyRepositoryPortJpaImpl] findByPolicyName END - found: {}", result.isPresent());
        return result;
    }
    
    @Override
    public Optional<SecurityPolicy> findByPolicyTypeAndPolicyName(String policyType, String policyName) {
        log.info("[SecurityPolicyRepositoryPortJpaImpl] findByPolicyTypeAndPolicyName START - policyType: {}, policyName: {}", policyType, policyName);
        
        Optional<SecurityPolicy> result = securityPolicyRepositoryJpa.findByPolicyTypeAndPolicyName(policyType, policyName)
                .map(SecurityPolicyEntity::toDomain);
        
        log.info("[SecurityPolicyRepositoryPortJpaImpl] findByPolicyTypeAndPolicyName END - found: {}", result.isPresent());
        return result;
    }
    
    @Override
    public List<SecurityPolicy> findActivePasswordPolicies() {
        log.info("[SecurityPolicyRepositoryPortJpaImpl] findActivePasswordPolicies START");
        
        List<SecurityPolicy> result = securityPolicyRepositoryJpa.findActivePasswordPolicies()
                .stream()
                .map(SecurityPolicyEntity::toDomain)
                .collect(Collectors.toList());
        
        log.info("[SecurityPolicyRepositoryPortJpaImpl] findActivePasswordPolicies END - count: {}", result.size());
        return result;
    }
    
    @Override
    public List<SecurityPolicy> findActiveSessionPolicies() {
        log.info("[SecurityPolicyRepositoryPortJpaImpl] findActiveSessionPolicies START");
        
        List<SecurityPolicy> result = securityPolicyRepositoryJpa.findActiveSessionPolicies()
                .stream()
                .map(SecurityPolicyEntity::toDomain)
                .collect(Collectors.toList());
        
        log.info("[SecurityPolicyRepositoryPortJpaImpl] findActiveSessionPolicies END - count: {}", result.size());
        return result;
    }
    
    @Override
    public List<SecurityPolicy> findActiveIpWhitelistPolicies() {
        log.info("[SecurityPolicyRepositoryPortJpaImpl] findActiveIpWhitelistPolicies START");
        
        List<SecurityPolicy> result = securityPolicyRepositoryJpa.findActiveIpWhitelistPolicies()
                .stream()
                .map(SecurityPolicyEntity::toDomain)
                .collect(Collectors.toList());
        
        log.info("[SecurityPolicyRepositoryPortJpaImpl] findActiveIpWhitelistPolicies END - count: {}", result.size());
        return result;
    }
    
    @Override
    public List<SecurityPolicy> findAll() {
        log.info("[SecurityPolicyRepositoryPortJpaImpl] findAll START");
        
        List<SecurityPolicy> result = securityPolicyRepositoryJpa.findAll()
                .stream()
                .map(SecurityPolicyEntity::toDomain)
                .collect(Collectors.toList());
        
        log.info("[SecurityPolicyRepositoryPortJpaImpl] findAll END - count: {}", result.size());
        return result;
    }
    
    @Override
    public void deleteById(Long id) {
        log.info("[SecurityPolicyRepositoryPortJpaImpl] deleteById START - id: {}", id);
        
        securityPolicyRepositoryJpa.deleteById(id);
        
        log.info("[SecurityPolicyRepositoryPortJpaImpl] deleteById END - id: {}", id);
    }
    
    @Override
    public SecurityPolicy updateActiveStatus(Long id, boolean isActive) {
        log.info("[SecurityPolicyRepositoryPortJpaImpl] updateActiveStatus START - id: {}, isActive: {}", id, isActive);
        
        Optional<SecurityPolicyEntity> optionalEntity = securityPolicyRepositoryJpa.findById(id);
        if (optionalEntity.isPresent()) {
            SecurityPolicyEntity entity = optionalEntity.get();
            entity = SecurityPolicyEntity.builder()
                    .id(entity.getId())
                    .policyName(entity.getPolicyName())
                    .policyType(entity.getPolicyType())
                    .policyValue(entity.getPolicyValue())
                    .description(entity.getDescription())
                    .isActive(isActive)
                    .createdAt(entity.getCreatedAt())
                    .updatedAt(LocalDateTime.now())
                    .build();
            SecurityPolicyEntity savedEntity = securityPolicyRepositoryJpa.save(entity);
            SecurityPolicy result = savedEntity.toDomain();
            
            log.info("[SecurityPolicyRepositoryPortJpaImpl] updateActiveStatus END - policyId: {}", result.getId());
            return result;
        }
        
        log.error("[SecurityPolicyRepositoryPortJpaImpl] updateActiveStatus END - Security policy not found with id: {}", id);
        throw new RuntimeException("Security policy not found with id: " + id);
    }
} 