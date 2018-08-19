package grpc.chat.Client;

import grpc.chat.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by prabhav.a on 12/08/18.
 */
public class ChatClient {

    private final ManagedChannel channel;
    private final ChatServerGrpc.ChatServerBlockingStub blockingStub;
    private final ChatServerGrpc.ChatServerStub asyncStub;

    /** Construct client for accessing RouteGuide server at {@code host:port}. */
    public ChatClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port).usePlaintext());
    }

    /** Construct client for accessing RouteGuide server using the existing channel. */
    public ChatClient(ManagedChannelBuilder<?> channelBuilder) {
        channel = channelBuilder.build();
        blockingStub = ChatServerGrpc.newBlockingStub(channel);
        asyncStub = ChatServerGrpc.newStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }



    private static class State {
        boolean isLoggedIn;
        private String userName;
        private String password;
        private String token;

        public State(){
            isLoggedIn = false;
        }
        public synchronized boolean isLoggedIn() {
            return isLoggedIn;
        }

        public void setLoggedIn(boolean loggedIn) {
            isLoggedIn = loggedIn;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

    private State state = new State();


    public static void main(String[] args) throws InterruptedException {

        Scanner scanner = new Scanner(System.in);


        final ChatClient client = new ChatClient("localhost", 8980);

        while (true) {

            try {
                if (!client.state.isLoggedIn()) {
                    Thread.sleep(2000);
                    if (!client.state.isLoggedIn()) {

                        System.out.println("Please Login First");
                        System.out.println("UserName:");
                        String userName = scanner.nextLine();
                        System.out.println("Password:");
                        String password = scanner.nextLine();

                        client.login(userName, password);
                    }

                } else {
                    String input = scanner.nextLine();
                    try {
                        String[] receiverMessageSplit = input.split(":", 2);
                        String to = receiverMessageSplit[0], message = receiverMessageSplit[1];
                        client.sendMessage(to, message);
                    } catch (Exception ignored){}

                }
            } catch (Exception e) {
                System.out.println("Exception thrown");
                e.printStackTrace();
            }

        }
    }


    private void login(String userName, String password) {

        if (state.isLoggedIn()) {
            System.out.println("Already Logged In as " + state.getUserName());
            return;
        }

        LoginResponse loginResponse = blockingStub.login(LoginRequest.newBuilder()
                                                .setUserName(userName)
                                                .setPassword(password)
                                                .build());

        System.out.println(loginResponse.getStatus());
        if (loginResponse.getStatus().equals("Login Successful!")) {
            state.setLoggedIn(true);
            state.setToken(loginResponse.getToken());
            state.setUserName(userName);

            Executors.newScheduledThreadPool(2).scheduleAtFixedRate(new ReceiveMessage(asyncStub, loginResponse.getToken()), 0, 1, TimeUnit.SECONDS);

        }

    }

    private void sendMessage(String receiver, String message) {


        SendMessageRequest sendMessageRequest = SendMessageRequest.newBuilder()
                                                            .setMessage(
                                                                    Message.newBuilder()
                                                                        .setMessageText(message)
                                                                        .setTo(receiver)
                                                                        .build())
                                                            .setToken(state.getToken())
                                                            .build();

        SendMessageResponse sendMessageResponse = blockingStub.sendMessage(sendMessageRequest);

        if (!sendMessageResponse.getStatus().toLowerCase().contains("sent")) {

            System.out.print(sendMessageResponse.getStatus());
            System.out.println(" To:" + receiver);

        } else {
            System.out.println(sendMessageResponse.getStatus());
        }

    }

}
