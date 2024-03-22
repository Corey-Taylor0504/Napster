# Use the official Maven image to create a build stage
FROM maven:3.6.3-jdk-17 as build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Build your application
RUN mvn clean package

# Use OpenJDK for running the application
FROM openjdk:17-alpine
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
