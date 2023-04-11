# jar built manually in target dir
FROM eclipse-temurin:11
RUN apt-get update && apt-get install -y telnet && rm -rf /var/lib/apt/lists/*
RUN mkdir /opt/app
COPY ./target/demo-verify-token-0.0.1-SNAPSHOT.jar /opt/app/app.jar
EXPOSE 9991
CMD ["java", "-jar", "/opt/app/app.jar"]
