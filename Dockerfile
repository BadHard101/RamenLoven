
# Этап 1: Сборка приложения
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests
# Этап 2: Запуск приложения
FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]