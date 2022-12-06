# ✍ 1차 과제
<p>
    URL,tag타입, 묶음 데이터를 입려받으면 해당 url파싱 후 데이터 가공 출력
    <br />
    <a href="http://wmphtml.bloodgang.shop"><strong>HTML 파싱 사이트이동</strong></a>
    <br />
  </p> 

## 개발 프레임워크 및 라이브러리

* Java 11
* Spring Boot 2.7.6
* Gradle
* Lombok
* Jsoup
* Thymeleaf
* Jasypt
* Jpa
* Mariadb

## 제약 조건
1. 모든 문자 입력
    - 세상에 존재하는 모든 형태의 URL을 입력받는다.
    - 유효하지 않은 url 이라면 400에러 반환
2. 영어, 숫자 출력
    - 결과 데이터는 영어와 숫자로만 구성된 데이터만 출력한다
      > 노출 유형이 HTML 일 경우는 `<>` 형식의 HTML TAG 를 제거하고 출력한다  
      노출 유형이 TEXT 일 경우에는 모든 영어와 숫자로만 구성된 데이터를 출력한다
3. 오름차순
    - 영어 : AaBbCcDd `...`
    - 숫자 : 0123456789
5. 교차 출력
    - 영어 숫자 영어 숫자 `...`
      > 더 이상 출력될 숫자 또는 영어가 없을 경우 남아있는 정렬된 문자열 그대로 출력
6. 출력 묶음 단위
    - 사용자가 입력한 자연수의 묶음 단위를 기준으로 몫과 나머지를 구하여 노출
    -
# 구현과정
## 1. 프론트 구현
1. 입력한 값 체크 번들 숫자는 1이상, 유효한 url 검증
2. 실패시에 toastr 알림을 사용
   <p align="center"><img width="70%" src="https://user-images.githubusercontent.com/72914519/205791193-52c4db57-c92d-4d89-8002-83dc7b400e85.png"></p>

## 2. 서버 구현

### validation을 활용하여 DTO 검증 작업

### 패키지 구조
#### 이번 프로젝트에서 고수준 모듈이 저수준 모듈에 의존 관계를 역전 시키기 위해 인터페이스 활용(DIP)
-  도메인 주도 설계에서 말하는 일반적인 엔터프라이즈 애플리케이션 레이어 구성을 참고
- 레이어간의 참조 관계에서는 단방향 의존을 유지하고 계층간 호출에서는 인터페이스를 통한
  호출이 되도록 한다
- 사용자 인터페이스(interfaces) : 사용자에게 정보를 보여주고 사용자의 명령을 해석하는 책임을 진다.
- 응용 계층(application) : 작업을 정의하고 표현력 있는 도메인 객체가 문제를 해결하게 한다.
- 도메인 계층(domain) : 업무 개념과 업무 상황에 대한 정보, 업무 규칙을 표현하는 일을 책임진다.
- 인프라 스트럭쳐 계층(infrastructure) :상위 계층을 지원하는 일반화된 기술적 기능을 제공한다.


```yaml
├─main
│  ├─java
│  │  └─com
│  │      └─example
│  │          └─htmlparsing
│  │              ├─application
│  │              ├─common
│  │              │  └─exception
│  │              ├─config
│  │              ├─domain
│  │              │  └─parsing
│  │              ├─infrastructure
│  │              ├─interfaces
│  │              │  └─parsing
│  │              └─util
│  └─resources
│      ├─static
│      │  └─resource
│      │      └─common
│      └─templates
└─test
└─java
└─com
└─example
└─htmlparsing
├─controller
├─domain
│  └─parsing
├─infrastructure
├─repository
└─service
```
### jasypt을 활용한 설정값 암호화 작업

### AOP를 사용하여 로그 처리, 메서드 소요 시간 측정


## 3. 테스트 코드 작성
- 단위 테스트, Controller 테스트, Service 테스트, Repository 테스트 코드 작성

## 4. GitHubActions 이용하여 CI/CD 구축 및 배포
- Code Push -> 테스트 -> 빌드 -> 이미지 빌드 -> 도커 허브 push -> ssh 접속 -> 도커 허브 pull -> docker run

---
## 구현중 어려운 작업
에러 내용

```jsx
Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'jasyptConfig': Injection of autowired dependencies failed; nested exception is java.lang.IllegalArgumentException: Could not resolve placeholder 'jasypt.encryptor.password' in value "${jasypt.encryptor.password}"
```

원인 : 현재 프로젝트에서 데이터 베이스 소스 정보를 암호화 하기 위해서 jasypt 라이브러리를 사용 하고 빌드시에 VM옵션 에서 -Djasypt.encryptor.password={비밀번호 값} 을 주입하고 빌드가 된다.

하지만 테스트 코드 작성시에 -Djasypt.encryptor.password이 값을 주입을 못하는 상태이다.

해결하기 위해서 build.gradle 파일에서

```jsx
tasks.named('test') {
    useJUnitPlatform()
    (추가) systemProperty "jasypt.encryptor.password", project.getProperties().get("jasypt.encryptor.password")
}
```

위와같은 명령을 추가 하였다.

그러나 다른 오류를 또 만났다.

```jsx
Caused by: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'dataSourceScriptDatabaseInitializer' defined in class path resource [org/springframework/boot/autoconfigure/sql/init/DataSourceInitializationConfiguration.class]
```

자꾸 데이터베이스의 비밀번호 값이 없다고 에러가 나서 Test환경에서는 h2 인메모리 디비로 데이터 베이스를 설정을 하기로 결정을 했다.

application-test.yml

```jsx
spring:
  datasource:
    url: jdbc:h2:mem:test
    username: sa
    password: 123
    driver-class-name: org.h2.Driver

  jpa:
    hibernate:
      ddl-auto: create
    properties:
      hibernate:
        format_sql: true
logging:
  level:
    org.hibernate.SQL: debug
```

위와 같이 파일을 작성하고 테스트 코드에서

```jsx
@SpringBootTest()
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class MainControllerTests {
```

ActiveProfiles 어노테이션을 통해 test 설정값을 적용시켰다.

테스트와 실제 코드 랑 데이터베이스를 분리 시켜주는게 좋다고한다.

그결과

![Untitled](https://user-images.githubusercontent.com/72914519/205794306-43f11f0b-37d7-4905-8d41-278d1c7c690a.png)

컨트롤러 테스트가 성공 했다!!
