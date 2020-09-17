#FROM adoptopenjdk:13-jre-hotspot
#RUN mkdir /opt/app
#ADD target/*.jar /opt/app/eventstore.jar
#CMD ["java", "-jar", "/opt/app/eventstore.jar"]

FROM adoptopenjdk:14-jre-hotspot as builder
WORKDIR application
COPY target/eventstore-0.0.1-SNAPSHOT.jar eventstore.jar
RUN java -Djarmode=layertools -jar eventstore.jar extract

FROM adoptopenjdk:14-jre-hotspot
WORKDIR application
RUN  apt-get update \
     && apt-get install wget \
     && apt-get install -y netcat \
     && wget https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh \
     && chmod +x ./wait-for-it.sh
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", \
  "org.springframework.boot.loader.JarLauncher"]