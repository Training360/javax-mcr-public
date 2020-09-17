# FROM adoptopenjdk:14-jre-hotspot
# RUN mkdir /opt/app
# ADD target/*.jar /opt/app/timesheet.jar
# CMD ["java", "-jar", "/opt/app/timesheet.jar"]

FROM adoptopenjdk:14-jre-hotspot as builder
WORKDIR application
COPY target/timesheet-0.0.1-SNAPSHOT.jar timesheet.jar
RUN java -Djarmode=layertools -jar timesheet.jar extract

FROM adoptopenjdk:14-jre-hotspot
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", \
  "org.springframework.boot.loader.JarLauncher"]
