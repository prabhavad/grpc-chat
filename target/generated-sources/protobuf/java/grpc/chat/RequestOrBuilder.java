// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: chat.proto

package grpc.chat;

public interface RequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:Request)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string requestType = 1;</code>
   */
  java.lang.String getRequestType();
  /**
   * <code>string requestType = 1;</code>
   */
  com.google.protobuf.ByteString
      getRequestTypeBytes();

  /**
   * <code>string from = 2;</code>
   */
  java.lang.String getFrom();
  /**
   * <code>string from = 2;</code>
   */
  com.google.protobuf.ByteString
      getFromBytes();

  /**
   * <code>string to = 3;</code>
   */
  java.lang.String getTo();
  /**
   * <code>string to = 3;</code>
   */
  com.google.protobuf.ByteString
      getToBytes();

  /**
   * <code>string userName = 4;</code>
   */
  java.lang.String getUserName();
  /**
   * <code>string userName = 4;</code>
   */
  com.google.protobuf.ByteString
      getUserNameBytes();

  /**
   * <code>string password = 5;</code>
   */
  java.lang.String getPassword();
  /**
   * <code>string password = 5;</code>
   */
  com.google.protobuf.ByteString
      getPasswordBytes();

  /**
   * <code>string message = 6;</code>
   */
  java.lang.String getMessage();
  /**
   * <code>string message = 6;</code>
   */
  com.google.protobuf.ByteString
      getMessageBytes();
}