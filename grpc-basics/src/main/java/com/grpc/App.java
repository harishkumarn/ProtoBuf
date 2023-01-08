package com.grpc;


import com.harish.grpc.unary.ServerGrpc;
import com.harish.grpc.unary.Name;
import com.harish.grpc.unary.Greet;
import io.grpc.stub.StreamObserver;

import java.time.LocalDate;
import java.time.LocalTime;

public class App extends ServerGrpc.ServerImplBase
{
    @Override
    public void getGreeting(Name request, StreamObserver<Greet> responseObserver){

        Greet greet = Greet.newBuilder()
                    .setGreeting("Hello " + request.getName() + "!!")
                    .setCurrentTime(LocalDate.now().toString() + " " + LocalTime.now().toString())
                    .build();
        
        responseObserver.onNext(greet);
        responseObserver.onCompleted();

    }
}
