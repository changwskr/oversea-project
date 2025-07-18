package com.skcc.oversea.techspec.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 기술 스펙 JPA 리포지토리
 */
@Repository
public interface TechSpecRepository extends JpaRepository<TechSpecEntity, Long> {

    /**
     * 활성화된 모든 기술 스펙 조회
     */
    List<TechSpecEntity> findByIsActiveTrue();

    /**
     * 카테고리별 기술 스펙 조회
     */
    List<TechSpecEntity> findByCategoryAndIsActiveTrue(String category);

    /**
     * 기술명으로 검색
     */
    List<TechSpecEntity> findByTechnologyNameContainingAndIsActiveTrue(String technologyName);

    /**
     * 설명으로 검색
     */
    List<TechSpecEntity> findByDescriptionContainingAndIsActiveTrue(String description);

    /**
     * 카테고리와 세부 항목으로 조회
     */
    Optional<TechSpecEntity> findByCategoryAndSubItemAndIsActiveTrue(String category, String subItem);

    /**
     * 기술명과 버전으로 조회
     */
    Optional<TechSpecEntity> findByTechnologyNameAndVersionAndIsActiveTrue(String technologyName, String version);

    /**
     * 복합 검색 (카테고리, 기술명, 서브아이템, 버전, 키워드)
     */
    @Query("SELECT t FROM TechSpecEntity t WHERE t.isActive = true " +
           "AND (:category IS NULL OR :category = '' OR t.category = :category) " +
           "AND (:technologyName IS NULL OR :technologyName = '' OR t.technologyName LIKE %:technologyName%) " +
           "AND (:subItem IS NULL OR :subItem = '' OR t.subItem LIKE %:subItem%) " +
           "AND (:version IS NULL OR :version = '' OR t.version LIKE %:version%) " +
           "AND (:keyword IS NULL OR :keyword = '' OR t.description LIKE %:keyword%) " +
           "ORDER BY t.category, t.subItem")
    List<TechSpecEntity> findBySearchCriteria(
            @Param("category") String category,
            @Param("technologyName") String technologyName,
            @Param("subItem") String subItem,
            @Param("version") String version,
            @Param("keyword") String keyword
    );

    /**
     * 모든 카테고리 조회
     */
    @Query("SELECT DISTINCT t.category FROM TechSpecEntity t WHERE t.isActive = true ORDER BY t.category")
    List<String> findAllCategories();

    /**
     * 카테고리별 기술 스펙 개수 조회
     */
    @Query("SELECT t.category, COUNT(t) FROM TechSpecEntity t WHERE t.isActive = true GROUP BY t.category ORDER BY t.category")
    List<Object[]> countByCategory();
} 