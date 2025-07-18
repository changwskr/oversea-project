package com.skcc.oversea.deposit.controller;

import com.skcc.oversea.deposit.dto.DepositDTO;
import com.skcc.oversea.deposit.dto.DepositStatistics;
import com.skcc.oversea.deposit.service.DepositService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * 예금 컨트롤러
 */
@Controller
@RequestMapping("/deposit")
@RequiredArgsConstructor
@Slf4j
public class DepositController {

    private final DepositService depositService;

    /**
     * 예금 메인 페이지
     */
    @GetMapping
    public String depositMain(Model model) {
        log.info("[DepositController] depositMain START");

        try {
            DepositStatistics statistics = depositService.getDepositStatistics();
            List<DepositDTO> recentDeposits = depositService.getRecentDeposits();

            model.addAttribute("title", "예금 관리");
            model.addAttribute("deposits", recentDeposits);
            model.addAttribute("totalDeposits", statistics.getTotalDeposits());
            model.addAttribute("totalActiveDeposits", statistics.getActiveDeposits());
            model.addAttribute("totalAmount", statistics.getTotalAmount());
            model.addAttribute("totalBalance", statistics.getTotalBalance());

            log.info("[DepositController] depositMain END - total deposits: {}", statistics.getTotalDeposits());
            return "deposit/main";
        } catch (Exception e) {
            log.error("Error in deposit main page: {}", e.getMessage(), e);
            model.addAttribute("errorMessage", "예금 정보를 불러오는 중 오류가 발생했습니다: " + e.getMessage());
            model.addAttribute("title", "예금 관리");
            return "deposit/main";
        }
    }

    /**
     * 예금 목록 페이지
     */
    @GetMapping("/list")
    public String depositList(
            @RequestParam(required = false) String accountNumber,
            @RequestParam(required = false) String cifNo,
            @RequestParam(required = false) String depositType,
            @RequestParam(required = false) String status,
            Model model) {

        log.info("[DepositController] depositList START");

        try {
            List<DepositDTO> deposits = depositService.searchDeposits(accountNumber, cifNo, null, depositType, status);

            model.addAttribute("title", "예금 목록");
            model.addAttribute("deposits", deposits);
            model.addAttribute("searchAccountNumber", accountNumber);
            model.addAttribute("searchCifNo", cifNo);
            model.addAttribute("searchDepositType", depositType);
            model.addAttribute("searchStatus", status);

            log.info("[DepositController] depositList END - filtered count: {}", deposits.size());
            return "deposit/list";
        } catch (Exception e) {
            log.error("Error in deposit list page: {}", e.getMessage(), e);
            model.addAttribute("errorMessage", "예금 목록을 불러오는 중 오류가 발생했습니다: " + e.getMessage());
            model.addAttribute("title", "예금 목록");
            return "deposit/list";
        }
    }

    /**
     * 예금 등록 페이지
     */
    @GetMapping("/register")
    public String registerForm(Model model) {
        log.info("[DepositController] registerForm START");

        DepositDTO depositDTO = DepositDTO.builder()
                .accountNumber("")
                .cifNo("")
                .cifName("")
                .depositType("")
                .amount(BigDecimal.ZERO)
                .currency("KRW")
                .interestRate(BigDecimal.ZERO)
                .status("A")
                .build();

        model.addAttribute("deposit", depositDTO);
        model.addAttribute("title", "예금 등록");

        log.info("[DepositController] registerForm END");
        return "deposit/register";
    }

    /**
     * 예금 등록 처리
     */
    @PostMapping("/register")
    public String register(@ModelAttribute DepositDTO depositDTO, Model model) {
        log.info("[DepositController] register START - accountNumber: {}", depositDTO.getAccountNumber());

        try {
            depositService.createDeposit(depositDTO);
            log.info("[DepositController] register END - success");
            return "redirect:/deposit/list";
        } catch (Exception e) {
            log.error("Error registering deposit: {}", e.getMessage(), e);
            model.addAttribute("error", "예금 등록 중 오류가 발생했습니다: " + e.getMessage());
            model.addAttribute("deposit", depositDTO);
            model.addAttribute("title", "예금 등록");
            return "deposit/register";
        }
    }

    /**
     * 예금 상세 페이지
     */
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        log.info("[DepositController] detail START - id: {}", id);

