package com.skcc.oversea.techspec.controller.request;

import com.skcc.oversea.techspec.domain.TechSpec;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * 기술 스펙 생성 요청 DTO
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TechSpecCreateRequest {

    @NotBlank(message = "분류 영역은 필수입니다")
    @Size(max = 100, message = "분류 영역은 100자 이하여야 합니다")
    private String category;

    @NotBlank(message = "세부 항목은 필수입니다")
    @Size(max = 200, message = "세부 항목은 200자 이하여야 합니다")
    private String subItem;

    @NotBlank(message = "기술 명칭은 필수입니다")
    @Size(max = 200, message = "기술 명칭은 200자 이하여야 합니다")
    private String technologyName;

    @Size(max = 50, message = "버전은 50자 이하여야 합니다")
    private String version;

    @Size(max = 1000, message = "설명은 1000자 이하여야 합니다")
    private String description;

    private String createdBy;

    /**
     * 도메인 객체로 변환
     */
    public TechSpec toDomain() {
        return TechSpec.builder()
                .category(this.category)
                .subItem(this.subItem)
                .technologyName(this.technologyName)
                .version(this.version)
                .description(this.description)
                .createdBy(this.createdBy)
                .build();
    }
} 