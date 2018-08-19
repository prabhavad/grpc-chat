package grpc.chat.Client;

import grpc.chat.ChatServerGrpc;
import grpc.chat.Request;
import grpc.chat.Response;
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

        StreamObserver<Response> responseStreamObserver = new StreamObserver<Response>() {

            public void onNext(Response response) {
                if (response.getResponseType().equals("login")) {

                    System.out.println(response.getLoginStatus());
                    if (response.getLoginStatus().equals("Login Successful!")) {
                        client.state.setLoggedIn(true);
                        client.state.setToken(response.getToken());
                        client.state.setUserName(response.getMessage());
                    }

                } else if (response.getResponseType().equals("send")) {

                    if (!response.getSendingStatus().toLowerCase().contains("sent")) {

                        System.out.print(response.getSendingStatus());
                        System.out.println(" To:" + response.getMessage());

                    } else {

                        System.out.println(response.getSendingStatus());

                    }

                } else if (response.getResponseType().equals("receive")) {

                    System.out.println(response.getMessageSender()+":"+response.getMessage());
                }
            }

            public void onError(Throwable throwable) {
                System.out.println("Error");
            }

            public void onCompleted() {
                System.out.println("Completed");
            }
        };

        final StreamObserver<Request> requestStreamObserver = client.asyncStub.chatting(responseStreamObserver);

        Executors.newScheduledThreadPool(2).scheduleAtFixedRate(new Runnable() {
            public void run() {
                if (client.state.isLoggedIn()) {
                    Request request = Request.newBuilder()
                            .setRequestType("receive")
                            .setFrom(client.state.getUserName())
                            .setToken(client.state.getToken())
                            .build();

                    requestStreamObserver.onNext(request);
                }
            }
        }, 0, 1, TimeUnit.SECONDS);

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

                        client.login(userName, password, requestStreamObserver);
                    }

                } else {
                    String input = scanner.nextLine();
                    try {
                        String[] receiverMessageSplit = input.split(":", 2);
                        String to = receiverMessageSplit[0], message = receiverMessageSplit[1];
                        client.sendMessage(to, message, requestStreamObserver);
                    } catch (Exception ignored){}

                }
            } catch (Exception e) {
                System.out.println("Exception thrown");
                e.printStackTrace();
            }

        }
    }


    private void login(String userName, String password, StreamObserver<Request> streamObserver) {

        if (state.isLoggedIn()) {
            System.out.println("Already Logged In as " + state.getUserName());
            return;
        }

        streamObserver.onNext(Request.newBuilder()
                .setRequestType("login")
                .setUserName(userName)
                .setPassword(password)
                .build());

    }

    private void sendMessage(String receiver, String message, StreamObserver<Request> streamObserver) {

        streamObserver.onNext(Request.newBuilder()
                .setRequestType("send")
                .setToken(state.token)
                .setTo(receiver)
                .setMessage(message)
                .build());

    }

}
