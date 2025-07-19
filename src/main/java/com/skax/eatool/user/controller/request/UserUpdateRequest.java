package com.skax.eatool.user.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import com.skax.eatool.user.domain.User;
import com.skax.eatool.user.domain.UserStatus;

@Getter
@Builder
public class UserUpdateRequest {

    @NotNull(message = "{javax.validation.constraints.NotNull.message}")
    private final Long userId;
    private final String email;
    private final String username;
    private final String password;
    private final UserStatus status;

    public User toModel() {
        return User.builder()
                .id(userId)
                .email(email)
                .username(username)
                .password(password)
                .status(status)
                .build();
    }
}
