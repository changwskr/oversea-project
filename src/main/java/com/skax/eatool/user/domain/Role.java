package com.skax.eatool.user.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Role {
    private final Long id;
    private final String roleId;
    private final String roleName;
    private final String description;

    @Builder
    public Role(Long id, String roleId, String roleName, String description) {
        this.id = id;
        this.roleId = roleId;
        this.roleName = roleName;
        this.description = description;
    }

    public static Role defaultRole() {
        return Role.builder()
                .roleId("USER")
                .roleName("일반 사용자")
                .description("기본 사용자 권한")
                .build();
    }

    public static Role adminRole() {
        return Role.builder()
                .roleId("ADMIN")
                .roleName("관리자")
                .description("시스템 관리자 권한")
                .build();
    }
}
