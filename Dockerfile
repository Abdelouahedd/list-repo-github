FROM maven:3.8-jdk-11 AS build
WORKDIR /app
COPY pom.xml pom.xml
COPY src src
RUN mvn package -DskipTests=true

FROM openjdk:11
COPY --from=build /app/target/challengeCode-0.0.1-SNAPSHOT.jar /usr/local/lib/app.jar
EXPOSE 9090
CMD ["java", "-jar", "/usr/local/lib/app.jar"]