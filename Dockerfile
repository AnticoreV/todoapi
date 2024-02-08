FROM maven:3.9.6-sapmachine-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean install package spring-boot:repackage -DskipTests
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/todoapi-0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
CMD ["java", "-jar", "todoapi-0.0.1-SNAPSHOT.jar"]
