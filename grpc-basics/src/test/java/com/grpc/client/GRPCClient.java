package com.grpc.client;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.harish.grpc.unary.Name;
import com.harish.grpc.unary.Greet;
import com.harish.grpc.unary.ServerGrpc;

import io.grpc.ManagedChannelBuilder;
import io.grpc.Deadline;
import io.grpc.ManagedChannel;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

import com.grpc.interceptor.SampleInterceptor;
import com.grpc.observers.GreetResponseObserver;
import io.grpc.stub.StreamObserver;



@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GRPCClient {
    
    private ServerGrpc.ServerBlockingStub blockingStub = null;

    private ServerGrpc.ServerStub nonBlockingStub = null;


    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    /**
     * Stubs are what gRPC clients use to make remote procedure calls
     * These stubs wrap a channel to make RPCs
     */
    @BeforeAll
    public void setup(){
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8585)
                              .usePlaintext()
                              .build();
        
        this.blockingStub = ServerGrpc.newBlockingStub(channel);

        this.nonBlockingStub = ServerGrpc.newStub(channel);
        System.setOut(new PrintStream(out));
    }

    @AfterAll
    public void restoreInitialStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void unaryTest(){
        Greet greet = this.blockingStub
                                    .withDeadline(Deadline.after(2, TimeUnit.SECONDS))
                                    .getGreeting(Name.newBuilder().setName("Harish Kumar").build());

        System.out.println(greet);
    }

    @Test
    public void clientStreamTest(){
        StreamObserver<Name> req = this.nonBlockingStub.greetAllAtOnce(new GreetResponseObserver());
        for(int i = 0 ; i < 4; ++i){
            req.onNext(Name.newBuilder().setLanguage("English").setName("Harish " + i).build());
        }
        req.onCompleted();
    }

    @Test 
    public void serverStreamTest(){
        this.nonBlockingStub
            .withInterceptors(new SampleInterceptor())
            .getGreetings(Name.newBuilder().setName("Harish Kumar").build(), new GreetResponseObserver());
    }

    @Test
    public void biDirectionalStreamTest(){
        StreamObserver<Name> req = this.nonBlockingStub.greetMultiple(new GreetResponseObserver());
        for(int i = 0 ; i < 4; ++i){
            req.onNext(Name.newBuilder().setLanguage("English").setName("Harish " + i).build());
        }
        req.onCompleted();
    }
}
