FROM openjdk:11
VOLUME /tmp
EXPOSE 8086
ADD ./target/ms-debit-card-0.0.1-SNAPSHOT.jar ms-debit-card.jar
ENTRYPOINT ["java","-jar","/ms-debit-card.jar"]