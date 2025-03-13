FROM jelastic/maven:3.9.5-openjdk-21 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-slim
COPY --from=build target/furniture-shopping-spring-0.0.1-SNAPSHOT.jar furniture-shopping-spring.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "furniture-shopping-spring.jar"]