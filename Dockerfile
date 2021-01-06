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

ARG APPINSIGHTS_CONNECTION_STRING=""

ARG REDIS_HOST=redis
ARG REDIS_PORT=6379
ARG REDIS_SSL_ENABLE=false
ARG REDIS_PASSWORD=redis

ENV APPINSIGHTS_CONNECTION_STRING=${APPINSIGHTS_CONNECTION_STRING}

ENV REDIS_HOST=${REDIS_HOST}
ENV REDIS_PORT=${REDIS_PORT}
ENV REDIS_SSL_ENABLE=${REDIS_SSL_ENABLE}
ENV REDIS_PASSWORD=${REDIS_PASSWORD}

ENV AGENT_HOME="/app/ApplicationInsights-agent"
ENV SPRING_PROFILE=${SPRING_PROFILE}
ENV JVM_OPTS=" -server -Xms${JVM_MEMORY}m -Xmx${JVM_MEMORY}m -Dspring.profiles.active=${SPRING_PROFILE}"

ENTRYPOINT exec java ${JVM_OPTS} -javaagent:${AGENT_HOME}/applicationinsights-agent-3.0.0.jar -jar /app/app.jar