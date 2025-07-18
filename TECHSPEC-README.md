# 기술 스펙 CRUD 애플리케이션

Spring Boot 기반의 기술 스펙 관리 시스템입니다. 프로젝트에서 사용되는 기술 스택을 체계적으로 관리할 수 있습니다.

## 🚀 주요 기능

### 1. 기술 스펙 관리

- **생성**: 새로운 기술 스펙 추가
- **조회**: 전체 목록, 카테고리별, 검색 기능
- **수정**: 기존 기술 스펙 정보 업데이트
- **삭제**: 기술 스펙 논리적 삭제 (Soft Delete)

### 2. 검색 및 필터링

- 분류 영역별 필터링
- 기술명 검색
- 설명 내용 검색
- 복합 검색 지원

### 3. 데이터 관리

- 카테고리별 통계
- 일괄 생성 기능
- 중복 체크 기능

## 🏗️ 아키텍처

### Hexagonal Architecture (Ports & Adapters)

```
┌─────────────────────────────────────────────────────────────┐
│                    Presentation Layer                       │
├─────────────────────────────────────────────────────────────┤
│  Web Controller (Thymeleaf)  │  REST Controller (API)      │
├─────────────────────────────────────────────────────────────┤
│                    Application Layer                        │
├─────────────────────────────────────────────────────────────┤
│  Use Case (Service)  │  Port (Interface)                   │
├─────────────────────────────────────────────────────────────┤
│                    Domain Layer                             │
├─────────────────────────────────────────────────────────────┤
│  Domain Entity  │  Business Logic                          │
├─────────────────────────────────────────────────────────────┤
│                  Infrastructure Layer                       │
├─────────────────────────────────────────────────────────────┤
│  JPA Entity  │  Repository  │  Adapter                     │
└─────────────────────────────────────────────────────────────┘
```

### 패키지 구조

```
src/main/java/com/skcc/oversea/techspec/
├── domain/                          # 도메인 계층
│   └── TechSpec.java               # 도메인 엔티티
├── application/                     # 애플리케이션 계층
│   ├── port/
│   │   ├── in/                     # 인바운드 포트
│   │   │   └── TechSpecUseCase.java
│   │   └── out/                    # 아웃바운드 포트
│   │       └── TechSpecPort.java
│   └── service/                    # 서비스 구현
│       └── TechSpecService.java
├── infrastructure/                 # 인프라스트럭처 계층
│   ├── jpa/                       # JPA 관련
│   │   ├── TechSpecEntity.java
│   │   └── TechSpecRepository.java
│   └── adapter/                   # 어댑터
│       └── TechSpecPersistenceAdapter.java
└── controller/                    # 프레젠테이션 계층
    ├── request/                   # 요청 DTO
    │   ├── TechSpecCreateRequest.java
    │   └── TechSpecUpdateRequest.java
    ├── response/                  # 응답 DTO
    │   └── TechSpecResponse.java
    ├── TechSpecRestController.java # REST API
    └── TechSpecWebController.java  # 웹 컨트롤러
```

## 🛠️ 기술 스택

### Backend

- **Spring Boot 3.1.4**: 메인 프레임워크
- **Java 18**: 프로그래밍 언어
- **Spring Data JPA**: 데이터 접근 계층
- **H2 Database**: 개발용 인메모리 데이터베이스
- **Thymeleaf**: 서버사이드 템플릿 엔진
- **Bootstrap 5**: UI 프레임워크
- **Lombok**: 코드 자동화

### Architecture

- **Hexagonal Architecture**: 포트와 어댑터 패턴
- **Clean Architecture**: 계층 분리
- **DDD**: 도메인 주도 설계

## 📋 데이터 모델

### TechSpec 엔티티

```java
public class TechSpec {
    private Long id;                    // 고유 식별자
    private String category;            // 분류 영역
    private String subItem;             // 세부 항목
    private String technologyName;      // 기술 명칭
    private String version;             // 버전
    private String description;         // 설명
    private LocalDateTime createdDate;  // 생성일
    private LocalDateTime updatedDate;  // 수정일
    private String createdBy;           // 생성자
    private String updatedBy;           // 수정자
    private boolean isActive;           // 활성 상태
}
```

## 🚀 실행 방법

### 1. 애플리케이션 실행

```bash
# 프로젝트 루트 디렉토리에서
./mvnw spring-boot:run
```

### 2. 접속 URL

- **웹 인터페이스**: http://localhost:8080/tech-specs
- **REST API**: http://localhost:8080/api/tech-specs
- **H2 콘솔**: http://localhost:8080/h2-console

## 📖 API 문서

### REST API 엔드포인트

#### 1. 기술 스펙 생성

```http
POST /api/tech-specs
Content-Type: application/json

{
  "category": "아키텍처",
  "subItem": "아키텍처 패턴",
  "technologyName": "Spring Boot MSA",
  "version": "3.1.4",
  "description": "모듈화된 마이크로서비스 아키텍처 적용",
  "createdBy": "admin"
}
```

#### 2. 기술 스펙 조회

```http
GET /api/tech-specs/{id}
```

#### 3. 전체 기술 스펙 조회

