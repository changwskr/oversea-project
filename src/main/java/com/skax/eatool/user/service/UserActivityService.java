package com.skax.eatool.user.service;

import com.skax.eatool.user.domain.UserActivity;
import com.skax.eatool.user.service.port.UserActivityRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 사용자 활동 로그 서비스
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserActivityService {

    private final UserActivityRepositoryPort userActivityRepositoryPort;

    /**
     * 로그인 활동 로그 기록
     */
    @Transactional
    public UserActivity logLoginActivity(Long userId, String ipAddress, String userAgent) {
        log.info("Logging login activity for userId: {}", userId);
        UserActivity activity = UserActivity.createLoginActivity(userId, ipAddress, userAgent);
        return userActivityRepositoryPort.save(activity);
    }

    /**
     * 로그아웃 활동 로그 기록
     */
    @Transactional
    public UserActivity logLogoutActivity(Long userId, String ipAddress, String userAgent) {
        log.info("Logging logout activity for userId: {}", userId);
        UserActivity activity = UserActivity.createLogoutActivity(userId, ipAddress, userAgent);
        return userActivityRepositoryPort.save(activity);
    }

    /**
     * 사용자 활동 로그 기록
     */
    @Transactional
    public UserActivity logUserActivity(Long userId, String activityType, String description, String ipAddress,
            String userAgent) {
        log.info("Logging user activity: userId={}, type={}, description={}", userId, activityType, description);
        UserActivity activity = UserActivity.createUserActivity(userId, activityType, description, ipAddress,
                userAgent);
        return userActivityRepositoryPort.save(activity);
    }

    /**
     * 사용자별 활동 로그 조회
     */
    public List<UserActivity> getUserActivities(Long userId) {
        log.info("Getting user activities for userId: {}", userId);
        return userActivityRepositoryPort.findByUserId(userId);
    }

    /**
     * 사용자별 활동 로그 페이징 조회
     */
    public Page<UserActivity> getUserActivities(Long userId, Pageable pageable) {
        log.info("Getting user activities for userId: {} with pageable: {}", userId, pageable);
        return userActivityRepositoryPort.findByUserId(userId, pageable);
    }

    /**
     * 활동 타입별 로그 조회
     */
    public List<UserActivity> getActivitiesByType(String activityType) {
        log.info("Getting activities by type: {}", activityType);
        return userActivityRepositoryPort.findByActivityType(activityType);
    }

    /**
     * 기간별 활동 로그 조회
     */
    public List<UserActivity> getActivitiesByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        log.info("Getting activities between {} and {}", startDate, endDate);
        return userActivityRepositoryPort.findByActivityTimeBetween(startDate, endDate);
    }

    /**
     * 사용자별 기간별 활동 로그 조회
     */
    public List<UserActivity> getUserActivitiesByDateRange(Long userId, LocalDateTime startDate,
            LocalDateTime endDate) {
        log.info("Getting user activities for userId: {} between {} and {}", userId, startDate, endDate);
        return userActivityRepositoryPort.findByUserIdAndActivityTimeBetween(userId, startDate, endDate);
    }

    /**
     * IP 주소별 활동 로그 조회
     */
    public List<UserActivity> getActivitiesByIpAddress(String ipAddress) {
        log.info("Getting activities by IP address: {}", ipAddress);
        return userActivityRepositoryPort.findByIpAddress(ipAddress);
    }

    /**
     * 상태별 활동 로그 조회
     */
    public List<UserActivity> getActivitiesByStatus(String status) {
        log.info("Getting activities by status: {}", status);
        return userActivityRepositoryPort.findByStatus(status);
    }

    /**
     * 최근 활동 로그 조회
     */
    public List<UserActivity> getRecentActivities(Pageable pageable) {
        log.info("Getting recent activities with pageable: {}", pageable);
        return userActivityRepositoryPort.findRecentActivities(pageable);
    }

    /**
     * 모든 활동 로그 조회
     */
    public List<UserActivity> getAllActivities() {
        log.info("Getting all activities");
        return userActivityRepositoryPort.findAll();
    }

    /**
     * 활동 로그 삭제
     */
    @Transactional
    public void deleteActivity(Long id) {
        log.info("Deleting activity with id: {}", id);
        userActivityRepositoryPort.deleteById(id);
    }
}
