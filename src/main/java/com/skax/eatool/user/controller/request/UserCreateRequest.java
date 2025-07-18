package com.skax.eatool.user.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import com.skax.eatool.user.domain.UserCreate;

@Getter
@Builder
public class UserCreateRequest {

    @NotNull(message = "{javax.validation.constraints.NotNull.message}")
    private final String email;
    @NotNull(message = "{javax.validation.constraints.NotNull.message}")
    private final String password;
    @NotNull(message = "{javax.validation.constraints.NotNull.message}")
    private final String username;

    public UserCreate toModel() {
        return  UserCreate.builder()
                .username(username)
                .email(email)
                .password(password)
                .build();
    }
}