        try {
            var depositOpt = depositService.getDepositById(id);
            if (depositOpt.isPresent()) {
                model.addAttribute("deposit", depositOpt.get());
                model.addAttribute("title", "예금 상세");
                log.info("[DepositController] detail END - found");
                return "deposit/detail";
            } else {
                model.addAttribute("errorMessage", "예금을 찾을 수 없습니다.");
                model.addAttribute("title", "예금 상세");
                log.info("[DepositController] detail END - not found");
                return "deposit/detail";
            }
        } catch (Exception e) {
            log.error("Error in deposit detail page: {}", e.getMessage(), e);
            model.addAttribute("errorMessage", "예금 정보를 불러오는 중 오류가 발생했습니다: " + e.getMessage());
            model.addAttribute("title", "예금 상세");
            return "deposit/detail";
        }
    }

    /**
     * 예금 수정 페이지
     */
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        log.info("[DepositController] editForm START - id: {}", id);

        try {
            var depositOpt = depositService.getDepositById(id);
            if (depositOpt.isPresent()) {
                model.addAttribute("deposit", depositOpt.get());
                model.addAttribute("title", "예금 수정");
                log.info("[DepositController] editForm END - found");
                return "deposit/edit";
            } else {
                model.addAttribute("errorMessage", "예금을 찾을 수 없습니다.");
                model.addAttribute("title", "예금 수정");
                log.info("[DepositController] editForm END - not found");
                return "deposit/edit";
            }
        } catch (Exception e) {
            log.error("Error in deposit edit form: {}", e.getMessage(), e);
            model.addAttribute("errorMessage", "예금 정보를 불러오는 중 오류가 발생했습니다: " + e.getMessage());
            model.addAttribute("title", "예금 수정");
            return "deposit/edit";
        }
    }

    /**
     * 예금 수정 처리
     */
    @PostMapping("/update")
    public String update(@ModelAttribute DepositDTO depositDTO, Model model) {
        log.info("[DepositController] update START - id: {}", depositDTO.getDepositId());

        try {
            depositService.updateDeposit(depositDTO.getDepositId(), depositDTO);
            log.info("[DepositController] update END - success");
            return "redirect:/deposit/list";
        } catch (Exception e) {
            log.error("Error updating deposit: {}", e.getMessage(), e);
            model.addAttribute("error", "예금 수정 중 오류가 발생했습니다: " + e.getMessage());
            model.addAttribute("deposit", depositDTO);
            model.addAttribute("title", "예금 수정");
            return "deposit/edit";
        }
    }

    /**
     * 예금 삭제 처리
     */
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        log.info("[DepositController] delete START - id: {}", id);

        try {
            depositService.deleteDeposit(id);
            log.info("[DepositController] delete END - success");
            return "redirect:/deposit/list";
        } catch (Exception e) {
            log.error("Error deleting deposit: {}", e.getMessage(), e);
            return "redirect:/deposit/list";
        }
    }

    /**
     * 예금 상태 변경
     */
    @PostMapping("/status/{id}")
    @ResponseBody
    public String updateStatus(@PathVariable Long id, @RequestParam String status) {
        log.info("[DepositController] updateStatus START - id: {}, status: {}", id, status);

        try {
            depositService.updateDepositStatus(id, status);
            log.info("[DepositController] updateStatus END - success");
            return "success";
        } catch (Exception e) {
            log.error("Error updating deposit status: {}", e.getMessage(), e);
            return "error";
        }
    }

    /**
     * 예금 입금 처리
     */
    @PostMapping("/deposit-money/{id}")
    @ResponseBody
    public String depositMoney(@PathVariable Long id, @RequestParam BigDecimal amount) {
        log.info("[DepositController] depositMoney START - id: {}, amount: {}", id, amount);

        try {
            depositService.depositMoney(id, amount);
            log.info("[DepositController] depositMoney END - success");
            return "success";
        } catch (Exception e) {
            log.error("Error depositing money: {}", e.getMessage(), e);
            return "error: " + e.getMessage();
        }
    }

    /**
     * 예금 출금 처리
     */
    @PostMapping("/withdraw-money/{id}")
    @ResponseBody
    public String withdrawMoney(@PathVariable Long id, @RequestParam BigDecimal amount) {
        log.info("[DepositController] withdrawMoney START - id: {}, amount: {}", id, amount);

        try {
            depositService.withdrawMoney(id, amount);
            log.info("[DepositController] withdrawMoney END - success");
            return "success";
        } catch (Exception e) {
            log.error("Error withdrawing money: {}", e.getMessage(), e);
            return "error: " + e.getMessage();
        }
    }
}