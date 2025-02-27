FROM openjdk:17-jdk-slim

WORKDIR /app

ARG JAR_FILE=*.jar

COPY target/${JAR_FILE} /app/discovery-service.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "/app/discovery-service.jar"]
