FROM openjdk:11-jre-slim
MAINTAINER "Docker App <docker@app.com>"
WORKDIR /app
EXPOSE 9296
COPY ./target/*.jar ./app.jar
ENTRYPOINT exec java -jar /app/app.jar configserver