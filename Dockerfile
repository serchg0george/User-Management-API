FROM openjdk:17

WORKDIR /app

COPY target/user-management-api-1.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
