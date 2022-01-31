FROM openjdk:11
EXPOSE 8085:8085
ADD target/book-service.jar book-service.jar
ENTRYPOINT ["java", "-jar","/book-service.jar"]