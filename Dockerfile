FROM eclipse-temurin:11-jdk-jammy
WORKDIR /app
COPY target/packers-movers-1.0.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
