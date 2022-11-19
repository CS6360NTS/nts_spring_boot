FROM java:8-jdk-alpine
ADD target/*.jar opt/nts-0.0.1-SNAPSHOT.jar
WORKDIR /opt/
ENTRYPOINT exec -jar nts-0.0.1-SNAPSHOT.jar
