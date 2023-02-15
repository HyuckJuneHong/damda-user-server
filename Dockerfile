#open jdk java11 버전의 환경 구성
FROM adoptopenjdk/openjdk11

#가상의 디렉토 생성
VOLUME /tmp

#damda-user.jar에 복사
#주의할 점 : Dockerfile과 복사할 파일은 동일 위치에 있어야 한다.
COPY build/libs/damda-user-0.0.1-SNAPSHOT.jar damda-user.jar

#jar 파일을 실행하는 명령어(java -jar jar파일)
ENTRYPOINT ["java", "-jar", "damda-user.jar"]

