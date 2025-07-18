# SKAX AI TOOL System - 아키텍처 및 기술 스택 가이드

## 📋 프로젝트 개요

**SKAX AI TOOL**은 레거시 J2EE/EJB 아키텍처에서 **Spring Boot 3.x**로 마이그레이션된 현대적인 AI 도구 시스템입니다. 현금카드 관리, 예금 서비스, 공통 서비스, 텔러 관리, 사용자 관리 등 다양한 뱅킹 기능을 제공하는 **MSA(Microservice Architecture)** 기반 시스템입니다.

---

## 🏗️ 아키텍처 구조

### 📊 전체 아키텍처 다이어그램

```
┌─────────────────────────────────────────────────────────────────┐
│                    SKAX AI TOOL System                  │
├─────────────────────────────────────────────────────────────────┤
│  Presentation Layer (Web UI)                                    │
│  ├── Thymeleaf Templates                                        │
│  ├── Static Resources (CSS/JS/Images)                          │
│  └── REST API Controllers                                       │
├─────────────────────────────────────────────────────────────────┤
│  Security Layer                                                 │
│  ├── Spring Security                                            │
│  ├── Custom User Details Service                               │
│  ├── Password Encoder (BCrypt)                                 │
│  └── Security Policies                                         │
├─────────────────────────────────────────────────────────────────┤
│  Business Layer (Service Layer)                                 │
│  ├── User Management Service                                   │
│  ├── Cash Card Management Service                              │
│  ├── Deposit Management Service                                │
│  ├── Teller Management Service                                 │
│  ├── Common Service                                            │
│  └── Transaction Log Service                                   │
├─────────────────────────────────────────────────────────────────┤
│  Data Access Layer                                              │
│  ├── JPA Repositories                                          │
│  ├── MyBatis Mappers                                           │
│  ├── Entity Classes                                            │
│  └── DTO Classes                                               │
├─────────────────────────────────────────────────────────────────┤
│  Infrastructure Layer                                           │
│  ├── H2 Database (Development)                                 │
│  ├── Oracle Database (Production)                              │
│  ├── Connection Pooling                                        │
│  └── Transaction Management                                    │
└─────────────────────────────────────────────────────────────────┘
```

### 🔄 마이그레이션 전후 비교

| 구분              | 마이그레이션 전 (Legacy) | 마이그레이션 후 (Modern)  |
| ----------------- | ------------------------ | ------------------------- |
| **아키텍처 패턴** | J2EE/EJB Monolithic      | Spring Boot MSA           |
| **Java 버전**     | Java 8                   | Java 18                   |
| **빌드 도구**     | Ant/Maven                | Maven 3.x                 |
| **의존성 주입**   | EJB Container            | Spring IoC Container      |
| **데이터베이스**  | Oracle                   | H2 (개발) / Oracle (운영) |
| **API 문서화**    | 없음                     | Swagger/OpenAPI 3.0       |
| **보안**          | 기본 인증                | Spring Security + BCrypt  |
| **템플릿 엔진**   | JSP                      | Thymeleaf                 |
| **ORM**           | EJB Entity Bean          | JPA/Hibernate             |
| **트랜잭션**      | EJB Transaction          | Spring Transaction        |

---

## 🛠️ 기술 스택

### 🎯 Core Technologies

| 카테고리       | 기술            | 버전  | 설명                         |
| -------------- | --------------- | ----- | ---------------------------- |
| **Framework**  | Spring Boot     | 3.1.4 | 메인 애플리케이션 프레임워크 |
| **Language**   | Java            | 18    | 프로그래밍 언어              |
| **Build Tool** | Maven           | 3.x   | 의존성 관리 및 빌드 도구     |
| **Database**   | H2              | 2.x   | 인메모리 데이터베이스 (개발) |
| **Database**   | Oracle          | 19c   | 운영 데이터베이스            |
| **ORM**        | JPA/Hibernate   | 6.x   | 객체 관계 매핑               |
| **Security**   | Spring Security | 6.x   | 보안 프레임워크              |

