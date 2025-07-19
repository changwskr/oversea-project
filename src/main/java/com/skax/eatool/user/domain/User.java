package com.skax.eatool.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Getter
@Setter
public class User {

    private Long id;
    private String email;
    private String password;
    private String username;
    private String userId; // 필수항목: USERID
    private String address; // 필수항목: 주소
    private String job; // 필수항목: 직업
    private Integer age; // 필수항목: 나이
    private String company; // 필수항목: 회사
    private UserStatus status;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    // 추가 사용자 필드
    private String name; // 이름
    private String phone; // 전화번호
    private String department; // 부서
    private String position; // 직책
    private String userType; // 사용자 유형

    // 기본 생성자 추가
    public User() {
        // 기본 생성자 - Thymeleaf 바인딩을 위해 필요
    }

    @Builder
    public User(Long id, String email, String password, String username, String userId, String address, String job,
            Integer age, String company, UserStatus status, LocalDateTime createdDate, LocalDateTime lastModifiedDate,
            String name, String phone, String department, String position, String userType) {
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
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.name = name;
        this.phone = phone;
        this.department = department;
        this.position = position;
        this.userType = userType;
    }

    /**
     * 사용자 생성할 때 사용
     *
     * @param userCreate      사용자 생성 모델
     * @param passwordEncoder 비밀번호 인코딩 구현체
     * @return 사용자 모델
     */
    public static User from(UserCreate userCreate, PasswordEncoder passwordEncoder) {
        User newUser = User.builder()
                .email(userCreate.getEmail())
                .username(userCreate.getUsername())
                .userId(userCreate.getUserId())
                .address(userCreate.getAddress())
                .job(userCreate.getJob())
                .age(userCreate.getAge())
                .company(userCreate.getCompany())
                .name(userCreate.getName())
                .phone(userCreate.getPhone())
                .department(userCreate.getDepartment())
                .position(userCreate.getPosition())
                .userType(userCreate.getUserType())
                .password(passwordEncoder.encode(userCreate.getPassword()))
                .status(UserStatus.PENDING)
                // JPA의 경우 BaseEntity로 처리
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();
        return newUser;
    }

    /**
     * 상태 값을 변경할 때 사용
     * 
     * @param requestStatus
     * @return
     */
    public User updateStatus(UserStatus requestStatus) {

        if (requestStatus == null) {
            throw new IllegalArgumentException("requestStatus is null");
        }

        if (status == requestStatus) {
            throw new IllegalStateException("status is same");
        }

        User target = User.builder()
                .id(id)
                .email(email)
                .username(username)
                .userId(userId)
                .address(address)
                .job(job)
                .age(age)
                .company(company)
                .password(password)
                .name(name)
                .phone(phone)
                .department(department)
                .position(position)
                .userType(userType)
                .status(requestStatus)
                .build();
        return target;
    }

    /**
     * 사용자 정보 업데이트
     */
    public User updateUser(User updateUser, PasswordEncoder passwordEncoder) {
        User userToUpdate = User.builder()
                .id(id)
                .email(email)
                .username(updateUser.getUsername() != null ? updateUser.getUsername() : username)
                .userId(updateUser.getUserId() != null ? updateUser.getUserId() : userId)
                .address(updateUser.getAddress() != null ? updateUser.getAddress() : address)
                .job(updateUser.getJob() != null ? updateUser.getJob() : job)
                .age(updateUser.getAge() != null ? updateUser.getAge() : age)
                .company(updateUser.getCompany() != null ? updateUser.getCompany() : company)
                .name(updateUser.getName() != null ? updateUser.getName() : name)
                .phone(updateUser.getPhone() != null ? updateUser.getPhone() : phone)
                .department(updateUser.getDepartment() != null ? updateUser.getDepartment() : department)
                .position(updateUser.getPosition() != null ? updateUser.getPosition() : position)
                .userType(updateUser.getUserType() != null ? updateUser.getUserType() : userType)
                .password(
                        updateUser.getPassword() != null ? passwordEncoder.encode(updateUser.getPassword()) : password)
                .status(updateUser.getStatus() != null ? updateUser.getStatus() : status)
                .build();
        return userToUpdate;
    }

    /**
     * 생성 시점 반환
     */
    public LocalDateTime getCreatedAt() {
        return createdDate;
    }

    /**
     * 수정 시점 반환
     */
    public LocalDateTime getUpdatedAt() {
        return lastModifiedDate;
    }
}
