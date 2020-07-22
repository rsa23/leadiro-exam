FROM openjdk:13
WORKDIR /app
COPY app/*.jar /app/leadiro.jar

CMD ["java", "-jar", "/leadiro.jar"]

