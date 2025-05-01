FROM openjdk:11-slim

WORKDIR /usr/src/app

COPY src/Land/ Land/
COPY src/MissionManagementSystem/ MissionManagementSystem/
COPY src/Nursel/ Nursel/

COPY run.sh .
RUN chmod +x run.sh

RUN find . -name "*.java" | xargs javac -d .

CMD ["bash", "./run.sh"]
