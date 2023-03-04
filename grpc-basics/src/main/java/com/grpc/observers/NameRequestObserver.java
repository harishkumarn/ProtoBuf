package com.grpc.observers;

import io.grpc.stub.StreamObserver;
import com.harish.grpc.unary.Name;
import com.harish.grpc.unary.Greet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

import java.time.LocalDate;
import java.time.LocalTime;

import java.util.Iterator;
import com.grpc.App;

public class NameRequestObserver implements StreamObserver<Name>{
    

    StreamObserver<Greet> respObs;

    List<String> people = new ArrayList<String>();

    List<String> langs = new ArrayList<String>();

    public NameRequestObserver(StreamObserver<Greet> resp){
        this.respObs = resp;
    }

    @Override
    public void onNext(Name name){
        if(name.getLanguage().equals("")){
            respObs.onError(new Exception("Bruh!! send some input !!"));
            return;
        }else if(!App.greetMap.containsKey(name.getLanguage())){
            respObs.onError(new Exception("Bruh!! can't translate !!"));
            return;
        }
        people.add(name.getName());
        langs.add(name.getLanguage());
    }

    @Override
    public void onError(Throwable err){}

    @Override
    public void onCompleted(){
        Iterator<String> itr = langs.iterator();
        Greet greet = Greet.newBuilder()
                    .setGreeting(new StringBuilder().append(App.greetMap.get(itr.next())).append(people.stream().collect(Collectors.joining(" "))).toString())
                    .setCurrentTime(LocalDate.now().toString() + " " + LocalTime.now().toString())
                    .build();
        respObs.onNext(greet);
        respObs.onCompleted();
    }
}
