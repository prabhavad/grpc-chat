// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: chat.proto

package grpc.chat;

public interface ResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:Response)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string messageSender = 1;</code>
   */
  java.lang.String getMessageSender();
  /**
   * <code>string messageSender = 1;</code>
   */
  com.google.protobuf.ByteString
      getMessageSenderBytes();

  /**
   * <code>string message = 2;</code>
   */
  java.lang.String getMessage();
  /**
   * <code>string message = 2;</code>
   */
  com.google.protobuf.ByteString
      getMessageBytes();

  /**
   * <code>string sendingStatus = 3;</code>
   */
  java.lang.String getSendingStatus();
  /**
   * <code>string sendingStatus = 3;</code>
   */
  com.google.protobuf.ByteString
      getSendingStatusBytes();

  /**
   * <code>string loginStatus = 4;</code>
   */
  java.lang.String getLoginStatus();
  /**
   * <code>string loginStatus = 4;</code>
   */
  com.google.protobuf.ByteString
      getLoginStatusBytes();

  /**
   * <code>string responseType = 5;</code>
   */
  java.lang.String getResponseType();
  /**
   * <code>string responseType = 5;</code>
   */
  com.google.protobuf.ByteString
      getResponseTypeBytes();

  /**
   * <code>string token = 6;</code>
   */
  java.lang.String getToken();
  /**
   * <code>string token = 6;</code>
   */
  com.google.protobuf.ByteString
      getTokenBytes();
}
