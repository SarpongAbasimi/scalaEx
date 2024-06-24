FROM openjdk:21

WORKDIR /app

COPY . .

ADD target/scala-2.13/ecr-assembly-0.1.0-SNAPSHOT.jar .

ENTRYPOINT ["java","-jar","ecr-assembly-0.1.0-SNAPSHOT.jar"]