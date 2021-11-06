FROM openjdk:11-jdk-slim
VOLUME /tpm
ADD target/app-meli-test-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar", "/app.jar"]