### 🌐 Web Technologies

| 기술                  | 버전  | 용도                   |
| --------------------- | ----- | ---------------------- |
| **Thymeleaf**         | 3.x   | 서버사이드 템플릿 엔진 |
| **Spring MVC**        | 6.x   | 웹 MVC 프레임워크      |
| **SpringDoc OpenAPI** | 2.2.0 | API 문서화 (Swagger)   |
| **MyBatis**           | 3.0.2 | SQL 매퍼               |

### 🔧 Development Tools

| 도구                   | 버전    | 용도                 |
| ---------------------- | ------- | -------------------- |
| **Lombok**             | 1.18.30 | 코드 생성 라이브러리 |
| **Jakarta Annotation** | 2.x     | 어노테이션 API       |
| **JAXB Runtime**       | 4.x     | XML 파싱 지원        |

---

## 📁 프로젝트 구조

### 🗂️ 디렉토리 구조

```
oversea-project/
├── src/
│   ├── main/
│   │   ├── java/com/skcc/oversea/
│   │   │   ├── OverseaApplication.java              # 메인 애플리케이션
│   │   │   ├── config/                              # 설정 클래스들
│   │   │   │   ├── SecurityConfig.java             # 보안 설정
│   │   │   │   ├── SwaggerConfig.java              # API 문서 설정
│   │   │   │   └── DataInitializer.java            # 데이터 초기화
│   │   │   ├── controller/                          # 웹 컨트롤러
│   │   │   │   ├── MainController.java             # 메인 페이지
│   │   │   │   ├── HealthController.java           # 헬스 체크 API
│   │   │   │   └── UserManagementWebController.java # 사용자 관리 웹
│   │   │   ├── user/                               # 사용자 관리 모듈
│   │   │   │   ├── controller/                     # 사용자 컨트롤러
│   │   │   │   ├── service/                        # 사용자 서비스
│   │   │   │   ├── domain/                         # 사용자 도메인
│   │   │   │   ├── infrastructure/                 # 사용자 인프라
│   │   │   │   └── dto/                            # 사용자 DTO
│   │   │   ├── cashCard/                           # 현금카드 모듈
│   │   │   │   ├── business/                       # 비즈니스 로직
│   │   │   │   ├── repository/                     # 데이터 접근
│   │   │   │   └── entity/                         # 엔티티 클래스
│   │   │   ├── deposit/                            # 예금 모듈
│   │   │   │   ├── entity/                         # 예금 엔티티
│   │   │   │   └── repository/                     # 예금 리포지토리
│   │   │   ├── teller/                             # 텔러 모듈
│   │   │   │   ├── entity/                         # 텔러 엔티티
│   │   │   │   └── repository/                     # 텔러 리포지토리
│   │   │   ├── common/                             # 공통 모듈
│   │   │   │   ├── entity/                         # 공통 엔티티
│   │   │   │   └── repository/                     # 공통 리포지토리
│   │   │   ├── eplatonframework/                   # E-Platon 프레임워크
│   │   │   │   ├── business/                       # 비즈니스 로직
│   │   │   │   ├── config/                         # 프레임워크 설정
│   │   │   │   └── transfer/                       # 전송 객체
│   │   │   ├── foundation/                         # 기반 모듈
│   │   │   │   ├── config/                         # 기반 설정
│   │   │   │   ├── security/                       # 보안 유틸리티
│   │   │   │   └── db/                             # 데이터베이스 설정
│   │   │   └── hello/                              # 테스트 모듈
│   │   └── resources/
│   │       ├── application.yml                     # 애플리케이션 설정
│   │       ├── schema.sql                          # 데이터베이스 스키마
│   │       ├── data.sql                            # 초기 데이터
│   │       ├── config/                             # 설정 파일들
│   │       │   ├── oversea-config.xml              # 비즈니스 액션 설정
│   │       │   └── skcc-oversea.properties         # SKCC 설정
│   │       ├── static/                             # 정적 리소스
│   │       │   ├── css/                            # 스타일시트
│   │       │   ├── js/                             # 자바스크립트
│   │       │   └── images/                         # 이미지 파일
│   │       └── templates/                          # Thymeleaf 템플릿
│   │           ├── user/                           # 사용자 관련 템플릿
│   │           ├── cashcard/                       # 현금카드 관련 템플릿
│   │           └── common/                         # 공통 템플릿
│   └── test/                                       # 테스트 코드
├── pom.xml                                         # Maven 설정
└── README.md                                       # 프로젝트 문서
```

