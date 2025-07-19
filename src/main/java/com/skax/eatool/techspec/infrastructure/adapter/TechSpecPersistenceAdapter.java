package com.skax.eatool.techspec.infrastructure.adapter;

import com.skax.eatool.techspec.application.port.out.TechSpecPort;
import com.skax.eatool.techspec.domain.TechSpec;
import com.skax.eatool.techspec.infrastructure.jpa.TechSpecEntity;
import com.skax.eatool.techspec.infrastructure.jpa.TechSpecRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 기술 스펙 퍼시스턴스 어댑터
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class TechSpecPersistenceAdapter implements TechSpecPort {

    private final TechSpecRepository techSpecRepository;

    @Override
    public TechSpec save(TechSpec techSpec) {
        log.info("Saving tech spec: {}", techSpec.getTechnologyName());

        TechSpecEntity entity = TechSpecEntity.fromDomain(techSpec);
        TechSpecEntity savedEntity = techSpecRepository.save(entity);

        log.info("Saved tech spec with ID: {}", savedEntity.getId());
        return savedEntity.toDomain();
    }

    @Override
    public Optional<TechSpec> findById(Long id) {
        log.info("Finding tech spec by ID: {}", id);

        return techSpecRepository.findById(id)
                .map(TechSpecEntity::toDomain);
    }

    @Override
    public List<TechSpec> findAll() {
        log.info("Finding all tech specs");

        return techSpecRepository.findByIsActiveTrue()
                .stream()
                .map(TechSpecEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<TechSpec> findByCategory(String category) {
        log.info("Finding tech specs by category: {}", category);

        return techSpecRepository.findByCategoryAndIsActiveTrue(category)
                .stream()
                .map(TechSpecEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<TechSpec> findBySearchCriteria(String category, String technologyName, String subItem, String version,
            String keyword) {
        log.info(
                "Finding tech specs by search criteria - category: {}, technologyName: {}, subItem: {}, version: {}, keyword: {}",
                category, technologyName, subItem, version, keyword);

        return techSpecRepository.findBySearchCriteria(category, technologyName, subItem, version, keyword)
                .stream()
                .map(TechSpecEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting tech spec by ID: {}", id);

        TechSpecEntity entity = techSpecRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("기술 스펙을 찾을 수 없습니다: " + id));

        entity.setActive(false);
        techSpecRepository.save(entity);

        log.info("Deleted tech spec with ID: {}", id);
    }

    @Override
    public List<String> findAllCategories() {
        log.info("Finding all categories");
        return techSpecRepository.findAllCategories();
    }

    @Override
    public List<Object[]> countByCategory() {
        log.info("Counting tech specs by category");
        return techSpecRepository.countByCategory();
    }

    @Override
    public List<TechSpec> saveAll(List<TechSpec> techSpecs) {
        log.info("Saving {} tech specs", techSpecs.size());

        List<TechSpecEntity> entities = techSpecs.stream()
                .map(TechSpecEntity::fromDomain)
                .collect(Collectors.toList());

        List<TechSpecEntity> savedEntities = techSpecRepository.saveAll(entities);

        log.info("Saved {} tech specs", savedEntities.size());
        return savedEntities.stream()
                .map(TechSpecEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByCategoryAndSubItem(String category, String subItem) {
        return techSpecRepository.findByCategoryAndSubItemAndIsActiveTrue(category, subItem).isPresent();
    }

    @Override
    public boolean existsByTechnologyNameAndVersion(String technologyName, String version) {
        return techSpecRepository.findByTechnologyNameAndVersionAndIsActiveTrue(technologyName, version).isPresent();
    }
}
