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
        log.info("[UserRepositoryPortJpaCustomImpl] findById START - id: {}", id);
        
        Optional<User> result = userRepositoryJpa.findById(id).map(UserJpaEntity::toModel);
        
        log.info("[UserRepositoryPortJpaCustomImpl] findById END - found: {}", result.isPresent());
        return result;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        log.info("[UserRepositoryPortJpaCustomImpl] findByEmail START - email: {}", email);
        
        Optional<User> result = userRepositoryJpa.findByEmail(email)
                .map(UserJpaEntity::toModel);
        
        log.info("[UserRepositoryPortJpaCustomImpl] findByEmail END - found: {}", result.isPresent());
        return result;
    }

    @Override
    public User save(User user) {
        log.info("[UserRepositoryPortJpaCustomImpl] save START - userId: {}", user.getUserId());
        
        User result = userRepositoryJpa.save(UserJpaEntity.from(user)).toModel();
        
        log.info("[UserRepositoryPortJpaCustomImpl] save END - userId: {}", result.getUserId());
        return result;
    }

    @Override
    public List<User> findAll() {
        log.info("[UserRepositoryPortJpaCustomImpl] findAll START");
        
        List<User> result = userRepositoryJpa.findAll()
                .stream()
                .map(UserJpaEntity::toModel)
                .toList();
        
        log.info("[UserRepositoryPortJpaCustomImpl] findAll END - count: {}", result.size());
        return result;
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        log.info("[UserRepositoryPortJpaCustomImpl] findAll(Pageable) START - pageable: {}", pageable);
        
        Page<User> result = userRepositoryJpa.findAll(pageable)
                .map(UserJpaEntity::toModel);
        
        log.info("[UserRepositoryPortJpaCustomImpl] findAll(Pageable) END - totalElements: {}", result.getTotalElements());
        return result;
    }

    @Override
    @Deprecated
    public Page<User> findAdminUsers(Pageable pageable) {
        log.info("[UserRepositoryPortJpaCustomImpl] findAdminUsers START - pageable: {}", pageable);
        
        // 예시: role이 ADMIN인 사용자만 조회 (UserJpaEntity에 role 필드가 있다고 가정)
        // 실제로는 UserRepositoryJpa에 쿼리 메서드를 추가해야 함
        // return userRepositoryJpa.findByRole("ADMIN", pageable).map(UserJpaEntity::toModel);
        // 임시로 전체 사용자 반환
        Page<User> result = userRepositoryJpa.findAll(pageable).map(UserJpaEntity::toModel);
        
        log.info("[UserRepositoryPortJpaCustomImpl] findAdminUsers END - totalElements: {}", result.getTotalElements());
        return result;
    }

    @Override
    public User updateStatus(User user) {
        log.info("[UserRepositoryPortJpaCustomImpl] updateStatus START - userId: {}, status: {}", user.getUserId(), user.getStatus());
        
        User result = userRepositoryJpa.save(UserJpaEntity.from(user)).toModel();
        
        log.info("[UserRepositoryPortJpaCustomImpl] updateStatus END - userId: {}", result.getUserId());
        return result;
    }

    @Override
    public Page<User> findAdminUsers(Pageable pageable, List<Long> userIds) {
        log.info("[UserRepositoryPortJpaCustomImpl] findAdminUsers(Pageable, List) START - pageable: {}, userIds: {}", pageable, userIds);
        
        // 예시: userIds에 포함된 사용자만 조회
        // 실제로는 UserRepositoryJpa에 쿼리 메서드를 추가해야 함
        // return userRepositoryJpa.findByIdIn(userIds, pageable).map(UserJpaEntity::toModel);
        // 임시로 전체 사용자 반환
        Page<User> result = userRepositoryJpa.findAll(pageable).map(UserJpaEntity::toModel);
        
        log.info("[UserRepositoryPortJpaCustomImpl] findAdminUsers(Pageable, List) END - totalElements: {}", result.getTotalElements());
        return result;
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        log.info("[UserRepositoryPortJpaCustomImpl] findByUserId START - userId: {}", userId);
        
        Optional<User> result = userRepositoryJpa.findByUserId(userId)
                .map(UserJpaEntity::toModel);
        
        log.info("[UserRepositoryPortJpaCustomImpl] findByUserId END - found: {}", result.isPresent());
        return result;
    }

    @Override
    public void deleteById(Long id) {
        log.info("[UserRepositoryPortJpaCustomImpl] deleteById START - id: {}", id);
        
        userRepositoryJpa.deleteById(id);
        
        log.info("[UserRepositoryPortJpaCustomImpl] deleteById END - id: {}", id);
    }
}
