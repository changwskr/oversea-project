package com.skcc.oversea.user.infrastructure.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositoryJpa extends JpaRepository<UserJpaEntity, Long> {

    Optional<UserJpaEntity> findByEmail(String email);

    Optional<UserJpaEntity> findByUserId(String userId);

    // 검색 메서드들
    @Query("SELECT u FROM UserJpaEntity u WHERE " +
            "u.name LIKE %:keyword% OR " +
            "u.email LIKE %:keyword% OR " +
            "u.userId LIKE %:keyword% OR " +
            "u.username LIKE %:keyword%")
    Page<UserJpaEntity> searchUsers(@Param("keyword") String keyword, Pageable pageable);
}