FROM eclipse-temurin:17 as builder
WORKDIR application
COPY target/locations-db-0.0.1-SNAPSHOT.jar locations.jar
RUN java -Djarmode=layertools -jar locations.jar extract

FROM eclipse-temurin:17
WORKDIR application

# Alkalmazás futtatása Docker Compose-zal
ADD https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh .
RUN chmod +x ./wait-for-it.sh

COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", \
  "org.springframework.boot.loader.JarLauncher"]