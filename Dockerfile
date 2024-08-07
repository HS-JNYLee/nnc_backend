FROM openjdk:17-jdk-slim

# 작업 디렉토리 설정
WORKDIR /app

# jar 파일 경로
ARG JAR_FILE=build/libs/*.jar

# JAR 파일을 컨테이너의 /app 디렉토리에 복사
COPY ${JAR_FILE} app.jar

# 컨테이너가 사용하는 포트 노출
EXPOSE 8080

# 컨테이너 시작 시 실행할 명령어
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]

# 타임존 설정
ENV TZ=Asia/Seoul
