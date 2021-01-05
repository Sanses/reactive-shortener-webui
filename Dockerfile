FROM maven:3.6.3-openjdk-11-slim AS build
WORKDIR /usr/app
COPY . /usr/app
RUN mvn clean package -Dmaven.test.skip=true

FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /usr/app/target/*.jar /app/app.jar
COPY ./ApplicationInsights-agent/* /app/ApplicationInsights-agent/
EXPOSE 8080

ARG SPRING_PROFILE=dev
ARG JVM_MEMORY="256"

ENV AGENT_HOME="/app/ApplicationInsights-agent"
ENV SPRING_PROFILE=${SPRING_PROFILE}
ENV JAVA_OPTS=" -server -Xms${JVM_MEMORY}m -Xmx${JVM_MEMORY}m -Dspring.profiles.active=${SPRING_PROFILE}"

ENTRYPOINT exec java ${JAVA_OPTS} -javaagent:${AGENT_HOME}/applicationinsights-agent-3.0.0.jar -jar /app/app.jar