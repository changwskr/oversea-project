package com.skax.eatool.user.infrastructure.jpa;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.skax.eatool.user.domain.User;
import com.skax.eatool.user.domain.UserStatus;

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
    private String userId; // 요구사항: USERID

    @Column(length = 500)
    private String address; // 요구사항: 주소

    @Column(length = 100)
    private String job; // 요구사항: 직업

    private Integer age; // 요구사항: 나이

    @Column(length = 200)
    private String company; // 요구사항: 회사

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    // 추가로 사용하는 추가 필드들
    @Column(length = 100)
    private String name; // 이름

    @Column(length = 20)
    private String phone; // 전화번호

    @Column(length = 100)
    private String department; // 부서

    @Column(length = 100)
    private String position; // 직책

    @Column(length = 50)
    private String userType; // 사용자 유형

    @Builder
    public UserJpaEntity(Long id, String email, String password, String username, String userId, String address,
            String job, Integer age, String company, UserStatus status, String name, String phone, String department,
            String position, String userType) {
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
        this.name = name;
        this.phone = phone;
        this.department = department;
        this.position = position;
        this.userType = userType;
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
                .name(user.getName())
                .phone(user.getPhone())
                .department(user.getDepartment())
                .position(user.getPosition())
                .userType(user.getUserType())
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
                .name(name)
                .phone(phone)
                .department(department)
                .position(position)
                .userType(userType)
                .createdDate(getCreatedDate())
                .lastModifiedDate(getLastModifiedDate())
                .build();
    }
}
