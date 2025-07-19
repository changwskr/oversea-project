package com.skax.eatool.techspec.application.port.out;

import com.skax.eatool.techspec.domain.TechSpec;

import java.util.List;
import java.util.Optional;

/**
 * 기술 스펙 아웃바운드 포트 인터페이스
 */
public interface TechSpecPort {

    /**
     * 기술 스펙 저장
     */
    TechSpec save(TechSpec techSpec);

    /**
     * 기술 스펙 조회 (ID)
     */
    Optional<TechSpec> findById(Long id);

    /**
     * 모든 기술 스펙 조회
     */
    List<TechSpec> findAll();

    /**
     * 카테고리별 기술 스펙 조회
     */
    List<TechSpec> findByCategory(String category);

    /**
     * 기술 스펙 검색
     */
    List<TechSpec> findBySearchCriteria(String category, String technologyName, String subItem, String version,
            String keyword);

    /**
     * 기술 스펙 삭제 (물리적 삭제)
     */
    void deleteById(Long id);

    /**
     * 모든 카테고리 조회
     */
    List<String> findAllCategories();

    /**
     * 카테고리별 기술 스펙 개수 조회
     */
    List<Object[]> countByCategory();

    /**
     * 기술 스펙 일괄 저장
     */
    List<TechSpec> saveAll(List<TechSpec> techSpecs);

    /**
     * 기술 스펙 존재 여부 확인
     */
    boolean existsByCategoryAndSubItem(String category, String subItem);

    /**
     * 기술 스펙 존재 여부 확인 (기술명과 버전)
     */
    boolean existsByTechnologyNameAndVersion(String technologyName, String version);
}
