package com.skax.eatool.user.infrastructure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import com.skax.eatool.user.domain.UserRole;
import com.skax.eatool.user.infrastructure.jpa.UserRoleEntity;
import com.skax.eatool.user.infrastructure.jpa.UserRoleRepositoryJpa;
import com.skax.eatool.user.service.port.UserRoleRepositoryPort;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRoleRepositoryPortJpaImpl implements UserRoleRepositoryPort {

    private final UserRoleRepositoryJpa userRoleRepositoryJpa;

    @Override
    public List<UserRole> findByUserId(Long userId) {
        log.info("[UserRoleRepositoryPortJpaImpl] findByUserId START - userId: {}", userId);
        
        List<UserRole> result = userRoleRepositoryJpa.findByUserId(userId)
                .stream()
                .map(this::toUserRole)
                .toList();
        
        log.info("[UserRoleRepositoryPortJpaImpl] findByUserId END - count: {}", result.size());
        return result;
    }

    @Override
    public List<UserRole> findByRoleId(Long roleId) {
        log.info("[UserRoleRepositoryPortJpaImpl] findByRoleId START - roleId: {}", roleId);
        
        List<UserRole> result = userRoleRepositoryJpa.findByRoleId(roleId)
                .stream()
                .map(this::toUserRole)
                .toList();
        
        log.info("[UserRoleRepositoryPortJpaImpl] findByRoleId END - count: {}", result.size());
        return result;
    }

    private UserRole toUserRole(UserRoleEntity entity) {
        log.debug("[UserRoleRepositoryPortJpaImpl] toUserRole START - entityId: {}", entity.getId());
        
        UserRole result = UserRole.builder()
                .id(entity.getId())
                .user(entity.getUser().toModel())
                .role(entity.getRole().toModel())
                .build();
        
        log.debug("[UserRoleRepositoryPortJpaImpl] toUserRole END - userRoleId: {}", result.getId());
        return result;
    }
} 
