FROM openjdk:8
EXPOSE 9090
ADD "target/authorization-microservice.jar" "authorization-microservice.jar"
ENTRYPOINT [ "java", "-jar", "/authorization-microservice.jar" ]