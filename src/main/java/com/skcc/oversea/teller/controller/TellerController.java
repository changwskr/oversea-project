package com.skcc.oversea.teller.controller;

import com.skcc.oversea.teller.dto.TellerDTO;
import com.skcc.oversea.teller.service.TellerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 텔러 컨트롤러
 */
@Controller
@RequestMapping("/teller")
@RequiredArgsConstructor
@Slf4j
public class TellerController {

    private final TellerService tellerService;

    /**
     * 텔러 메인 페이지
     */
    @GetMapping
    public String tellerMain(Model model) {
        log.info("[TellerController] tellerMain START");

        try {
            List<TellerDTO> tellers = tellerService.getAllTellers();
            List<TellerDTO> activeTellers = tellerService.getActiveTellers();

            model.addAttribute("title", "텔러 관리");
            model.addAttribute("tellers", tellers);
            model.addAttribute("activeTellers", activeTellers);
            model.addAttribute("totalTellers", tellers.size());
            model.addAttribute("totalActiveTellers", activeTellers.size());

            log.info("[TellerController] tellerMain END - total tellers: {}", tellers.size());
            return "teller/main";
        } catch (Exception e) {
            log.error("Error in teller main page: {}", e.getMessage(), e);
            model.addAttribute("errorMessage", "텔러 정보를 불러오는 중 오류가 발생했습니다: " + e.getMessage());
            model.addAttribute("title", "텔러 관리");
            return "teller/main";
        }
    }

    /**
     * 텔러 목록 페이지
     */
    @GetMapping("/list")
    public String tellerList(
            @RequestParam(required = false) String tellerId,
            @RequestParam(required = false) String tellerName,
            @RequestParam(required = false) String branchCode,
            @RequestParam(required = false) String status,
            Model model) {

        log.info("[TellerController] tellerList START");

        try {
            List<TellerDTO> tellers = tellerService.getAllTellers();

            // 검색 필터링
            if (tellerId != null && !tellerId.trim().isEmpty()) {
                tellers = tellers.stream()
                        .filter(teller -> teller.getTellerId() != null &&
                                teller.getTellerId().toLowerCase().contains(tellerId.toLowerCase()))
                        .toList();
            }

            if (tellerName != null && !tellerName.trim().isEmpty()) {
                tellers = tellers.stream()
                        .filter(teller -> teller.getTellerName() != null &&
                                teller.getTellerName().toLowerCase().contains(tellerName.toLowerCase()))
                        .toList();
            }

            if (branchCode != null && !branchCode.trim().isEmpty()) {
                tellers = tellers.stream()
                        .filter(teller -> teller.getBranchCode() != null &&
                                teller.getBranchCode().toLowerCase().contains(branchCode.toLowerCase()))
                        .toList();
            }

            if (status != null && !status.trim().isEmpty()) {
                tellers = tellers.stream()
                        .filter(teller -> teller.getStatus() != null &&
                                teller.getStatus().equalsIgnoreCase(status))
                        .toList();
            }

            model.addAttribute("title", "텔러 목록");
            model.addAttribute("tellers", tellers);
            model.addAttribute("searchTellerId", tellerId);
            model.addAttribute("searchTellerName", tellerName);
            model.addAttribute("searchBranchCode", branchCode);
            model.addAttribute("searchStatus", status);

            log.info("[TellerController] tellerList END - filtered count: {}", tellers.size());
            return "teller/list";
        } catch (Exception e) {
            log.error("Error in teller list page: {}", e.getMessage(), e);
            model.addAttribute("errorMessage", "텔러 목록을 불러오는 중 오류가 발생했습니다: " + e.getMessage());
            model.addAttribute("title", "텔러 목록");
            return "teller/list";
        }
    }

    /**
     * 텔러 등록 페이지
     */
    @GetMapping("/register")
    public String registerForm(Model model) {
        log.info("[TellerController] registerForm START");

        TellerDTO tellerDTO = TellerDTO.builder()
                .tellerId("")
                .tellerName("")
                .branchCode("")
                .bankCode("")
                .status("ACTIVE")
                .build();

        model.addAttribute("teller", tellerDTO);
        model.addAttribute("title", "텔러 등록");

        log.info("[TellerController] registerForm END");
        return "teller/register";
    }

