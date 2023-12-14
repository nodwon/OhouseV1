# OhouseDocker Dockerfile

# Use the official OpenJDK 17 base image
FROM eclipse-temurin:17-jdk-alpine

COPY /build/libs/*-SNAPSHOT.jar app.jar
# 빌드된 이미지가 run될 때 실행할 명령어
ENTRYPOINT ["java","-jar","/app.jar"]
