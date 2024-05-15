# Этап 1: Сборка приложения
FROM maven:3.8.1-openjdk-11-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Этап 2: Запуск приложения
FROM adoptopenjdk:11-jre-hotspot-bionic
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080

# Установите переменные окружения для подключения к базе данных MySQL
ENV SPRING_DATASOURCE_URL=jdbc:mysql://viaduct.proxy.rlwy.net:29772/railway
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=cNbYuBcgDpaQITUfnhaYoMJVGpxSlNps

CMD ["java", "-jar", "app.jar"]