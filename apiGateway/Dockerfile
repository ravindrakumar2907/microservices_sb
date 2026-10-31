#This is docker file for Tech Ravi API project
###############
# --- STAGE 1: Build ---
###############
FROM maven:3.9.11-eclipse-temurin-25-alpine AS build
WORKDIR /build

# Cache Maven dependencies layer (only rebuilds if pom.xml changes)
COPY pom.xml .
RUN mvn dependency:resolve -B -q

# Copy source and build
COPY src ./src
RUN echo "Building jar for apiGateway API"
RUN mvn package -DskipTests -q

# --- STAGE 2: Runtime ---
# Using JRE (Java Runtime Environment) for smaller attack surface
FROM eclipse-temurin:25-jre-alpine
RUN echo "Started Preparing Runtime for apiGateway API"
WORKDIR /app

RUN ls -altr
# Copy the JAR from the build stage
COPY --from=build /build/target/apiGateway.jar app.jar

# Create non-root user with specific UID/GID (for consistency across environments)
RUN addgroup -g 1001 -S techravi && \
    adduser -u 1001 -S raviuser -G techravi && \
    chown -R 1001:1001 /app

# Switch to the non-root user
USER 1001:1001

# Set timezone (optional - adjust to your region)
ENV TZ=UTC

# ============================================================
# OPTIMIZED JVM FLAGS FOR MEMORY & REQUEST HANDLING
# ============================================================
# Virtual Threads: ENABLED via spring.threads.virtual.enabled=true in application.properties
# SerialGC: Best for low-memory containers
# MaxRAMPercentage: 60% (safer than 65%, leaves room for overhead)
# Virtual Thread Scheduler: Tuned for concurrent requests without thread explosion
# String Dedup: Reduces memory footprint for duplicate strings (common in APIs)
# Compact Strings: Reduces String object size by ~50%
# ============================================================
ENV JAVA_OPTS="-XX:+UseSerialGC \
  -XX:MaxRAMPercentage=60.0 \
  -XX:+UnlockDiagnosticVMOptions \
  -XX:+UseStringDeduplication \
  -XX:+UseCompactObjectHeaders \
  -XX:+UseNUMA \
  -Djdk.virtualThreadScheduler.parallelism=16 \
  -Djdk.virtualThreadScheduler.maxPoolSize=256 \
  -Dspring.profiles.active=prod"

RUN ls -altr

EXPOSE 8080

# HEALTHCHECK: Verify container is responsive
HEALTHCHECK --interval=30s --timeout=3s --start-period=15s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8080/actuator/health || exit 1

# Use 'exec' form for ENTRYPOINT to allow graceful shutdowns (SIGTERM)
# Note: java command properly expands $JAVA_OPTS in exec form
ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS -jar app.jar"]



