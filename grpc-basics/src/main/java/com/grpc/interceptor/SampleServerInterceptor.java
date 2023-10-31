package com.grpc.interceptor;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCall.Listener;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;

public class SampleServerInterceptor  implements ServerInterceptor{

    @Override
    public <ReqT, RespT> Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers,
            ServerCallHandler<ReqT, RespT> next) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'interceptCall'");
    }
    
}
