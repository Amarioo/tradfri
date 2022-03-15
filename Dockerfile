FROM arm32v7/adoptopenjdk:11-jdk-hotspot
RUN addgroup spring
RUN adduser spring --ingroup spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} tradfri-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/tradfri-0.0.1-SNAPSHOT.jar"]