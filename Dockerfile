#Build Stage
FROM maven:3.9.2-amazoncorretto-20 AS build
COPY pom.xml /app/
COPY src /app/src
RUN mvn -f /app/pom.xml clean package

FROM openjdk:20
COPY --from=build /app/target/spring-boot-web-0.0.1-SNAPSHOT.jar spring-boot-web-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java", "-jar", "spring-boot-web-0.0.1-SNAPSHOT.jar"]