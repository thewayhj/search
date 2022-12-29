# Search API
  * 외부 Open API를 활용하여 검색 API 개발 (미 완성)
  ## 요구사항
    * Java 8 이상 또는 Kotlin 언어로 구현
    * Spring Boot 사용
    * Gradle 또는 Maven 기반 프로젝트
    * 저장소가 필요할 경우 자유롭게 선택( 예: h2, in-memory 자료구조 등 )

# 구성
  * 버전
    * com.api.0.0.1-SNAPSHOT
  * 개발 환경
    * mac OS Big Sur 11.6
    * IntelliJ IDEA 2021.3.2 (Ultimate Edition)
    * java version : java 17
    * h2database : 1.4.199
    * gradle
  * 라이브러리
    * spring boot 3.0.1
    * org.springframework.boot:spring-boot-starter-data-jpa
        * jpa 사용하기 위해 선언
    * lombok
    * commons-lang3 3.0.1



## Getting Started
 ## valut 설치 (Open API Secret key 등 중요한 보안 소스 vault 연동하여 보관)
  * brew를 사용하여 vault 설치
    ``` 
      brew install vault 
    ```
  * vault server 실행
    ```
    vault server --dev --dev-root-token-id="00000000-0000-0000-0000-000000000000"
    ```
      * 참고 Document : https://spring.io/guides/gs/vault-config/
  * 설치된 jdk 없을 시 아래 방법에서 설치
    * https://adoptopenjdk.net/index.html 에서 다운로드 후 설치 (OpenJDK 17)
    * yum list java*jdk-devel 에서 설치 가능한 java 확인 후 설치

 ## curl 명령어
  * cURL 로 테스트하는 방법
  * cURL 요청 시 Get 방식 url에 한글이나 띄어쓰기가 있는 경우 아래와 같이 encode 처리하여 요청하여야 한다.
  ```
    curl -G --data-urlencode "query=곱창" localhost:8080/search/keyword
  ```

