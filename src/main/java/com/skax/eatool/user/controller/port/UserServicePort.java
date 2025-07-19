package com.skax.eatool.user.controller.port;

import com.skax.eatool.user.domain.User;
import com.skax.eatool.user.domain.UserCreate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserServicePort {

    // 회원가입 메서드
    User signUp(UserCreate userCreate);

    // 로그인 처리
    String authenticate(String email, String password);

    // 전체 사용자 조회
    List<User> findAllUsers();

    Page<User> findAll(Pageable pageable);

    // ID로 사용자 조회
    User getById(Long id);

    // ADMIN 사용자 조회
    Page<User> findAdminUsers(Pageable pageable);

    // 사용자 상태 변경
    @Deprecated
    User updateUserStatus(User user);

    // 사용자 정보 변경
    User updateUser(User user);

    // 사용자 생성 (관리자 컨트롤러)
    User createUser(User user);

    // 사용자 ID로 조회
    Optional<User> findByUserId(String userId);

    // 사용자 삭제
    void deleteUser(Long id);
}
