package com.skcc.oversea.user.controller;

import com.skcc.oversea.user.domain.UserActivity;
import com.skcc.oversea.user.dto.ApiResponse;
import com.skcc.oversea.user.service.UserActivityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 사용자 활동 로그 컨트롤러
 */
@RestController
@RequestMapping("/api/user-activities")
@RequiredArgsConstructor
@Slf4j
public class UserActivityController {
    
    private final UserActivityService userActivityService;
    
    /**
     * 사용자별 활동 로그 조회
     */
    @GetMapping("/user/{userId}")
    public ApiResponse<List<UserActivity>> getUserActivities(@PathVariable Long userId) {
        log.info("Getting user activities for userId: {}", userId);
        List<UserActivity> activities = userActivityService.getUserActivities(userId);
        return ApiResponse.ok(activities);
    }
    
    /**
     * 사용자별 활동 로그 페이징 조회
     */
    @GetMapping("/user/{userId}/page")
    public ApiResponse<Page<UserActivity>> getUserActivitiesPage(@PathVariable Long userId, Pageable pageable) {
        log.info("Getting user activities page for userId: {} with pageable: {}", userId, pageable);
        Page<UserActivity> activities = userActivityService.getUserActivities(userId, pageable);
        return ApiResponse.ok(activities);
    }
    
    /**
     * 활동 타입별 로그 조회
     */
    @GetMapping("/type/{activityType}")
    public ApiResponse<List<UserActivity>> getActivitiesByType(@PathVariable String activityType) {
        log.info("Getting activities by type: {}", activityType);
        List<UserActivity> activities = userActivityService.getActivitiesByType(activityType);
        return ApiResponse.ok(activities);
    }
    
    /**
     * 기간별 활동 로그 조회
     */
    @GetMapping("/date-range")
    public ApiResponse<List<UserActivity>> getActivitiesByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        log.info("Getting activities between {} and {}", startDate, endDate);
        List<UserActivity> activities = userActivityService.getActivitiesByDateRange(startDate, endDate);
        return ApiResponse.ok(activities);
    }
    
    /**
     * 사용자별 기간별 활동 로그 조회
     */
    @GetMapping("/user/{userId}/date-range")
    public ApiResponse<List<UserActivity>> getUserActivitiesByDateRange(
            @PathVariable Long userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        log.info("Getting user activities for userId: {} between {} and {}", userId, startDate, endDate);
        List<UserActivity> activities = userActivityService.getUserActivitiesByDateRange(userId, startDate, endDate);
        return ApiResponse.ok(activities);
    }
    
    /**
     * IP 주소별 활동 로그 조회
     */
    @GetMapping("/ip/{ipAddress}")
    public ApiResponse<List<UserActivity>> getActivitiesByIpAddress(@PathVariable String ipAddress) {
        log.info("Getting activities by IP address: {}", ipAddress);
        List<UserActivity> activities = userActivityService.getActivitiesByIpAddress(ipAddress);
        return ApiResponse.ok(activities);
    }
    
    /**
     * 상태별 활동 로그 조회
     */
    @GetMapping("/status/{status}")
    public ApiResponse<List<UserActivity>> getActivitiesByStatus(@PathVariable String status) {
        log.info("Getting activities by status: {}", status);
        List<UserActivity> activities = userActivityService.getActivitiesByStatus(status);
        return ApiResponse.ok(activities);
    }
    
    /**
     * 최근 활동 로그 조회
     */
    @GetMapping("/recent")
    public ApiResponse<List<UserActivity>> getRecentActivities(Pageable pageable) {
        log.info("Getting recent activities with pageable: {}", pageable);
        List<UserActivity> activities = userActivityService.getRecentActivities(pageable);
        return ApiResponse.ok(activities);
    }
    
    /**
     * 모든 활동 로그 조회
     */
    @GetMapping
    public ApiResponse<List<UserActivity>> getAllActivities() {
        log.info("Getting all activities");
        List<UserActivity> activities = userActivityService.getAllActivities();
        return ApiResponse.ok(activities);
    }
    
    /**
     * 활동 로그 삭제
     */
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteActivity(@PathVariable Long id) {
        log.info("Deleting activity with id: {}", id);
        userActivityService.deleteActivity(id);
        return ApiResponse.ok("Activity deleted successfully");
    }
    
    /**
     * 로그인 활동 로그 저장 (내부용)
     */
    @PostMapping("/log-login")
    public ApiResponse<UserActivity> logLoginActivity(
            @RequestParam Long userId,
            HttpServletRequest request) {
        log.info("Logging login activity for userId: {}", userId);
        String ipAddress = getClientIpAddress(request);
        String userAgent = request.getHeader("User-Agent");
        UserActivity activity = userActivityService.logLoginActivity(userId, ipAddress, userAgent);
        return ApiResponse.ok(activity);
    }
    
    /**
     * 로그아웃 활동 로그 저장 (내부용)
     */
    @PostMapping("/log-logout")
    public ApiResponse<UserActivity> logLogoutActivity(
            @RequestParam Long userId,
            HttpServletRequest request) {
        log.info("Logging logout activity for userId: {}", userId);
        String ipAddress = getClientIpAddress(request);
        String userAgent = request.getHeader("User-Agent");
        UserActivity activity = userActivityService.logLogoutActivity(userId, ipAddress, userAgent);
        return ApiResponse.ok(activity);
    }
    
    /**
     * 사용자 활동 로그 저장 (내부용)
     */
    @PostMapping("/log-activity")
    public ApiResponse<UserActivity> logUserActivity(
            @RequestParam Long userId,
            @RequestParam String activityType,
            @RequestParam String description,
            HttpServletRequest request) {
        log.info("Logging user activity: userId={}, type={}, description={}", userId, activityType, description);
        String ipAddress = getClientIpAddress(request);
        String userAgent = request.getHeader("User-Agent");
        UserActivity activity = userActivityService.logUserActivity(userId, activityType, description, ipAddress, userAgent);
        return ApiResponse.ok(activity);
    }
    
    /**
     * 클라이언트 IP 주소 추출
     */
    private String getClientIpAddress(HttpServletRequest request) {
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        if (xForwardedFor != null && !xForwardedFor.isEmpty() && !"unknown".equalsIgnoreCase(xForwardedFor)) {
            return xForwardedFor.split(",")[0];
        }
        
        String xRealIp = request.getHeader("X-Real-IP");
        if (xRealIp != null && !xRealIp.isEmpty() && !"unknown".equalsIgnoreCase(xRealIp)) {
            return xRealIp;
        }
        
        return request.getRemoteAddr();
    }
} 