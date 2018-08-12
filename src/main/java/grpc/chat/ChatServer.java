package grpc.chat;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

/**
 * Created by prabhav.a on 12/08/18.
 */
public class ChatServer {

    private static final Logger logger = Logger.getLogger(ChatServer.class.getName());

    private final int port;
    private final Server server;

    public ChatServer(int port) throws IOException {
        this(ServerBuilder.forPort(port), port);
    }

    public ChatServer(ServerBuilder<?> serverBuilder, int port) {
        this.port = port;
        server = serverBuilder.addService(new ChatService())
                .build();
    }


    /** Start serving requests. */
    public void start() throws IOException {
        server.start();
        logger.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may has been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                ChatServer.this.stop();
                System.err.println("*** server shut down");
            }
        });
    }



    /** Stop serving requests and shutdown resources. */
    public void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    /**
     * Main method.  This comment makes the linter happy.
     */
    public static void main(String[] args) throws Exception {
        ChatServer server = new ChatServer(8980);
        server.start();
        server.blockUntilShutdown();
    }

    public static class ChatService extends ChatServerGrpc.ChatServerImplBase {

        Map<String, Queue<Message>> userToMessagesMap;

        Map<String, String> userNamePasswordMap;



        public ChatService() {

            userNamePasswordMap = new HashMap<String, String>();
            userNamePasswordMap.put("user1", "pass1");
            userNamePasswordMap.put("user2", "pass2");
            userNamePasswordMap.put("user3", "pass3");
            userNamePasswordMap.put("user4", "pass4");

            userToMessagesMap = new HashMap<String, Queue<Message>>();

        }


        public void login(Request request, StreamObserver<Response> responseObserver) {


            if (userNamePasswordMap.containsKey(request.getUserName())
                && userNamePasswordMap.get(request.getUserName()).equals(request.getPassword())) {

                responseObserver.onNext(Response.newBuilder().setResponseType("login").setLoginStatus("Login Successful!").setMessage(request.getUserName()).build());

            } else {
                responseObserver.onNext(Response.newBuilder().setResponseType("login").setLoginStatus("Login Failed. Try Again!").build());
            }

        }


        public void sendMessage(Request request, StreamObserver<Response> responseObserver) {
            if (userExists(request.getTo())
                    && userExists(request.getFrom())) {

                sendMessage(request.getTo(), request.getFrom(), request.getMessage());
                responseObserver.onNext(Response.newBuilder().setResponseType("send").setSendingStatus("Message Sent!").build());
            } else {
                responseObserver.onNext(Response.newBuilder().setResponseType("send").setSendingStatus("Message Sending Failed").setMessage(request.getTo()).build());
            }
        }


        public void receiveMessage(Request request, StreamObserver<Response> responseObserver) {
            String receiver = request.getFrom();

            if (userToMessagesMap.get(receiver) == null) {

            } else {
                while(!userToMessagesMap.get(receiver).isEmpty()) {
                    Message message = userToMessagesMap.get(receiver).peek();
                    responseObserver.onNext(Response.newBuilder().setResponseType("receive").setMessageSender(message.getSenderUserName())
                            .setMessage(message.getMessageText()).build());
                    userToMessagesMap.get(receiver).remove();
                }
            }
        }

        @Override
        public StreamObserver<Request> chatting(final StreamObserver<Response> responseObserver) {
            return new StreamObserver<Request>() {

                public void onNext(Request request) {
                    String requestType = request.getRequestType();
                    System.out.println(requestType);
                    if (requestType.equals("login")) {
                        login(request, responseObserver);
                    } else if (requestType.equals("send")) {
                        sendMessage(request, responseObserver);
                    } else if (requestType.equals("receive")) {
                        receiveMessage(request, responseObserver);
                    }
                }

                public void onError(Throwable throwable) {
                    System.out.println("Error");
                }

                public void onCompleted() {
                    System.out.println("completed");
                }
            };
        }

        private boolean userExists(String userName) {
            return userNamePasswordMap.containsKey(userName);
        }

        private void sendMessage(String receiver, String sender, String message) {
            if (userToMessagesMap == null) {
                userToMessagesMap = new HashMap<String, Queue<Message>>();
            }

            if (userToMessagesMap.get(receiver) == null) {
                userToMessagesMap.put(receiver, new LinkedList<Message>());
            }

            userToMessagesMap.get(receiver).add(Message.newBuilder().setMessageText(message).setSenderUserName(sender).build());
        }
    }
}
