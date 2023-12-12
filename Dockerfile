# OhouseDocker Dockerfile

# Use the official OpenJDK 17 base image
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

COPY target/OhouseV1.jar OhouseV1.jar
EXPOSE 8080
# 빌드된 이미지가 run될 때 실행할 명령어
CMD ["java","-jar","OhouseV1.jar"]
