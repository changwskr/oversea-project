package com.skax.eatool.techspec.infrastructure.jpa;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.skax.eatool.techspec.domain.TechSpec;

import java.time.LocalDateTime;

/**
 * 기술 스펙 JPA 엔티티
 */
@Entity
@Table(name = "tech_spec")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TechSpecEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category", nullable = false, length = 100)
    private String category;

    @Column(name = "sub_item", nullable = false, length = 200)
    private String subItem;

    @Column(name = "technology_name", nullable = false, length = 200)
    private String technologyName;

    @Column(name = "version", length = 50)
    private String version;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "updated_by", length = 50)
    private String updatedBy;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    // 도메인 객체로 변환
    public TechSpec toDomain() {
        return TechSpec.builder()
                .id(this.id)
                .category(this.category)
                .subItem(this.subItem)
                .technologyName(this.technologyName)
                .version(this.version)
                .description(this.description)
                .createdDate(this.createdDate)
                .updatedDate(this.updatedDate)
                .createdBy(this.createdBy)
                .updatedBy(this.updatedBy)
                .isActive(this.isActive)
                .build();
    }

    // 도메인 객체에서 엔티티 생성
    public static TechSpecEntity fromDomain(TechSpec techSpec) {
        return TechSpecEntity.builder()
                .id(techSpec.getId())
                .category(techSpec.getCategory())
                .subItem(techSpec.getSubItem())
                .technologyName(techSpec.getTechnologyName())
                .version(techSpec.getVersion())
                .description(techSpec.getDescription())
                .createdDate(techSpec.getCreatedDate())
                .updatedDate(techSpec.getUpdatedDate())
                .createdBy(techSpec.getCreatedBy())
                .updatedBy(techSpec.getUpdatedBy())
                .isActive(techSpec.isActive())
                .build();
    }

    // 업데이트 메서드
    public void update(TechSpec techSpec) {
        this.category = techSpec.getCategory();
        this.subItem = techSpec.getSubItem();
        this.technologyName = techSpec.getTechnologyName();
        this.version = techSpec.getVersion();
        this.description = techSpec.getDescription();
        this.updatedDate = LocalDateTime.now();
        this.updatedBy = techSpec.getUpdatedBy();
        this.isActive = techSpec.isActive();
    }
}
