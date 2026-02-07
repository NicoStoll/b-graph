FROM maven:3-eclipse-temurin-21 AS build
WORKDIR /opt/app
COPY src ./src
COPY pom.xml .
RUN --mount=type=cache,target=/root/.m2 mvn -f /opt/app/pom.xml package -DskipTests

FROM eclipse-temurin:21-jre
RUN mkdir -p /opt/app
COPY --from=build /opt/app/target/b-graph-*.jar /opt/app.jar
ENTRYPOINT ["java","-jar","/opt/app.jar"]