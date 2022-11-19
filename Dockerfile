FROM openjdk:19-jdk-alpine3.16
ADD target/*.jar opt/nts-0.0.1-SNAPSHOT.jar
WORKDIR /opt/
ENTRYPOINT exec -jar nts-0.0.1-SNAPSHOT.jar
