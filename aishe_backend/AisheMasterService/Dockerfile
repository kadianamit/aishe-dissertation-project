# Build stage
FROM maven:3.8.6-openjdk-8 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Runtime stage
FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=build /app/target/aisheMasterService.jar app.jar
EXPOSE 9770
ENTRYPOINT ["java", "-jar", "app.jar"]
