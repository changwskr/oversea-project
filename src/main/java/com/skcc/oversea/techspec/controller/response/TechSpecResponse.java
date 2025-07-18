package com.skcc.oversea.techspec.controller.response;

import com.skcc.oversea.techspec.domain.TechSpec;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

/**
 * 기술 스펙 응답 DTO
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TechSpecResponse {

    private Long id;
    private String category;
    private String subItem;
    private String technologyName;
    private String version;
    private String description;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String createdBy;
    private String updatedBy;
    private boolean isActive;

    /**
     * 도메인 객체에서 응답 DTO 생성
     */
    public static TechSpecResponse fromDomain(TechSpec techSpec) {
        return TechSpecResponse.builder()
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
} 