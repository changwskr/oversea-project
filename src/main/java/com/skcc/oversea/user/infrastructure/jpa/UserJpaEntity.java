package com.skcc.oversea.user.infrastructure.jpa;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.skcc.oversea.user.domain.User;
import com.skcc.oversea.user.domain.UserStatus;


import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users_jpa")
public class UserJpaEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String userId;  // 요구사항: USERID

    @Column(length = 500)
    private String address;  // 요구사항: 주소

    @Column(length = 100)
    private String job;      // 요구사항: 직업

    private Integer age;     // 요구사항: 나이

    @Column(length = 200)
    private String company;  // 요구사항: 회사

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Builder
    public UserJpaEntity(Long id, String email, String password, String username, String userId, String address, String job, Integer age, String company, UserStatus status) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.userId = userId;
        this.address = address;
        this.job = job;
        this.age = age;
        this.company = company;
        this.status = status;
    }

    public static UserJpaEntity from(User user) {
        return UserJpaEntity.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .username(user.getUsername())
                .userId(user.getUserId())
                .address(user.getAddress())
                .job(user.getJob())
                .age(user.getAge())
                .company(user.getCompany())
                .status(user.getStatus())
                .build();
    }

    public User toModel() {
        return User.builder()
                .id(id)
                .email(email)
                .password(password)
                .username(username)
                .userId(userId)
                .address(address)
                .job(job)
                .age(age)
                .company(company)
                .status(status)
                .createdDate(getCreatedDate())
                .lastModifiedDate(getLastModifiedDate())
                .build();
    }
}