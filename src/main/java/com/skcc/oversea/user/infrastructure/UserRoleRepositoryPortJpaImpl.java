package com.skcc.oversea.user.infrastructure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import com.skcc.oversea.user.domain.UserRole;
import com.skcc.oversea.user.infrastructure.jpa.UserRoleEntity;
import com.skcc.oversea.user.infrastructure.jpa.UserRoleRepositoryJpa;
import com.skcc.oversea.user.service.port.UserRoleRepositoryPort;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRoleRepositoryPortJpaImpl implements UserRoleRepositoryPort {

    private final UserRoleRepositoryJpa userRoleRepositoryJpa;

    @Override
    public List<UserRole> findByUserId(Long userId) {
        return userRoleRepositoryJpa.findByUserId(userId)
                .stream()
                .map(this::toUserRole)
                .toList();
    }

    @Override
    public List<UserRole> findByRoleId(Long roleId) {
        return userRoleRepositoryJpa.findByRoleId(roleId)
                .stream()
                .map(this::toUserRole)
                .toList();
    }

    private UserRole toUserRole(UserRoleEntity entity) {
        return UserRole.builder()
                .id(entity.getId())
                .user(entity.getUser().toModel())
                .role(entity.getRole().toModel())
                .build();
    }
} 