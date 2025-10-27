# --- STAGE 1: Build the JAR file ---
# Use an official Maven/Gradle image with JDK 21
FROM eclipse-temurin:21-jdk-alpine AS builder

# Set the working directory
WORKDIR /app

# Copy all resources from Backend directory
COPY /Backend/. .

# Build the application
RUN ./mvnw package -DskipTests
# For Gradle:
# RUN ./gradlew build -x test

# Extract the JAR file. The target folder for Maven is 'target', for Gradle is 'build/libs'
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)


# --- STAGE 2: Create the minimal runtime image ---
# Use a minimal JDK 21 JRE image (no development tools needed)
FROM eclipse-temurin:21-jre-alpine

# Set arguments for running the application
ARG DEPENDENCY=/app/target/dependency

# Copy dependencies and the application JAR from the builder stage
COPY --from=builder ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=builder ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=builder ${DEPENDENCY}/BOOT-INF/classes /app/classes
COPY --from=builder /app/target/*.jar /app/app.jar 
# Copy the executable JAR

# Define the entry point to run the application
# Use java -jar for the executable JAR or specific class path for layered approach
#ENTRYPOINT ["java", "-cp", "app.jar", "org.springframework.boot.loader.JarLauncher"]
#ENTRYPOINT ["java","-jar","/app/app.jar"]
ENTRYPOINT ["java","-jar","app/target/car-management-system-0.0.1-SNAPSHOT.jar"]

# Expose the default Spring Boot port
EXPOSE 8080