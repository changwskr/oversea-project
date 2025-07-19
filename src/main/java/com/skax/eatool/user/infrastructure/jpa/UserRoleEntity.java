package com.skax.eatool.user.infrastructure.jpa;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.skax.eatool.user.domain.Role;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_roles_jpa")
public class UserRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserJpaEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    @Builder
    public UserRoleEntity(Long id, UserJpaEntity user, RoleEntity role) {
        this.id = id;
        this.user = user;
        this.role = role;
    }
} 
