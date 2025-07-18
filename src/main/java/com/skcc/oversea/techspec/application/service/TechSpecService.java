package com.skcc.oversea.techspec.application.service;

import com.skcc.oversea.techspec.application.port.in.TechSpecUseCase;
import com.skcc.oversea.techspec.application.port.out.TechSpecPort;
import com.skcc.oversea.techspec.domain.TechSpec;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 기술 스펙 서비스
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class TechSpecService implements TechSpecUseCase {

    private final TechSpecPort techSpecPort;

    @Override
    public TechSpec createTechSpec(TechSpec techSpec) {
        log.info("Creating tech spec: {}", techSpec.getTechnologyName());
        
        // 중복 체크
        if (techSpecPort.existsByCategoryAndSubItem(techSpec.getCategory(), techSpec.getSubItem())) {
            throw new IllegalArgumentException("이미 존재하는 카테고리와 세부 항목입니다: " + 
                techSpec.getCategory() + " - " + techSpec.getSubItem());
        }

        // 생성 정보 설정
        techSpec.setCreatedDate(LocalDateTime.now());
        techSpec.setUpdatedDate(LocalDateTime.now());
        techSpec.setActive(true);

        TechSpec savedTechSpec = techSpecPort.save(techSpec);
        log.info("Created tech spec with ID: {}", savedTechSpec.getId());
        
        return savedTechSpec;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TechSpec> getTechSpecById(Long id) {
        log.info("Getting tech spec by ID: {}", id);
        return techSpecPort.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TechSpec> getAllTechSpecs() {
        log.info("Getting all tech specs");
        return techSpecPort.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TechSpec> getTechSpecsByCategory(String category) {
        log.info("Getting tech specs by category: {}", category);
        return techSpecPort.findByCategory(category);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TechSpec> searchTechSpecs(String category, String technologyName, String subItem, String version, String keyword) {
        log.info("Searching tech specs - category: {}, technologyName: {}, subItem: {}, version: {}, keyword: {}", 
                category, technologyName, subItem, version, keyword);
        return techSpecPort.findBySearchCriteria(category, technologyName, subItem, version, keyword);
    }

    @Override
    public TechSpec updateTechSpec(Long id, TechSpec techSpec) {
        log.info("Updating tech spec with ID: {}", id);
        
        TechSpec existingTechSpec = techSpecPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("기술 스펙을 찾을 수 없습니다: " + id));

        // 업데이트 정보 설정
        techSpec.setId(id);
        techSpec.setCreatedDate(existingTechSpec.getCreatedDate());
        techSpec.setCreatedBy(existingTechSpec.getCreatedBy());
        techSpec.setUpdatedDate(LocalDateTime.now());
        techSpec.setActive(true);

        TechSpec updatedTechSpec = techSpecPort.save(techSpec);
        log.info("Updated tech spec with ID: {}", id);
        
        return updatedTechSpec;
    }

    @Override
    public void deleteTechSpec(Long id) {
        log.info("Deleting tech spec with ID: {}", id);
        
        TechSpec techSpec = techSpecPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("기술 스펙을 찾을 수 없습니다: " + id));

        techSpec.setActive(false);
        techSpec.setUpdatedDate(LocalDateTime.now());
        
        techSpecPort.save(techSpec);
        log.info("Deleted tech spec with ID: {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getAllCategories() {
        log.info("Getting all categories");
        return techSpecPort.findAllCategories();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Object[]> getTechSpecCountByCategory() {
        log.info("Getting tech spec count by category");
        return techSpecPort.countByCategory();
    }

    @Override
    public List<TechSpec> createTechSpecs(List<TechSpec> techSpecs) {
        log.info("Creating {} tech specs", techSpecs.size());
        
        techSpecs.forEach(techSpec -> {
            techSpec.setCreatedDate(LocalDateTime.now());
            techSpec.setUpdatedDate(LocalDateTime.now());
            techSpec.setActive(true);
        });

        List<TechSpec> savedTechSpecs = techSpecPort.saveAll(techSpecs);
        log.info("Created {} tech specs", savedTechSpecs.size());
        
        return savedTechSpecs;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByCategoryAndSubItem(String category, String subItem) {
        return techSpecPort.existsByCategoryAndSubItem(category, subItem);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByTechnologyNameAndVersion(String technologyName, String version) {
        return techSpecPort.existsByTechnologyNameAndVersion(technologyName, version);
    }
} 