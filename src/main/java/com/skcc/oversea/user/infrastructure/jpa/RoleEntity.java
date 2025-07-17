package com.skcc.oversea.user.infrastructure.jpa;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.skcc.oversea.user.domain.Role;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "roles_jpa")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String roleId;

    @Column(nullable = false)
    private String roleName;

    @Column(length = 500)
    private String description;

    @Builder
    public RoleEntity(Long id, String roleId, String roleName, String description) {
        this.id = id;
        this.roleId = roleId;
        this.roleName = roleName;
        this.description = description;
    }

    public static RoleEntity from(Role role) {
        return RoleEntity.builder()
                .id(role.getId())
                .roleId(role.getRoleId())
                .roleName(role.getRoleName())
                .description(role.getDescription())
                .build();
    }

    public Role toModel() {
        return Role.builder()
                .id(id)
                .roleId(roleId)
                .roleName(roleName)
                .description(description)
                .build();
    }
} 