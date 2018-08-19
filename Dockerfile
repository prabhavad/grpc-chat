FROM java:8
COPY . .
RUN apt-get update
RUN apt-get install --assume-yes maven
RUN mvn clean compile
CMD mvn exec:java -Dexec.mainClass="grpc.chat.ChatClient"