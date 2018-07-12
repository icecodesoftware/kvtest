package com.github.icecodesoftware.generated;

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
 * <pre>
 * The greeting service definition.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.13.1)",
    comments = "Source: lookup.proto")
public final class LookupServiceGrpc {

  private LookupServiceGrpc() {}

  public static final String SERVICE_NAME = "lookup.LookupService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.github.icecodesoftware.generated.Key,
      com.github.icecodesoftware.generated.Value> getGetMethod;

  public static io.grpc.MethodDescriptor<com.github.icecodesoftware.generated.Key,
      com.github.icecodesoftware.generated.Value> getGetMethod() {
    io.grpc.MethodDescriptor<com.github.icecodesoftware.generated.Key, com.github.icecodesoftware.generated.Value> getGetMethod;
    if ((getGetMethod = LookupServiceGrpc.getGetMethod) == null) {
      synchronized (LookupServiceGrpc.class) {
        if ((getGetMethod = LookupServiceGrpc.getGetMethod) == null) {
          LookupServiceGrpc.getGetMethod = getGetMethod = 
              io.grpc.MethodDescriptor.<com.github.icecodesoftware.generated.Key, com.github.icecodesoftware.generated.Value>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "lookup.LookupService", "get"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.github.icecodesoftware.generated.Key.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.github.icecodesoftware.generated.Value.getDefaultInstance()))
                  .setSchemaDescriptor(new LookupServiceMethodDescriptorSupplier("get"))
                  .build();
          }
        }
     }
     return getGetMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static LookupServiceStub newStub(io.grpc.Channel channel) {
    return new LookupServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static LookupServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new LookupServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static LookupServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new LookupServiceFutureStub(channel);
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static abstract class LookupServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Lookup a key with the service
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.github.icecodesoftware.generated.Key> get(
        io.grpc.stub.StreamObserver<com.github.icecodesoftware.generated.Value> responseObserver) {
      return asyncUnimplementedStreamingCall(getGetMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.github.icecodesoftware.generated.Key,
                com.github.icecodesoftware.generated.Value>(
                  this, METHODID_GET)))
          .build();
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class LookupServiceStub extends io.grpc.stub.AbstractStub<LookupServiceStub> {
    private LookupServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private LookupServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LookupServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LookupServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Lookup a key with the service
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.github.icecodesoftware.generated.Key> get(
        io.grpc.stub.StreamObserver<com.github.icecodesoftware.generated.Value> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getGetMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class LookupServiceBlockingStub extends io.grpc.stub.AbstractStub<LookupServiceBlockingStub> {
    private LookupServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private LookupServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LookupServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LookupServiceBlockingStub(channel, callOptions);
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class LookupServiceFutureStub extends io.grpc.stub.AbstractStub<LookupServiceFutureStub> {
    private LookupServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private LookupServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LookupServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LookupServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_GET = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final LookupServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(LookupServiceImplBase serviceImpl, int methodId) {
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
        case METHODID_GET:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.get(
              (io.grpc.stub.StreamObserver<com.github.icecodesoftware.generated.Value>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class LookupServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    LookupServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.github.icecodesoftware.generated.LookupProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("LookupService");
    }
  }

  private static final class LookupServiceFileDescriptorSupplier
      extends LookupServiceBaseDescriptorSupplier {
    LookupServiceFileDescriptorSupplier() {}
  }

  private static final class LookupServiceMethodDescriptorSupplier
      extends LookupServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    LookupServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (LookupServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new LookupServiceFileDescriptorSupplier())
              .addMethod(getGetMethod())
              .build();
        }
      }
    }
    return result;
  }
}
