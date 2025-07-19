package com.skax.eatool.user.service.port;

import com.skax.eatool.user.domain.UserRole;

import java.util.List;

public interface UserRoleRepositoryPort {
    List<UserRole> findByUserId(Long userId);
    List<UserRole> findByRoleId(Long roleId);
} 
