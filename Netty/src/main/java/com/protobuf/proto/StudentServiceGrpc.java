package com.protobuf.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.*;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.*;

/**
 * <pre>
 *这是protobuf中定义service的方法，跟thrift差不多，但是关键字不一样
 *service中有四种rpc方法类型
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.20.0)",
    comments = "Source: Studuent.proto")
public final class StudentServiceGrpc {

  private StudentServiceGrpc() {}

  public static final String SERVICE_NAME = "com.protobuf.proto.StudentService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<MyRequest,
      MyResponse> getGetRealNameByUserNameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetRealNameByUserName",
      requestType = MyRequest.class,
      responseType = MyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<MyRequest,
      MyResponse> getGetRealNameByUserNameMethod() {
    io.grpc.MethodDescriptor<MyRequest, MyResponse> getGetRealNameByUserNameMethod;
    if ((getGetRealNameByUserNameMethod = StudentServiceGrpc.getGetRealNameByUserNameMethod) == null) {
      synchronized (StudentServiceGrpc.class) {
        if ((getGetRealNameByUserNameMethod = StudentServiceGrpc.getGetRealNameByUserNameMethod) == null) {
          StudentServiceGrpc.getGetRealNameByUserNameMethod = getGetRealNameByUserNameMethod = 
              io.grpc.MethodDescriptor.<MyRequest, MyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.protobuf.proto.StudentService", "GetRealNameByUserName"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MyResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new StudentServiceMethodDescriptorSupplier("GetRealNameByUserName"))
                  .build();
          }
        }
     }
     return getGetRealNameByUserNameMethod;
  }

  private static volatile io.grpc.MethodDescriptor<MyAge,
      MyStudent> getGetStudentByAgeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetStudentByAge",
      requestType = MyAge.class,
      responseType = MyStudent.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<MyAge,
      MyStudent> getGetStudentByAgeMethod() {
    io.grpc.MethodDescriptor<MyAge, MyStudent> getGetStudentByAgeMethod;
    if ((getGetStudentByAgeMethod = StudentServiceGrpc.getGetStudentByAgeMethod) == null) {
      synchronized (StudentServiceGrpc.class) {
        if ((getGetStudentByAgeMethod = StudentServiceGrpc.getGetStudentByAgeMethod) == null) {
          StudentServiceGrpc.getGetStudentByAgeMethod = getGetStudentByAgeMethod = 
              io.grpc.MethodDescriptor.<MyAge, MyStudent>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "com.protobuf.proto.StudentService", "GetStudentByAge"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MyAge.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MyStudent.getDefaultInstance()))
                  .setSchemaDescriptor(new StudentServiceMethodDescriptorSupplier("GetStudentByAge"))
                  .build();
          }
        }
     }
     return getGetStudentByAgeMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static StudentServiceStub newStub(io.grpc.Channel channel) {
    return new StudentServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static StudentServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new StudentServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static StudentServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new StudentServiceFutureStub(channel);
  }

  /**
   * <pre>
   *这是protobuf中定义service的方法，跟thrift差不多，但是关键字不一样
   *service中有四种rpc方法类型
   * </pre>
   */
  public static abstract class StudentServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *1.发送一个request请求，返回一个response响应
     * </pre>
     */
    public void getRealNameByUserName(MyRequest request,
                                      io.grpc.stub.StreamObserver<MyResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetRealNameByUserNameMethod(), responseObserver);
    }

    /**
     * <pre>
     *2.发送一个request请求，返回一个stream响应
     *虽然我们这里只是想传入一个int32（age），但是protobuf传入的参数必须是message类型，和thrift不同
     *thrift是可以直接传入int，详细可看thrift
     * </pre>
     */
    public void getStudentByAge(MyAge request,
                                io.grpc.stub.StreamObserver<MyStudent> responseObserver) {
      asyncUnimplementedUnaryCall(getGetStudentByAgeMethod(), responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetRealNameByUserNameMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                MyRequest,
                MyResponse>(
                  this, METHODID_GET_REAL_NAME_BY_USER_NAME)))
          .addMethod(
            getGetStudentByAgeMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                MyAge,
                MyStudent>(
                  this, METHODID_GET_STUDENT_BY_AGE)))
          .build();
    }
  }

  /**
   * <pre>
   *这是protobuf中定义service的方法，跟thrift差不多，但是关键字不一样
   *service中有四种rpc方法类型
   * </pre>
   */
  public static final class StudentServiceStub extends io.grpc.stub.AbstractStub<StudentServiceStub> {
    private StudentServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private StudentServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected StudentServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new StudentServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     *1.发送一个request请求，返回一个response响应
     * </pre>
     */
    public void getRealNameByUserName(MyRequest request,
                                      io.grpc.stub.StreamObserver<MyResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetRealNameByUserNameMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *2.发送一个request请求，返回一个stream响应
     *虽然我们这里只是想传入一个int32（age），但是protobuf传入的参数必须是message类型，和thrift不同
     *thrift是可以直接传入int，详细可看thrift
     * </pre>
     */
    public void getStudentByAge(MyAge request,
                                io.grpc.stub.StreamObserver<MyStudent> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getGetStudentByAgeMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   *这是protobuf中定义service的方法，跟thrift差不多，但是关键字不一样
   *service中有四种rpc方法类型
   * </pre>
   */
  public static final class StudentServiceBlockingStub extends io.grpc.stub.AbstractStub<StudentServiceBlockingStub> {
    private StudentServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private StudentServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected StudentServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new StudentServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *1.发送一个request请求，返回一个response响应
     * </pre>
     */
    public MyResponse getRealNameByUserName(MyRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetRealNameByUserNameMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *2.发送一个request请求，返回一个stream响应
     *虽然我们这里只是想传入一个int32（age），但是protobuf传入的参数必须是message类型，和thrift不同
     *thrift是可以直接传入int，详细可看thrift
     * </pre>
     */
    public java.util.Iterator<MyStudent> getStudentByAge(
        MyAge request) {
      return blockingServerStreamingCall(
          getChannel(), getGetStudentByAgeMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   *这是protobuf中定义service的方法，跟thrift差不多，但是关键字不一样
   *service中有四种rpc方法类型
   * </pre>
   */
  public static final class StudentServiceFutureStub extends io.grpc.stub.AbstractStub<StudentServiceFutureStub> {
    private StudentServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private StudentServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected StudentServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new StudentServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *1.发送一个request请求，返回一个response响应
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<MyResponse> getRealNameByUserName(
        MyRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetRealNameByUserNameMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_REAL_NAME_BY_USER_NAME = 0;
  private static final int METHODID_GET_STUDENT_BY_AGE = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final StudentServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(StudentServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_REAL_NAME_BY_USER_NAME:
          serviceImpl.getRealNameByUserName((MyRequest) request,
              (io.grpc.stub.StreamObserver<MyResponse>) responseObserver);
          break;
        case METHODID_GET_STUDENT_BY_AGE:
          serviceImpl.getStudentByAge((MyAge) request,
              (io.grpc.stub.StreamObserver<MyStudent>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class StudentServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    StudentServiceBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return StudentInfo.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("StudentService");
    }
  }

  private static final class StudentServiceFileDescriptorSupplier
      extends StudentServiceBaseDescriptorSupplier {
    StudentServiceFileDescriptorSupplier() {}
  }

  private static final class StudentServiceMethodDescriptorSupplier
      extends StudentServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    StudentServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (StudentServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new StudentServiceFileDescriptorSupplier())
              .addMethod(getGetRealNameByUserNameMethod())
              .addMethod(getGetStudentByAgeMethod())
              .build();
        }
      }
    }
    return result;
  }
}
