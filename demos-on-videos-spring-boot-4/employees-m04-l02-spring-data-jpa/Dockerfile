#FROM eclipse-temurin:17
#RUN mkdir /opt/app
#COPY target/employees-0.0.1-SNAPSHOT.jar /opt/app/employees.jar
#CMD ["java", "-jar", "/opt/app/employees.jar"]

FROM eclipse-temurin:17 AS builder
WORKDIR /application
COPY target/employees-0.0.1-SNAPSHOT.jar employees.jar
RUN java -Djarmode=tools -jar employees.jar extract --layers --destination extracted

FROM eclipse-temurin:17
WORKDIR /application
COPY --from=builder /application/extracted/dependencies/ ./
COPY --from=builder /application/extracted/spring-boot-loader/ ./
COPY --from=builder /application/extracted/snapshot-dependencies/ ./
COPY --from=builder /application/extracted/application/ ./
ENTRYPOINT ["java", "-jar", "employees.jar"]