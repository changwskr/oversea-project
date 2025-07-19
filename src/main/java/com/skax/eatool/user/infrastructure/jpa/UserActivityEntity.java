package com.skax.eatool.user.infrastructure.jpa;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

/**
 * ?�용???�동 로그 JPA ?�티??
 */
@Entity
@Table(name = "user_activities")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserActivityEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "activity_type", nullable = false, length = 50)
    private String activityType;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "ip_address", length = 45)
    private String ipAddress;

    @Column(name = "user_agent", length = 500)
    private String userAgent;

    @Column(name = "activity_time", nullable = false)
    private LocalDateTime activityTime;

    @Column(name = "status", length = 20, nullable = false)
    private String status;

    // ?�메??객체�?변??
    public com.skax.eatool.user.domain.UserActivity toDomain() {
        return com.skax.eatool.user.domain.UserActivity.builder()
                .id(this.id)
                .userId(this.userId)
                .activityType(this.activityType)
                .description(this.description)
                .ipAddress(this.ipAddress)
                .userAgent(this.userAgent)
                .activityTime(this.activityTime)
                .status(this.status)
                .build();
    }

    // ?�메??객체?�서 ?�티???�성
    public static UserActivityEntity fromDomain(com.skax.eatool.user.domain.UserActivity userActivity) {
        return UserActivityEntity.builder()
                .id(userActivity.getId())
                .userId(userActivity.getUserId())
                .activityType(userActivity.getActivityType())
                .description(userActivity.getDescription())
                .ipAddress(userActivity.getIpAddress())
                .userAgent(userActivity.getUserAgent())
                .activityTime(userActivity.getActivityTime())
                .status(userActivity.getStatus())
                .build();
    }
}