---

## 🔧 Spring 주요 기술 상세

### 1. 🚀 Spring Boot 3.1.4

#### 주요 특징

- **자동 설정 (Auto-Configuration)**: 클래스패스 기반 자동 설정
- **내장 서버**: Tomcat 내장으로 독립 실행 가능
- **Actuator**: 모니터링 및 관리 기능
- **Starter POM**: 의존성 그룹화

#### 핵심 어노테이션

```java
@SpringBootApplication                    // 메인 애플리케이션 클래스
@ComponentScan(basePackages = {...})      // 컴포넌트 스캔 범위
@EntityScan(basePackages = {...})         // JPA 엔티티 스캔
@EnableJpaRepositories(basePackages = {...}) // JPA 리포지토리 활성화
@EnableTransactionManagement              // 트랜잭션 관리 활성화
@EnableAsync                              // 비동기 처리 활성화
@EnableScheduling                         // 스케줄링 활성화
```

### 2. 🔒 Spring Security 6.x

#### 보안 구성

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/login", "/signup").permitAll()
                .requestMatchers("/home").authenticated()
                .anyRequest().permitAll()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/home")
                .failureUrl("/login?error=true")
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
            )
            .csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

#### 사용자 인증 서비스

```java
@Service
public class CustomUserDetailService implements UserDetailsService {

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepositoryPort.findByUserId(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ELEMENT));

        List<UserRole> userRoles = userRoleRepositoryPort.findByUserId(user.getId());
        String[] roles = userRoles.stream()
                .map(item -> item.getRole().getRoleId())
                .toArray(String[]::new);

        return User.builder()
                .username(user.getUserId())
                .password(user.getPassword())
                .roles(roles)
                .build();
    }
}
```

### 3. 🗄️ Spring Data JPA

#### 엔티티 정의

```java
@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String userId;

    @Column(nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
```

#### 리포지토리 인터페이스

```java
@Repository
public interface UserRepositoryJpa extends JpaRepository<UserJpaEntity, Long> {

    Optional<UserJpaEntity> findByUserId(String userId);
    Optional<UserJpaEntity> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByUserId(String userId);

    @Query("SELECT u FROM UserJpaEntity u WHERE u.status = :status")
    List<UserJpaEntity> findByStatus(@Param("status") UserStatus status);
}
```

### 4. 🌐 Spring MVC + Thymeleaf

#### 컨트롤러

```java
@Controller
@RequestMapping("/user-management-web")
@RequiredArgsConstructor
@Slf4j
public class UserManagementWebController {

    private final UserService userService;
    private final SecurityPolicyService securityPolicyService;

    @GetMapping
    public String userManagementPage(Model model) {
        log.info("[UserManagementWebController.userManagementPage START]");

        List<User> users = userService.findAllUsers();
        model.addAttribute("title", "사용자 관리");
        model.addAttribute("users", users);

        log.info("[UserManagementWebController.userManagementPage END]");
        return "user/management/index";
    }

    @PostMapping("/users")
    public String createUser(@ModelAttribute UserCreateRequest request,
                           Model model,
                           RedirectAttributes redirectAttributes) {
        try {
            UserCreate userCreate = UserCreate.builder()
                    .userId(request.getUserId())
                    .username(request.getUsername())
                    .email(request.getEmail())
                    .password(request.getPassword())
                    .address(request.getAddress())
                    .job(request.getJob())
                    .age(request.getAge())
                    .company(request.getCompany())
                    .build();

            userService.signUp(userCreate);
            redirectAttributes.addFlashAttribute("successMessage", "사용자가 성공적으로 생성되었습니다.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "사용자 생성 중 오류가 발생했습니다: " + e.getMessage());
        }

        return "redirect:/user-management-web";
    }
}
```

