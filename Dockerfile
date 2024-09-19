FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/librare-api-1.0.0.jar app.jar
COPY src/main/resources /app/resources

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
