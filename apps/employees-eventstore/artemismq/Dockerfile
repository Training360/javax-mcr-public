FROM adoptopenjdk:14-jdk-hotspot

RUN apt-get update \
    && apt-get install wget \
    && wget -q -O /tmp/apache-artemis-2.15.0-bin.tar.gz "https://www.apache.org/dyn/closer.cgi?filename=activemq/activemq-artemis/2.15.0/apache-artemis-2.15.0-bin.tar.gz&action=download" \
    && tar xzf /tmp/apache-artemis-2.15.0-bin.tar.gz -C /opt \
    && rm /tmp/apache-artemis-2.15.0-bin.tar.gz

WORKDIR /var/lib
RUN /opt/apache-artemis-2.15.0/bin/artemis create --http-host 0.0.0.0 --relax-jolokia --queues eventsQueue --allow-anonymous --user artemis --password artemis eventstorebroker

RUN sed -i "s|<max-disk-usage>90</max-disk-usage>|<max-disk-usage>100</max-disk-usage>|g" /var/lib/eventstorebroker/etc/broker.xml

EXPOSE 8161
EXPOSE 61616

CMD ["/var/lib/eventstorebroker/bin/artemis", "run"]
