package com.skax.eatool.user.controller.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserAuthRequest {

    private final String email;
    private final String password;
}
