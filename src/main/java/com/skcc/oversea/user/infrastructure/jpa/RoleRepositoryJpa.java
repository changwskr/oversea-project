package com.skcc.oversea.user.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepositoryJpa extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByRoleId(String roleId);
} 