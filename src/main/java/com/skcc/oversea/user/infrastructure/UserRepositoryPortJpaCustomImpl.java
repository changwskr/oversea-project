package com.skcc.oversea.user.infrastructure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import com.skcc.oversea.user.domain.User;
import com.skcc.oversea.user.infrastructure.jpa.UserJpaEntity;
import com.skcc.oversea.user.infrastructure.jpa.UserRepositoryJpa;
import com.skcc.oversea.user.service.port.UserRepositoryPort;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryPortJpaCustomImpl implements UserRepositoryPort {

    private final UserRepositoryJpa userRepositoryJpa;

    @Override
    public Optional<User> findById(Long id) {
        return userRepositoryJpa.findById(id).map(UserJpaEntity::toModel);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepositoryJpa.findByEmail(email)
                .map(UserJpaEntity::toModel);
    }

    @Override
    public User save(User user) {
        return userRepositoryJpa.save(UserJpaEntity.from(user)).toModel();
    }

    @Override
    public List<User> findAll() {
        return userRepositoryJpa.findAll()
                .stream()
                .map(UserJpaEntity::toModel)
                .toList();
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepositoryJpa.findAll(pageable)
                .map(UserJpaEntity::toModel);
    }

    @Override
    @Deprecated
    public Page<User> findAdminUsers(Pageable pageable) {
        // 예시: role이 ADMIN인 사용자만 조회 (UserJpaEntity에 role 필드가 있다고 가정)
        // 실제로는 UserRepositoryJpa에 쿼리 메서드를 추가해야 함
        // return userRepositoryJpa.findByRole("ADMIN", pageable).map(UserJpaEntity::toModel);
        // 임시로 전체 사용자 반환
        return userRepositoryJpa.findAll(pageable).map(UserJpaEntity::toModel);
    }

    @Override
    public User updateStatus(User user) {
        return userRepositoryJpa.save(UserJpaEntity.from(user)).toModel();
    }

    @Override
    public Page<User> findAdminUsers(Pageable pageable, List<Long> userIds) {
        // 예시: userIds에 포함된 사용자만 조회
        // 실제로는 UserRepositoryJpa에 쿼리 메서드를 추가해야 함
        // return userRepositoryJpa.findByIdIn(userIds, pageable).map(UserJpaEntity::toModel);
        // 임시로 전체 사용자 반환
        return userRepositoryJpa.findAll(pageable).map(UserJpaEntity::toModel);
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        return userRepositoryJpa.findByUserId(userId)
                .map(UserJpaEntity::toModel);
    }

    @Override
    public void deleteById(Long id) {
        userRepositoryJpa.deleteById(id);
    }
}
