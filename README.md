# Demo Verify Token
A simple implementation for API Verify Token for Backend Application, using Spring Boot 2.7.

- Java 11
- Spring Boot 2.7, JJWT, Commons Codec, BouncyCastle

## How To

Pre-requisite: JDK 11 and Maven are installed

```sh
mvn clean package
java -jar target/demo-verify-token-0.0.1-SNAPSHOT.jar
```
Demo Verify Token runs at port 9991

To test:

```sh
curl -X POST http://localhost:9991/api/devices/verify -H 'Content-Type: application/json' -d '{"device_id":"7cbd0a85c1b95740"}'
```
