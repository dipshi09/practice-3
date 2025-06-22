# Use an official OpenJDK image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy the built jar file (assumes you've already built it with Maven)
COPY target/springboot-login-app-0.0.1-SNAPSHOT.jar app.jar

# Expose port (same as in application.properties)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

