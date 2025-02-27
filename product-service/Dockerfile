FROM openjdk:17-jdk-slim

WORKDIR /app

ARG JAR_FILE=*.jar

COPY target/${JAR_FILE} /app/product-service.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/app/product-service.jar"]
