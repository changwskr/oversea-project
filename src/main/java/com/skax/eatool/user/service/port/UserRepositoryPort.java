package com.skax.eatool.user.service.port;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.skax.eatool.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {
    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    Optional<User> findByUserId(String userId);

    User save(User user);

    List<User> findAll();

    Page<User> findAll(Pageable pageable);

    Page<User> findAdminUsers(Pageable pageable);

    User updateStatus(User user);

    Page<User> findAdminUsers(Pageable pageable, List<Long> userIds);

    void deleteById(Long id);

    Page<User> searchUsers(String keyword, Pageable pageable);
}
