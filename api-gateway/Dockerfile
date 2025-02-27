FROM openjdk:17-jdk-slim

WORKDIR /app

ARG JAR_FILE=*.jar

COPY target/${JAR_FILE} /app/api-gateway.jar

EXPOSE 9191

ENTRYPOINT ["java", "-jar", "/app/api-gateway.jar"]
