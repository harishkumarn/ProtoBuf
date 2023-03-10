package com.grpc.loadbalancer;

import java.io.IOException;

import com.grpc.App;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.lang.Thread;

public class GRPCServer {
    public static void main(String[] args){

        System.out.println("Starting GRPC Server....");

        for(int i = 1; i <= 4;++i){
            final int thread_num = i;
            Thread thread = new Thread(()->{
                System.out.printf("GRPC Server listening on port %d ....\n\n",1234 * thread_num);
                try{
                    Server server = ServerBuilder
                        .forPort(1234 * thread_num)
                        .addService(new App())
                        .build();
                    server.start();
                    server.awaitTermination();
                }catch(IOException | InterruptedException e){}
            });
            thread.start();
        }
    }
}
