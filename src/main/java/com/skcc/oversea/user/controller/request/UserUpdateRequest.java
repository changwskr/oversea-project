package com.skcc.oversea.user.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import com.skcc.oversea.user.domain.User;
import com.skcc.oversea.user.domain.UserStatus;

@Getter
@Builder
public class UserUpdateRequest {

    @NotNull(message = "{javax.validation.constraints.NotNull.message}")
    private final String email;
    private final String username;
    private final String password;
    private final UserStatus status;

    public User toModel() {
        return User.builder()
                .email(email)
                .username(username)
                .password(password)
                .status(status)
                .build();
    }
}
