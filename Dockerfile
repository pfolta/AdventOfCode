FROM gradle:9.2.1-jdk21 AS builder
WORKDIR /app
COPY . .
RUN gradle build

FROM amazoncorretto:21
WORKDIR /app
COPY --from=builder /app/build/libs/AdventOfCode.jar .
ENTRYPOINT ["java", "-jar", "AdventOfCode.jar"]
