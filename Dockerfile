# OhouseDocker Dockerfile

# Use the official OpenJDK 17 base image
FROM eclipse-temurin:17-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
COPY build/libs/OhouseV1-0.0.1-SNAPSHOT.jar app.jar
CMD ["java","-jar","/app.jar"]
