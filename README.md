# 콘서트 예매 시스템

### 프로젝트 소개
본 레포지토리는 콘서트 예매 시스템을 구현한 개인 학습용 프로젝트입니다.
레이어드 아키텍처와 클린 아키텍처의 장점을 결합하여, 유지보수성과 확장성을 높이는 방법을 학습하고 적용해보는 데 목적이 있습니다.

### 기술 스택
Language: Java 21   
Framework: Spring Boot, Spring Boot data JPA, Spring Boot MVC    
Documentation: Spring Rest Docs   
Build/Dependency: Gradle   
Test: JUnit5   
Database: MySQL, H2   
Caching/Queue: Redis   

### 프로젝트 구조
```
concert
├── interfaces        # Controller(REST API) / DTO 등 외부와의 인터페이스 계층
├── application       # UseCase / Service 등 애플리케이션 로직
├── domain            # 엔티티, 도메인 서비스, 도메인 모델
├── infrastructure    # DB, Redis, 메시지 큐 등 외부 인프라스트럭처 연동
├── test              
└── build.gradle or pom.xml
```

- Interfaces Layer 
  - REST Controller, DTO(요청/응답 모델) 등 외부와 소통하는 계층
  비즈니스 로직은 최대한 Application/Domain 계층으로 위임

- Application Layer 
  - UseCase 및 Service에 해당
  도메인 로직을 조합하여 실제 애플리케이션 흐름을 구현

- Domain Layer
  - 엔티티, 도메인 이벤트, 도메인 서비스 등 핵심 비즈니스 로직
  외부 의존성 없이 비즈니스 규칙을 모델링
  
- Infrastructure Layer
  - DB, Redis, 메시지 큐 등 외부 시스템 연동 로직
  Repository 구현체, Client, Configuration 등 배치

### 실행 방법

빌드   
`./gradlew clean build`

테스트   
`./gradlew test`

API 문서 생성   
`./gradlw copyDocument`
