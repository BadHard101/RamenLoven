FROM adoptopenjdk:11-jre-hotspot-bionic

WORKDIR /app

COPY target/rschir_buysell-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

# Установите переменные окружения для подключения к базе данных MySQL
#ENV SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/rschir_pr5
#ENV SPRING_DATASOURCE_USERNAME=root
#ENV SPRING_DATASOURCE_PASSWORD=badhardsql!

# Запустите приложение при запуске контейнера
CMD ["java", "-jar", "app.jar"]
