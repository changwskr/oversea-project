package com.skax.eatool.user.controller;

import com.skax.eatool.user.domain.SecurityPolicy;
import com.skax.eatool.user.domain.User;
import com.skax.eatool.user.domain.UserActivity;
import com.skax.eatool.user.service.SecurityPolicyService;
import com.skax.eatool.user.service.UserActivityService;
import com.skax.eatool.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import com.skax.eatool.user.domain.UserStatus;

/**
 * 사용자 관리 페이지 컨트롤러
 */
@Controller
@RequestMapping("/user-management-web")
@RequiredArgsConstructor
@Slf4j
public class UserManagementWebController {

    private final UserService userService;
    private final UserActivityService userActivityService;
    private final SecurityPolicyService securityPolicyService;

    /**
     * 사용자 관리 메인 페이지
     */
    @GetMapping
    public String userManagementMain(
            @PageableDefault(size = 10) Pageable pageable,
            @RequestParam(required = false) String searchKeyword,
            Model model) {
        log.info("[UserManagementWebController.userManagementMain START]");

        Page<User> users;
        if (searchKeyword != null && !searchKeyword.trim().isEmpty()) {
            users = userService.searchUsers(searchKeyword, pageable);
        } else {
            users = userService.findAll(pageable);
        }

        model.addAttribute("title", "사용자 관리");
        model.addAttribute("users", users);
        model.addAttribute("searchKeyword", searchKeyword);

        log.info("[UserManagementWebController.userManagementMain END]");
        return "user/management/main";
    }

    /**
     * 사용자 등록 페이지
     */
    @GetMapping("/register")
    public String userRegisterPage(Model model) {
        log.info("[UserManagementWebController.userRegisterPage START]");
        model.addAttribute("title", "사용자 등록");
        model.addAttribute("user", new User());
        log.info("[UserManagementWebController.userRegisterPage END]");
        return "user/management/register";
    }

    /**
     * 사용자 등록 처리
     */
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        log.info("[UserManagementWebController.registerUser START]");

        try {
            // userId가 없으면 자동 생성
            if (user.getUserId() == null || user.getUserId().trim().isEmpty()) {
                String generatedUserId = generateUserId(user.getName(), user.getEmail());
                user.setUserId(generatedUserId);
            }

            // username이 없으면 email을 사용
            if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
                user.setUsername(user.getEmail());
            }

            // 기본 상태 설정
            if (user.getStatus() == null) {
                user.setStatus(UserStatus.ACTIVE);
            }

