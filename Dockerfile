# OhouseDocker Dockerfile

# Use the official OpenJDK 17 base image
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY build/libs/OhouseV1-0.0.1-SNAPSHOT.jar OhouseV1-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java","-jar","OhouseV1-0.0.1-SNAPSHOT.jar"]
