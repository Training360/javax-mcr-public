FROM eclipse-temurin:17
RUN mkdir /opt/app
COPY target/employees-0.0.1-SNAPSHOT.jar /opt/app/employees.jar
CMD ["java", "-jar", "/opt/app/employees.jar"]