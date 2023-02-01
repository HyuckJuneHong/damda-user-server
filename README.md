# damda-user-server
> MSA 기반의 사이드 프로젝트입니다.
> User Server 입니다.

- 프로젝트 기간 : 2023.02.01 - continue

# 프로젝트 기록
- 23/02/01
  1. User Server 생성
  2. Eureka Server에 Eureka Server 등록
  3. 랜덤 포트 부여, Load Balancer 적용
    ```
    server:
      port: 0
  
    eureka:
      instance:
        instance-id: ${spring.cloud.client.hostname}:${spring.application.instance_id:${random.value}}
  ```
  4. 

