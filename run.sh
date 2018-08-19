#!/bin/bash

redis-cli ping

echo $1

if [ $1 = "s" ]
then
    nohup redis-server &
    sleep 4
    redis-cli hset userNamePasswordMap user1 "pass1"
    redis-cli hset userNamePasswordMap user2 "pass2"
    redis-cli hset userNamePasswordMap user3 "pass3"
    redis-cli hset userNamePasswordMap user4 "pass4"

    java -cp  target/share-chat-1.0-SNAPSHOT-jar-with-dependencies.jar grpc.chat.Server.ChatServer
else
    java -cp  target/share-chat-1.0-SNAPSHOT-jar-with-dependencies.jar grpc.chat.Client.ChatClient
fi
