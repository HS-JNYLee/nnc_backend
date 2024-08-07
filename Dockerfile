FROM openjdk:17-jdk-slim

# 작업 디렉토리 설정
WORKDIR /app

# JAR 파일을 컨테이너의 /app 디렉토리로 복사
COPY build/libs/nnc_backend-0.0.1-SNAPSHOT.jar app.jar

# 컨테이너가 사용하는 포트 노출
EXPOSE 8080

# 컨테이너 시작 시 실행할 명령어 설정
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]

# 타임존 설정 (선택 사항)
ENV TZ=Asia/Seoul
