FROM gradle:6.6.1-jdk11-hotspot

WORKDIR /home/gradle/project

EXPOSE 8082

## RUN apk update

ENV GRADLE_USER_HOME /home/gradle/project

COPY . /home/gradle/project

RUN gradle :shopping-cart-api:build

# PROJECT BUILD IS COMPLETE, NOW RUN THE APPLICATION

FROM adoptopenjdk/openjdk11:jre-11.0.9.1_1-alpine

WORKDIR /home/gradle/project

COPY --from=0 /home/gradle/project/shopping-cart-api/build/libs/shopping-cart-api-0.0.1-SNAPSHOT.jar .

ENTRYPOINT java -Dspring.profiles.active=cloud -Dserver.port=8082 -jar shopping-cart-api-0.0.1-SNAPSHOT.jar

