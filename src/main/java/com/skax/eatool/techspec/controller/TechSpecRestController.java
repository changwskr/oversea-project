package com.skax.eatool.techspec.controller;

import com.skax.eatool.techspec.application.port.in.TechSpecUseCase;
import com.skax.eatool.techspec.controller.request.TechSpecCreateRequest;
import com.skax.eatool.techspec.controller.request.TechSpecUpdateRequest;
import com.skax.eatool.techspec.controller.response.TechSpecResponse;
import com.skax.eatool.techspec.domain.TechSpec;
import com.skax.eatool.user.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 기술 스펙 REST API 컨트롤러
 */
@RestController
@RequestMapping("/api/techspec")
@RequiredArgsConstructor
@Slf4j
public class TechSpecRestController {

    private final TechSpecUseCase techSpecUseCase;

    /**
     * 기술 스펙 생성
     */
    @PostMapping
    public ApiResponse<TechSpecResponse> createTechSpec(@Valid @RequestBody TechSpecCreateRequest request) {
        log.info("Creating tech spec: {}", request.getTechnologyName());

        TechSpec techSpec = techSpecUseCase.createTechSpec(request.toDomain());
        TechSpecResponse response = TechSpecResponse.fromDomain(techSpec);

        log.info("Created tech spec with ID: {}", techSpec.getId());
        return ApiResponse.ok(response);
    }

    /**
     * 기술 스펙 조회 (ID)
     */
    @GetMapping("/{id}")
    public ApiResponse<TechSpecResponse> getTechSpecById(@PathVariable Long id) {
        log.info("Getting tech spec by ID: {}", id);

        return techSpecUseCase.getTechSpecById(id)
                .map(techSpec -> {
                    TechSpecResponse response = TechSpecResponse.fromDomain(techSpec);
                    return ApiResponse.ok(response);
                })
                .orElse(ApiResponse.error("기술 스펙을 찾을 수 없습니다"));
    }

    /**
     * 모든 기술 스펙 조회
     */
    @GetMapping
    public ApiResponse<List<TechSpecResponse>> getAllTechSpecs() {
        log.info("Getting all tech specs");

        List<TechSpecResponse> responses = techSpecUseCase.getAllTechSpecs()
                .stream()
                .map(TechSpecResponse::fromDomain)
                .collect(Collectors.toList());

        return ApiResponse.ok(responses);
    }

    /**
     * 카테고리별 기술 스펙 조회
     */
    @GetMapping("/category/{category}")
    public ApiResponse<List<TechSpecResponse>> getTechSpecsByCategory(@PathVariable String category) {
        log.info("Getting tech specs by category: {}", category);

        List<TechSpecResponse> responses = techSpecUseCase.getTechSpecsByCategory(category)
                .stream()
                .map(TechSpecResponse::fromDomain)
                .collect(Collectors.toList());

        return ApiResponse.ok(responses);
    }

    /**
     * 기술 스펙 검색
     */
    @GetMapping("/search")
    public ApiResponse<List<TechSpecResponse>> searchTechSpecs(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String technologyName,
            @RequestParam(required = false) String subItem,
            @RequestParam(required = false) String version,
            @RequestParam(required = false) String keyword) {

        log.info("Searching tech specs - category: {}, technologyName: {}, subItem: {}, version: {}, keyword: {}",
                category, technologyName, subItem, version, keyword);

        List<TechSpecResponse> responses = techSpecUseCase
                .searchTechSpecs(category, technologyName, subItem, version, keyword)
                .stream()
                .map(TechSpecResponse::fromDomain)
                .collect(Collectors.toList());

        return ApiResponse.ok(responses);
    }

    /**
     * 기술 스펙 업데이트
     */
    @PutMapping("/{id}")
    public ApiResponse<TechSpecResponse> updateTechSpec(
            @PathVariable Long id,
            @Valid @RequestBody TechSpecUpdateRequest request) {

        log.info("Updating tech spec with ID: {}", id);

        TechSpec techSpec = techSpecUseCase.updateTechSpec(id, request.toDomain());
        TechSpecResponse response = TechSpecResponse.fromDomain(techSpec);

        log.info("Updated tech spec with ID: {}", id);
        return ApiResponse.ok(response);
    }

    /**
     * 기술 스펙 삭제
     */
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteTechSpec(@PathVariable Long id) {
        log.info("Deleting tech spec with ID: {}", id);

        techSpecUseCase.deleteTechSpec(id);

        log.info("Deleted tech spec with ID: {}", id);
        return ApiResponse.ok();
    }

    /**
     * 모든 카테고리 조회
     */
    @GetMapping("/categories")
    public ApiResponse<List<String>> getAllCategories() {
        log.info("Getting all categories");

        List<String> categories = techSpecUseCase.getAllCategories();
        return ApiResponse.ok(categories);
    }

    /**
     * 카테고리별 기술 스펙 개수 조회
     */
    @GetMapping("/categories/count")
    public ApiResponse<List<Object[]>> getTechSpecCountByCategory() {
        log.info("Getting tech spec count by category");

        List<Object[]> counts = techSpecUseCase.getTechSpecCountByCategory();
        return ApiResponse.ok(counts);
    }

    /**
     * 기술 스펙 일괄 생성
     */
    @PostMapping("/batch")
    public ApiResponse<List<TechSpecResponse>> createTechSpecs(
            @Valid @RequestBody List<TechSpecCreateRequest> requests) {
        log.info("Creating {} tech specs", requests.size());

        List<TechSpec> techSpecs = requests.stream()
                .map(TechSpecCreateRequest::toDomain)
                .collect(Collectors.toList());

        List<TechSpecResponse> responses = techSpecUseCase.createTechSpecs(techSpecs)
                .stream()
                .map(TechSpecResponse::fromDomain)
                .collect(Collectors.toList());

        log.info("Created {} tech specs", responses.size());
        return ApiResponse.ok(responses);
    }

    /**
     * 기술 스펙 존재 여부 확인
     */
    @GetMapping("/exists")
    public ApiResponse<Boolean> existsByCategoryAndSubItem(
            @RequestParam String category,
            @RequestParam String subItem) {

        log.info("Checking existence - category: {}, subItem: {}", category, subItem);

        boolean exists = techSpecUseCase.existsByCategoryAndSubItem(category, subItem);
        return ApiResponse.ok(exists);
    }
}
