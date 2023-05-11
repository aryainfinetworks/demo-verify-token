# Build stage
FROM maven:3.6.0-jdk-11-slim AS build

COPY pom.xml /app/
COPY src /app/src
RUN mvn -f /app/pom.xml clean package

# Base image
FROM eclipse-temurin:11
RUN apt-get update && apt-get install -y telnet && rm -rf /var/lib/apt/lists/*
RUN mkdir /opt/app
COPY --from=build /app/target/demo-verify-token-0.0.1-SNAPSHOT.jar /opt/app/app.jar
ENV pkfile=classpath:private.txt
EXPOSE 9991
CMD ["java", "-jar", "/opt/app/app.jar"]
