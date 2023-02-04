# damda-user-server
> MSA 기반의 사이드 프로젝트입니다.
> User Server 입니다.

- 프로젝트 기간 : 2023.02.01 - continue

# 프로젝트 기록
<ol>
      <h3>23/02/01</h3>
      <li>User Server 생성</li>
      <li>Eureka Server에 등록</li>
      <li>자동으로 랜덤 포트 부여</li>
    
          server:
            port: 0
          eureka:
            instance:
              instance-id: ${spring.cloud.client.hostname}:${spring.application.instance_id:${random.value}}
    
<li>Load Balancer 적용</li>
    
          routes:
            - id: damda-user
              uri: lb://DAMDA-USER

<li>Status API 구현</li>
      <li>Spring Security을 활용해 Password 정보 인코딩 후 디비 저장</li>
      <li>Login 성공 시 Json Web Token 발급 추가</li>
    <li>core module 추가해서, 코드 공통화</li>
</ol>
