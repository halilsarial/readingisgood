FROM openjdk:11
EXPOSE 8761:8761
ADD target/discovery-server-service.jar discovery-server-service.jar
ENTRYPOINT ["java", "-jar","/discovery-server-service.jar"]