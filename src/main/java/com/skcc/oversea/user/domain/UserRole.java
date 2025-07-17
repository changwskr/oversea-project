package com.skcc.oversea.user.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserRole {
    private final Long id;
    private final User user;
    private final Role role;

    @Builder
    public UserRole(Long id, User user, Role role) {
        this.id = id;
        this.user = user;
        this.role = role;
    }
} 