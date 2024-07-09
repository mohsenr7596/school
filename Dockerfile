FROM openjdk:21-jdk-alpine
VOLUME /tmp
COPY target/app.jar school.jar
ENTRYPOINT ["java", "-jar", "/school.jar"]
