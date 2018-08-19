FROM maven:slim

RUN apt-get update
RUN apt-get -y install redis-server

EXPOSE 80/tcp
EXPOSE 6379/tcp

COPY . .
#RUN mvn clean compile assembly:single
RUN chmod +x run.sh
EXPOSE 6379
EXPOSE 8980
ENTRYPOINT ["./run.sh"]
CMD []