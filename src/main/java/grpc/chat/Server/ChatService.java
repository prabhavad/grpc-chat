package grpc.chat.Server;

import grpc.chat.*;
import io.grpc.stub.StreamObserver;


/**
 * Created by prabhav.a on 18/08/18.
 */

public class ChatService extends ChatServerGrpc.ChatServerImplBase {

    private RedisRepo redisRepo;

    ChatService() {
        redisRepo = RedisRepo.getRedisRepo();
    }

    @Override
    public void login(LoginRequest request, StreamObserver<LoginResponse> responseObserver) {
        if (redisRepo.isValidCredential(request.getUserName(), request.getPassword())) {

            String token = JWTService.getJwtService().getJWT(request.getUserName());

            LoginResponse response = LoginResponse
                    .newBuilder()
                    .setStatus("Login Successful!")
                    .setToken(token)
                    .build();

            responseObserver.onNext(response);

        } else {
            responseObserver.onNext(LoginResponse.newBuilder().setStatus("Login Failed. Try Again!").build());
        }
        responseObserver.onCompleted();
    }

    @Override
    public void sendMessage(SendMessageRequest request, StreamObserver<SendMessageResponse> responseObserver) {
        String from;
        try {
            String token = request.getToken();
            from = JWTService.getJwtService().parseJwt(token);
        } catch (Exception e) {
            responseObserver.onNext(SendMessageResponse.newBuilder().setStatus("Message Sending Failed").setTo(request.getMessage().getTo()).build());
            return;
        }

        if (userExists(request.getMessage().getTo())
                && userExists(from)) {

            sendMessage(request.getMessage().getTo(), from, request.getMessage().getMessageText());
            responseObserver.onNext(SendMessageResponse.newBuilder().setStatus("Message Sent!").build());

        } else {
            responseObserver.onNext(SendMessageResponse.newBuilder().setStatus("Message Sending Failed").setTo(request.getMessage().getTo()).build());
        }

        responseObserver.onCompleted();
    }

    @Override
    public void receiveMessage(ReceiveMessageRequest request, StreamObserver<Message> responseObserver) {
        String receiver;

        try {
            String token = request.getToken();
            receiver = JWTService.getJwtService().parseJwt(token);
        } catch (Exception e) {
            return;
        }

        while((redisRepo.hasNextMessage(receiver))) {

            Message message = redisRepo.getNextMessage(receiver);

            responseObserver.onNext(message);

            redisRepo.removeLastMessage(receiver);
        }

    }


    private boolean userExists(String userName) {
        return redisRepo.userExists(userName);
    }

    private void sendMessage(String receiver, String sender, String message) {
        redisRepo.addMessage(sender, receiver, message);
    }
}
