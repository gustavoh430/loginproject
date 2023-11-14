FROM openjdk:17-alpine
EXPOSE 8080
COPY /app/target/*.jar app.jar
ENTRYPOINT [ "java", "-jar", "/app.jar" ]
