FROM gradle:9.2.1-jdk21-corretto AS builder
WORKDIR /app
COPY . .
RUN gradle build

FROM amazoncorretto:21-al2023-jdk
WORKDIR /app
COPY --from=builder /app/build/libs/AdventOfCode.jar .
ENTRYPOINT ["java", "-jar", "AdventOfCode.jar"]
