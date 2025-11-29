# ---- Build stage ----
FROM gradle:8.5.0-jdk17 AS build
WORKDIR /app

# Copy Gradle wrapper & build files first (for caching)
COPY gradlew .
COPY gradle ./gradle
COPY build.gradle .
COPY settings.gradle .

# Download dependencies (improves Docker caching)
RUN ./gradlew dependencies --no-daemon || true

# Copy source code
COPY src ./src

# Build the application
RUN ./gradlew clean bootJar --no-daemon

# ---- Run stage ----
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copy built JAR from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose Spring Boot port
EXPOSE 8080

# Run application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
