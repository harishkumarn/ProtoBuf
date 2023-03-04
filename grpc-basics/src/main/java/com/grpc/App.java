package com.grpc;

import com.harish.grpc.unary.ServerGrpc;
import com.harish.grpc.unary.Name;
import com.harish.grpc.unary.Greet;

import io.grpc.stub.StreamObserver;

import java.time.LocalDate;
import java.time.LocalTime;

import com.grpc.observers.NameRequestObserver;


public class App extends ServerGrpc.ServerImplBase
{

    String[] langs = new String[]{"Vanakkam ", "Namaskaram ","Namaste ", "Hello ","Adios "};

    @Override
    public void getGreeting(Name request, StreamObserver<Greet> responseObserver){

        if(request.getLanguage().equals("")){
            
            responseObserver.onError(new Exception("Bruh!! send some input !!"));
            return;
        }



        Greet greet = Greet.newBuilder()
                    .setGreeting("Hello " + request.getName() + "!!")
                    .setCurrentTime(LocalDate.now().toString() + " " + LocalTime.now().toString())
                    .build();
        
        responseObserver.onNext(greet);
        responseObserver.onCompleted();

    }
    @Override
    public StreamObserver<Name> greetAllAtOnce(StreamObserver<Greet> resStreamObserver){
        return new NameRequestObserver(resStreamObserver);
    }

    @Override
    public void getGreetings(Name req, StreamObserver<Greet> resStreamObserver){
        try{
            for(String lang : langs){
                resStreamObserver.onNext(Greet.newBuilder().setGreeting(lang + " " + req.getName())
                                .setCurrentTime(LocalDate.now().toString() + " " + LocalTime.now().toString())
                                .build());
                Thread.sleep(1000);
            }
        }catch(InterruptedException e){}
        resStreamObserver.onCompleted();
    }
}
