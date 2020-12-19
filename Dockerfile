FROM gradle:6.6.1-jdk-hotspot

WORKDIR /home/gradle/project

EXPOSE 8082

## RUN apk update

ENV GRADLE_USER_HOME /home/gradle/project

COPY . /home/gradle/project

RUN gradle :shoppring-cart-api:build

# PROJECT BUILD IS COMPLETE, NOW RUN THE APPLICATION

FROM adoptopenjdk/openjdk11:jre-11.0.9.1_1-alpine

WORKDIR /home/gradle/project

COPY --from=0 /home/gradle/project/shopping-cart-api/build/libs/shopping-cart-api-${version}.jar .

ENTRYPOINT java -Dspring.profiles.active=cloud -jar project-0.0.1-SNAPSHOT.jar

