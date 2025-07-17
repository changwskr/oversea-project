package com.skcc.oversea.user.infrastructure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import com.skcc.oversea.user.domain.User;
import com.skcc.oversea.user.infrastructure.mybatis.UserDto;
import com.skcc.oversea.user.infrastructure.mybatis.UserRepositoryMybatis;
import com.skcc.oversea.user.service.port.UserRepositoryPort;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryPortMybatisImpl implements UserRepositoryPort {

    private final UserRepositoryMybatis userRepositoryMybatis;

    @Override
    public Optional<User> findById(Long id) {
        log.info("[UserRepositoryPortMybatisImpl] findById START - id: {}", id);
        
        Optional<User> result = userRepositoryMybatis.findById(id).map(UserDto::toModel);
        
        log.info("[UserRepositoryPortMybatisImpl] findById END - found: {}", result.isPresent());
        return result;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        log.info("[UserRepositoryPortMybatisImpl] findByEmail START - email: {}", email);
        
        Optional<User> result = userRepositoryMybatis.findByEmail(email).map(UserDto::toModel);
        
        log.info("[UserRepositoryPortMybatisImpl] findByEmail END - found: {}", result.isPresent());
        return result;
    }

    @Override
    public User save(User user) {
        log.info("[UserRepositoryPortMybatisImpl] save START - userId: {}", user.getUserId());
        
        UserDto userDto = UserDto.from(user);
        Long savedCount = userRepositoryMybatis.save(userDto);
        if(savedCount == 0) {
            log.warn("[UserRepositoryPortMybatisImpl] save END - save failed, savedCount: 0");
            return null;
        }
        User result = userRepositoryMybatis.findById(userDto.getId()).map(UserDto::toModel)
                .orElse(null);
        
        log.info("[UserRepositoryPortMybatisImpl] save END - userId: {}", result != null ? result.getUserId() : "null");
        return result;
    }

    @Override
    public List<User> findAll() {
        log.info("[UserRepositoryPortMybatisImpl] findAll START");
        
        List<User> result = userRepositoryMybatis.findAll()
                .stream()
                .map(UserDto::toModel)
                .toList();
        
        log.info("[UserRepositoryPortMybatisImpl] findAll END - count: {}", result.size());
        return result;
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        log.info("[UserRepositoryPortMybatisImpl] findAll(Pageable) START - pageable: {}", pageable);

        // MyBatis 쿼리를 호출
        List<UserDto> userDtos = userRepositoryMybatis.findAllWithPageable(
                pageable.getOffset(),
                pageable.getPageSize()
        );

        // 총 데이터 개수를 가져오는 로직
        long totalCount = userRepositoryMybatis.countAll();

        // Page<User>로 변환
        List<User> users = userDtos.stream()
                .map(UserDto::toModel)
                .toList();

        // 반환: Page 구현체 생성
        Page<User> result = new PageImpl<>(users, pageable, totalCount);
        
        log.info("[UserRepositoryPortMybatisImpl] findAll(Pageable) END - totalElements: {}", result.getTotalElements());
        return result;
    }

    @Override
    public Page<User> findAdminUsers(Pageable pageable) {
        log.info("[UserRepositoryPortMybatisImpl] findAdminUsers START - pageable: {}", pageable);
        
        log.info("[UserRepositoryPortMybatisImpl] findAdminUsers END - not implemented");
        return null;
    }

    @Override
    public User updateStatus(User user) {
        log.info("[UserRepositoryPortMybatisImpl] updateStatus START - userId: {}", user.getUserId());
        
        log.info("[UserRepositoryPortMybatisImpl] updateStatus END - not implemented");
        return null;
    }

    @Override
    public Page<User> findAdminUsers(Pageable pageable, List<Long> userIds) {
        log.info("[UserRepositoryPortMybatisImpl] findAdminUsers(Pageable, List) START - pageable: {}, userIds: {}", pageable, userIds);
        
        log.info("[UserRepositoryPortMybatisImpl] findAdminUsers(Pageable, List) END - not implemented");
        return null;
    }

    @Override
    public void deleteById(Long id) {
        log.info("[UserRepositoryPortMybatisImpl] deleteById START - id: {}", id);
        
        // MyBatis를 사용한 사용자 삭제 로직
        // 현재는 기본 구현만 제공
        log.error("[UserRepositoryPortMybatisImpl] deleteById END - UnsupportedOperationException");
        throw new UnsupportedOperationException("MyBatis 구현에서는 삭제 기능이 아직 구현되지 않았습니다.");
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        log.info("[UserRepositoryPortMybatisImpl] findByUserId START - userId: {}", userId);
        
        // MyBatis를 사용한 사용자 ID 검색 로직
        // 현재는 기본 구현만 제공
        log.error("[UserRepositoryPortMybatisImpl] findByUserId END - UnsupportedOperationException");
        throw new UnsupportedOperationException("MyBatis 구현에서는 findByUserId 기능이 아직 구현되지 않았습니다.");
    }
}
