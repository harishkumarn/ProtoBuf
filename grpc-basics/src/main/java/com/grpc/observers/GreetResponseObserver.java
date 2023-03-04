package com.grpc.observers;

import io.grpc.stub.StreamObserver;

import com.harish.grpc.unary.Greet;



public class GreetResponseObserver implements StreamObserver<Greet>{
    
    @Override
    public void onNext(Greet name){
        System.out.println(name);
    }

    @Override
    public void onError(Throwable t){}

    @Override
    public void onCompleted(){
        System.out.println("Sayonara!");
    }
}
