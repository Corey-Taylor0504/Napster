FROM maven:3.8.4-openjdk-17 as build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-alpine
COPY --from=build /app/target/*.jar app.jar
COPY src/main/resources/wait-for-it.sh wait-for-it.sh
RUN chmod +x /wait-for-it.sh
ENTRYPOINT ["java", "-jar", "/app.jar"]
