package com.skcc.oversea.user.infrastructure.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 사용자 활동 로그 JPA Repository
 */
@Repository
public interface UserActivityRepositoryJpa extends JpaRepository<UserActivityEntity, Long> {
    
    // 사용자별 활동 로그 조회
    List<UserActivityEntity> findByUserIdOrderByActivityTimeDesc(Long userId);
    
    // 사용자별 활동 로그 페이징 조회
    Page<UserActivityEntity> findByUserIdOrderByActivityTimeDesc(Long userId, Pageable pageable);
    
    // 활동 타입별 조회
    List<UserActivityEntity> findByActivityTypeOrderByActivityTimeDesc(String activityType);
    
    // 기간별 활동 로그 조회
    @Query("SELECT ua FROM UserActivityEntity ua WHERE ua.activityTime BETWEEN :startDate AND :endDate ORDER BY ua.activityTime DESC")
    List<UserActivityEntity> findByActivityTimeBetween(@Param("startDate") LocalDateTime startDate, 
                                                      @Param("endDate") LocalDateTime endDate);
    
    // 사용자별 기간별 활동 로그 조회
    @Query("SELECT ua FROM UserActivityEntity ua WHERE ua.userId = :userId AND ua.activityTime BETWEEN :startDate AND :endDate ORDER BY ua.activityTime DESC")
    List<UserActivityEntity> findByUserIdAndActivityTimeBetween(@Param("userId") Long userId,
                                                               @Param("startDate") LocalDateTime startDate,
                                                               @Param("endDate") LocalDateTime endDate);
    
    // IP 주소별 활동 로그 조회
    List<UserActivityEntity> findByIpAddressOrderByActivityTimeDesc(String ipAddress);
    
    // 상태별 활동 로그 조회
    List<UserActivityEntity> findByStatusOrderByActivityTimeDesc(String status);
    
    // 최근 활동 로그 조회 (최근 100개)
    @Query("SELECT ua FROM UserActivityEntity ua ORDER BY ua.activityTime DESC")
    List<UserActivityEntity> findRecentActivities(Pageable pageable);
} 