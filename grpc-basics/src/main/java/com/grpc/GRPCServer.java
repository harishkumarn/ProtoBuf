package com.grpc;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GRPCServer {
    public static void main(String[] args) throws IOException, InterruptedException{

        System.out.println("Starting GRPC Server....");

        Server server = ServerBuilder
                            .forPort(1234)
                            .addService(new App())
                            .build();
        
        server.start();

        System.out.println("GRPC Server listening on port 1234....");

        server.awaitTermination();

    }
}
