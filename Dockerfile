# Use a Maven image for building the application
FROM maven:3.4.2-openjdk-23 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Use a lightweight image for running the application
FROM amazoncorretto:23
WORKDIR /app
COPY target/budgettracker.jar /app
#Tomcat runs on 8090
EXPOSE 8090
#port number of application
ENTRYPOINT ["java", "-jar", "budgettracker.jar"]