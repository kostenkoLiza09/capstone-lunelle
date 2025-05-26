FROM openjdk:21
EXPOSE 8080
COPY backend/target/app.jar lunelle.jar
ENTRYPOINT ["java", "-jar", "lunelle.jar"]
