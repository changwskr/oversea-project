# SKAX AI TOOL System

## 📋 프로젝트 개요

SKAX AI TOOL은 레거시 J2EE/EJB 아키텍처에서 Spring Boot로 마이그레이션된 AI 도구 시스템입니다. 현금카드 관리, 예금 서비스, 공통 서비스 등 다양한 뱅킹 기능을 제공합니다.

## 🏗️ 아키텍처 개요

### 마이그레이션 전후 비교

| 구분             | 마이그레이션 전 | 마이그레이션 후           |
| ---------------- | --------------- | ------------------------- |
| **아키텍처**     | J2EE/EJB        | Spring Boot               |
| **Java 버전**    | Java 8          | Java 21                   |
| **빌드 도구**    | Ant/Maven       | Maven                     |
| **데이터베이스** | Oracle          | H2 (개발) / Oracle (운영) |
| **API 문서**     | 없음            | Swagger/OpenAPI 3.0       |
| **의존성 주입**  | EJB Container   | Spring IoC Container      |

### 기술 스택

- **Backend**: Spring Boot 3.1.4, Java 21
- **Database**: H2 (개발), Oracle (운영)
- **ORM**: JPA/Hibernate
- **API Documentation**: SpringDoc OpenAPI 3.0
- **Build Tool**: Maven
- **Template Engine**: Thymeleaf

## 📁 프로젝트 구조

```
oversea-project/
├── src/
│   ├── main/
│   │   ├── java/com/skcc/oversea/
│   │   │   ├── OverseaApplication.java          # 메인 애플리케이션
│   │   │   ├── config/
│   │   │   │   └── SwaggerConfig.java          # Swagger 설정
│   │   │   ├── controller/
│   │   │   │   ├── HealthController.java       # 헬스 체크 API
│   │   │   │   └── CashCardController.java     # 캐시카드 API
│   │   │   ├── cashCard/                       # 캐시카드 모듈
│   │   │   │   ├── business/
│   │   │   │   │   ├── cashCard/
│   │   │   │   │   │   ├── CashCardSBBean.java
│   │   │   │   │   │   └── model/
│   │   │   │   │   │       ├── CashCardDDTO.java
│   │   │   │   │   │       └── HotCardDDTO.java
│   │   │   │   │   ├── facade/
│   │   │   │   │   │   ├── CashCardManagementSBBean.java
│   │   │   │   │   │   └── dao/
│   │   │   │   │   │       └── CashCardDAOFactory.java
│   │   │   │   │   └── cashCardRule/
│   │   │   │   └── helper/
│   │   │   ├── entity/
│   │   │   │   ├── CashCard.java
│   │   │   │   └── HotCard.java
│   │   │   ├── repository/
│   │   │   │   ├── CashCardRepository.java
│   │   │   │   └── HotCardRepository.java
│   │   │   └── transfer/
│   │   │       ├── CashCardCDTO.java
│   │   │       ├── HotCardCDTO.java
│   │   │       └── AccountQueryCDTO.java
│   │   │   ├── deposit/                        # 예금 모듈
│   │   │   │   ├── business/
│   │   │   │   ├── entity/
│   │   │   │   │   └── Deposit.java
│   │   │   │   ├── repository/
│   │   │   │   │   └── DepositRepository.java
│   │   │   │   └── transfer/
│   │   │   │       └── DepositTransferDTO.java
│   │   │   ├── common/                         # 공통 모듈
│   │   │   │   ├── business/
│   │   │   │   ├── entity/
│   │   │   │   │   └── Common.java
│   │   │   │   ├── repository/
│   │   │   │   │   └── CommonRepository.java
│   │   │   │   └── transfer/
│   │   │   │       └── CommonTransferDTO.java
│   │   │   ├── teller/                         # 텔러 모듈
│   │   │   │   ├── business/
│   │   │   │   ├── entity/
│   │   │   │   │   └── Teller.java
│   │   │   │   ├── repository/
│   │   │   │   │   └── TellerRepository.java
│   │   │   │   └── transfer/
│   │   │   ├── user/                           # 사용자 모듈
│   │   │   │   └── entity/
│   │   │   │       └── User.java
│   │   │   ├── eplatonframework/               # E-Platon 프레임워크
│   │   │   │   ├── business/
│   │   │   │   │   ├── service/
│   │   │   │   │   │   ├── CommonServiceImpl.java
│   │   │   │   │   │   ├── SPcashcardServiceImpl.java
│   │   │   │   │   │   ├── SPdepositServiceImpl.java
│   │   │   │   │   │   ├── TellerServiceImpl.java
│   │   │   │   │   │   └── TransactionLogServiceImpl.java
│   │   │   │   │   ├── repository/
│   │   │   │   │   │   └── TransactionLogRepository.java
│   │   │   │   │   └── entity/
│   │   │   │   │       └── TransactionLog.java
│   │   │   │   ├── client/
│   │   │   │   │   ├── ECLTtest.java
│   │   │   │   │   └── EPLCLT.java
│   │   │   │   └── transfer/
│   │   │   │       ├── EPlatonEvent.java
│   │   │   │       ├── EPlatonCommonDTO.java
│   │   │   │       ├── EPLcommonCDTO.java
│   │   │   │       └── BatchJobProcessorResultDTO.java
│   │   │   ├── foundation/                     # 기반 모듈
│   │   │   │   ├── base/
│   │   │   │   ├── calendar/
│   │   │   │   ├── config/
│   │   │   │   ├── constant/
│   │   │   │   ├── convert/
│   │   │   │   ├── db/
│   │   │   │   ├── jndi/
│   │   │   │   ├── log/
│   │   │   │   ├── security/
│   │   │   │   ├── tpmservice/
│   │   │   │   └── utility/
│   │   │   └── framework/                      # 프레임워크 모듈
│   │   │       ├── business/
│   │   │       ├── constant/
│   │   │       ├── exception/
│   │   │       ├── transaction/
│   │   │       └── transfer/
│   │   └── resources/
│   │       ├── application.yml                 # 애플리케이션 설정
│   │       ├── static/
│   │       └── templates/
│   └── test/
│       └── java/
├── pom.xml                                      # Maven 설정
└── README.md                                    # 프로젝트 문서
```