    /**
     * 텔러 등록 처리
     */
    @PostMapping("/register")
    public String register(@ModelAttribute TellerDTO tellerDTO, Model model) {
        log.info("[TellerController] register START - tellerId: {}", tellerDTO.getTellerId());

        try {
            tellerService.createTeller(tellerDTO);
            log.info("[TellerController] register END - success");
            return "redirect:/teller/list";
        } catch (Exception e) {
            log.error("Error registering teller: {}", e.getMessage(), e);
            model.addAttribute("error", "텔러 등록 중 오류가 발생했습니다: " + e.getMessage());
            model.addAttribute("teller", tellerDTO);
            model.addAttribute("title", "텔러 등록");
            return "teller/register";
        }
    }

    /**
     * 텔러 상세 페이지
     */
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        log.info("[TellerController] detail START - id: {}", id);

        try {
            var tellerOpt = tellerService.getTellerById(id);
            if (tellerOpt.isPresent()) {
                model.addAttribute("teller", tellerOpt.get());
                model.addAttribute("title", "텔러 상세");
                log.info("[TellerController] detail END - found");
                return "teller/detail";
            } else {
                model.addAttribute("errorMessage", "텔러를 찾을 수 없습니다.");
                model.addAttribute("title", "텔러 상세");
                log.info("[TellerController] detail END - not found");
                return "teller/detail";
            }
        } catch (Exception e) {
            log.error("Error in teller detail page: {}", e.getMessage(), e);
            model.addAttribute("errorMessage", "텔러 정보를 불러오는 중 오류가 발생했습니다: " + e.getMessage());
            model.addAttribute("title", "텔러 상세");
            return "teller/detail";
        }
    }

    /**
     * 텔러 수정 페이지
     */
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        log.info("[TellerController] editForm START - id: {}", id);

        try {
            var tellerOpt = tellerService.getTellerById(id);
            if (tellerOpt.isPresent()) {
                model.addAttribute("teller", tellerOpt.get());
                model.addAttribute("title", "텔러 수정");
                log.info("[TellerController] editForm END - found");
                return "teller/edit";
            } else {
                model.addAttribute("errorMessage", "텔러를 찾을 수 없습니다.");
                model.addAttribute("title", "텔러 수정");
                log.info("[TellerController] editForm END - not found");
                return "teller/edit";
            }
        } catch (Exception e) {
            log.error("Error in teller edit form: {}", e.getMessage(), e);
            model.addAttribute("errorMessage", "텔러 정보를 불러오는 중 오류가 발생했습니다: " + e.getMessage());
            model.addAttribute("title", "텔러 수정");
            return "teller/edit";
        }
    }

    /**
     * 텔러 수정 처리
     */
    @PostMapping("/update")
    public String update(@ModelAttribute TellerDTO tellerDTO, Model model) {
        log.info("[TellerController] update START - id: {}", tellerDTO.getId());

        try {
            tellerService.updateTeller(tellerDTO.getId(), tellerDTO);
            log.info("[TellerController] update END - success");
            return "redirect:/teller/list";
        } catch (Exception e) {
            log.error("Error updating teller: {}", e.getMessage(), e);
            model.addAttribute("error", "텔러 수정 중 오류가 발생했습니다: " + e.getMessage());
            model.addAttribute("teller", tellerDTO);
            model.addAttribute("title", "텔러 수정");
            return "teller/edit";
        }
    }

    /**
     * 텔러 삭제 처리
     */
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        log.info("[TellerController] delete START - id: {}", id);

        try {
            tellerService.deleteTeller(id);
            log.info("[TellerController] delete END - success");
            return "redirect:/teller/list";
        } catch (Exception e) {
            log.error("Error deleting teller: {}", e.getMessage(), e);
            return "redirect:/teller/list";
        }
    }

    /**
     * 텔러 상태 변경
     */
    @PostMapping("/status/{id}")
    @ResponseBody
    public String updateStatus(@PathVariable Long id, @RequestParam String status) {
        log.info("[TellerController] updateStatus START - id: {}, status: {}", id, status);

        try {
            tellerService.updateTellerStatus(id, status);
            log.info("[TellerController] updateStatus END - success");
            return "success";
        } catch (Exception e) {
            log.error("Error updating teller status: {}", e.getMessage(), e);
            return "error";
        }
    }
}