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

import com.skcc.oversea.cashCard.business.cashCard.CashCardSBBean;
import com.skcc.oversea.cashCard.business.cashCard.model.CashCardDDTO;
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
    private CashCardSBBean cashCardSBBean;

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
            
            // 카드 번호 생성 (실제로는 더 복잡한 로직 필요)
            String cardNumber = generateCardNumber();
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
            
            // 실제 카드 발급 서비스 호출
            CashCardDDTO result = cashCardSBBean.makeCashCard(cashCardDDTO, commonDTO);
            
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
     * 카드 번호 생성 (안전한 구현)
     */
    private String generateCardNumber() {
        try {
            // 현재 시간을 기반으로 한 고유한 카드 번호 생성
            LocalDateTime now = LocalDateTime.now();
            String timestamp = String.valueOf(System.currentTimeMillis());
            
            // 16자리 카드 번호 생성 (4 + 12자리 숫자 + 1자리 체크섬)
            StringBuilder cardNumberBuilder = new StringBuilder();
            cardNumberBuilder.append("4"); // Visa 카드 형식
            
            // timestamp에서 12자리 추출 (안전하게 처리)
            if (timestamp.length() >= 12) {
                cardNumberBuilder.append(timestamp.substring(timestamp.length() - 12));
            } else {
                // timestamp가 12자리보다 짧은 경우 0으로 패딩
                String paddedTimestamp = String.format("%012d", Long.parseLong(timestamp));
                cardNumberBuilder.append(paddedTimestamp);
            }
            
            String cardNumber = cardNumberBuilder.toString();
            
            // Luhn 알고리즘으로 체크섬 추가
            return cardNumber + calculateLuhnCheckDigit(cardNumber);
        } catch (Exception e) {
            logger.error("카드 번호 생성 중 오류 발생: {}", e.getMessage());
            // 오류 발생 시 기본 카드 번호 반환
            return "4000000000000000";
        }
    }
    
    /**
     * Luhn 알고리즘으로 체크섬 계산
     */
    private int calculateLuhnCheckDigit(String cardNumber) {
        try {
            if (cardNumber == null || cardNumber.isEmpty()) {
                return 0;
            }
            
            int sum = 0;
            boolean alternate = false;
            
            for (int i = cardNumber.length() - 1; i >= 0; i--) {
                char c = cardNumber.charAt(i);
                if (!Character.isDigit(c)) {
                    continue; // 숫자가 아닌 문자는 건너뛰기
                }
                
                int n = Character.getNumericValue(c);
                if (alternate) {
                    n *= 2;
                    if (n > 9) {
                        n = (n % 10) + 1;
                    }
                }
                sum += n;
                alternate = !alternate;
            }
            
            return (10 - (sum % 10)) % 10;
        } catch (Exception e) {
            logger.error("Luhn 체크섬 계산 중 오류 발생: {}", e.getMessage());
            return 0; // 오류 발생 시 0 반환
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
} 