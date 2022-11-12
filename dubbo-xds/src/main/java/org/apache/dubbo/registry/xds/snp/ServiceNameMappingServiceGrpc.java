package org.apache.dubbo.registry.xds.snp;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Provides an service for reporting the mapping relationship between interface =&gt; cluster
 * the cluster name will be versioned FQDN. such as "demo.default.svc.cluster.local"
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.39.0)",
    comments = "Source: servicenamemapping.proto")
public final class ServiceNameMappingServiceGrpc {

    private ServiceNameMappingServiceGrpc() {}

    public static final String SERVICE_NAME = "servicenamemapping.ServiceNameMappingService";

    // Static method descriptors that strictly reflect the proto.
    private static volatile io.grpc.MethodDescriptor<Servicenamemapping.ServiceMappingRequest,
        Servicenamemapping.ServiceMappingResponse> getRegisterServiceAppMappingMethod;

    @io.grpc.stub.annotations.RpcMethod(
        fullMethodName = SERVICE_NAME + '/' + "registerServiceAppMapping",
        requestType = Servicenamemapping.ServiceMappingRequest.class,
        responseType = Servicenamemapping.ServiceMappingResponse.class,
        methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<Servicenamemapping.ServiceMappingRequest,
        Servicenamemapping.ServiceMappingResponse> getRegisterServiceAppMappingMethod() {
        io.grpc.MethodDescriptor<Servicenamemapping.ServiceMappingRequest, Servicenamemapping.ServiceMappingResponse> getRegisterServiceAppMappingMethod;
        if ((getRegisterServiceAppMappingMethod = ServiceNameMappingServiceGrpc.getRegisterServiceAppMappingMethod) == null) {
            synchronized (ServiceNameMappingServiceGrpc.class) {
                if ((getRegisterServiceAppMappingMethod = ServiceNameMappingServiceGrpc.getRegisterServiceAppMappingMethod) == null) {
                    ServiceNameMappingServiceGrpc.getRegisterServiceAppMappingMethod = getRegisterServiceAppMappingMethod =
                        io.grpc.MethodDescriptor.<Servicenamemapping.ServiceMappingRequest, Servicenamemapping.ServiceMappingResponse>newBuilder()
                            .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                            .setFullMethodName(generateFullMethodName(SERVICE_NAME, "registerServiceAppMapping"))
                            .setSampledToLocalTracing(true)
                            .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                Servicenamemapping.ServiceMappingRequest.getDefaultInstance()))
                            .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                Servicenamemapping.ServiceMappingResponse.getDefaultInstance()))
                            .setSchemaDescriptor(new ServiceNameMappingServiceMethodDescriptorSupplier("registerServiceAppMapping"))
                            .build();
                }
            }
        }
        return getRegisterServiceAppMappingMethod;
    }

    /**
     * Creates a new async stub that supports all call types for the service
     */
    public static ServiceNameMappingServiceStub newStub(io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<ServiceNameMappingServiceStub> factory =
            new io.grpc.stub.AbstractStub.StubFactory<ServiceNameMappingServiceStub>() {
                @java.lang.Override
                public ServiceNameMappingServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                    return new ServiceNameMappingServiceStub(channel, callOptions);
                }
            };
        return ServiceNameMappingServiceStub.newStub(factory, channel);
    }

    /**
     * Creates a new blocking-style stub that supports unary and streaming output calls on the service
     */
    public static ServiceNameMappingServiceBlockingStub newBlockingStub(
        io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<ServiceNameMappingServiceBlockingStub> factory =
            new io.grpc.stub.AbstractStub.StubFactory<ServiceNameMappingServiceBlockingStub>() {
                @java.lang.Override
                public ServiceNameMappingServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                    return new ServiceNameMappingServiceBlockingStub(channel, callOptions);
                }
            };
        return ServiceNameMappingServiceBlockingStub.newStub(factory, channel);
    }

    /**
     * Creates a new ListenableFuture-style stub that supports unary calls on the service
     */
    public static ServiceNameMappingServiceFutureStub newFutureStub(
        io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<ServiceNameMappingServiceFutureStub> factory =
            new io.grpc.stub.AbstractStub.StubFactory<ServiceNameMappingServiceFutureStub>() {
                @java.lang.Override
                public ServiceNameMappingServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                    return new ServiceNameMappingServiceFutureStub(channel, callOptions);
                }
            };
        return ServiceNameMappingServiceFutureStub.newStub(factory, channel);
    }

    /**
     * <pre>
     * Provides an service for reporting the mapping relationship between interface =&gt; cluster
     * the cluster name will be versioned FQDN. such as "demo.default.svc.cluster.local"
     * </pre>
     */
    public static abstract class ServiceNameMappingServiceImplBase implements io.grpc.BindableService {

        /**
         */
        public void registerServiceAppMapping(Servicenamemapping.ServiceMappingRequest request,
                                              io.grpc.stub.StreamObserver<Servicenamemapping.ServiceMappingResponse> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRegisterServiceAppMappingMethod(), responseObserver);
        }

        @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                .addMethod(
                    getRegisterServiceAppMappingMethod(),
                    io.grpc.stub.ServerCalls.asyncUnaryCall(
                        new MethodHandlers<
                            Servicenamemapping.ServiceMappingRequest,
                            Servicenamemapping.ServiceMappingResponse>(
                            this, METHODID_REGISTER_SERVICE_APP_MAPPING)))
                .build();
        }
    }

    /**
     * <pre>
     * Provides an service for reporting the mapping relationship between interface =&gt; cluster
     * the cluster name will be versioned FQDN. such as "demo.default.svc.cluster.local"
     * </pre>
     */
    public static final class ServiceNameMappingServiceStub extends io.grpc.stub.AbstractAsyncStub<ServiceNameMappingServiceStub> {
        private ServiceNameMappingServiceStub(
            io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected ServiceNameMappingServiceStub build(
            io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new ServiceNameMappingServiceStub(channel, callOptions);
        }

        /**
         */
        public void registerServiceAppMapping(Servicenamemapping.ServiceMappingRequest request,
                                              io.grpc.stub.StreamObserver<Servicenamemapping.ServiceMappingResponse> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(
                getChannel().newCall(getRegisterServiceAppMappingMethod(), getCallOptions()), request, responseObserver);
        }
    }

    /**
     * <pre>
     * Provides an service for reporting the mapping relationship between interface =&gt; cluster
     * the cluster name will be versioned FQDN. such as "demo.default.svc.cluster.local"
     * </pre>
     */
    public static final class ServiceNameMappingServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<ServiceNameMappingServiceBlockingStub> {
        private ServiceNameMappingServiceBlockingStub(
            io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected ServiceNameMappingServiceBlockingStub build(
            io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new ServiceNameMappingServiceBlockingStub(channel, callOptions);
        }

        /**
         */
        public Servicenamemapping.ServiceMappingResponse registerServiceAppMapping(Servicenamemapping.ServiceMappingRequest request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(
                getChannel(), getRegisterServiceAppMappingMethod(), getCallOptions(), request);
        }
    }

    /**
     * <pre>
     * Provides an service for reporting the mapping relationship between interface =&gt; cluster
     * the cluster name will be versioned FQDN. such as "demo.default.svc.cluster.local"
     * </pre>
     */
    public static final class ServiceNameMappingServiceFutureStub extends io.grpc.stub.AbstractFutureStub<ServiceNameMappingServiceFutureStub> {
        private ServiceNameMappingServiceFutureStub(
            io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected ServiceNameMappingServiceFutureStub build(
            io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new ServiceNameMappingServiceFutureStub(channel, callOptions);
        }

        /**
         */
        public com.google.common.util.concurrent.ListenableFuture<Servicenamemapping.ServiceMappingResponse> registerServiceAppMapping(
            Servicenamemapping.ServiceMappingRequest request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(
                getChannel().newCall(getRegisterServiceAppMappingMethod(), getCallOptions()), request);
        }
    }

    private static final int METHODID_REGISTER_SERVICE_APP_MAPPING = 0;

    private static final class MethodHandlers<Req, Resp> implements
        io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
        io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
        io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
        io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final ServiceNameMappingServiceImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(ServiceNameMappingServiceImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_REGISTER_SERVICE_APP_MAPPING:
                    serviceImpl.registerServiceAppMapping((Servicenamemapping.ServiceMappingRequest) request,
                        (io.grpc.stub.StreamObserver<Servicenamemapping.ServiceMappingResponse>) responseObserver);
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

    private static abstract class ServiceNameMappingServiceBaseDescriptorSupplier
        implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
        ServiceNameMappingServiceBaseDescriptorSupplier() {}

        @java.lang.Override
        public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
            return Servicenamemapping.getDescriptor();
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
            return getFileDescriptor().findServiceByName("ServiceNameMappingService");
        }
    }

    private static final class ServiceNameMappingServiceFileDescriptorSupplier
        extends ServiceNameMappingServiceBaseDescriptorSupplier {
        ServiceNameMappingServiceFileDescriptorSupplier() {}
    }

    private static final class ServiceNameMappingServiceMethodDescriptorSupplier
        extends ServiceNameMappingServiceBaseDescriptorSupplier
        implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
        private final String methodName;

        ServiceNameMappingServiceMethodDescriptorSupplier(String methodName) {
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
            synchronized (ServiceNameMappingServiceGrpc.class) {
                result = serviceDescriptor;
                if (result == null) {
                    serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
                        .setSchemaDescriptor(new ServiceNameMappingServiceFileDescriptorSupplier())
                        .addMethod(getRegisterServiceAppMappingMethod())
                        .build();
                }
            }
        }
        return result;
    }
}
