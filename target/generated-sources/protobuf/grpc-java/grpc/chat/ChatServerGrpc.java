package grpc.chat;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.14.0)",
    comments = "Source: chat.proto")
public final class ChatServerGrpc {

  private ChatServerGrpc() {}

  public static final String SERVICE_NAME = "ChatServer";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.chat.LoginRequest,
      grpc.chat.LoginResponse> getLoginMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Login",
      requestType = grpc.chat.LoginRequest.class,
      responseType = grpc.chat.LoginResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.chat.LoginRequest,
      grpc.chat.LoginResponse> getLoginMethod() {
    io.grpc.MethodDescriptor<grpc.chat.LoginRequest, grpc.chat.LoginResponse> getLoginMethod;
    if ((getLoginMethod = ChatServerGrpc.getLoginMethod) == null) {
      synchronized (ChatServerGrpc.class) {
        if ((getLoginMethod = ChatServerGrpc.getLoginMethod) == null) {
          ChatServerGrpc.getLoginMethod = getLoginMethod = 
              io.grpc.MethodDescriptor.<grpc.chat.LoginRequest, grpc.chat.LoginResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "ChatServer", "Login"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.chat.LoginRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.chat.LoginResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ChatServerMethodDescriptorSupplier("Login"))
                  .build();
          }
        }
     }
     return getLoginMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.chat.SendMessageRequest,
      grpc.chat.SendMessageResponse> getSendMessageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SendMessage",
      requestType = grpc.chat.SendMessageRequest.class,
      responseType = grpc.chat.SendMessageResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.chat.SendMessageRequest,
      grpc.chat.SendMessageResponse> getSendMessageMethod() {
    io.grpc.MethodDescriptor<grpc.chat.SendMessageRequest, grpc.chat.SendMessageResponse> getSendMessageMethod;
    if ((getSendMessageMethod = ChatServerGrpc.getSendMessageMethod) == null) {
      synchronized (ChatServerGrpc.class) {
        if ((getSendMessageMethod = ChatServerGrpc.getSendMessageMethod) == null) {
          ChatServerGrpc.getSendMessageMethod = getSendMessageMethod = 
              io.grpc.MethodDescriptor.<grpc.chat.SendMessageRequest, grpc.chat.SendMessageResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "ChatServer", "SendMessage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.chat.SendMessageRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.chat.SendMessageResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ChatServerMethodDescriptorSupplier("SendMessage"))
                  .build();
          }
        }
     }
     return getSendMessageMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.chat.ReceiveMessageRequest,
      grpc.chat.Message> getReceiveMessageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ReceiveMessage",
      requestType = grpc.chat.ReceiveMessageRequest.class,
      responseType = grpc.chat.Message.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.chat.ReceiveMessageRequest,
      grpc.chat.Message> getReceiveMessageMethod() {
    io.grpc.MethodDescriptor<grpc.chat.ReceiveMessageRequest, grpc.chat.Message> getReceiveMessageMethod;
    if ((getReceiveMessageMethod = ChatServerGrpc.getReceiveMessageMethod) == null) {
      synchronized (ChatServerGrpc.class) {
        if ((getReceiveMessageMethod = ChatServerGrpc.getReceiveMessageMethod) == null) {
          ChatServerGrpc.getReceiveMessageMethod = getReceiveMessageMethod = 
              io.grpc.MethodDescriptor.<grpc.chat.ReceiveMessageRequest, grpc.chat.Message>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "ChatServer", "ReceiveMessage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.chat.ReceiveMessageRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.chat.Message.getDefaultInstance()))
                  .setSchemaDescriptor(new ChatServerMethodDescriptorSupplier("ReceiveMessage"))
                  .build();
          }
        }
     }
     return getReceiveMessageMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ChatServerStub newStub(io.grpc.Channel channel) {
    return new ChatServerStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ChatServerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ChatServerBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ChatServerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ChatServerFutureStub(channel);
  }

  /**
   */
  public static abstract class ChatServerImplBase implements io.grpc.BindableService {

    /**
     */
    public void login(grpc.chat.LoginRequest request,
        io.grpc.stub.StreamObserver<grpc.chat.LoginResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLoginMethod(), responseObserver);
    }

    /**
     */
    public void sendMessage(grpc.chat.SendMessageRequest request,
        io.grpc.stub.StreamObserver<grpc.chat.SendMessageResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSendMessageMethod(), responseObserver);
    }

    /**
     */
    public void receiveMessage(grpc.chat.ReceiveMessageRequest request,
        io.grpc.stub.StreamObserver<grpc.chat.Message> responseObserver) {
      asyncUnimplementedUnaryCall(getReceiveMessageMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getLoginMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.chat.LoginRequest,
                grpc.chat.LoginResponse>(
                  this, METHODID_LOGIN)))
          .addMethod(
            getSendMessageMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.chat.SendMessageRequest,
                grpc.chat.SendMessageResponse>(
                  this, METHODID_SEND_MESSAGE)))
          .addMethod(
            getReceiveMessageMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                grpc.chat.ReceiveMessageRequest,
                grpc.chat.Message>(
                  this, METHODID_RECEIVE_MESSAGE)))
          .build();
    }
  }

  /**
   */
  public static final class ChatServerStub extends io.grpc.stub.AbstractStub<ChatServerStub> {
    private ChatServerStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChatServerStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatServerStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChatServerStub(channel, callOptions);
    }

    /**
     */
    public void login(grpc.chat.LoginRequest request,
        io.grpc.stub.StreamObserver<grpc.chat.LoginResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLoginMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void sendMessage(grpc.chat.SendMessageRequest request,
        io.grpc.stub.StreamObserver<grpc.chat.SendMessageResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendMessageMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void receiveMessage(grpc.chat.ReceiveMessageRequest request,
        io.grpc.stub.StreamObserver<grpc.chat.Message> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getReceiveMessageMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ChatServerBlockingStub extends io.grpc.stub.AbstractStub<ChatServerBlockingStub> {
    private ChatServerBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChatServerBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatServerBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChatServerBlockingStub(channel, callOptions);
    }

    /**
     */
    public grpc.chat.LoginResponse login(grpc.chat.LoginRequest request) {
      return blockingUnaryCall(
          getChannel(), getLoginMethod(), getCallOptions(), request);
    }

    /**
     */
    public grpc.chat.SendMessageResponse sendMessage(grpc.chat.SendMessageRequest request) {
      return blockingUnaryCall(
          getChannel(), getSendMessageMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<grpc.chat.Message> receiveMessage(
        grpc.chat.ReceiveMessageRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getReceiveMessageMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ChatServerFutureStub extends io.grpc.stub.AbstractStub<ChatServerFutureStub> {
    private ChatServerFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChatServerFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatServerFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChatServerFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.chat.LoginResponse> login(
        grpc.chat.LoginRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLoginMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.chat.SendMessageResponse> sendMessage(
        grpc.chat.SendMessageRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSendMessageMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LOGIN = 0;
  private static final int METHODID_SEND_MESSAGE = 1;
  private static final int METHODID_RECEIVE_MESSAGE = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ChatServerImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ChatServerImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LOGIN:
          serviceImpl.login((grpc.chat.LoginRequest) request,
              (io.grpc.stub.StreamObserver<grpc.chat.LoginResponse>) responseObserver);
          break;
        case METHODID_SEND_MESSAGE:
          serviceImpl.sendMessage((grpc.chat.SendMessageRequest) request,
              (io.grpc.stub.StreamObserver<grpc.chat.SendMessageResponse>) responseObserver);
          break;
        case METHODID_RECEIVE_MESSAGE:
          serviceImpl.receiveMessage((grpc.chat.ReceiveMessageRequest) request,
              (io.grpc.stub.StreamObserver<grpc.chat.Message>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class ChatServerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ChatServerBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.chat.Chat.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ChatServer");
    }
  }

  private static final class ChatServerFileDescriptorSupplier
      extends ChatServerBaseDescriptorSupplier {
    ChatServerFileDescriptorSupplier() {}
  }

  private static final class ChatServerMethodDescriptorSupplier
      extends ChatServerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ChatServerMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ChatServerGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ChatServerFileDescriptorSupplier())
              .addMethod(getLoginMethod())
              .addMethod(getSendMessageMethod())
              .addMethod(getReceiveMessageMethod())
              .build();
        }
      }
    }
    return result;
  }
}
