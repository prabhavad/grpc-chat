# grpc-chat


Run the following command:
  mvn clean compile package 

And:

To Run Server: mvn exec:java -Dexec.mainClass="grpc.chat.ChatServer"
To Run Client: mvn exec:java -Dexec.mainClass="grpc.chat.ChatClient"

1. Run Client only after server starts.

2. There are 4 users registered: user1, user2, user3, user4. Their corresponding passwords are pass1, pass2, pass3, pass4.

3. To send a message, it should be in this format: "user1:Message". So, if you want to send user2 "i am fine", you will need to type "user2:i am fine". quotes for clarity only.

4. Messages will be received as soon as the user logs in (or as received). No history of messages is maintained, messages will be deleted as soon as it is read.

5. After logging in, just type the message in the format (as given in point3) to send message.

