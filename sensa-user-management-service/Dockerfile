FROM eclipse-temurin:21-jdk
COPY build/libs/*.jar /app.jar

EXPOSE 8000
ENTRYPOINT ["java", "-jar", "/app.jar"]
