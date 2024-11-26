FROM maven:3-ibm-semeru-17-focal AS build

WORKDIR /app

COPY pom.xml /app

COPY src /app/src

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/A3-0.0.1-SNAPSHOT.jar /app/meu-projeto.jar

EXPOSE 3000

CMD ["java", "-jar", "meu-projeto.jar"]
