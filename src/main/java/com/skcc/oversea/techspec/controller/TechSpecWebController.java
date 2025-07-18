package com.skcc.oversea.techspec.controller;

import com.skcc.oversea.techspec.application.port.in.TechSpecUseCase;
import com.skcc.oversea.techspec.controller.request.TechSpecCreateRequest;
import com.skcc.oversea.techspec.controller.request.TechSpecUpdateRequest;
import com.skcc.oversea.techspec.controller.response.TechSpecResponse;
import com.skcc.oversea.techspec.domain.TechSpec;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 기술 스펙 웹 컨트롤러
 */
@Controller
@RequestMapping("/techspec")
@RequiredArgsConstructor
@Slf4j
public class TechSpecWebController {

    private final TechSpecUseCase techSpecUseCase;

    /**
     * 기술 스펙 목록 페이지
     */
    @GetMapping
    public String listTechSpecs(Model model) {
        log.info("Showing tech specs list page");

        List<TechSpecResponse> techSpecs = techSpecUseCase.getAllTechSpecs()
                .stream()
                .map(TechSpecResponse::fromDomain)
                .collect(Collectors.toList());

        List<String> categories = techSpecUseCase.getAllCategories();

        model.addAttribute("techSpecs", techSpecs);
        model.addAttribute("categories", categories);

        return "techspec/list";
    }

    /**
     * 기술 스펙 상세 페이지
     */
    @GetMapping("/{id}")
    public String viewTechSpec(@PathVariable Long id, Model model) {
        log.info("Showing tech spec detail page for ID: {}", id);

        return techSpecUseCase.getTechSpecById(id)
                .map(techSpec -> {
                    TechSpecResponse response = TechSpecResponse.fromDomain(techSpec);
                    model.addAttribute("techSpec", response);
                    return "techspec/detail";
                })
                .orElse("redirect:/techspec");
    }

    /**
     * 기술 스펙 생성 페이지
     */
    @GetMapping("/create")
    public String createTechSpecForm(Model model) {
        log.info("Showing tech spec create form");

        List<String> categories = techSpecUseCase.getAllCategories();
        model.addAttribute("techSpec", new TechSpecCreateRequest());
        model.addAttribute("categories", categories);

        return "techspec/create";
    }

    /**
     * 기술 스펙 생성 처리
     */
    @PostMapping("/create")
    public String createTechSpec(@Valid @ModelAttribute("techSpec") TechSpecCreateRequest request,
            BindingResult result,
            RedirectAttributes redirectAttributes) {
        log.info("Creating tech spec: {}", request.getTechnologyName());

        if (result.hasErrors()) {
            log.warn("Validation errors occurred during tech spec creation");
            return "techspec/create";
        }

        try {
            TechSpec techSpec = techSpecUseCase.createTechSpec(request.toDomain());
            redirectAttributes.addFlashAttribute("message", "기술 스펙이 성공적으로 생성되었습니다.");
            log.info("Created tech spec with ID: {}", techSpec.getId());
            return "redirect:/techspec/" + techSpec.getId();
        } catch (Exception e) {
            log.error("Error creating tech spec", e);
            redirectAttributes.addFlashAttribute("error", "기술 스펙 생성 중 오류가 발생했습니다: " + e.getMessage());
            return "redirect:/techspec/create";
        }
    }

    /**
     * 기술 스펙 수정 페이지
     */
    @GetMapping("/{id}/edit")
    public String editTechSpecForm(@PathVariable Long id, Model model) {
        log.info("Showing tech spec edit form for ID: {}", id);

        return techSpecUseCase.getTechSpecById(id)
                .map(techSpec -> {
                    TechSpecUpdateRequest updateRequest = TechSpecUpdateRequest.builder()
                            .id(techSpec.getId())
                            .category(techSpec.getCategory())
                            .subItem(techSpec.getSubItem())
                            .technologyName(techSpec.getTechnologyName())
                            .version(techSpec.getVersion())
                            .description(techSpec.getDescription())
                            .build();

                    List<String> categories = techSpecUseCase.getAllCategories();

                    model.addAttribute("techSpec", updateRequest);
                    model.addAttribute("categories", categories);

                    return "techspec/edit";
                })
                .orElse("redirect:/techspec");
    }

