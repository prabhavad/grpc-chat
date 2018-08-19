package grpc.chat.Client;

import grpc.chat.ChatServerGrpc;
import grpc.chat.Message;
import grpc.chat.ReceiveMessageRequest;
import io.grpc.stub.StreamObserver;

/**
 * Created by prabhav.a on 19/08/18.
 */
public class ReceiveMessage implements Runnable {

    private String token;
    private ChatServerGrpc.ChatServerStub serverStub;
    private StreamObserver<Message> responseStreamObserver;

    ReceiveMessage(ChatServerGrpc.ChatServerStub serverStub, String token) {
        this.token = token;
        this.serverStub = serverStub;

        responseStreamObserver = new StreamObserver<Message>() {

            public void onNext(Message message) {
                System.out.println("New Message From " + message.getFrom()+":"+message.getMessageText());
            }

            public void onError(Throwable throwable) {
                System.out.println("Error");
            }

            public void onCompleted() {
                System.out.println("Completed");
            }
        };
    }

    public void run() {

        ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.newBuilder()
                .setToken(token)
                .build();

        serverStub.receiveMessage(receiveMessageRequest, responseStreamObserver);
    }
}
