package com.grpc;

import com.harish.grpc.unary.ServerGrpc;
import com.harish.grpc.unary.Name;
import com.harish.grpc.unary.Greet;

import io.grpc.stub.StreamObserver;

import java.time.LocalDate;
import java.time.LocalTime;

import com.grpc.observers.NameRequestObserver;
import java.util.HashMap;

public class App extends ServerGrpc.ServerImplBase
{
    public static HashMap<String, String> greetMap = new HashMap<String,String>();

    static{
        greetMap.put("Eng","Hello");
        greetMap.put("Tam","Vanakkam");
        greetMap.put("Tel","Namaskaram");
        greetMap.put("Hin","Namaste");
        greetMap.put("Spa","Adios");
    }

    // Unary
    @Override
    public void getGreeting(Name request, StreamObserver<Greet> responseObserver){

        if(request.getLanguage().equals("")){
            
            responseObserver.onError(new Exception("Bruh!! send some input !!"));
            return;
        }else if(!greetMap.containsKey(request.getLanguage())){
            responseObserver.onError(new Exception("Bruh!! can't translate !!"));
            return;
        }

        Greet greet = Greet.newBuilder()
                    .setGreeting(greetMap.get(request.getLanguage()) + request.getName() + "!!")
                    .setCurrentTime(LocalDate.now().toString() + " " + LocalTime.now().toString())
                    .build();
        
        responseObserver.onNext(greet);
        responseObserver.onCompleted();

    }

    // Bidirectional streaming
    @Override
    public StreamObserver<Name> greetAllAtOnce(StreamObserver<Greet> resStreamObserver){
        return new NameRequestObserver(resStreamObserver, false);
    }

    //Server streaming
    @Override
    public void getGreetings(Name req, StreamObserver<Greet> resStreamObserver){
        try{
            for(String lang : greetMap.values()){
                resStreamObserver.onNext(Greet.newBuilder().setGreeting(lang + " " + req.getName())
                                .setCurrentTime(LocalDate.now().toString() + " " + LocalTime.now().toString())
                                .build());
                Thread.sleep(1000);
            }
        }catch(InterruptedException e){}
        resStreamObserver.onCompleted();
    }

    //Client streaming
    @Override
    public StreamObserver<Name> greetMultiple(StreamObserver<Greet> resStreamObserver){
        return new NameRequestObserver(resStreamObserver, true);
    }
}