    /**
     * 기술 스펙 수정 처리
     */
    @PostMapping("/{id}/edit")
    public String updateTechSpec(@PathVariable Long id,
            @Valid @ModelAttribute("techSpec") TechSpecUpdateRequest request,
            BindingResult result,
            RedirectAttributes redirectAttributes) {
        log.info("Updating tech spec with ID: {}", id);

        if (result.hasErrors()) {
            log.warn("Validation errors occurred during tech spec update");
            return "techspec/edit";
        }

        try {
            TechSpec techSpec = techSpecUseCase.updateTechSpec(id, request.toDomain());
            redirectAttributes.addFlashAttribute("message", "기술 스펙이 성공적으로 수정되었습니다.");
            log.info("Updated tech spec with ID: {}", id);
            return "redirect:/techspec/" + id;
        } catch (Exception e) {
            log.error("Error updating tech spec", e);
            redirectAttributes.addFlashAttribute("error", "기술 스펙 수정 중 오류가 발생했습니다: " + e.getMessage());
            return "redirect:/techspec/" + id + "/edit";
        }
    }

    /**
     * 기술 스펙 삭제 처리
     */
    @PostMapping("/{id}/delete")
    public String deleteTechSpec(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        log.info("Deleting tech spec with ID: {}", id);

        try {
            techSpecUseCase.deleteTechSpec(id);
            redirectAttributes.addFlashAttribute("message", "기술 스펙이 성공적으로 삭제되었습니다.");
            log.info("Deleted tech spec with ID: {}", id);
        } catch (Exception e) {
            log.error("Error deleting tech spec", e);
            redirectAttributes.addFlashAttribute("error", "기술 스펙 삭제 중 오류가 발생했습니다: " + e.getMessage());
        }

        return "redirect:/techspec";
    }

    /**
     * 카테고리별 기술 스펙 목록
     */
    @GetMapping("/category/{category}")
    public String listTechSpecsByCategory(@PathVariable String category, Model model) {
        log.info("Showing tech specs by category: {}", category);

        List<TechSpecResponse> techSpecs = techSpecUseCase.getTechSpecsByCategory(category)
                .stream()
                .map(TechSpecResponse::fromDomain)
                .collect(Collectors.toList());

        List<String> categories = techSpecUseCase.getAllCategories();

        model.addAttribute("techSpecs", techSpecs);
        model.addAttribute("categories", categories);
        model.addAttribute("selectedCategory", category);

        return "techspec/list";
    }

    /**
     * 기술 스펙 검색
     */
    @GetMapping("/search")
    public String searchTechSpecs(@RequestParam(required = false) String category,
            @RequestParam(required = false) String technologyName,
            @RequestParam(required = false) String subItem,
            @RequestParam(required = false) String version,
            @RequestParam(required = false) String keyword,
            Model model) {
        log.info("Searching tech specs - category: {}, technologyName: {}, subItem: {}, version: {}, keyword: {}",
                category, technologyName, subItem, version, keyword);

        // 검색 조건을 description으로 통합 (키워드 검색)
        String description = keyword;

        List<TechSpecResponse> techSpecs = techSpecUseCase
                .searchTechSpecs(category, technologyName, subItem, version, keyword)
                .stream()
                .map(TechSpecResponse::fromDomain)
                .collect(Collectors.toList());

        List<String> categories = techSpecUseCase.getAllCategories();

        model.addAttribute("techSpecs", techSpecs);
        model.addAttribute("categories", categories);
        model.addAttribute("searchCategory", category);
        model.addAttribute("searchTechnologyName", technologyName);
        model.addAttribute("searchSubItem", subItem);
        model.addAttribute("searchVersion", version);
        model.addAttribute("searchKeyword", keyword);

        return "techspec/search";
    }
}