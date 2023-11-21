# OhouseDocker Dockerfile

# Use the official OpenJDK 17 base image
FROM azul/zulu-openjdk:17

# Set the working directory in the container
WORKDIR /app

# Argument for JAR file
ARG JAR_FILE=build/libs/*.jar

# Copy the application JAR file into the container at /app
COPY ${JAR_FILE} app.jar

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
