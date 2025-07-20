# 공통 라이브러리

## 사용법

- common-java 레포지토리의 모듈들은 다음 플러그인에 의존합니다.
  - `org.springframework.boot:3.4.3`
  - `io.spring.dependency-management:1.1.7`
- 모듈을 라이브러리처럼 사용하려는 경우 플러그인을 추가해야합니다.
  ```gradle
  plugins {
      ...
      id 'org.springframework.boot' version '3.4.3'
      id 'io.spring.dependency-management' version '1.1.7'
      ...
  }
  
  ...
  ```

## Contents

- [domain](./domain)

