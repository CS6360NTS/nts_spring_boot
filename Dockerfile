FROM java:8-jdk-alpine
ADD ./build/libs/*.jar opt/nts-0.0.1-SNAPSHOT.jar
WORKDIR /opt/
ENTRYPOINT exec -jar visibility-api-0.0.1-SNAPSHOT.jar
