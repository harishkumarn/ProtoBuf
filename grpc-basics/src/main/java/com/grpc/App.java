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

    @Override
    public void getGreetings(Name requName, StreamObserver<Greet> responseObserver) {
        String[] arr = new String[]{"Vanakkam ", "Namaskaram ","Namaste ", "Hello ","Adios "};
        for(String greet : arr){
            try{
            Greet newGreet = Greet.newBuilder()
                                .setGreeting(greet +requName.getName() )
                                .setCurrentTime(LocalDate.now().toString() + " " + LocalTime.now().toString())
                                .build();
            
            responseObserver.onNext(newGreet);
            Thread.sleep(1000);
            }catch(Exception e){}
        }
        responseObserver.onCompleted();
    } 
   
}
