# Multi-stage build for Kotlin/Ktor application
FROM gradle:8.5-jdk21 AS build

# Set working directory
WORKDIR /app

# Copy Gradle wrapper and configuration files
COPY gradlew gradlew.bat gradle.properties build.gradle.kts settings.gradle.kts ./
COPY gradle/ gradle/

# Copy source code
COPY src/ src/

# Build the application (skip tests for faster builds)
RUN ./gradlew build -x test --no-daemon

# Production stage with minimal JRE
FROM eclipse-temurin:21-jre-alpine

# Create non-root user for security
RUN addgroup -g 1001 -S appuser && \
    adduser -u 1001 -S appuser -G appuser

# Set working directory
WORKDIR /app

# Copy the built JAR from build stage
COPY --from=build /app/build/libs/*-all.jar app.jar

# Change ownership to non-root user
RUN chown appuser:appuser /app/app.jar

# Switch to non-root user
USER appuser

# Expose the port
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=30s --retries=3 \
    CMD wget --no-verbose --tries=1 --spider http://localhost:8080/ || exit 1

# Run the application
CMD ["java", "-jar", "app.jar"]
