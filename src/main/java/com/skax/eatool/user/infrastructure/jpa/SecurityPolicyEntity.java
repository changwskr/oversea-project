package com.skax.eatool.user.infrastructure.jpa;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

/**
 * 보안 ?�책 JPA ?�티??
 */
@Entity
@Table(name = "security_policies")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SecurityPolicyEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "policy_name", nullable = false, length = 100)
    private String policyName;

    @Column(name = "policy_type", nullable = false, length = 50)
    private String policyType;

    @Column(name = "policy_value", columnDefinition = "TEXT")
    private String policyValue;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // ?�메??객체�?변??
    public com.skax.eatool.user.domain.SecurityPolicy toDomain() {
        return com.skax.eatool.user.domain.SecurityPolicy.builder()
                .id(this.id)
                .policyName(this.policyName)
                .policyType(this.policyType)
                .policyValue(this.policyValue)
                .description(this.description)
                .isActive(this.isActive)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }

    // ?�메??객체?�서 ?�티???�성
    public static SecurityPolicyEntity fromDomain(com.skax.eatool.user.domain.SecurityPolicy securityPolicy) {
        return SecurityPolicyEntity.builder()
                .id(securityPolicy.getId())
                .policyName(securityPolicy.getPolicyName())
                .policyType(securityPolicy.getPolicyType())
                .policyValue(securityPolicy.getPolicyValue())
                .description(securityPolicy.getDescription())
                .isActive(securityPolicy.isActive())
                .createdAt(securityPolicy.getCreatedAt())
                .updatedAt(securityPolicy.getUpdatedAt())
                .build();
    }
}
