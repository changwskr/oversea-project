package com.skcc.oversea.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SKCC Oversea 메인 컨트롤러
 * 각 서비스(cashcard, deposit, teller, user)에 접근할 수 있는 메인 페이지 제공
 */
@Controller
@RequestMapping("/")
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    /**
     * 메인 페이지 - 인증 상태에 따라 홈 페이지 또는 로그인 페이지로 리다이렉트
     */
    @GetMapping
    public String mainPage() {
        logger.info("==================[MainController.mainPage START]");
        try {
            // 인증 상태 확인
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            boolean isAuthenticated = authentication != null &&
                    authentication.isAuthenticated() &&
                    !"anonymousUser".equals(authentication.getName());

            if (isAuthenticated) {
                logger.info("==================[MainController.mainPage END] - Redirecting to home (authenticated)");
                return "redirect:/home";
            } else {
                logger.info(
                        "==================[MainController.mainPage END] - Redirecting to login (not authenticated)");
                return "redirect:/login";
            }
        } catch (Exception e) {
            logger.error("==================[MainController.mainPage ERROR] - {}", e.getMessage(), e);
            return "redirect:/login";
        }
    }

    /**
     * 홈 페이지 - 서비스 선택 화면 (로그인 후 접근)
     */
    @GetMapping("/home")
    public String homePage(Model model) {
        logger.info("==================[MainController.homePage START]");
        try {
            model.addAttribute("title", "SKCC Oversea Banking System");
            model.addAttribute("services", new String[] { "cashcard", "deposit", "teller", "user" });

            // 인증 상태 확인
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            boolean isAuthenticated = authentication != null &&
                    authentication.isAuthenticated() &&
                    !"anonymousUser".equals(authentication.getName());

            model.addAttribute("isAuthenticated", isAuthenticated);
            if (isAuthenticated) {
                model.addAttribute("username", authentication.getName());
            }

            logger.info("==================[MainController.homePage END] - isAuthenticated: {}", isAuthenticated);
            return "main";
        } catch (Exception e) {
            logger.error("==================[MainController.homePage ERROR] - {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Cash Card 서비스 페이지 (더 이상 사용하지 않음 - CashCardController에서 처리)
     */
    @GetMapping("/cashcard-old")
    public String cashCardPage(Model model) {
        logger.info("==================[MainController.cashCardPage START]");
        try {
            model.addAttribute("title", "Cash Card Management");
            model.addAttribute("serviceName", "Cash Card");
            model.addAttribute("description", "현금카드 발급, 관리, 조회 서비스");
            logger.info("==================[MainController.cashCardPage END]");
            return "service/cashcard";
        } catch (Exception e) {
            logger.error("==================[MainController.cashCardPage ERROR] - {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Deposit 서비스 페이지
     */
    @GetMapping("/deposit")
    public String depositPage(Model model) {
        logger.info("==================[MainController.depositPage START]");
        try {
            model.addAttribute("title", "Deposit Management");
            model.addAttribute("serviceName", "Deposit");
            model.addAttribute("description", "예금 계좌 관리 및 거래 서비스");
            logger.info("==================[MainController.depositPage END]");
            return "service/deposit";
        } catch (Exception e) {
            logger.error("==================[MainController.depositPage ERROR] - {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Teller 서비스 페이지
     */
    @GetMapping("/teller")
    public String tellerPage(Model model) {
        logger.info("==================[MainController.tellerPage START]");
        try {
            model.addAttribute("title", "Teller Management");
            model.addAttribute("serviceName", "Teller");
            model.addAttribute("description", "창구 업무 및 고객 서비스");
            logger.info("==================[MainController.tellerPage END]");
            return "service/teller";
        } catch (Exception e) {
            logger.error("==================[MainController.tellerPage ERROR] - {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * User 서비스 페이지
     */
    @GetMapping("/user")
    public String userPage(Model model) {
        logger.info("==================[MainController.userPage START]");
        try {
            model.addAttribute("title", "User Management");
            model.addAttribute("serviceName", "User");
            model.addAttribute("description", "사용자 관리 및 인증 서비스");
            logger.info("==================[MainController.userPage END]");
            return "service/user";
        } catch (Exception e) {
            logger.error("==================[MainController.userPage ERROR] - {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 로그인 페이지
     */
    @GetMapping("/login")
    public String loginPage(Model model) {
        logger.info("==================[MainController.loginPage START]");
        try {
            model.addAttribute("title", "Login - SKCC Oversea");
            logger.info("==================[MainController.loginPage END]");
            return "login";
        } catch (Exception e) {
            logger.error("==================[MainController.loginPage ERROR] - {}", e.getMessage(), e);
            throw e;
        }
    }
}