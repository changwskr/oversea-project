package com.skax.eatool.user.infrastructure;

import com.skax.eatool.user.domain.UserActivity;
import com.skax.eatool.user.infrastructure.jpa.UserActivityEntity;
import com.skax.eatool.user.infrastructure.jpa.UserActivityRepositoryJpa;
import com.skax.eatool.user.service.port.UserActivityRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 사용자 활동 로그 JPA Repository 구현체
 */
@Repository("userActivityRepositoryPortJpaImpl")
@RequiredArgsConstructor
@Slf4j
public class UserActivityRepositoryPortJpaImpl implements UserActivityRepositoryPort {

    private final UserActivityRepositoryJpa userActivityRepositoryJpa;

    @Override
    public UserActivity save(UserActivity userActivity) {
        log.info("[UserActivityRepositoryPortJpaImpl] save START - userId: {}, activityType: {}",
                userActivity.getUserId(), userActivity.getActivityType());

        UserActivityEntity entity = UserActivityEntity.fromDomain(userActivity);
        UserActivityEntity savedEntity = userActivityRepositoryJpa.save(entity);
        UserActivity result = savedEntity.toDomain();

        log.info("[UserActivityRepositoryPortJpaImpl] save END - activityId: {}", result.getId());
        return result;
    }

    @Override
    public Optional<UserActivity> findById(Long id) {
        log.info("[UserActivityRepositoryPortJpaImpl] findById START - id: {}", id);

        Optional<UserActivity> result = userActivityRepositoryJpa.findById(id)
                .map(UserActivityEntity::toDomain);

        log.info("[UserActivityRepositoryPortJpaImpl] findById END - found: {}", result.isPresent());
        return result;
    }

    @Override
    public List<UserActivity> findByUserId(Long userId) {
        log.info("[UserActivityRepositoryPortJpaImpl] findByUserId START - userId: {}", userId);

        List<UserActivity> result = userActivityRepositoryJpa.findByUserIdOrderByActivityTimeDesc(userId)
                .stream()
                .map(UserActivityEntity::toDomain)
                .collect(Collectors.toList());

        log.info("[UserActivityRepositoryPortJpaImpl] findByUserId END - count: {}", result.size());
        return result;
    }

    @Override
    public Page<UserActivity> findByUserId(Long userId, Pageable pageable) {
        log.info("[UserActivityRepositoryPortJpaImpl] findByUserId(Pageable) START - userId: {}, pageable: {}", userId,
                pageable);

        Page<UserActivity> result = userActivityRepositoryJpa.findByUserIdOrderByActivityTimeDesc(userId, pageable)
                .map(UserActivityEntity::toDomain);

        log.info("[UserActivityRepositoryPortJpaImpl] findByUserId(Pageable) END - totalElements: {}",
                result.getTotalElements());
        return result;
    }

    @Override
    public List<UserActivity> findByActivityType(String activityType) {
        log.info("[UserActivityRepositoryPortJpaImpl] findByActivityType START - activityType: {}", activityType);

        List<UserActivity> result = userActivityRepositoryJpa.findByActivityTypeOrderByActivityTimeDesc(activityType)
                .stream()
                .map(UserActivityEntity::toDomain)
                .collect(Collectors.toList());

        log.info("[UserActivityRepositoryPortJpaImpl] findByActivityType END - count: {}", result.size());
        return result;
    }

    @Override
    public List<UserActivity> findByActivityTimeBetween(LocalDateTime startDate, LocalDateTime endDate) {
        log.info("[UserActivityRepositoryPortJpaImpl] findByActivityTimeBetween START - startDate: {}, endDate: {}",
                startDate, endDate);

        List<UserActivity> result = userActivityRepositoryJpa.findByActivityTimeBetween(startDate, endDate)
                .stream()
                .map(UserActivityEntity::toDomain)
                .collect(Collectors.toList());

        log.info("[UserActivityRepositoryPortJpaImpl] findByActivityTimeBetween END - count: {}", result.size());
        return result;
    }

    @Override
    public List<UserActivity> findByUserIdAndActivityTimeBetween(Long userId, LocalDateTime startDate,
            LocalDateTime endDate) {
        log.info(
                "[UserActivityRepositoryPortJpaImpl] findByUserIdAndActivityTimeBetween START - userId: {}, startDate: {}, endDate: {}",
                userId, startDate, endDate);

        List<UserActivity> result = userActivityRepositoryJpa
                .findByUserIdAndActivityTimeBetween(userId, startDate, endDate)
                .stream()
                .map(UserActivityEntity::toDomain)
                .collect(Collectors.toList());

        log.info("[UserActivityRepositoryPortJpaImpl] findByUserIdAndActivityTimeBetween END - count: {}",
                result.size());
        return result;
    }

    @Override
    public List<UserActivity> findByIpAddress(String ipAddress) {
        log.info("[UserActivityRepositoryPortJpaImpl] findByIpAddress START - ipAddress: {}", ipAddress);

        List<UserActivity> result = userActivityRepositoryJpa.findByIpAddressOrderByActivityTimeDesc(ipAddress)
                .stream()
                .map(UserActivityEntity::toDomain)
                .collect(Collectors.toList());

        log.info("[UserActivityRepositoryPortJpaImpl] findByIpAddress END - count: {}", result.size());
        return result;
    }

    @Override
    public List<UserActivity> findByStatus(String status) {
        log.info("[UserActivityRepositoryPortJpaImpl] findByStatus START - status: {}", status);

        List<UserActivity> result = userActivityRepositoryJpa.findByStatusOrderByActivityTimeDesc(status)
                .stream()
                .map(UserActivityEntity::toDomain)
                .collect(Collectors.toList());

        log.info("[UserActivityRepositoryPortJpaImpl] findByStatus END - count: {}", result.size());
        return result;
    }

    @Override
    public List<UserActivity> findRecentActivities(Pageable pageable) {
        log.info("[UserActivityRepositoryPortJpaImpl] findRecentActivities START - pageable: {}", pageable);

        List<UserActivity> result = userActivityRepositoryJpa.findRecentActivities(pageable)
                .stream()
                .map(UserActivityEntity::toDomain)
                .collect(Collectors.toList());

        log.info("[UserActivityRepositoryPortJpaImpl] findRecentActivities END - count: {}", result.size());
        return result;
    }

    @Override
    public List<UserActivity> findAll() {
        log.info("[UserActivityRepositoryPortJpaImpl] findAll START");

        List<UserActivity> result = userActivityRepositoryJpa.findAll()
                .stream()
                .map(UserActivityEntity::toDomain)
                .collect(Collectors.toList());

        log.info("[UserActivityRepositoryPortJpaImpl] findAll END - count: {}", result.size());
        return result;
    }

    @Override
    public void deleteById(Long id) {
        log.info("[UserActivityRepositoryPortJpaImpl] deleteById START - id: {}", id);

        userActivityRepositoryJpa.deleteById(id);

        log.info("[UserActivityRepositoryPortJpaImpl] deleteById END - id: {}", id);
    }
}
