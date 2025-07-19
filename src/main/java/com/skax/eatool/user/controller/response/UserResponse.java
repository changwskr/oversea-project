package com.skax.eatool.user.controller.response;

import lombok.Builder;
import lombok.Getter;
import com.skax.eatool.user.domain.User;
import com.skax.eatool.user.domain.UserStatus;

@Getter
@Builder
public class UserResponse {

    // 응답값으로 필요한 정보만 매핑
    private Long id;
    private String email;
    private String username;
    private UserStatus status;

    public static UserResponse fromUser(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .status(user.getStatus())
                .build();
    }
}
