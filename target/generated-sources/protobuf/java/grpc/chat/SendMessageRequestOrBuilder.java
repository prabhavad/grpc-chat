// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: chat.proto

package grpc.chat;

public interface SendMessageRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:SendMessageRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.Message message = 1;</code>
   */
  boolean hasMessage();
  /**
   * <code>.Message message = 1;</code>
   */
  grpc.chat.Message getMessage();
  /**
   * <code>.Message message = 1;</code>
   */
  grpc.chat.MessageOrBuilder getMessageOrBuilder();

  /**
   * <code>string token = 3;</code>
   */
  java.lang.String getToken();
  /**
   * <code>string token = 3;</code>
   */
  com.google.protobuf.ByteString
      getTokenBytes();
}