#### Thymeleaf 템플릿

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
  <head>
    <meta charset="UTF-8" />
    <title th:text="${title}">사용자 관리</title>
    <link rel="stylesheet" th:href="@{/css/style.css}" />
  </head>
  <body>
    <div class="container">
      <h1 th:text="${title}">사용자 관리</h1>

      <!-- 성공/오류 메시지 -->
      <div
        th:if="${successMessage}"
        class="alert alert-success"
        th:text="${successMessage}"
      ></div>
      <div
        th:if="${errorMessage}"
        class="alert alert-danger"
        th:text="${errorMessage}"
      ></div>

      <!-- 사용자 목록 -->
      <div class="user-list">
        <div th:each="user : ${users}" class="user-card">
          <h3 th:text="${user.username}">사용자명</h3>
          <p th:text="${user.email}">이메일</p>
          <p th:text="${user.status}">상태</p>
        </div>
      </div>
    </div>

    <script th:src="@{/js/user-management.js}"></script>
  </body>
</html>
```

### 5. 📚 SpringDoc OpenAPI (Swagger)

#### API 문서화 설정

```java
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("SKAX AI TOOL API")
.description("SKAX AI TOOL System API Documentation")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("SKCC Development Team")
                                .email("dev@skcc.com")
                                .url("https://www.skcc.com"))
                        .license(new License()
                                .name("SKCC Internal License")
                                .url("https://www.skcc.com/license")))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Development Server"),
                        new Server().url("https://api.skcc.com").description("Production Server")
                ));
    }
}
```

#### API 컨트롤러 예시

```java
@RestController
@RequestMapping("/api/health")
@Tag(name = "Health Check", description = "Health check and application information endpoints")
public class HealthController {

    @GetMapping
    @Operation(
        summary = "Health Check",
        description = "Returns the current health status of the SKAX AI TOOL application"
    )
    public Map<String, Object> health() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("timestamp", LocalDateTime.now());
        response.put("application", "SKAX AI TOOL");
        response.put("version", "1.0.0");
        return response;
    }
}
```

### 6. 🔄 Spring Transaction Management

#### 트랜잭션 설정

```java
@Configuration
@EnableTransactionManagement
public class TransactionConfig {

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
```

#### 서비스 레이어 트랜잭션

```java
@Service
@Transactional
public class UserService implements UserServicePort {

