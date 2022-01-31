FROM openjdk:11
EXPOSE 8084:8084
ADD target/user-service.jar user-service.jar
ENTRYPOINT ["java", "-jar","/user-service.jar"]