package com.skax.eatool.user.service.port;

import com.skax.eatool.user.domain.UserActivity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 사용자 활동 로그 Repository Port
 */
public interface UserActivityRepositoryPort {

    // 활동 로그 저장
    UserActivity save(UserActivity userActivity);

    // ID로 조회
    Optional<UserActivity> findById(Long id);

    // 사용자별 활동 로그 조회
    List<UserActivity> findByUserId(Long userId);

    // 사용자별 활동 로그 페이징 조회
    Page<UserActivity> findByUserId(Long userId, Pageable pageable);

    // 활동 타입별 조회
    List<UserActivity> findByActivityType(String activityType);

    // 기간별 활동 로그 조회
    List<UserActivity> findByActivityTimeBetween(LocalDateTime startDate, LocalDateTime endDate);

    // 사용자별 기간별 활동 로그 조회
    List<UserActivity> findByUserIdAndActivityTimeBetween(Long userId, LocalDateTime startDate, LocalDateTime endDate);

    // IP 주소별 활동 로그 조회
    List<UserActivity> findByIpAddress(String ipAddress);

    // 상태별 활동 로그 조회
    List<UserActivity> findByStatus(String status);

    // 최근 활동 로그 조회
    List<UserActivity> findRecentActivities(Pageable pageable);

    // 모든 활동 로그 조회
    List<UserActivity> findAll();

    // 활동 로그 삭제
    void deleteById(Long id);
}
