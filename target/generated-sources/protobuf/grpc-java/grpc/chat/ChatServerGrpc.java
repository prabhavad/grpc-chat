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
  private static volatile io.grpc.MethodDescriptor<grpc.chat.Request,
      grpc.chat.Response> getChattingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Chatting",
      requestType = grpc.chat.Request.class,
      responseType = grpc.chat.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.chat.Request,
      grpc.chat.Response> getChattingMethod() {
    io.grpc.MethodDescriptor<grpc.chat.Request, grpc.chat.Response> getChattingMethod;
    if ((getChattingMethod = ChatServerGrpc.getChattingMethod) == null) {
      synchronized (ChatServerGrpc.class) {
        if ((getChattingMethod = ChatServerGrpc.getChattingMethod) == null) {
          ChatServerGrpc.getChattingMethod = getChattingMethod = 
              io.grpc.MethodDescriptor.<grpc.chat.Request, grpc.chat.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "ChatServer", "Chatting"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.chat.Request.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.chat.Response.getDefaultInstance()))
                  .setSchemaDescriptor(new ChatServerMethodDescriptorSupplier("Chatting"))
                  .build();
          }
        }
     }
     return getChattingMethod;
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
    public io.grpc.stub.StreamObserver<grpc.chat.Request> chatting(
        io.grpc.stub.StreamObserver<grpc.chat.Response> responseObserver) {
      return asyncUnimplementedStreamingCall(getChattingMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getChattingMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                grpc.chat.Request,
                grpc.chat.Response>(
                  this, METHODID_CHATTING)))
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
    public io.grpc.stub.StreamObserver<grpc.chat.Request> chatting(
        io.grpc.stub.StreamObserver<grpc.chat.Response> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getChattingMethod(), getCallOptions()), responseObserver);
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
  }

  private static final int METHODID_CHATTING = 0;

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
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CHATTING:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.chatting(
              (io.grpc.stub.StreamObserver<grpc.chat.Response>) responseObserver);
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
              .addMethod(getChattingMethod())
              .build();
        }
      }
    }
    return result;
  }
}
