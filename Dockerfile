FROM openjdk:8
COPY ./target/contact-be.jar /home/contact-be.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/home/contact-be.jar"]