    @Override
    @Transactional
    public User signUp(UserCreate userCreate) {
        // 중복 이메일 체크
        checkUserExistByEmail(userCreate.getEmail());

        // 사용자 생성
        User user = User.from(userCreate, passwordEncoder);

        // 저장
        User savedUser = userRepositoryPort.save(user);

        return savedUser;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllUsers() {
        return userRepositoryPort.findAll();
    }
}
```

---

## 🗄️ 데이터베이스 설계

### 📊 ERD (Entity Relationship Diagram)

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│     USERS       │    │   USER_ROLES    │    │     ROLES       │
├─────────────────┤    ├─────────────────┤    ├─────────────────┤
│ id (PK)         │    │ id (PK)         │    │ id (PK)         │
│ user_id (UK)    │◄───┤ user_id (FK)    │    │ role_id (UK)    │
│ username        │    │ role_id (FK)    ├────┤ role_name       │
│ email (UK)      │    │ created_at      │    │ description     │
│ password        │    └─────────────────┘    │ created_at      │
│ address         │                           └─────────────────┘
│ job             │
│ age             │    ┌─────────────────┐    ┌─────────────────┐
│ company         │    │   CASH_CARDS    │    │    HOT_CARDS    │
│ status          │    ├─────────────────┤    ├─────────────────┤
│ created_at      │    │ id (PK)         │    │ id (PK)         │
│ updated_at      │    │ card_number     │    │ card_number     │
└─────────────────┘    │ card_type       │    │ card_type       │
                       │ balance         │    │ status          │
                       │ status          │    │ created_at      │
                       │ created_at      │    └─────────────────┘
                       └─────────────────┘
```

### 🔧 데이터베이스 설정

#### application.yml

```yaml
spring:
  datasource:
    url: jdbc:h2:mem:testdb
    driver-class-name: org.h2.Driver
    username: sa
    password:
  jpa:
    hibernate:
      ddl-auto: create-drop
    show-sql: true
    properties:
      hibernate:
        format_sql: true
  sql:
    init:
      mode: always
      schema-locations: classpath:schema.sql
      data-locations: classpath:data.sql
      continue-on-error: true
  h2:
    console:
      enabled: true
```

---

## 🚀 실행 및 배포

### 📋 사전 요구사항

| 항목      | 버전                    | 설명        |
| --------- | ----------------------- | ----------- |
| **Java**  | 18+                     | JDK 18 이상 |
| **Maven** | 3.8+                    | 빌드 도구   |
| **IDE**   | IntelliJ IDEA / Eclipse | 개발 환경   |
| **Git**   | 2.x+                    | 버전 관리   |

### 🔧 로컬 개발 환경 설정

#### 1. 프로젝트 클론

```bash
git clone <repository-url>
cd oversea-project
```

#### 2. 의존성 설치 및 빌드

```bash
mvn clean compile
```

#### 3. 애플리케이션 실행

```bash
# Maven으로 실행
mvn spring-boot:run

# 또는 JAR 파일로 실행
mvn clean package
java -jar target/oversea-0.0.1-SNAPSHOT.jar
```

### 🌐 접속 URL

| 서비스                | URL                                       | 설명                                |
| --------------------- | ----------------------------------------- | ----------------------------------- |
| **메인 애플리케이션** | http://localhost:8080                     | 루트 페이지 (로그인으로 리다이렉트) |
| **로그인 페이지**     | http://localhost:8080/login               | 사용자 로그인                       |
| **홈 페이지**         | http://localhost:8080/home                | 서비스 선택 화면                    |
| **사용자 관리**       | http://localhost:8080/user-management-web | 사용자 관리 웹                      |
| **현금카드 관리**     | http://localhost:8080/cashcard            | 현금카드 관리                       |
| **예금 관리**         | http://localhost:8080/deposit             | 예금 관리                           |
| **텔러 관리**         | http://localhost:8080/teller              | 텔러 관리                           |
| **Swagger UI**        | http://localhost:8080/swagger-ui.html     | API 문서                            |
| **H2 콘솔**           | http://localhost:8080/h2-console          | 데이터베이스 관리                   |

---

## 🔍 주요 기능 모듈

### 👥 사용자 관리 (User Management)

#### 기능

- 사용자 등록/수정/삭제
- 사용자 인증 및 권한 관리
- 보안 정책 관리 (비밀번호, 세션, IP 화이트리스트)
- 사용자 활동 로그

#### API 엔드포인트

```
POST   /api/users                    # 사용자 생성
GET    /api/users                    # 사용자 목록 조회
GET    /api/users/{id}               # 사용자 상세 조회
PUT    /api/users/{id}               # 사용자 수정
DELETE /api/users/{id}               # 사용자 삭제
POST   /api/auth/login               # 로그인
POST   /api/auth/logout              # 로그아웃
```

### 💳 현금카드 관리 (Cash Card Management)

#### 기능

- 현금카드 발급/해지
- 잔액 조회 및 관리
- 핫카드 관리
- 거래 내역 조회

#### API 엔드포인트

```
GET    /api/cashcards                # 현금카드 목록
GET    /api/cashcards/{id}           # 현금카드 상세
POST   /api/cashcards                # 현금카드 발급
PUT    /api/cashcards/{id}           # 현금카드 수정
DELETE /api/cashcards/{id}           # 현금카드 해지
GET    /api/hotcards                 # 핫카드 목록
```

### 💰 예금 관리 (Deposit Management)

#### 기능

- 예금 계좌 개설/해지
- 입출금 처리
- 이자 계산
- 예금 상품 관리

#### API 엔드포인트

```
GET    /api/deposits                 # 예금 목록
GET    /api/deposits/{id}            # 예금 상세
POST   /api/deposits                 # 예금 계좌 개설
PUT    /api/deposits/{id}            # 예금 정보 수정
DELETE /api/deposits/{id}            # 예금 계좌 해지
POST   /api/deposits/{id}/deposit    # 입금
POST   /api/deposits/{id}/withdraw   # 출금
```

### 🏦 텔러 관리 (Teller Management)

#### 기능

- 텔러 등록/수정/삭제
- 텔러 권한 관리
- 업무 처리 내역
- 성과 관리

#### API 엔드포인트

```
GET    /api/tellers                  # 텔러 목록
GET    /api/tellers/{id}             # 텔러 상세
POST   /api/tellers                  # 텔러 등록
PUT    /api/tellers/{id}             # 텔러 정보 수정
DELETE /api/tellers/{id}             # 텔러 삭제
```

### 📊 공통 서비스 (Common Services)

#### 기능

- 공통 코드 관리
- 은행/지점 정보 관리
- 통화 정보 관리
- 시스템 설정 관리

#### API 엔드포인트

```
GET    /api/common/codes             # 공통 코드 조회
GET    /api/common/banks             # 은행 정보 조회
GET    /api/common/branches          # 지점 정보 조회
GET    /api/common/currencies        # 통화 정보 조회
```

---

## 🔧 개발 가이드

### 📝 새로운 API 추가

#### 1. 컨트롤러 생성

```java
@RestController
@RequestMapping("/api/example")
@Tag(name = "Example", description = "Example API")
@RequiredArgsConstructor
@Slf4j
public class ExampleController {

    private final ExampleService exampleService;

    @GetMapping
    @Operation(summary = "Get Examples", description = "Get all examples")
    public ResponseEntity<List<Example>> getAllExamples() {
        log.info("[ExampleController.getAllExamples START]");
        List<Example> examples = exampleService.findAll();
        log.info("[ExampleController.getAllExamples END]");
        return ResponseEntity.ok(examples);
    }

    @PostMapping
    @Operation(summary = "Create Example", description = "Create a new example")
    public ResponseEntity<Example> createExample(@RequestBody ExampleCreateRequest request) {
        log.info("[ExampleController.createExample START]");
        Example example = exampleService.create(request);
        log.info("[ExampleController.createExample END]");
        return ResponseEntity.status(HttpStatus.CREATED).body(example);
    }
}
```

#### 2. 서비스 생성

```java
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ExampleService {

    private final ExampleRepository exampleRepository;

    public List<Example> findAll() {
        return exampleRepository.findAll();
    }

    public Example create(ExampleCreateRequest request) {
        Example example = Example.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
        return exampleRepository.save(example);
    }
}
```

#### 3. 엔티티 생성

```java
@Entity
@Table(name = "examples")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Example {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
```

#### 4. 리포지토리 생성

```java
@Repository
public interface ExampleRepository extends JpaRepository<Example, Long> {
    List<Example> findByNameContaining(String name);
    Optional<Example> findByName(String name);
}
```

### 🎨 새로운 웹 페이지 추가

#### 1. 컨트롤러 메서드 추가

```java
@Controller
@RequestMapping("/example")
public class ExampleWebController {

    @GetMapping
    public String examplePage(Model model) {
        model.addAttribute("title", "Example Management");
        return "example/index";
    }
}
```

#### 2. Thymeleaf 템플릿 생성

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
  <head>
    <meta charset="UTF-8" />
    <title th:text="${title}">Example Management</title>
    <link rel="stylesheet" th:href="@{/css/style.css}" />
  </head>
  <body>
    <div class="container">
      <h1 th:text="${title}">Example Management</h1>
      <!-- 페이지 내용 -->
    </div>
  </body>
</html>
```

---

## 🧪 테스트

### 📊 테스트 데이터

애플리케이션 시작 시 자동으로 생성되는 테스트 데이터:

#### 사용자 데이터

- 관리자 사용자 (admin/admin123)
- 일반 사용자 3명

#### 현금카드 데이터

- 5개의 테스트 현금카드
- 3개의 핫카드

#### 예금 데이터

- 7개의 테스트 예금 계좌

#### 텔러 데이터

- 4명의 테스트 텔러

#### 공통 데이터

- 은행 정보 (신한은행, 국민은행 등)
- 지점 정보
- 통화 정보 (KRW, USD, EUR 등)

### 🔍 테스트 API

#### 헬스 체크

```bash
curl http://localhost:8080/api/health
```

#### 사용자 목록 조회

```bash
curl http://localhost:8080/api/users
```

#### 현금카드 목록 조회

```bash
curl http://localhost:8080/api/cashcards
```

---

## 🔒 보안

### 🛡️ 보안 정책

#### 비밀번호 정책

- 최소 8자 이상
- 영문, 숫자, 특수문자 조합
- BCrypt 해싱 알고리즘 사용

#### 세션 정책

- 세션 타임아웃: 30분
- 동시 접속 제한: 1000명

#### IP 화이트리스트

- 개발 환경: 모든 IP 허용
- 운영 환경: 허용된 IP만 접근

### 🔐 인증 및 권한

#### 사용자 역할

- **ADMIN**: 시스템 관리자
- **USER**: 일반 사용자
- **TELLER**: 텔러
- **MANAGER**: 관리자

#### 권한 체계

```java
@PreAuthorize("hasRole('ADMIN')")
@PreAuthorize("hasRole('USER')")
@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
```

---

## 📈 모니터링 및 로깅

### 📊 로깅 설정

#### application.yml

```yaml
logging:
  level:
    com.skcc.oversea: DEBUG
    org.springframework.web: DEBUG
    org.hibernate.SQL: DEBUG
    org.hibernate.type.descriptor.sql.BasicBinder: TRACE
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n"
```

#### 로그 레벨

- **DEBUG**: 개발 환경에서 상세 로그
- **INFO**: 일반적인 정보 로그
- **WARN**: 경고 메시지
- **ERROR**: 오류 메시지

### 🔍 모니터링

#### 헬스 체크

- 애플리케이션 상태 확인
- 데이터베이스 연결 상태
- 메모리 사용량

#### 메트릭

- 요청 처리 시간
- 오류율
- 동시 사용자 수

---

## 🚀 배포

### 📦 JAR 파일 생성

```bash
mvn clean package -DskipTests
```

### 🐳 Docker 배포 (선택사항)

```dockerfile
FROM openjdk:18-jre-slim
COPY target/oversea-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

### ☁️ 클라우드 배포

- **AWS**: Elastic Beanstalk, ECS
- **Azure**: App Service, Container Instances
- **GCP**: App Engine, Cloud Run

---

## 📚 참고 자료

### 🔗 공식 문서

- [Spring Boot Reference](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Spring Security Reference](https://docs.spring.io/spring-security/reference/)
- [Spring Data JPA Reference](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
- [Thymeleaf Documentation](https://www.thymeleaf.org/documentation.html)

### 📖 추가 학습 자료

- Spring Boot 마이그레이션 가이드
- J2EE에서 Spring Boot로의 전환
- 마이크로서비스 아키텍처 설계
- 보안 모범 사례

---

## 👥 개발팀

### 🏢 SKCC Development Team

- **이메일**: dev@skcc.com
- **웹사이트**: https://www.skcc.com
- **라이선스**: SKCC Internal License

### 📞 연락처

- **기술 지원**: tech-support@skcc.com
- **문서 관련**: docs@skcc.com
- **보안 관련**: security@skcc.com

---

## 📄 라이선스

이 프로젝트는 SKCC 내부 라이선스 하에 배포됩니다. 외부 배포나 상업적 사용은 금지됩니다.

---

_마지막 업데이트: 2024년 12월_
_버전: 1.0.0_
