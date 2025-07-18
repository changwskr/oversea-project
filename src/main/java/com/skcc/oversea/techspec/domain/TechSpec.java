package com.skcc.oversea.techspec.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

/**
 * 기술 스펙 도메인 엔티티
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TechSpec {

    private Long id;
    private String category;        // 분류 영역
    private String subItem;         // 세부 항목
    private String technologyName;  // 기술 명칭
    private String version;         // 버전
    private String description;     // 설명
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String createdBy;
    private String updatedBy;
    private boolean isActive;

    // Builder 패턴을 위한 정적 내부 클래스
    public static class TechSpecBuilder {
        private boolean isActive = true;
        private LocalDateTime createdDate = LocalDateTime.now();
        private LocalDateTime updatedDate = LocalDateTime.now();
    }
} 