## 🔧 주요 마이그레이션 작업

### 1. EJB → Spring Boot 변환

- **EJB Session Bean** → **Spring Service**
- **EJB Home Interface** → **Spring Repository**
- **JNDI Lookup** → **Spring Dependency Injection**

### 2. 의존성 업데이트

- **javax.annotation** → **jakarta.annotation**
- **javax.persistence** → **jakarta.persistence**
- **EJB API** → **Spring Boot Starter**

### 3. 데이터베이스 접근

- **EJB Entity Bean** → **JPA Entity**
- **EJB DAO** → **Spring Data JPA Repository**

### 4. API 문서화

- **기존**: API 문서 없음
- **현재**: Swagger/OpenAPI 3.0 자동 문서화

## 🚀 실행 방법

### 1. 사전 요구사항

- Java 21
- Maven 3.8+
- IDE (IntelliJ IDEA, Eclipse, VS Code)

### 2. 프로젝트 빌드

```bash
# 프로젝트 클론
git clone <repository-url>
cd oversea-project

# 의존성 다운로드 및 컴파일
mvn clean compile
```

### 3. 애플리케이션 실행

```bash
# Maven으로 실행
mvn spring-boot:run

# 또는 JAR 파일로 실행
mvn clean package
java -jar target/oversea-0.0.1-SNAPSHOT.jar
```

### 4. 접속 URL

- **애플리케이션**: http://localhost:8080
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **API 문서**: http://localhost:8080/api-docs
- **H2 콘솔**: http://localhost:8080/h2-console

## 📚 API 문서

### 헬스 체크 API

- `GET /api/health` - 애플리케이션 상태 확인
- `GET /api/health/info` - 애플리케이션 정보 조회

### 캐시카드 API

- `GET /api/cashcards` - 모든 캐시카드 조회
- `GET /api/cashcards/{cardId}` - 특정 캐시카드 조회
- `POST /api/cashcards` - 캐시카드 생성
- `PUT /api/cashcards/{cardId}` - 캐시카드 수정
- `DELETE /api/cashcards/{cardId}` - 캐시카드 삭제

## 🔍 주요 컴포넌트

### 1. 메인 애플리케이션 (`OverseaApplication.java`)

- Spring Boot 애플리케이션 진입점
- JPA 엔티티 및 Repository 스캔 설정
- 트랜잭션 관리 활성화

### 2. 서비스 레이어

- **CashCardManagementSBBean**: 캐시카드 관리 서비스
- **CommonServiceImpl**: 공통 서비스
- **TransactionLogServiceImpl**: 트랜잭션 로그 서비스

### 3. 데이터 액세스 레이어

- **JPA Repository**: Spring Data JPA 기반 데이터 접근
- **Entity**: JPA 엔티티 클래스
- **DTO**: 데이터 전송 객체

### 4. 프레임워크 레이어

- **E-Platon Framework**: 레거시 프레임워크 마이그레이션
- **Foundation**: 기반 유틸리티 클래스
- **Transaction**: 트랜잭션 관리

## 🛠️ 개발 가이드

### 1. 새로운 API 추가

```java
@RestController
@RequestMapping("/api/example")
@Tag(name = "Example", description = "Example API")
public class ExampleController {

    @GetMapping
    @Operation(summary = "Get Example", description = "Get example data")
    public ResponseEntity<Map<String, Object>> getExample() {
        // 구현
    }
}
```

### 2. 새로운 엔티티 추가

```java
@Entity
@Table(name = "example")
public class Example extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 필드 및 메서드
}
```

### 3. 새로운 Repository 추가

```java
@Repository
public interface ExampleRepository extends JpaRepository<Example, Long> {
    // 커스텀 쿼리 메서드
}
```

