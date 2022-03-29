FROM openjdk:8-jdk-alpine

# Copy the JAR from the target folder into the container
COPY /target/bambooOrg.jar bambooOrg.jar 

ENTRYPOINT ["java", "-jar", "/bambooOrg.jar"]