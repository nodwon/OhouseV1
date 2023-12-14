# OhouseDocker Dockerfile

# Use the official OpenJDK 17 base image
FROM eclipse-temurin:17-jdk-alpine as builder
WORKDIR /build
COPY build.gradle settings.gradle /build/
RUN gradle build -x test --parallel --continue > /dev/null 2>&1 || true

 # 빌더 이미지에서 애플리 케이션 빌드
COPY . /build
RUN gradle build -x test --parallel

#APP
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# 빌더 이미지에서 jar 파일만 복사
COPY --from=builder /build/build/libs/*-SNAPSHOT.jar ./app.jar
CMD ["java","-jar","app.jar"]
