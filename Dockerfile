FROM eclipse-temurin:21-jdk-alpine AS JAR_EXTRACT
WORKDIR /app
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} ./app.jar
RUN java -Djarmode=layertools -jar ./app.jar extract

FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp
WORKDIR /application
COPY --from=JAR_EXTRACT /app/dependencies ./
COPY --from=JAR_EXTRACT /app/spring-boot-loader ./
COPY --from=JAR_EXTRACT /app/snapshot-dependencies ./
COPY --from=JAR_EXTRACT /app/application ./
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]