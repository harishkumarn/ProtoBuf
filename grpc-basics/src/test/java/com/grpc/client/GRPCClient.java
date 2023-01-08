package com.grpc.client;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.harish.grpc.unary.Name;
import com.harish.grpc.unary.Greet;
import com.harish.grpc.unary.ServerGrpc;

import io.grpc.ManagedChannelBuilder;
import io.grpc.ManagedChannel;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GRPCClient {
    
    private ServerGrpc.ServerBlockingStub blockingStub = null;


    /**
     * Stubs are what gRPC clients use to make remote procedure calls
     * These stubs wrap a channel to make RPCs
     */
    @BeforeAll
    public void setup(){
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 1234)
                              .usePlaintext()
                              .build();
        
        this.blockingStub = ServerGrpc.newBlockingStub(channel);
    }

    @Test
    public void greetTest(){
        Greet greet = this.blockingStub.getGreeting(Name.newBuilder().setName("Harish Kumar").build());

        System.out.println(greet);
    }

}
