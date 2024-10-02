FROM openjdk:17-alpine

EXPOSE 8080

ENV JAVA_TOOL_OPTIONS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"
EXPOSE 5005

ADD build/libs/hitsto-0.0.1-SNAPSHOT.jar hitsto-0.0.1.jar

ENTRYPOINT ["java","-jar","/hitsto-0.0.1.jar"]
