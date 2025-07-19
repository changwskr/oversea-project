package com.skax.eatool.user.service.port;

import com.skax.eatool.user.domain.Role;

import java.util.Optional;

public interface RoleRepositoryPort {
    Optional<Role> findByRoleId(String roleId);
    Role save(Role role);
} 
