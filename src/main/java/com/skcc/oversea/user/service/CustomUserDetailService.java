package com.skcc.oversea.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.skcc.oversea.user.exception.CustomException;
import com.skcc.oversea.user.exception.ErrorCode;
import com.skcc.oversea.user.domain.User;
import com.skcc.oversea.user.service.port.UserRepositoryPort;
import com.skcc.oversea.user.domain.UserRole;
import com.skcc.oversea.user.service.port.UserRoleRepositoryPort;

import java.util.Arrays;
import java.util.List;


/**
 * 사용자 인증 및 사용자 세부 정보 검색을 관리하기 위한 서비스 구현입니다.
 * 이 클래스는 Spring Security의 인증 과정에서 사용됩니다.
 */
@Service
public class CustomUserDetailService implements UserDetailsService {

    /**
     * 데이터 소스에서 사용자 정보를 액세스하기 위한 리포지토리 인터페이스입니다.
     */
    private final UserRepositoryPort userRepositoryPort;
    private final UserRoleRepositoryPort userRoleRepositoryPort;

    // 생성자에 @Qualifier 추가
    public CustomUserDetailService(
        @Qualifier("userRepositoryPortJpaCustomImpl") UserRepositoryPort userRepositoryPort,
        UserRoleRepositoryPort userRoleRepositoryPort
    ) {
        this.userRepositoryPort = userRepositoryPort;
        this.userRoleRepositoryPort = userRoleRepositoryPort;
    }

    /**
     * 주어진 이메일을 통해 사용자 세부 정보를 로드합니다.
     *
     * @param email 로드할 사용자의 이메일
     * @return Spring Security를 위한 사용자 자격증명 및 역할이 포함된 UserDetails
     * @throws UsernameNotFoundException 지정된 이메일의 사용자를 찾을 수 없는 경우 예외가 발생합니다.
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        // userId로 사용자를 찾음
        User myUser = userRepositoryPort.findByUserId(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ELEMENT));

        List<UserRole> userRoleList = userRoleRepositoryPort.findByUserId(myUser.getId());
        String[] roleArr = userRoleList.stream().map(item->item.getRole().getRoleId()).toArray(String[]::new);

        return org.springframework.security.core.userdetails.User.builder()
                .username(myUser.getUserId())
                .password(myUser.getPassword())
                .roles(roleArr)
                .build();
    }
    
    /**
     * 주어진 사용자 ID를 통해 사용자 세부 정보를 로드합니다. (JWT 토큰 검증용)
     *
     * @param userId 로드할 사용자의 ID
     * @return Spring Security를 위한 사용자 자격증명 및 역할이 포함된 UserDetails
     * @throws UsernameNotFoundException 지정된 사용자 ID의 사용자를 찾을 수 없는 경우 예외가 발생합니다.
     */
    @Transactional
    public UserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        // 사용자 ID로 사용자를 찾을 수 없으면 사용자 정의 예외를 던집니다.
        User myUser = userRepositoryPort.findByUserId(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ELEMENT));

        List<UserRole> userRoleList = userRoleRepositoryPort.findByUserId(myUser.getId());
        String[] roleArr = userRoleList.stream().map(item->item.getRole().getRoleId()).toArray(String[]::new);

        return org.springframework.security.core.userdetails.User.builder()
                .username(myUser.getUserId()) // JWT에서는 userId를 username으로 사용
                .password(myUser.getPassword())
                .roles(roleArr)
                .build();
    }
}
