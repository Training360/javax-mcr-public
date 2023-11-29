#FROM eclipse-temurin:17
#RUN mkdir /opt/app
#COPY target/employees-0.0.1-SNAPSHOT.jar /opt/app/employees.jar
#CMD ["java", "-jar", "/opt/app/employees.jar"]

FROM eclipse-temurin:17 as builder
WORKDIR application
COPY target/employees-0.0.1-SNAPSHOT.jar employees.jar
RUN java -Djarmode=layertools -jar employees.jar extract

FROM eclipse-temurin:17
WORKDIR application
RUN apt update \
    && apt-get install wget \
    && apt-get install -y netcat \
    && wget http://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh \
    && chmod +x ./wait-for-it.sh
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", \
  "org.springframework.boot.loader.JarLauncher"]