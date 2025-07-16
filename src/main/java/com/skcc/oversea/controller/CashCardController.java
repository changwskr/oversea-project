package com.skcc.oversea.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.skcc.oversea.cashCard.business.facade.CashCardManagementSBBean;
import com.skcc.oversea.cashCard.business.cashCard.model.CashCardDDTO;
import com.skcc.oversea.cashCard.business.cashCard.model.HotCardDDTO;
import com.skcc.oversea.framework.transfer.CosesCommonDTO;
import com.skcc.oversea.framework.exception.CosesAppException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Cash Card 서비스 컨트롤러
 * 카드 발급, 조회, 관리, 핫카드 관리 기능 제공
 */
@Controller
@RequestMapping("/cashcard")
public class CashCardController {

    private static final Logger logger = LoggerFactory.getLogger(CashCardController.class);
    
    @Autowired
    private CashCardManagementSBBean cashCardManagementSBBean;

    /**
     * Cash Card 메인 페이지
     */
    @GetMapping
    public String cashCardMain(Model model) {
        logger.info("==================[CashCardController.cashCardMain START]");
        try {
            model.addAttribute("title", "Cash Card Management");
            model.addAttribute("serviceName", "Cash Card");
            model.addAttribute("description", "현금카드 발급, 관리, 조회 서비스");
            logger.info("==================[CashCardController.cashCardMain END]");
            return "service/cashcard";
        } catch (Exception e) {
            logger.error("==================[CashCardController.cashCardMain ERROR] - {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 카드 발급 페이지
     */
    @GetMapping("/issue")
    public String cardIssue(Model model) {
        logger.info("==================[CashCardController.cardIssue START]");
        try {
            model.addAttribute("title", "카드 발급");
            model.addAttribute("pageTitle", "현금카드 발급");
            model.addAttribute("description", "새로운 현금카드를 발급합니다");
            logger.info("==================[CashCardController.cardIssue END]");
            return "cashcard/issue";
        } catch (Exception e) {
            logger.error("==================[CashCardController.cardIssue ERROR] - {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 카드 조회 페이지
     */
    @GetMapping("/search")
    public String cardSearch(Model model) {
        logger.info("==================[CashCardController.cardSearch START]");
        try {
            model.addAttribute("title", "카드 조회");
            model.addAttribute("pageTitle", "현금카드 조회");
            model.addAttribute("description", "현금카드 정보를 조회합니다");
            logger.info("==================[CashCardController.cardSearch END]");
            return "cashcard/search";
        } catch (Exception e) {
            logger.error("==================[CashCardController.cardSearch ERROR] - {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 카드 관리 페이지
     */
    @GetMapping("/manage")
    public String cardManage(Model model) {
        logger.info("==================[CashCardController.cardManage START]");
        try {
            model.addAttribute("title", "카드 관리");
            model.addAttribute("pageTitle", "현금카드 관리");
            model.addAttribute("description", "현금카드 정보를 관리합니다");
            logger.info("==================[CashCardController.cardManage END]");
            return "cashcard/manage";
        } catch (Exception e) {
            logger.error("==================[CashCardController.cardManage ERROR] - {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 핫카드 관리 페이지
     */
    @GetMapping("/hotcard")
    public String hotCardManage(Model model) {
        logger.info("==================[CashCardController.hotCardManage START]");
        try {
            model.addAttribute("title", "핫카드 관리");
            model.addAttribute("pageTitle", "핫카드 관리");
            model.addAttribute("description", "분실/도난 카드를 관리합니다");
            logger.info("==================[CashCardController.hotCardManage END]");
            return "cashcard/hotcard";
        } catch (Exception e) {
            logger.error("==================[CashCardController.hotCardManage ERROR] - {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 카드 상세 정보 페이지
     */
    @GetMapping("/detail")
    public String cardDetail(@RequestParam String cardNumber, Model model) {
        logger.info("==================[CashCardController.cardDetail START] - 카드번호: {}", cardNumber);
        try {
            model.addAttribute("title", "카드 상세 정보");
            model.addAttribute("pageTitle", "카드 상세 정보");
            model.addAttribute("cardNumber", cardNumber);
            model.addAttribute("description", "카드 상세 정보를 확인합니다");
            logger.info("==================[CashCardController.cardDetail END] - 카드번호: {}", cardNumber);
            return "cashcard/detail";
        } catch (Exception e) {
            logger.error("==================[CashCardController.cardDetail ERROR] - 카드번호: {}, 에러: {}", cardNumber, e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 카드 발급 신청 페이지
     */
    @GetMapping("/issue/apply")
    public String cardIssueApply(Model model) {
        logger.info("==================[CashCardController.cardIssueApply START]");
        try {
            model.addAttribute("title", "카드 발급 신청");
            model.addAttribute("pageTitle", "카드 발급 신청");
            model.addAttribute("description", "카드 발급을 신청합니다");
            logger.info("==================[CashCardController.cardIssueApply END]");
            return "cashcard/issue-apply";
        } catch (Exception e) {
            logger.error("==================[CashCardController.cardIssueApply ERROR] - {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 카드 발급 신청서 제출 처리
     */
    @PostMapping("/issue/apply")
    public String submitCardIssueApply(@RequestParam String customerName,
                                      @RequestParam String customerId,
                                      @RequestParam String phoneNumber,
                                      @RequestParam(required = false) String email,
                                      @RequestParam String address,
                                      @RequestParam String accountNumber,
                                      @RequestParam String accountType,
                                      @RequestParam String branchCode,
                                      @RequestParam String bankCode,
                                      @RequestParam String cardType,
                                      @RequestParam(required = false) String cardDesign,
                                      @RequestParam String dailyLimit,
                                      @RequestParam String deliveryMethod,
                                      @RequestParam(required = false) String marketingAgreement,
                                      RedirectAttributes redirectAttributes) {
        logger.info("==================[CashCardController.submitCardIssueApply START] - 고객명: {}, 계좌번호: {}", customerName, accountNumber);
        try {
            // CashCardDDTO 생성 및 설정
            CashCardDDTO cashCardDDTO = new CashCardDDTO();
            cashCardDDTO.setCIFName(customerName);
            cashCardDDTO.setCIFNo(customerId);
            cashCardDDTO.setPrimaryAccountNo(accountNumber);
            cashCardDDTO.setBankCode(bankCode);
            cashCardDDTO.setBranchCode(branchCode);
            cashCardDDTO.setType(cardType);
            cashCardDDTO.setDailyLimitAmount(new BigDecimal(dailyLimit));
            cashCardDDTO.setDailyLimitCcy("KRW");
            cashCardDDTO.setStatus("PENDING"); // 승인 대기 상태
            
            // 카드 번호 생성 (Facade를 통해 처리)
            String cardNumber = cashCardManagementSBBean.generateCardNumber();
            cashCardDDTO.setCardNumber(cardNumber);
            
            // 시퀀스 번호 설정
            cashCardDDTO.setSequenceNo(1);
            
            // 등록 정보 설정
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmss");
            cashCardDDTO.setRegisterDate(now.format(dateFormatter));
            cashCardDDTO.setRegisterTime(now.format(timeFormatter));
            cashCardDDTO.setRegisterBy("SYSTEM");
            
            // CosesCommonDTO 생성
            CosesCommonDTO commonDTO = new CosesCommonDTO();
            commonDTO.setUserId("SYSTEM");
            commonDTO.setBankCode(bankCode);
            commonDTO.setBranchCode(branchCode);
            commonDTO.setBusinessDate(now.format(dateFormatter));
            commonDTO.setSystemDate(now.format(dateFormatter));
            commonDTO.setSystemInTime(now.format(timeFormatter));
            commonDTO.setTransactionNo("TXN" + System.currentTimeMillis());
            
            // Facade를 통해 카드 발급 처리 (Controller -> Facade -> Rule -> Thing)
            CashCardDDTO result = cashCardManagementSBBean.issueCard(cashCardDDTO, commonDTO);
            
            logger.info("카드 발급 신청서 제출 완료 - 고객명: {}, 계좌번호: {}, 카드번호: {}", customerName, accountNumber, cardNumber);
            
            // 성공 메시지 추가
            redirectAttributes.addFlashAttribute("successMessage", "카드 발급 신청이 성공적으로 제출되었습니다.");
            redirectAttributes.addFlashAttribute("applicationInfo", 
                String.format("고객명: %s, 계좌번호: %s, 카드종류: %s, 카드번호: %s", customerName, accountNumber, cardType, cardNumber));
            
            logger.info("==================[CashCardController.submitCardIssueApply END] - 고객명: {}, 계좌번호: {}", customerName, accountNumber);
            return "redirect:/cashcard/issue/apply/complete";
        } catch (CosesAppException e) {
            logger.error("==================[CashCardController.submitCardIssueApply COSES_ERROR] - 고객명: {}, 계좌번호: {}, 에러: {}", 
                customerName, accountNumber, e.getMessage(), e);
            redirectAttributes.addFlashAttribute("errorMessage", "카드 발급 신청 중 오류가 발생했습니다: " + e.getMessage());
            return "redirect:/cashcard/issue/apply";
        } catch (Exception e) {
            logger.error("==================[CashCardController.submitCardIssueApply ERROR] - 고객명: {}, 계좌번호: {}, 에러: {}", 
                customerName, accountNumber, e.getMessage(), e);
            redirectAttributes.addFlashAttribute("errorMessage", "카드 발급 신청 중 오류가 발생했습니다: " + e.getMessage());
            return "redirect:/cashcard/issue/apply";
        }
    }
    


    /**
     * 카드 발급 신청 완료 페이지
     */
    @GetMapping("/issue/apply/complete")
    public String cardIssueApplyComplete(Model model) {
        logger.info("==================[CashCardController.cardIssueApplyComplete START]");
        try {
            model.addAttribute("title", "카드 발급 신청 완료");
            model.addAttribute("pageTitle", "카드 발급 신청 완료");
            model.addAttribute("description", "카드 발급 신청이 완료되었습니다");
            logger.info("==================[CashCardController.cardIssueApplyComplete END]");
            return "cashcard/issue-apply-complete";
        } catch (Exception e) {
            logger.error("==================[CashCardController.cardIssueApplyComplete ERROR] - {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 카드 발급 승인 페이지
     */
    @GetMapping("/issue/approve")
    public String cardIssueApprove(Model model) {
        logger.info("==================[CashCardController.cardIssueApprove START]");
        try {
            model.addAttribute("title", "카드 발급 승인");
            model.addAttribute("pageTitle", "카드 발급 승인");
            model.addAttribute("description", "카드 발급 신청을 승인합니다");
            logger.info("==================[CashCardController.cardIssueApprove END]");
            return "cashcard/issue-approve";
        } catch (Exception e) {
            logger.error("==================[CashCardController.cardIssueApprove ERROR] - {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 핫카드 등록 페이지
     */
    @GetMapping("/hotcard/register")
    public String hotCardRegister(Model model) {
        logger.info("==================[CashCardController.hotCardRegister START]");
        try {
            model.addAttribute("title", "핫카드 등록");
            model.addAttribute("pageTitle", "핫카드 등록");
            model.addAttribute("description", "분실/도난 카드를 등록합니다");
            logger.info("==================[CashCardController.hotCardRegister END]");
            return "cashcard/hotcard-register";
        } catch (Exception e) {
            logger.error("==================[CashCardController.hotCardRegister ERROR] - {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 핫카드 해제 페이지
     */
    @GetMapping("/hotcard/release")
    public String hotCardRelease(Model model) {
        logger.info("==================[CashCardController.hotCardRelease START]");
        try {
            model.addAttribute("title", "핫카드 해제");
            model.addAttribute("pageTitle", "핫카드 해제");
            model.addAttribute("description", "핫카드 상태를 해제합니다");
            logger.info("==================[CashCardController.hotCardRelease END]");
            return "cashcard/hotcard-release";
        } catch (Exception e) {
            logger.error("==================[CashCardController.hotCardRelease ERROR] - {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 카드 정보 조회 처리
     */
    @PostMapping("/search")
    public String searchCard(@RequestParam String cardNumber, Model model) {
        logger.info("==================[CashCardController.searchCard START] - 카드번호: {}", cardNumber);
        try {
            // CosesCommonDTO 생성
            CosesCommonDTO commonDTO = new CosesCommonDTO();
            commonDTO.setUserId("SYSTEM");
            commonDTO.setBankCode("03");
            commonDTO.setBranchCode("001");
            commonDTO.setBusinessDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
            commonDTO.setSystemDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
            commonDTO.setSystemInTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmmss")));
            commonDTO.setTransactionNo("TXN" + System.currentTimeMillis());
            
            // Facade를 통해 카드 정보 조회 (Controller -> Facade -> Rule -> Thing)
            CashCardDDTO result = cashCardManagementSBBean.getCardInfo(cardNumber, commonDTO);
            
            model.addAttribute("title", "카드 조회 결과");
            model.addAttribute("pageTitle", "카드 조회 결과");
            model.addAttribute("cardInfo", result);
            model.addAttribute("successMessage", "카드 정보를 성공적으로 조회했습니다.");
            
            logger.info("==================[CashCardController.searchCard END] - 카드번호: {}", cardNumber);
            return "cashcard/search-result";
        } catch (CosesAppException e) {
            logger.error("==================[CashCardController.searchCard COSES_ERROR] - 카드번호: {}, 에러: {}", 
                cardNumber, e.getMessage(), e);
            model.addAttribute("errorMessage", "카드 조회 중 오류가 발생했습니다: " + e.getMessage());
            return "cashcard/search";
        } catch (Exception e) {
            logger.error("==================[CashCardController.searchCard ERROR] - 카드번호: {}, 에러: {}", 
                cardNumber, e.getMessage(), e);
            model.addAttribute("errorMessage", "카드 조회 중 오류가 발생했습니다: " + e.getMessage());
            return "cashcard/search";
        }
    }

    /**
     * 카드 정보 수정 처리
     */
    @PostMapping("/manage/update")
    public String updateCard(@RequestParam String cardNumber,
                            @RequestParam String dailyLimit,
                            @RequestParam String status,
                            @RequestParam(required = false) String amendReason,
                            RedirectAttributes redirectAttributes) {
        logger.info("==================[CashCardController.updateCard START] - 카드번호: {}", cardNumber);
        try {
            // CashCardDDTO 생성 및 설정
            CashCardDDTO cashCardDDTO = new CashCardDDTO();
            cashCardDDTO.setCardNumber(cardNumber);
            cashCardDDTO.setDailyLimitAmount(new BigDecimal(dailyLimit));
            cashCardDDTO.setStatus(status);
            cashCardDDTO.setAmendReason(amendReason);
            
            // CosesCommonDTO 생성
            CosesCommonDTO commonDTO = new CosesCommonDTO();
            commonDTO.setUserId("SYSTEM");
            commonDTO.setBankCode("03");
            commonDTO.setBranchCode("001");
            commonDTO.setBusinessDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
            commonDTO.setSystemDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
            commonDTO.setSystemInTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmmss")));
            commonDTO.setTransactionNo("TXN" + System.currentTimeMillis());
            
            // Facade를 통해 카드 정보 수정 (Controller -> Facade -> Rule -> Thing)
            CashCardDDTO result = cashCardManagementSBBean.updateCardInfo(cashCardDDTO, commonDTO);
            
            redirectAttributes.addFlashAttribute("successMessage", "카드 정보가 성공적으로 수정되었습니다.");
            redirectAttributes.addFlashAttribute("cardNumber", cardNumber);
            
            logger.info("==================[CashCardController.updateCard END] - 카드번호: {}", cardNumber);
            return "redirect:/cashcard/manage";
        } catch (CosesAppException e) {
            logger.error("==================[CashCardController.updateCard COSES_ERROR] - 카드번호: {}, 에러: {}", 
                cardNumber, e.getMessage(), e);
            redirectAttributes.addFlashAttribute("errorMessage", "카드 정보 수정 중 오류가 발생했습니다: " + e.getMessage());
            return "redirect:/cashcard/manage";
        } catch (Exception e) {
            logger.error("==================[CashCardController.updateCard ERROR] - 카드번호: {}, 에러: {}", 
                cardNumber, e.getMessage(), e);
            redirectAttributes.addFlashAttribute("errorMessage", "카드 정보 수정 중 오류가 발생했습니다: " + e.getMessage());
            return "redirect:/cashcard/manage";
        }
    }

    /**
     * 핫카드 등록 처리
     */
    @PostMapping("/hotcard/register")
    public String registerHotCard(@RequestParam String cardNumber,
                                 @RequestParam String reason,
                                 @RequestParam String reportDate,
                                 @RequestParam String reportTime,
                                 RedirectAttributes redirectAttributes) {
        logger.info("==================[CashCardController.registerHotCard START] - 카드번호: {}", cardNumber);
        try {
            // HotCardDDTO 생성 및 설정
            HotCardDDTO hotCardDDTO = new HotCardDDTO();
            hotCardDDTO.setCardNumber(cardNumber);
            hotCardDDTO.setReason(reason);
            hotCardDDTO.setReportDate(reportDate);
            hotCardDDTO.setReportTime(reportTime);
            hotCardDDTO.setStatus("ACTIVE");
            
            // CosesCommonDTO 생성
            CosesCommonDTO commonDTO = new CosesCommonDTO();
            commonDTO.setUserId("SYSTEM");
            commonDTO.setBankCode("03");
            commonDTO.setBranchCode("001");
            commonDTO.setBusinessDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
            commonDTO.setSystemDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
            commonDTO.setSystemInTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmmss")));
            commonDTO.setTransactionNo("TXN" + System.currentTimeMillis());
            
            // Facade를 통해 핫카드 등록 (Controller -> Facade -> Rule -> Thing)
            HotCardDDTO result = cashCardManagementSBBean.registerHotCard(hotCardDDTO, commonDTO);
            
            redirectAttributes.addFlashAttribute("successMessage", "핫카드가 성공적으로 등록되었습니다.");
            redirectAttributes.addFlashAttribute("cardNumber", cardNumber);
            
            logger.info("==================[CashCardController.registerHotCard END] - 카드번호: {}", cardNumber);
            return "redirect:/cashcard/hotcard";
        } catch (CosesAppException e) {
            logger.error("==================[CashCardController.registerHotCard COSES_ERROR] - 카드번호: {}, 에러: {}", 
                cardNumber, e.getMessage(), e);
            redirectAttributes.addFlashAttribute("errorMessage", "핫카드 등록 중 오류가 발생했습니다: " + e.getMessage());
            return "redirect:/cashcard/hotcard/register";
        } catch (Exception e) {
            logger.error("==================[CashCardController.registerHotCard ERROR] - 카드번호: {}, 에러: {}", 
                cardNumber, e.getMessage(), e);
            redirectAttributes.addFlashAttribute("errorMessage", "핫카드 등록 중 오류가 발생했습니다: " + e.getMessage());
            return "redirect:/cashcard/hotcard/register";
        }
    }

    /**
     * 핫카드 해제 처리
     */
    @PostMapping("/hotcard/release")
    public String releaseHotCard(@RequestParam String cardNumber,
                                @RequestParam String releaseReason,
                                RedirectAttributes redirectAttributes) {
        logger.info("==================[CashCardController.releaseHotCard START] - 카드번호: {}", cardNumber);
        try {
            // HotCardDDTO 생성 및 설정
            HotCardDDTO hotCardDDTO = new HotCardDDTO();
            hotCardDDTO.setCardNumber(cardNumber);
            hotCardDDTO.setReleaseReason(releaseReason);
            hotCardDDTO.setStatus("RELEASED");
            
            // CosesCommonDTO 생성
            CosesCommonDTO commonDTO = new CosesCommonDTO();
            commonDTO.setUserId("SYSTEM");
            commonDTO.setBankCode("03");
            commonDTO.setBranchCode("001");
            commonDTO.setBusinessDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
            commonDTO.setSystemDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
            commonDTO.setSystemInTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmmss")));
            commonDTO.setTransactionNo("TXN" + System.currentTimeMillis());
            
            // Facade를 통해 핫카드 해제 (Controller -> Facade -> Rule -> Thing)
            HotCardDDTO result = cashCardManagementSBBean.releaseHotCard(hotCardDDTO, commonDTO);
            
            redirectAttributes.addFlashAttribute("successMessage", "핫카드가 성공적으로 해제되었습니다.");
            redirectAttributes.addFlashAttribute("cardNumber", cardNumber);
            
            logger.info("==================[CashCardController.releaseHotCard END] - 카드번호: {}", cardNumber);
            return "redirect:/cashcard/hotcard";
        } catch (CosesAppException e) {
            logger.error("==================[CashCardController.releaseHotCard COSES_ERROR] - 카드번호: {}, 에러: {}", 
                cardNumber, e.getMessage(), e);
            redirectAttributes.addFlashAttribute("errorMessage", "핫카드 해제 중 오류가 발생했습니다: " + e.getMessage());
            return "redirect:/cashcard/hotcard/release";
        } catch (Exception e) {
            logger.error("==================[CashCardController.releaseHotCard ERROR] - 카드번호: {}, 에러: {}", 
                cardNumber, e.getMessage(), e);
            redirectAttributes.addFlashAttribute("errorMessage", "핫카드 해제 중 오류가 발생했습니다: " + e.getMessage());
            return "redirect:/cashcard/hotcard/release";
        }
    }
} 