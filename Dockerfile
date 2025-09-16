# Stage 1: Build the Spring Boot application
FROM eclipse-temurin:17-jdk-focal as build
WORKDIR /app
COPY . .
# Add this line to make the Maven Wrapper script executable
RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests

# Stage 2: Create the final lightweight image
FROM eclipse-temurin:17-jre-focal
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]