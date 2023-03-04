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

    boolean bi = false;

    List<String> people = new ArrayList<String>();

    List<String> langs = new ArrayList<String>();

    public NameRequestObserver(StreamObserver<Greet> resp, boolean bi){
        this.respObs = resp;
        this.bi = bi;
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
        if(bi){
            try{
                Greet greet = Greet.newBuilder()
                    .setGreeting(new StringBuilder().append(App.greetMap.get(name.getLanguage())).append(" ! ").append(name.getName()).toString())
                    .setCurrentTime(LocalDate.now().toString() + " " + LocalTime.now().toString())
                    .build();
                respObs.onNext(greet);
                Thread.sleep(1000);
            }catch(InterruptedException e){}
        }else{
            people.add(name.getName());
            langs.add(name.getLanguage());
        }
    }

    @Override
    public void onError(Throwable err){}

    @Override
    public void onCompleted(){
        Iterator<String> itr = langs.iterator();
        if(!bi){
            Greet greet = Greet.newBuilder()
                    .setGreeting(new StringBuilder().append(App.greetMap.get(itr.next())).append(" ! ").append(people.stream().collect(Collectors.joining(" "))).toString())
                    .setCurrentTime(LocalDate.now().toString() + " " + LocalTime.now().toString())
                    .build();
            respObs.onNext(greet);
        }
        respObs.onCompleted();
    }
}
