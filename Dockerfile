FROM gradle:6.9.1-jdk11 AS build
ENV APP_HOME=/app/
WORKDIR $APP_HOME
COPY . .

FROM build AS test
WORKDIR $APP_HOME
ENTRYPOINT ["gradle", "test"]
