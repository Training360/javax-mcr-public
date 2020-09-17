FROM adoptopenjdk:14-jre-hotspot as builder
WORKDIR application
COPY target/addresses-0.0.1-SNAPSHOT.jar addresses.jar
RUN java -Djarmode=layertools -jar addresses.jar extract

FROM adoptopenjdk:14-jre-hotspot
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", \
  "org.springframework.boot.loader.JarLauncher"]