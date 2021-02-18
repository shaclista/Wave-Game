FROM openjdk:8

RUN mkdir /applications

WORKDIR /applications

RUN apt-get update \
    && apt-get install -y libxrender1 libxtst6 libxi6 \
    && apt-get install curl

RUN curl -L -H "Accept: application/vnd.github.v3+json" -H "Authorization: token {token}" https://api.github.com/repos/shaclista/Wave-Game/actions/artifacts/40974227/zip --output Wave-Game.zip

RUN unzip Wave-Game.zip

CMD ["java", "-jar","Wave-Game-1.0.jar"]