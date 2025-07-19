package com.skax.eatool.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

/**
 * 사용자 활동 로그 도메인
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserActivity {

    private Long id;
    private Long userId;
    private String activityType; // LOGIN, LOGOUT, CREATE, UPDATE, DELETE, etc.
    private String description;
    private String ipAddress;
    private String userAgent;
    private LocalDateTime activityTime;
    private String status; // SUCCESS, FAILURE

    public static UserActivity createLoginActivity(Long userId, String ipAddress, String userAgent) {
        return UserActivity.builder()
                .userId(userId)
                .activityType("LOGIN")
                .description("사용자 로그인")
                .ipAddress(ipAddress)
                .userAgent(userAgent)
                .activityTime(LocalDateTime.now())
                .status("SUCCESS")
                .build();
    }

    public static UserActivity createLogoutActivity(Long userId, String ipAddress, String userAgent) {
        return UserActivity.builder()
                .userId(userId)
                .activityType("LOGOUT")
                .description("사용자 로그아웃")
                .ipAddress(ipAddress)
                .userAgent(userAgent)
                .activityTime(LocalDateTime.now())
                .status("SUCCESS")
                .build();
    }

    public static UserActivity createUserActivity(Long userId, String activityType, String description,
            String ipAddress, String userAgent) {
        return UserActivity.builder()
                .userId(userId)
                .activityType(activityType)
                .description(description)
                .ipAddress(ipAddress)
                .userAgent(userAgent)
                .activityTime(LocalDateTime.now())
                .status("SUCCESS")
                .build();
    }
}
