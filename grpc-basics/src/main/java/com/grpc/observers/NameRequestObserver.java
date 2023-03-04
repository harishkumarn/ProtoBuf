package com.grpc.observers;

import io.grpc.stub.StreamObserver;
import com.harish.grpc.unary.Name;
import com.harish.grpc.unary.Greet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

import java.time.LocalDate;
import java.time.LocalTime;

public class NameRequestObserver implements StreamObserver<Name>{
    

    StreamObserver<Greet> respObs;

    List<String> people = new ArrayList<String>();

    public NameRequestObserver(StreamObserver<Greet> resp){
        this.respObs = resp;
    }

    @Override
    public void onNext(Name name){
        people.add(name.getName());
    }

    @Override
    public void onError(Throwable err){}

    @Override
    public void onCompleted(){
        Greet greet = Greet.newBuilder()
                    .setGreeting(new StringBuilder().append("Hi eveyone !, ").append(people.stream().collect(Collectors.joining(" "))).toString())
                    .setCurrentTime(LocalDate.now().toString() + " " + LocalTime.now().toString())
                    .build();
        respObs.onNext(greet);
        respObs.onCompleted();
    }
}
