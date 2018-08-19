package grpc.chat.Server;

import grpc.chat.ChatServerGrpc;
import grpc.chat.Message;
import grpc.chat.Request;
import grpc.chat.Response;
import io.grpc.stub.StreamObserver;


/**
 * Created by prabhav.a on 18/08/18.
 */

public class ChatService extends ChatServerGrpc.ChatServerImplBase {

    private RedisRepo redisRepo;

    ChatService() {
        redisRepo = RedisRepo.getRedisRepo();
    }


    private void login(Request request, StreamObserver<Response> responseObserver) {

        System.out.println("Login Request Here !!");
        if (redisRepo.isValidCredential(request.getUserName(), request.getPassword())) {

            String token = JWTService.getJwtService().getJWT(request.getUserName());

            Response response = Response
                    .newBuilder()
                    .setResponseType("login")
                    .setLoginStatus("Login Successful!")
                    .setToken(token)
                    .setMessage(request.getUserName()).build();

            responseObserver.onNext(response);

        } else {
            responseObserver.onNext(Response.newBuilder().setResponseType("login").setLoginStatus("Login Failed. Try Again!").build());
        }

    }


    private void sendMessage(Request request, StreamObserver<Response> responseObserver) {
        String from;
        try {
            String token = request.getToken();
            from = JWTService.getJwtService().parseJwt(token);
        } catch (Exception e) {
            responseObserver.onNext(Response.newBuilder().setResponseType("send").setSendingStatus("Message Sending Failed").setMessage(request.getTo()).build());
            return;
        }

        if (userExists(request.getTo())
                && userExists(from)) {

            sendMessage(request.getTo(), from, request.getMessage());
            responseObserver.onNext(Response.newBuilder().setResponseType("send").setSendingStatus("Message Sent!").build());

        } else {
            responseObserver.onNext(Response.newBuilder().setResponseType("send").setSendingStatus("Message Sending Failed").setMessage(request.getTo()).build());
        }

    }


    private void receiveMessage(Request request, StreamObserver<Response> responseObserver) {
        String receiver;

        try {
            String token = request.getToken();
            receiver = JWTService.getJwtService().parseJwt(token);
        } catch (Exception e) {
            return;
        }

        while((redisRepo.hasNextMessage(receiver))) {

            Message message = redisRepo.getNextMessage(receiver);

            responseObserver.onNext(
                    Response.newBuilder()
                            .setResponseType("receive")
                            .setMessageSender(message.getSenderUserName())
                            .setMessage(message.getMessageText())
                            .build());

            redisRepo.removeLastMessage(receiver);
        }

    }

    @Override
    public StreamObserver<Request> chatting(final StreamObserver<Response> responseObserver) {
        return new StreamObserver<Request>() {

            public void onNext(Request request) {
                String requestType = request.getRequestType();
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
        return redisRepo.userExists(userName);
    }

    private void sendMessage(String receiver, String sender, String message) {
        redisRepo.addMessage(sender, receiver, message);
    }
}
