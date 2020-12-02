FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=./build/libs/delivery-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ./build/libs/delivery-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]