            userService.createUser(user);
            model.addAttribute("successMessage", "사용자가 성공적으로 등록되었습니다.");
            model.addAttribute("title", "사용자 등록");
            log.info("[UserManagementWebController.registerUser END]");
            return "user/management/register";
        } catch (Exception e) {
            log.error("Error registering user: {}", e.getMessage(), e);
            model.addAttribute("errorMessage", "사용자 등록 중 오류가 발생했습니다: " + e.getMessage());
            model.addAttribute("title", "사용자 등록");
            log.info("[UserManagementWebController.registerUser END]");
            return "user/management/register";
        }
    }

    /**
     * 사용자 ID 자동 생성
     */
    private String generateUserId(String name, String email) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String namePart = name != null && !name.trim().isEmpty() ? name.substring(0, Math.min(3, name.length()))
                : "USR";
        String emailPart = email != null && email.contains("@") ? email.substring(0, email.indexOf("@")) : "USER";

        return namePart.toUpperCase() + emailPart.toUpperCase() + timestamp.substring(timestamp.length() - 4);
    }

    /**
     * 사용자 상세 조회 페이지
     */
    @GetMapping("/detail/{id}")
    public String userDetailPage(@PathVariable Long id, Model model) {
        log.info("[UserManagementWebController.userDetailPage START] - id: {}", id);

        try {
            User user = userService.getById(id);
            model.addAttribute("title", "사용자 상세 정보");
            model.addAttribute("user", user);
            log.info("[UserManagementWebController.userDetailPage END]");
            return "user/management/detail";
        } catch (Exception e) {
            log.error("Error retrieving user: {}", e.getMessage(), e);
            model.addAttribute("errorMessage", "사용자 정보를 찾을 수 없습니다.");
            log.info("[UserManagementWebController.userDetailPage END]");
            return "redirect:/user-management-web";
        }
    }

    /**
     * 사용자 수정 페이지
     */
    @GetMapping("/edit/{id}")
    public String userEditPage(@PathVariable Long id, Model model) {
        log.info("[UserManagementWebController.userEditPage START] - id: {}", id);

        try {
            User user = userService.getById(id);
            model.addAttribute("title", "사용자 수정");
            model.addAttribute("user", user);
            log.info("[UserManagementWebController.userEditPage END]");
            return "user/management/edit";
        } catch (Exception e) {
            log.error("Error retrieving user for edit: {}", e.getMessage(), e);
            model.addAttribute("errorMessage", "사용자 정보를 찾을 수 없습니다.");
            log.info("[UserManagementWebController.userEditPage END]");
            return "redirect:/user-management-web";
        }
    }

    /**
     * 사용자 수정 처리
     */
    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute("user") User user, Model model) {
        log.info("[UserManagementWebController.updateUser START] - id: {}", id);

        try {
            user.setId(id);
            User updatedUser = userService.update(user);
            model.addAttribute("successMessage", "사용자 정보가 성공적으로 수정되었습니다.");
            model.addAttribute("title", "사용자 상세 정보");
            model.addAttribute("user", updatedUser);
            log.info("[UserManagementWebController.updateUser END]");
            return "user/management/detail";
        } catch (Exception e) {
            log.error("Error updating user: {}", e.getMessage(), e);
            model.addAttribute("errorMessage", "사용자 수정 중 오류가 발생했습니다: " + e.getMessage());
            model.addAttribute("title", "사용자 수정");
            model.addAttribute("user", user);
            log.info("[UserManagementWebController.updateUser END]");
            return "user/management/edit";
        }
    }

    /**
     * 사용자 삭제 처리
     */
    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id, Model model) {
        log.info("[UserManagementWebController.deleteUser START] - id: {}", id);

        try {
            userService.deleteUser(id);
            model.addAttribute("successMessage", "사용자가 성공적으로 삭제되었습니다.");
            log.info("[UserManagementWebController.deleteUser END]");
            return "redirect:/user-management-web";
        } catch (Exception e) {
            log.error("Error deleting user: {}", e.getMessage(), e);
            model.addAttribute("errorMessage", "사용자 삭제 중 오류가 발생했습니다: " + e.getMessage());
            log.info("[UserManagementWebController.deleteUser END]");
            return "redirect:/user-management-web";
        }
    }

    /**
     * 권한 관리 페이지
     */
    @GetMapping("/roles")
    public String roleManagementPage(Model model) {
        log.info("[UserManagementWebController.roleManagementPage START]");
        model.addAttribute("title", "권한 관리");
        log.info("[UserManagementWebController.roleManagementPage END]");
        return "user/management/roles";
    }

    /**
     * 보안 관리 페이지
     */
    @GetMapping("/security")
    public String securityManagementPage(Model model) {
        log.info("[UserManagementWebController.securityManagementPage START]");

        try {
            // 활성화된 보안 정책들을 조회
            List<SecurityPolicy> activePolicies = securityPolicyService.getActivePolicies();
            List<SecurityPolicy> passwordPolicies = securityPolicyService.getActivePasswordPolicies();
            List<SecurityPolicy> sessionPolicies = securityPolicyService.getActiveSessionPolicies();
            List<SecurityPolicy> ipWhitelistPolicies = securityPolicyService.getActiveIpWhitelistPolicies();

            // 기본 정책이 없으면 생성
            if (activePolicies.isEmpty()) {
                log.info("No active policies found, creating default policies");
                createDefaultSecurityPolicies();

                // 다시 조회
                activePolicies = securityPolicyService.getActivePolicies();
                passwordPolicies = securityPolicyService.getActivePasswordPolicies();
                sessionPolicies = securityPolicyService.getActiveSessionPolicies();
                ipWhitelistPolicies = securityPolicyService.getActiveIpWhitelistPolicies();
            }

            model.addAttribute("title", "보안 관리");
            model.addAttribute("activePolicies", activePolicies);
            model.addAttribute("passwordPolicies", passwordPolicies);
            model.addAttribute("sessionPolicies", sessionPolicies);
            model.addAttribute("ipWhitelistPolicies", ipWhitelistPolicies);

            // 계정 정보 추가
            model.addAttribute("totalActivePolicies", activePolicies.size());
            model.addAttribute("totalPasswordPolicies", passwordPolicies.size());
            model.addAttribute("totalSessionPolicies", sessionPolicies.size());
            model.addAttribute("totalIpWhitelistPolicies", ipWhitelistPolicies.size());

            log.info("[UserManagementWebController.securityManagementPage END] - Active policies: {}",
                    activePolicies.size());
            return "user/management/security";
        } catch (Exception e) {
            log.error("Error in security management page: {}", e.getMessage(), e);
            model.addAttribute("errorMessage", "보안 정책을 불러오는 중 오류가 발생했습니다: " + e.getMessage());
            model.addAttribute("title", "보안 관리");
            return "user/management/security";
        }
    }

    /**
     * 기본 보안 정책 생성
     */
    private void createDefaultSecurityPolicies() {
        try {
            // 기본 비밀번호 정책
            securityPolicyService.createPasswordPolicy(
                    "minLength:8,maxLength:20,requireUppercase:true,requireLowercase:true,requireNumber:true,requireSpecial:true",
                    "기본 비밀번호 정책 - 최소 8자 이상, 대문자, 소문자, 숫자, 특수문자 포함");

            // 기본 세션 정책
            securityPolicyService.createSessionPolicy(
                    "sessionTimeout:30,idleTimeout:15,maxConcurrentSessions:3,autoLogout:true",
                    "기본 세션 정책 - 30분 세션 타임아웃, 15분 유휴 타임아웃, 최대 3명 동시 접속, 자동 로그아웃");

            // 기본 IP 화이트리스트 정책
            securityPolicyService.createIpWhitelistPolicy(
                    "enableIPWhitelist:false,logBlockedAccess:true",
                    "기본 IP 화이트리스트 정책 - 비활성화 상태");

            log.info("Default security policies created successfully");
        } catch (Exception e) {
            log.error("Error creating default security policies: {}", e.getMessage(), e);
        }
    }

    /**
     * 동적 로그 페이지
     */
    @GetMapping("/activity-logs")
    public String activityLogsPage(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String activityType,
            @RequestParam(required = false) String status,
            @PageableDefault(size = 20) Pageable pageable,
            Model model) {
        log.info("[UserManagementWebController.activityLogsPage START]");

        List<UserActivity> activities;
        if (userId != null) {
            activities = userActivityService.getUserActivities(userId);
        } else if (activityType != null) {
            activities = userActivityService.getActivitiesByType(activityType);
        } else if (status != null) {
            activities = userActivityService.getActivitiesByStatus(status);
        } else {
            activities = userActivityService.getRecentActivities(pageable);
        }

        model.addAttribute("title", "동적 로그");
        model.addAttribute("activities", activities);
        model.addAttribute("userId", userId);
        model.addAttribute("activityType", activityType);
        model.addAttribute("status", status);

        log.info("[UserManagementWebController.activityLogsPage END]");
        return "user/management/activity-logs";
    }

    /**
     * 사용자별 동적 로그 페이지
     */
    @GetMapping("/activity-logs/user/{userId}")
    public String userActivityLogsPage(
            @PathVariable Long userId,
            @PageableDefault(size = 20) Pageable pageable,
            Model model) {
        log.info("[UserManagementWebController.userActivityLogsPage START]");

        Page<UserActivity> activities = userActivityService.getUserActivities(userId, pageable);

        model.addAttribute("title", "사용자 동적 로그");
        model.addAttribute("activities", activities);
        model.addAttribute("userId", userId);

        log.info("[UserManagementWebController.userActivityLogsPage END]");
        return "user/management/user-activity-logs";
    }

    /**
     * 기간별 동적 로그 페이지
     */
    @GetMapping("/activity-logs/date-range")
    public String dateRangeActivityLogsPage(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            Model model) {
        log.info("[UserManagementWebController.dateRangeActivityLogsPage START]");

        List<UserActivity> activities;
        if (startDate != null && endDate != null) {
            activities = userActivityService.getActivitiesByDateRange(startDate, endDate);
        } else {
            // 기본값: 최근 7일
            LocalDateTime defaultEndDate = LocalDateTime.now();
            LocalDateTime defaultStartDate = defaultEndDate.minusDays(7);
            activities = userActivityService.getActivitiesByDateRange(defaultStartDate, defaultEndDate);
            startDate = defaultStartDate;
            endDate = defaultEndDate;
        }

        model.addAttribute("title", "기간별 동적 로그");
        model.addAttribute("activities", activities);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);

        log.info("[UserManagementWebController.dateRangeActivityLogsPage END]");
        return "user/management/date-range-activity-logs";
    }

    /**
     * 보안 정책 관리 페이지
     */
    @GetMapping("/security/policies")
    public String securityPoliciesPage(Model model) {
        log.info("[UserManagementWebController.securityPoliciesPage START]");

        List<SecurityPolicy> allPolicies = securityPolicyService.getAllPolicies();
        List<SecurityPolicy> activePolicies = securityPolicyService.getActivePolicies();

        model.addAttribute("title", "보안 정책 관리");
        model.addAttribute("allPolicies", allPolicies);
        model.addAttribute("activePolicies", activePolicies);

        log.info("[UserManagementWebController.securityPoliciesPage END]");
        return "user/management/security-policies";
    }

    /**
     * 비밀번호 정책 관리 페이지
     */
    @GetMapping("/security/password-policies")
    public String passwordPoliciesPage(Model model) {
        log.info("[UserManagementWebController.passwordPoliciesPage START]");

        List<SecurityPolicy> passwordPolicies = securityPolicyService.getPoliciesByType("PASSWORD");
        List<SecurityPolicy> activePasswordPolicies = securityPolicyService.getActivePasswordPolicies();

        model.addAttribute("title", "비밀번호 정책 관리");
        model.addAttribute("passwordPolicies", passwordPolicies);
        model.addAttribute("activePasswordPolicies", activePasswordPolicies);

        log.info("[UserManagementWebController.passwordPoliciesPage END]");
        return "user/management/password-policies";
    }

    /**
     * 세션 정책 관리 페이지
     */
    @GetMapping("/security/session-policies")
    public String sessionPoliciesPage(Model model) {
        log.info("[UserManagementWebController.sessionPoliciesPage START]");

        List<SecurityPolicy> sessionPolicies = securityPolicyService.getPoliciesByType("SESSION");
        List<SecurityPolicy> activeSessionPolicies = securityPolicyService.getActiveSessionPolicies();

        model.addAttribute("title", "세션 정책 관리");
        model.addAttribute("sessionPolicies", sessionPolicies);
        model.addAttribute("activeSessionPolicies", activeSessionPolicies);

        log.info("[UserManagementWebController.sessionPoliciesPage END]");
        return "user/management/session-policies";
    }

    /**
     * IP 화이트리스트 정책 관리 페이지
     */
    @GetMapping("/security/ip-whitelist-policies")
    public String ipWhitelistPoliciesPage(Model model) {
        log.info("[UserManagementWebController.ipWhitelistPoliciesPage START]");

        List<SecurityPolicy> ipWhitelistPolicies = securityPolicyService.getPoliciesByType("IP_WHITELIST");
        List<SecurityPolicy> activeIpWhitelistPolicies = securityPolicyService.getActiveIpWhitelistPolicies();

        model.addAttribute("title", "IP 화이트리스트 정책 관리");
        model.addAttribute("ipWhitelistPolicies", ipWhitelistPolicies);
        model.addAttribute("activeIpWhitelistPolicies", activeIpWhitelistPolicies);

        log.info("[UserManagementWebController.ipWhitelistPoliciesPage END]");
        return "user/management/ip-whitelist-policies";
    }

    /**
     * 비밀번호 정책 저장
     */
    @PostMapping("/security/password-policies")
    public String savePasswordPolicies(
            @RequestParam(required = false) Integer minLength,
            @RequestParam(required = false) Integer maxLength,
            @RequestParam(required = false, defaultValue = "false") boolean requireUppercase,
            @RequestParam(required = false, defaultValue = "false") boolean requireLowercase,
            @RequestParam(required = false, defaultValue = "false") boolean requireNumber,
            @RequestParam(required = false, defaultValue = "false") boolean requireSpecial,
            @RequestParam(required = false, defaultValue = "false") boolean preventSequential,
            @RequestParam(required = false, defaultValue = "false") boolean preventRepeated,
            Model model) {
        log.info("[UserManagementWebController.savePasswordPolicies START]");

        try {
            // 비밀번호 정책 생성
            SecurityPolicy passwordPolicy = SecurityPolicy.builder()
                    .policyType("PASSWORD")
                    .policyName("복잡한 비밀번호 정책")
                    .description("복잡한 비밀번호 구성 항목")
                    .isActive(true)
                    .build();

            securityPolicyService.createPolicy(passwordPolicy);

            model.addAttribute("successMessage", "비밀번호 정책이 성공적으로 저장되었습니다.");
            log.info("[UserManagementWebController.savePasswordPolicies END]");
        } catch (Exception e) {
            log.error("Error saving password policies: {}", e.getMessage(), e);
            model.addAttribute("errorMessage", "비밀번호 정책 저장 중 오류가 발생했습니다: " + e.getMessage());
            log.info("[UserManagementWebController.savePasswordPolicies END]");
        }

        return "redirect:/user-management-web/security/password-policies";
    }

    /**
     * 세션 정책 저장
     */
    @PostMapping("/security/session-policies")
    public String saveSessionPolicies(
            @RequestParam(required = false) Integer sessionTimeout,
            @RequestParam(required = false) Integer idleTimeout,
            @RequestParam(required = false) Integer maxConcurrentSessions,
            @RequestParam(required = false, defaultValue = "false") boolean autoLogout,
            @RequestParam(required = false, defaultValue = "false") boolean allowSessionExtension,
            @RequestParam(required = false, defaultValue = "false") boolean terminateExistingSessions,
            Model model) {
        log.info("[UserManagementWebController.saveSessionPolicies START]");

        try {
            // 세션 정책 생성
            SecurityPolicy sessionPolicy = SecurityPolicy.builder()
                    .policyType("SESSION")
                    .policyName("세션 제한 정책")
                    .description("세션 관리 정책")
                    .isActive(true)
                    .build();

            securityPolicyService.createPolicy(sessionPolicy);

            model.addAttribute("successMessage", "세션 정책이 성공적으로 저장되었습니다.");
            log.info("[UserManagementWebController.saveSessionPolicies END]");
        } catch (Exception e) {
            log.error("Error saving session policies: {}", e.getMessage(), e);
            model.addAttribute("errorMessage", "세션 정책 저장 중 오류가 발생했습니다: " + e.getMessage());
            log.info("[UserManagementWebController.saveSessionPolicies END]");
        }

        return "redirect:/user-management-web/security/session-policies";
    }

    /**
     * IP 화이트리스트 정책 저장
     */
    @PostMapping("/security/ip-whitelist-policies")
    public String saveIpWhitelistPolicies(
            @RequestParam(required = false, defaultValue = "false") boolean enableIPWhitelist,
            @RequestParam(required = false, defaultValue = "false") boolean logBlockedAccess,
            Model model) {
        log.info("[UserManagementWebController.saveIpWhitelistPolicies START]");

        try {
            // IP 화이트리스트 정책 생성
            SecurityPolicy ipWhitelistPolicy = SecurityPolicy.builder()
                    .policyType("IP_WHITELIST")
                    .policyName("IP 화이트리스트 정책")
                    .description("IP 근처 접근 정책")
                    .isActive(enableIPWhitelist)
                    .build();

            securityPolicyService.createPolicy(ipWhitelistPolicy);

            model.addAttribute("successMessage", "IP 화이트리스트 정책이 성공적으로 저장되었습니다.");
            log.info("[UserManagementWebController.saveIpWhitelistPolicies END]");
        } catch (Exception e) {
            log.error("Error saving IP whitelist policies: {}", e.getMessage(), e);
            model.addAttribute("errorMessage", "IP 화이트리스트 정책 저장 중 오류가 발생했습니다: " + e.getMessage());
            log.info("[UserManagementWebController.saveIpWhitelistPolicies END]");
        }

        return "redirect:/user-management-web/security/ip-whitelist-policies";
    }

    /**
     * 테스트 페이지
     */
    @GetMapping("/test")
    public String testPage(Model model) {
        log.info("[UserManagementWebController.testPage START]");
        model.addAttribute("title", "테스트 페이지");
        log.info("[UserManagementWebController.testPage END]");
        return "user/management/test";
    }

    /**
     * 디버그 페이지
     */
    @GetMapping("/debug")
    public String debugPage(Model model) {
        log.info("[UserManagementWebController.debugPage START]");
        model.addAttribute("title", "디버그 페이지");
        log.info("[UserManagementWebController.debugPage END]");
        return "user/management/debug";
    }

    /**
     * 간단한 테스트 페이지
     */
    @GetMapping("/simple-test")
    public String simpleTestPage(Model model) {
        log.info("[UserManagementWebController.simpleTestPage START]");
        model.addAttribute("title", "간단한 테스트 페이지");
        log.info("[UserManagementWebController.simpleTestPage END]");
        return "user/management/simple-test";
    }
}
