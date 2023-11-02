FROM gradle:8.4.0-jdk8 AS builder
WORKDIR /app
COPY . .
RUN gradle build

FROM amazoncorretto:8u392-al2023-jre
WORKDIR /app
COPY --from=builder /app/build/libs/AdventOfCode.jar .
ENTRYPOINT ["java", "-jar", "AdventOfCode.jar"]