```http
GET /api/tech-specs
```

#### 4. 카테고리별 조회

```http
GET /api/tech-specs/category/{category}
```

#### 5. 검색

```http
GET /api/tech-specs/search?category=아키텍처&technologyName=Spring&description=마이크로서비스
```

#### 6. 기술 스펙 수정

```http
PUT /api/tech-specs/{id}
Content-Type: application/json

{
  "category": "아키텍처",
  "subItem": "아키텍처 패턴",
  "technologyName": "Spring Boot MSA",
  "version": "3.2.0",
  "description": "업데이트된 설명",
  "updatedBy": "admin"
}
```

#### 7. 기술 스펙 삭제

```http
DELETE /api/tech-specs/{id}
```

#### 8. 카테고리 목록 조회

```http
GET /api/tech-specs/categories
```

#### 9. 카테고리별 개수 조회

```http
GET /api/tech-specs/categories/count
```

#### 10. 일괄 생성

```http
POST /api/tech-specs/batch
Content-Type: application/json

[
  {
    "category": "개발언어",
    "subItem": "프로그래밍 언어",
    "technologyName": "Java",
    "version": "18",
    "description": "프로젝트 표준 언어",
    "createdBy": "admin"
  }
]
```

## 🎨 웹 인터페이스

### 주요 페이지

#### 1. 목록 페이지 (`/tech-specs`)

- 전체 기술 스펙 목록 표시
- 검색 및 필터링 기능
- 카테고리별 필터링
- CRUD 작업 버튼

#### 2. 생성 페이지 (`/tech-specs/create`)

- 새로운 기술 스펙 생성 폼
- 유효성 검사
- 카테고리 선택 드롭다운

#### 3. 상세 페이지 (`/tech-specs/{id}`)

- 기술 스펙 상세 정보 표시
- 수정 및 삭제 버튼
- 생성/수정 이력 정보

#### 4. 수정 페이지 (`/tech-specs/{id}/edit`)

- 기존 기술 스펙 수정 폼
- 기존 데이터 자동 입력
- 유효성 검사

## 🔧 설정

### application.yml

```yaml
spring:
  datasource:
    url: jdbc:h2:mem:testdb
    driver-class-name: org.h2.Driver
    username: sa
    password:

  h2:
    console:
      enabled: true
      path: /h2-console

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
      data-locations: classpath:data.sql
```

## 📊 초기 데이터

애플리케이션 시작 시 다음 카테고리의 기술 스펙이 자동으로 생성됩니다:

1. **아키텍처**: Spring Boot MSA, Spring MVC, Spring Transaction 등
2. **개발언어**: Java, Jakarta Annotation, JAXB Runtime 등
3. **데이터계층**: Oracle, H2, JPA/Hibernate, MyBatis 등
4. **빌드패키징**: Maven, spring-boot-dependencies 등
5. **보안**: Spring Security, JWT/OAuth2, JCE/BCrypt 등
6. **API 문서화**: SpringDoc OpenAPI, Swagger UI 등
7. **프론트 연계**: Thymeleaf, WebClient/RestTemplate 등
8. **공통개발**: Lombok, Apache Commons/Guava, Jackson 등
9. **테스트**: JUnit 5, Mockito, Postman 등
10. **운영지원**: SLF4J+Logback, Spring Boot Actuator 등
11. **devops**: Docker, Jenkins/GitHub Actions, Git 등
12. **보조도구**: Swagger, Git Flow 등

## 🧪 테스트

### 단위 테스트 실행

```bash
./mvnw test
```

### 통합 테스트 실행

```bash
./mvnw verify
```

## 📝 개발 가이드

### 새로운 기능 추가

1. **도메인 계층**: 비즈니스 로직 정의
2. **애플리케이션 계층**: 유스케이스 및 포트 정의
3. **인프라스트럭처 계층**: 외부 시스템 연동 구현
4. **프레젠테이션 계층**: 컨트롤러 및 DTO 구현

### 코드 컨벤션

- **패키지명**: 소문자, 점(.) 구분
- **클래스명**: PascalCase
- **메서드명**: camelCase
- **상수명**: UPPER_SNAKE_CASE
- **변수명**: camelCase

## 🔒 보안

- 입력값 유효성 검사 (Bean Validation)
- SQL 인젝션 방지 (JPA 사용)
- XSS 방지 (Thymeleaf 자동 이스케이프)
- CSRF 보호 (Spring Security)

## 📈 성능 최적화

- JPA 쿼리 최적화
- 페이징 처리 지원
- 인덱스 활용
- 캐싱 전략 적용 가능

## 🚀 배포

### Docker 배포

```bash
# Docker 이미지 빌드
docker build -t techspec-app .

# 컨테이너 실행
docker run -p 8080:8080 techspec-app
```

### JAR 배포

```bash
# JAR 파일 빌드
./mvnw clean package

# 애플리케이션 실행
java -jar target/oversea-0.0.1-SNAPSHOT.jar
```

## 📞 지원

문제가 발생하거나 개선 사항이 있으시면 이슈를 등록해 주세요.

---

**SKAX AI TOOL Project** - 기술 스펙 관리 시스템
