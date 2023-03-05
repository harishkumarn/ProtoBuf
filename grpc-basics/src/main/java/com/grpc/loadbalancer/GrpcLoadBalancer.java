package com.grpc.loadbalancer;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

import com.grpc.App;

public class GrpcLoadBalancer {
    
    public static void main(String[] args) throws InterruptedException, IOException{
        
        System.out.println("Starting GRPC Server....");
        
        Server server = ServerBuilder
                            .forPort(2468)
                            .addService(new App())
                            .build();

        server.start();

        server.awaitTermination();

        System.out.println("GRPC Server listening on port 2468....");
    }
}
