package com.skcc.oversea.user.service.port;

import com.skcc.oversea.user.domain.Role;

import java.util.Optional;

public interface RoleRepositoryPort {
    Optional<Role> findByRoleId(String roleId);
    Role save(Role role);
} 