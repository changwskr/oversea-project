package com.skcc.oversea.techspec.application.port.in;

import com.skcc.oversea.techspec.domain.TechSpec;

import java.util.List;
import java.util.Optional;

/**
 * 기술 스펙 유스케이스 인터페이스
 */
public interface TechSpecUseCase {

    /**
     * 기술 스펙 생성
     */
    TechSpec createTechSpec(TechSpec techSpec);

    /**
     * 기술 스펙 조회 (ID)
     */
    Optional<TechSpec> getTechSpecById(Long id);

    /**
     * 모든 기술 스펙 조회
     */
    List<TechSpec> getAllTechSpecs();

    /**
     * 카테고리별 기술 스펙 조회
     */
    List<TechSpec> getTechSpecsByCategory(String category);

    /**
     * 기술 스펙 검색
     */
    List<TechSpec> searchTechSpecs(String category, String technologyName, String subItem, String version, String keyword);

    /**
     * 기술 스펙 업데이트
     */
    TechSpec updateTechSpec(Long id, TechSpec techSpec);

    /**
     * 기술 스펙 삭제 (논리적 삭제)
     */
    void deleteTechSpec(Long id);

    /**
     * 모든 카테고리 조회
     */
    List<String> getAllCategories();

    /**
     * 카테고리별 기술 스펙 개수 조회
     */
    List<Object[]> getTechSpecCountByCategory();

    /**
     * 기술 스펙 일괄 생성
     */
    List<TechSpec> createTechSpecs(List<TechSpec> techSpecs);

    /**
     * 기술 스펙 존재 여부 확인
     */
    boolean existsByCategoryAndSubItem(String category, String subItem);

    /**
     * 기술 스펙 존재 여부 확인 (기술명, 버전)
     */
    boolean existsByTechnologyNameAndVersion(String technologyName, String version);
} 