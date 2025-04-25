FROM amazoncorretto:21
ARG JAR_FILE=library/target/library-0.0.1-SNAPSHOT.jar
WORKDIR /app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]