FROM eclipse-temurin:21-jdk-alpine-3.23
LABEL authors="dong"

WORKDIR /app
COPY build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]