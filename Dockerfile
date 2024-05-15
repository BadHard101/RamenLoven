# Этап 1: Сборка приложения
FROM maven:3.8.1-openjdk-11-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

#2222
FROM adoptopenjdk:11-jre-hotspot-bionic

WORKDIR /app

COPY target/Restaurant-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

# Установите переменные окружения для подключения к базе данных MySQL
ENV SPRING_DATASOURCE_URL=jdbc:mysql://viaduct.proxy.rlwy.net:29772/railway
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=cNbYuBcgDpaQITUfnhaYoMJVGpxSlNps

# Запустите приложение при запуске контейнера
CMD ["java", "-jar", "app.jar"]


## Этап 1: Сборка приложения
#FROM maven:3.8.1-openjdk-11-slim AS build
#WORKDIR /app
#COPY pom.xml .
#COPY src ./src
#RUN mvn clean package
#
## Этап 2: Запуск приложения
#FROM adoptopenjdk:11-jre-hotspot-bionic
#WORKDIR /app
#COPY --from=build /app/target/Restaurant-0.0.1-SNAPSHOT.jar app.jar
#EXPOSE 8080
#CMD ["java", "-jar", "app.jar"]