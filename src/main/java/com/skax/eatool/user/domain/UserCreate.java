package com.skax.eatool.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Builder
public class UserCreate {

    private final String email;
    private final String password;
    private final String username;
    private final String userId; // 필수항목: USERID
    private final String address; // 필수항목: 주소
    private final String job; // 필수항목: 직업
    private final Integer age; // 필수항목: 나이
    private final String company; // 필수항목: 회사
    private final String name; // 이름
    private final String phone; // 전화번호
    private final String department; // 부서
    private final String position; // 직책
    private final String userType; // 사용자 유형

}
