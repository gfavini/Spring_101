FROM openjdk

WORKDIR /app

COPY build/libs/demo-0.0.1-SNAPSHOT.jar /app/spring-101.jar

ENTRYPOINT ["java", "-jar", "spring-101.jar"]