## 🔧 설정 파일

### application.yml

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
  sql:
    init:
      mode: always
      schema-locations: classpath:schema.sql
      data-locations: classpath:data.sql
      continue-on-error: true

server:
  port: 8080

springdoc:
  api-docs:
    path: /api-docs
  swagger-ui:
    path: /swagger-ui.html
```

## 🗄️ 테스트 데이터 구성

### 자동 데이터 초기화

애플리케이션 기동 시 자동으로 테이블과 테스트 데이터가 생성됩니다.

#### 1. **스키마 파일** (`schema.sql`)

- 모든 테이블 생성 스크립트
- 인덱스 생성
- 제약 조건 설정

#### 2. **테스트 데이터 파일** (`data.sql`)

- 공통 코드 데이터 (은행, 지점, 통화, 상태 등)
- 사용자 데이터 (4명)
- 텔러 데이터 (4명)
- 캐시카드 데이터 (5개)
- 핫카드 데이터 (3개)
- 예금 데이터 (7개)
- 트랜잭션 로그 데이터 (10개)

#### 3. **데이터 초기화 컴포넌트** (`DataInitializer.java`)

- 애플리케이션 시작 시 추가 테스트 데이터 생성
- 중복 데이터 방지 로직
- 프로그래밍 방식 테스트 데이터 생성

### 테스트 데이터 API

#### 테스트 데이터 요약

- `GET /api/test-data/summary` - 전체 테스트 데이터 요약

#### 각 모듈별 데이터 조회

- `GET /api/test-data/cash-cards` - 모든 캐시카드
- `GET /api/test-data/hot-cards` - 모든 핫카드
- `GET /api/test-data/deposits` - 모든 예금
- `GET /api/test-data/commons` - 모든 공통 코드
- `GET /api/test-data/tellers` - 모든 텔러
- `GET /api/test-data/users` - 모든 사용자
- `GET /api/test-data/transaction-logs` - 모든 트랜잭션 로그

#### 특정 데이터 조회

- `GET /api/test-data/cash-cards/{cardNumber}` - 특정 캐시카드
- `GET /api/test-data/deposits/{accountNumber}` - 특정 예금
- `GET /api/test-data/users/{userId}` - 특정 사용자
- `GET /api/test-data/commons/{commonCode}` - 특정 공통 코드

#### 추가 테스트 데이터 생성

- `POST /api/test-data/create-additional` - 추가 테스트 데이터 생성

### 샘플 테스트 데이터

#### 캐시카드 데이터

```json
{
  "cardNumber": "1234567890123456",
  "primaryAccountNo": "110123456789",
  "cifName": "김철수",
  "currency": "KRW",
  "dailyLimitAmount": 1000000.0,
  "status": "ACTIVE"
}
```

#### 예금 데이터

```json
{
  "accountNumber": "110123456789",
  "cifName": "김철수",
  "currency": "KRW",
  "balance": 5000000.0,
  "interestRate": 2.5,
  "status": "ACTIVE"
}
```

#### 사용자 데이터

```json
{
  "userId": "USER001",
  "userName": "김철수",
  "email": "kim.cs@skcc.com",
  "phone": "010-1234-5678",
  "status": "ACTIVE"
}
```

### H2 콘솔 접속

- **URL**: http://localhost:8080/h2-console
- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: (비어있음)

### 데이터베이스 스키마

- **cash_card**: 캐시카드 정보
- **hot_card**: 핫카드 정보
- **deposit**: 예금 정보
- **common**: 공통 코드
- **teller**: 텔러 정보
- **user**: 사용자 정보
- **transaction_log**: 트랜잭션 로그

## 🐛 문제 해결

### 1. 컴파일 에러

- **DTO clone() 메서드**: `super.clone()` → 수동 deep copy 구현
- **String → IDTO 변환**: `EPlatonCommonDTO` 객체 생성
- **Repository 메서드 누락**: 필요한 메서드 추가

### 2. 런타임 에러

- **데이터베이스 연결**: H2 인메모리 DB 사용
- **의존성 주입**: Spring IoC 컨테이너 활용

## 📝 개발 노트

### 마이그레이션 완료 항목

- ✅ EJB Session Bean → Spring Service
- ✅ EJB Entity Bean → JPA Entity
- ✅ JNDI Lookup → Spring DI
- ✅ javax → jakarta 패키지
- ✅ API 문서화 (Swagger)
- ✅ 메인 애플리케이션 클래스
- ✅ 기본 컨트롤러

### 향후 작업

- [ ] 실제 데이터베이스 연결 (Oracle)
- [ ] 보안 설정 (Spring Security)
- [ ] 로깅 설정
- [ ] 단위 테스트 작성
- [ ] 통합 테스트 작성
- [ ] 배포 설정

## 📞 문의

- **개발팀**: SKCC Development Team
- **이메일**: dev@skcc.com
- **버전**: 1.0.0

---

**© 2024 SKCC. All rights reserved.**
