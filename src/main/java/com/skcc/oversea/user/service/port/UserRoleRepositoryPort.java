package com.skcc.oversea.user.service.port;

import com.skcc.oversea.user.domain.UserRole;

import java.util.List;

public interface UserRoleRepositoryPort {
    List<UserRole> findByUserId(Long userId);
    List<UserRole> findByRoleId(Long roleId);
} 