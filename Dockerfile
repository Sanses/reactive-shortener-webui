FROM maven:3.6.3-openjdk-11-slim AS build
WORKDIR /usr/app
COPY . /usr/app
RUN mvn clean package -Dmaven.test.skip=true

FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /usr/app/target/*.jar /app/app.jar
COPY ./ApplicationInsights-agent/* /app/ApplicationInsights-agent/
EXPOSE 8080

ARG APPLICATIONINSIGHTS_CONNECTION_STRING=""

ARG REDIS_HOST=redis
ARG REDIS_PORT=6379
ARG REDIS_SSL_ENABLE=false
ARG REDIS_PASSWORD=redis

ENV APPLICATIONINSIGHTS_CONNECTION_STRING=${APPLICATIONINSIGHTS_CONNECTION_STRING}

ENV REDIS_HOST=${REDIS_HOST}
ENV REDIS_PORT=${REDIS_PORT}
ENV REDIS_SSL_ENABLE=${REDIS_SSL_ENABLE}
ENV REDIS_PASSWORD=${REDIS_PASSWORD}

ENV AGENT_HOME="/app/ApplicationInsights-agent"

ENTRYPOINT exec java ${JAVA_OPTS} -javaagent:${AGENT_HOME}/applicationinsights-agent-3.0.0.jar -jar /app/app.jar
