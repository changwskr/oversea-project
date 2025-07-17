package com.skcc.oversea.user.infrastructure.mybatis;

import lombok.*;
import com.skcc.oversea.user.domain.User;
import com.skcc.oversea.user.domain.UserStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String email;
    private String password;
    private String username;
    private UserStatus status;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    public static UserDto from (User user){
        if (user == null) {
            return null;
        }
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .username(user.getUsername())
                .status(user.getStatus())
                .createdDate(user.getCreatedDate())
                .lastModifiedDate(user.getLastModifiedDate())
                .build();
    }

    public User toModel() {
        return User.builder()
                .id(id)
                .email(email)
                .password(password)
                .username(username)
                .status(status)
                .createdDate(createdDate)
                .lastModifiedDate(lastModifiedDate)
                .build();
    }
}
